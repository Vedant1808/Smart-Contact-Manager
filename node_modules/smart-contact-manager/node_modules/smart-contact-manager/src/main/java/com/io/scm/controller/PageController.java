package com.io.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.io.scm.entity.User;
import com.io.scm.forms.UserForm;
import com.io.scm.helper.Message;
import com.io.scm.helper.MessageType;
import com.io.scm.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    // for default endpoint
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    // home handler method
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        // sending data to view
        model.addAttribute("name", "Vedant");
        model.addAttribute("rollNumber", "82918121");
        return "home"; // return name of view
    }

    @RequestMapping("/about")
    public String aboutPage() {
        // System.out.println("About page loading");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        // System.out.println("Services page loading");
        return "services";
    }

    @RequestMapping("/contact")
    public String contactPage() {
        // System.out.println("Contact page loading");
        return "contact";
    }

    // this is login controller
    @RequestMapping("/login")
    public String loginPage() {
        // System.out.println("Login page loading");
        return "login";
    }

    // this is registration controller
    @RequestMapping("/register")
    public String registerPage(Model model) {
        // System.out.println("Sign up page loading");

        // transfer empty userForm from controller to view so it can be used in
        // register.html form
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // this is processing registration controller
    // when user submit the signup/register form
    @PostMapping("/do-register")
    public String processingRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession httpSession) {
        // System.out.println("Processing registration");

        // validate form data using hibernate validation
        if (rBindingResult.hasErrors()) {
            return "register";
        }

        // fetch form data
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setEnabled(false);
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic(
                "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?semt=ais_incoming");

        // save to database
        User saveUser = userServiceImpl.saveUser(user);

        // create new message of successful registration
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        // put message in session object and show it on top of page
        httpSession.setAttribute("message", message);

        // redirect
        return "redirect:/register";
    }

    @PostMapping("/contactUs")
    public String contactUs() {
        // System.out.println("Page got called");
        return "/contact";
    }

}