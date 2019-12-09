package by.fastrentcar.web.controller;

import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final SecurityService securityService;

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public String doGet() {
        return "/login";
    }

    @PostMapping
    public String doPost(HttpServletRequest rq) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        HttpSession session = rq.getSession();
        AuthUserDTO user = securityService.login(login, password);
        if (user == null) {
            session.setAttribute("error", "login or password invalid");
            return "redirect:/index";
        }
        log.info("user {} logged", user.getLogin());
        session.setAttribute("authuser", user);
        if (user.getRole().equals(Role.USER)) {
            return "redirect:/index";
        } else {
            return "/adminview/adminpage";
        }
    }

}





