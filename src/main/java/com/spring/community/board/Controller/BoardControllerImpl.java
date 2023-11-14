package com.spring.community.board.Controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.ant.jmx.JMXAccessorQueryTask;
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

import com.spring.community.board.BoardVO.BoardCommentVO;
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
	
	@Autowired
	private BoardCommentVO cVo;

	
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
//		System.out.println("글 번호 : " + no);
		String name = request.getParameter("name");
//		System.out.println("닉네임 : " + name);
		cVo.setBoardNo(Integer.parseInt(no));
		
		vo = boardservice.boardInfo(no, name);
		
		Map<String, String> nextTitle = new HashMap<String, String>();
		
		nextTitle = boardservice.nextTitle(no);
		
		List<BoardCommentVO> commentList = boardservice.commentList(cVo);
		
//		System.out.println("댓글 리턴 리스트 : " + commentList.size());
//		
//		System.out.println("리턴받은 VO : " + vo);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("commentList", commentList);
		mav.addObject("nextTitle", nextTitle);
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

	@Override
	@RequestMapping(value = "/board/addComment.do", method = RequestMethod.GET)
	public ModelAndView addCommnet(HttpServletRequest request, HttpServletResponse response) 
											throws Exception {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");
		System.out.println("보고있는 글 번호 : " + no);
		System.out.println("로그인된 nickname : " + name);
		System.out.println("작성된 comment : " + comment);
		
		cVo.setBoardNo(Integer.parseInt(no));
		cVo.setNickName(name);
		cVo.setContent(comment);
		
		Map<String, String> nextTitle = new HashMap<String, String>();
		nextTitle = boardservice.nextTitle(no);
		
		boardservice.addComment(cVo);
		
		List<BoardCommentVO> commentList = boardservice.commentList(cVo);
	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("commentList", commentList);
		mav.addObject("nextTitle", nextTitle);
		mav.addObject("boardInfo", vo);
		mav.addObject("center", "/WEB-INF/views/board/boardInfo.jsp");
		mav.setViewName("main");
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/board/delComment.do", method= RequestMethod.GET)
	public ModelAndView delComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String no = request.getParameter("no");
		
		System.out.println("삭제할 댓글 번호 : " + no);
		
		boardservice.delComment(no);
		List<BoardCommentVO> commentList = boardservice.commentList(cVo);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("commentList", commentList);
		mav.addObject("boardInfo", vo);
		mav.addObject("center", "/WEB-INF/views/board/boardInfo.jsp");
		mav.setViewName("main");
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/board/editComment.do", method = RequestMethod.GET)
	public ModelAndView editComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String comment = request.getParameter("comment");
		String cno = request.getParameter("cno");
		String bno = request.getParameter("bno");
		cVo.setBoardNo(Integer.parseInt(bno));
		cVo.setContent(comment);
		cVo.setNo(Integer.parseInt(cno));
		
		System.out.println("수정할 댓글 번호 : " + cno);
		System.out.println("수정할 댓글 내용 : " + comment);
		System.out.println("수정할 글 번호 : " + bno);
		
		boardservice.editComment(cVo);
		
		List<BoardCommentVO> commentList = boardservice.commentList(cVo);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("commentList", commentList);
		mav.addObject("boardInfo", vo);
		mav.addObject("center", "/WEB-INF/views/board/boardInfo.jsp");
		mav.setViewName("main");
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/board/editForm.do", method = RequestMethod.GET)
	public ModelAndView editForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String no = request.getParameter("no");
		System.out.println("editForm : " + no);
		
		vo = boardservice.editForm(no);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("editboard", vo);
		mav.addObject("center", "/WEB-INF/views/board/editForm.jsp");
		mav.setViewName("main");
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/board/delboard.do", method = RequestMethod.GET)
	public ModelAndView delboard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String no = request.getParameter("no");
		
		System.out.println("삭제할 글 번호 : " + no);
		
		boardservice.delBoard(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "/WEB-INF/views/board/boardlist.jsp");
		mav.setViewName("main");
		
		return mav;
	}

	@Override
	@RequestMapping(value = "/board/editBoard.do", method = RequestMethod.POST)
	public ModelAndView editboard(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		vo.setNo(Integer.parseInt(request.getParameter("no")));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		
		System.out.println("내부" + vo.getContent());
		System.out.println("내부" + vo.getTitle());
		System.out.println("내부" + vo.getNo());
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
			boardservice.editboard(vo);
			mav.addObject("center", "/WEB-INF/views/board/board.jsp");
			mav.setViewName("redirect:/board/listboard.do");
			System.out.println("입력한 값 있음");
		}
		
		return mav;
	}
	
	
	
	
	
    
    
    
   

}