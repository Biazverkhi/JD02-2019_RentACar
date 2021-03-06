package by.fastrentcar.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LogoutController {
    public LogoutController() {
    }

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping({"/logoutUser"})
    public String logout(HttpServletRequest req) {
        log.info("user logout");
        SecurityContextHolder.clearContext();
        try {
            req.logout();
        } catch (ServletException e) {
            throw new RuntimeException();
        }
        return "redirect:index";

    }
}
