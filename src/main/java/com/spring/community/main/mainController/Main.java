package com.spring.community.main.mainController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.community.gallery.vo.GalleryVO;
import com.spring.community.main.mainService.mainService;
import com.spring.community.trade.vo.TradeVO;

@Controller("MainController")
public class Main {

    @Autowired
    private mainService mainService;

    @RequestMapping(value = "/main/firstmain.do", method = RequestMethod.GET)
    public ModelAndView main() {
        System.out.println("firstmain 호출");

        List<GalleryVO> ReGallerylist = mainService.getRecentGarallyList();
        List<TradeVO> ReTradelist = mainService.getRecentTradeList();

        ModelAndView mav = new ModelAndView();
        mav.addObject("ReT", ReTradelist);
        mav.addObject("ReG", ReGallerylist);
        mav.addObject("center", "/WEB-INF/views/common/First.jsp");
        mav.setViewName("main");

        return mav;
    }

    @RequestMapping(value = "/main/index.do", method = RequestMethod.GET)
    public ModelAndView index() {
        System.out.println("index 호출");

        ModelAndView mav = new ModelAndView();
        mav.addObject("center", "/WEB-INF/views/common/First.jsp");
        mav.setViewName("main");

        return mav;
    }
}
