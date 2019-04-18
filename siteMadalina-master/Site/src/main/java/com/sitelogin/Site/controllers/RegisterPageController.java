package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterPageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage(){
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPagePost(@Valid  @ModelAttribute("user") User user,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes,
                                         HttpSession session){
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.setViewName("redirect:/invalidUser");
            return mav;
        }

        // la adaugarea unui user veni erori din baza
        // returnam view-ul asa cum este, nelasandu l pe user sa treaca la pagina urmatoare
        // in mod normal, userului i se explica ce greseste, pentru a putea scrie corect
        try {
            userService.addUser(user);
        } catch (Exception e) {
            return mav;
        }

        int userId = user.getID();
        session.setAttribute("userId", userId);

        mav.setViewName("redirect:/userProfile");
        redirectAttributes.addFlashAttribute("user", user);
        return mav;
    }
}
