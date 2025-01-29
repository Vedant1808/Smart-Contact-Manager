package com.io.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.io.scm.entity.User;
import com.io.scm.helper.Helper;
import com.io.scm.helper.Message;
import com.io.scm.helper.MessageType;
import com.io.scm.service.ContactService;
import com.io.scm.service.EmailService;
import com.io.scm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String ownerEmail;

    // user dashboard page
    @RequestMapping(value = "/dashboard")
    public String userDashboard(Model model, Authentication authentication) {
        // System.out.println("User dashboard");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        long numberOfContacts = contactService.countByUserId(user.getUserId());
        long numberOfFavouriteContacts = contactService.countByContactFavourite(user.getUserId());
        long numberOfContactsWithLinkdinLink = contactService.countByLinkedInLinkNotNull(user.getUserId());
        long numberOfContactsWithEmail = contactService.countByEmailNotNull(user.getUserId());
        String mostContactedPerson = contactService.findMostContactedPerson(user.getUserId());
        String recentlyAddedContact = contactService.findRecentlyAddedContact(user.getUserId());
        model.addAttribute("totalNumberOfContacts", numberOfContacts);
        model.addAttribute("totalNumberOfFavouriteContacts", numberOfFavouriteContacts);
        model.addAttribute("numberOfContactsWithLinkdinLink", numberOfContactsWithLinkdinLink);
        model.addAttribute("numberOfContactsWithEmail", numberOfContactsWithEmail);

        model.addAttribute("mostContactedPerson", mostContactedPerson == null ? "0" : mostContactedPerson);
        model.addAttribute("recentlyAddedContact", recentlyAddedContact == null ? "0" : recentlyAddedContact);
        return "user/dashboard";
    }

    // user profile page
    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {
        // System.out.println("User profile");
        return "user/profile";
    }

    @RequestMapping("/feedback/{emailId}")
    public String feedbackForm(@PathVariable("emailId") String emailId, Model model) {
        model.addAttribute("emailId", emailId);
        return "user/feedback";
    }

    @PostMapping("/submit-feedback/{emailId}")
    public String submitFeedbackForm(@PathVariable("emailId") String emailId, @RequestParam("data") String data,
            HttpSession session) {

        emailService.sendEmail(emailId, ownerEmail, "Feedback of SCM APP", data);
        session.setAttribute("message",
                Message.builder().content("Feedback is Successfully send").type(MessageType.green).build());
        return "user/feedback";
    }

}
