package by.fastrentcar.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/logout"})
public class LogoutController {
    public LogoutController() {
    }

    @GetMapping()
    public String logout(HttpServletRequest req) {

        SecurityContextHolder.clearContext();
        try {
            req.logout();
        } catch (ServletException e) {
            throw new RuntimeException();
        }
        HttpSession session=req.getSession();
        session.removeAttribute("authuser");
        session.invalidate();
        return "redirect:index";

    }
}
