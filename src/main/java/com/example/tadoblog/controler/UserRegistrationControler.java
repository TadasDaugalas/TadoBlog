package com.example.tadoblog.controler;

import com.example.tadoblog.data.UserRegistration;
import com.example.tadoblog.service.UserService;
import com.example.tadoblog.validator.UserRegistrationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/public/users")
public class UserRegistrationControler {
    private static final Logger LOG= LoggerFactory.getLogger(UserRegistrationControler.class);
    private final UserRegistrationValidator userRegistrationValidator;
    private final UserService userService;

    public UserRegistrationControler(UserRegistrationValidator userRegistrationValidator, UserService userService) {
        this.userRegistrationValidator = userRegistrationValidator;
        this.userService=userService;
    }

    @GetMapping("/create")
    public String openRegistrationPage(Model model){
        model.addAttribute("userRegistration",new UserRegistration());
        LOG.info("User Registration opened");
        return "userRegistration";
    }
    @PostMapping("/create")
    public String createUser(@Valid UserRegistration userRegistration, BindingResult bindingResult){
        userRegistrationValidator.validate(userRegistration,bindingResult);
        if(bindingResult.hasErrors()){
            LOG.warn("User cannot register {}",userRegistration);
            return "userRegistration";
        }
        userService.createNewUser(userRegistration);

        return "redirect:/public/users/create";
    }
}
