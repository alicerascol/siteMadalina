package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class EditProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/editProfile",  method = RequestMethod.GET)
    public ModelAndView editProfile(HttpSession session){
        ModelAndView mav = new ModelAndView();
        int userId = (Integer) session.getAttribute("userId");
        User user = userService.findUserById(userId);
        mav.addObject("user", user);
        return mav;
    }


    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public ModelAndView editProfilePost(@Valid User user,
                                        BindingResult bindingResult,
                                        HttpSession session){
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.setViewName("redirect:/invalidUser");
            return mav;
        }
        userService.updateUser((Integer) session.getAttribute("userId"), user);
        mav.setViewName("redirect:/userProfile");
        return mav;
    }
}