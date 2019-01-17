package by.scoring.controller;

import by.scoring.model.entity.User;
import by.scoring.model.service.IUserService;
import by.scoring.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for {@link by.scoring.model.entity.User}'s pages.
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator userValidator;



    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration/registration";
        }
        userService.save(userForm);
        return "registration/success_registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login/login";
    }


    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
    public String confirm(@PathVariable("id") long id)
    {
        try {
            userService.confirm(id);
        }catch (Exception e){
            return "confirm/error_confirm";
        }
        return "confirm/confirm";
    }

    public IUserService getIUserService() {
        return userService;
    }

    public void setIUserService(IUserService iUserService) {
        this.userService = iUserService;
    }
}

