package com.spring.community.board.Controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.spring.community.board.BoardVO.BoardVO;
import com.spring.community.board.Service.BoardService;
import com.spring.community.board.Utils.PagerVO;
import com.spring.community.board.Utils.PagingVO;

import lombok.Value;

@Controller("boardController")
public class BoardControllerImpl extends HttpServlet implements BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private BoardVO vo;
	
	@Autowired
	private PagingVO pvo;
	
	@Autowired
	private PagerVO pager;

	
    @Override
    @RequestMapping(value="/board/listboard.do", method = RequestMethod.GET)
    public ModelAndView selcetBoard(PagingVO vo, ModelAndView mav,
                                    @RequestParam(value = "nowPage", required = false) String nowPage,
                                    @RequestParam(value = "cntPerPage", required = false) String cntPerPage,
    								@RequestParam(value = "checksel", required = false) String checksel)
                                    throws Exception {
        
    	int total = boardservice.countBoard();
        System.out.println("total 값 : " + total);
        
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "10";
        }
        if(checksel == null) {
        	checksel = "no";
        }
        
        pvo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), checksel);
        System.out.println("vo 값 : " + pvo);
        
        List boardlist = boardservice.selectBoard(pvo);
        System.out.println("boardlist 값 : " + boardlist);

        mav.addObject("paging", pvo);  
        mav.addObject("boardlist", boardlist);
		mav.addObject("center", "/WEB-INF/views/board/board.jsp");
		mav.setViewName("main");
        
        System.out.println("mav / viewname : " + mav.getViewName());
  
        return mav;
        
    }

	@Override
	@RequestMapping(value = "/board/boardInfo.do", method = RequestMethod.GET)
	public ModelAndView selectInfo(HttpServletRequest request, HttpServletResponse response) 
											throws Exception {
		
		String no = request.getParameter("no");
		System.out.println("글 번호 : " + no);
		String name = request.getParameter("name");
		System.out.println("닉네임 : " + name);
		
		vo = boardservice.boardInfo(no, name);
		
		Map<String, String> nextTitle = new HashMap<String, String>();
		
		nextTitle = boardservice.nextTitle(no);
		
		System.out.println("리턴받은 VO : " + vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("nextTitle", nextTitle);
		mav.addObject("boardInfo", vo);
		mav.addObject("center", "/WEB-INF/views/board/boardInfo.jsp");
		mav.setViewName("main");
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/board/addCommnet.do", method = RequestMethod.GET)
	public ModelAndView addCommnet(HttpServletRequest request, HttpServletResponse response) 
											throws Exception {
		String no = request.getParameter("no");
		System.out.println("글 번호 : " + no);
		
		vo = boardservice.boardInfo(no, no);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("boardInfo", vo);
		mav.addObject("center", "/WEB-INF/views/board/boardInfo.jsp");
		mav.setViewName("main");
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/board/insertForm.do", method = RequestMethod.GET)
	public ModelAndView insertForm(HttpServletRequest request, HttpServletResponse response) 
											throws Exception {
		
		ModelAndView mav = new  ModelAndView();
		
		mav.addObject("center", "/WEB-INF/views/board/insertForm.jsp");
		mav.setViewName("main");
		
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/board/insertBoard.do", method = RequestMethod.POST)
	public ModelAndView insertboard(@ModelAttribute BoardVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		System.out.println("내부" + vo.getContent());
		System.out.println("내부" + vo.getTitle());
		System.out.println("내부" + vo.getNickName());
		String Path = request.getContextPath();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		
		ModelAndView mav = new ModelAndView();
		
		if(vo.getTitle().equals("") || vo.getContent().equals("")) {
			mav.addObject("center", "/WEB-INF/views/board/board.jsp");
			mav.setViewName("redirect:/board/insertForm.do");
			
			writer.println("<script>alert('제목 또는 내용을 입력하세요'); location.href='"+Path+"/board/insertForm.do';</script>"); 
			writer.close();
			
			System.out.println("입력한 값 없음");
		}else {
			boardservice.insertboard(vo);
			mav.addObject("center", "/WEB-INF/views/board/board.jsp");
			mav.setViewName("redirect:/board/listboard.do");
			System.out.println("입력한 값 있음");
		}
		
		return mav;
	}


	
	
    
    
    
   

}