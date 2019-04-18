package com.sitelogin.Site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InvalidUserController {

    @RequestMapping(value = "/invalidUser", method = RequestMethod.GET)
    public String invalidUser(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("afghjk", true);
        return "invalidUser";
    }
}
