package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginPageController {

    // injectare serviciu cu ajutorul caruia verifici validitatea datelor introduse
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(){
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPagePost(User user){
        ModelAndView mav = new ModelAndView();
        //dupa conexiunea cu baza de date trebuie verificat ca userul a introdus date valide
        mav.setViewName("redirect:/userProfile");
        return mav;
    }
}
