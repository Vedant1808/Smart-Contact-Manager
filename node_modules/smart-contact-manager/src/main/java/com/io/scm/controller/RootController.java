package com.io.scm.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.io.scm.entity.User;
import com.io.scm.helper.Helper;
import com.io.scm.service.UserService;

@ControllerAdvice
public class RootController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    // for every handler method this method execute and add logged in user attribute
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }
        // System.out.println("Adding logged in user information to the model");
        ;
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in: {}", username);
        // database se data ko fetch : get user from db :
        User user = userService.getUserByEmail(username);
        // System.out.println(user);
        // System.out.println(user.getName());
        // System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);

    }
}
