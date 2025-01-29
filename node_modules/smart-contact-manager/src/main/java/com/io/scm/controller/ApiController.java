package com.io.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.scm.entity.Contact;
import com.io.scm.entity.User;
import com.io.scm.helper.Helper;
import com.io.scm.helper.Message;
import com.io.scm.helper.MessageType;
import com.io.scm.service.ContactService;
import com.io.scm.service.EmailService;
import com.io.scm.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    EmailService emailService;

    // get contacts
    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId) {
        // http://localhost:8081/api/contacts/b615e63a-7db2-4400-92d1-ef08554393a5
        return contactService.getById(contactId);
    }

    @GetMapping("/contacts/message/{sendTo}/{subject}/{description}")
    public String sendMessage(@PathVariable String sendTo, @PathVariable String subject,
            @PathVariable String description, Authentication authentication, HttpSession session) {
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);
        emailService.sendEmail(user.getEmail(), sendTo, subject, description);
        session.setAttribute("message", Message.builder().content("Message is Successfully send to " + sendTo)
                .type(MessageType.green).build());
        return "user/direct_message";
    }
}
