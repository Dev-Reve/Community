package com.spring.community.board.Controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping(value = "/board/listboard.do", method = RequestMethod.GET)
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
		mav.addObject("center", "/WEB-INF/views/board/AAboard.jsp");
		mav.setViewName("main");
        
        System.out.println("mav / viewname : " + mav.getViewName());
  
        return mav;
        
    }

	@Override
	@RequestMapping(value = "/board/getList.do", method = RequestMethod.GET)
	public ModelAndView getList(PagerVO pager, ModelAndView mav, BoardVO bvo) throws Exception {
		
		List<BoardVO> getBoardList = boardservice.getList(pager);
		
		mav.addObject("pager", pager);
		mav.addObject("getBoardList", getBoardList);
		mav.addObject("center", "/WEB-INF/views/board/board.jsp");
		mav.setViewName("main");
		
		return mav;
	}
    
    
    
    

}