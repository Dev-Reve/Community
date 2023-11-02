package com.spring.community.board.Controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.board.BoardVO.BoardVO;
import com.spring.community.board.Service.BoardService;
import com.spring.community.board.utils.PagingVO;

@Controller("boardController")
public class BoardControllerImpl extends HttpServlet implements BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private BoardVO vo;
	
	@Autowired
	private PagingVO pvo;
	
	@Override
	@RequestMapping(value="/board/listboards.do", method = RequestMethod.GET)
	public ModelAndView selcetAllBoard(HttpServletRequest request, 
											HttpServletResponse response)
												throws Exception {

		List boardlist = boardservice.selcetAllBoard();
		
		ModelAndView mav = new ModelAndView();
		
		int total = boardservice.countBoard();

		mav.addObject("total", total);
		mav.addObject("boardlist", boardlist);
		mav.setViewName("board/board");
		mav.addObject("center", "/WEB-INF/views/board/board.jsp");
		mav.setViewName("main");

		System.out.println("mav / viewname : " + mav.getViewName());
		
		return mav;
	}
	
    @Override
    @RequestMapping(value = "/board/listboard.do", method = RequestMethod.GET)
    public ModelAndView selcetBoard(PagingVO pvo, ModelAndView mav,
                                    @RequestParam(value = "nowPage", required = false) String nowPage,
                                    @RequestParam(value = "cntPerPage", required = false) String cntPerPage)
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
        
        pvo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
        System.out.println("vo 값 : " + pvo);
        
        List boardlist = boardservice.selectBoard(pvo);
        System.out.println("boardlist 값 : " + boardlist);
        
        System.out.println("현재 페이지 번호 : " + pvo.getNowPage());
        
        mav.addObject("paging", pvo);    
        mav.addObject("boardlist", boardlist);
		mav.addObject("center", "/WEB-INF/views/board/board.jsp");
		mav.setViewName("main");
        
        System.out.println("mav / viewname : " + mav.getViewName());
  
        return mav;
        
    }


}