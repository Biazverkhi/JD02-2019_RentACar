package by.fastrentcar.web.controller;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import by.fastrentcar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserService defaultUserService;

    public UserRegistrationController(UserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    @GetMapping
    public String registrationPage() {
        return "registration";
    }

    @PostMapping
    public String registrationUser(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String passport_number = req.getParameter("passport_number");
        String passport_data = req.getParameter("passport_data");
        String passport_authority = req.getParameter("passport_authority");
        User user = new User(null, firstname, lastname, phone, email, passport_number, passport_data, passport_authority);
        AuthUser authUser = new AuthUser(null, login, password, Role.USER, null);
        defaultUserService.addCustomer(authUser, user);
        return "redirect:index";
    }
}
