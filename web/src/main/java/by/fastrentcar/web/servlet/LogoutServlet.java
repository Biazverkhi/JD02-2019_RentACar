package by.fastrentcar.web.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/logout"})
public class LogoutServlet {
    @GetMapping()
    public String doGet(HttpServletRequest req) {
        HttpSession session=req.getSession();
        session.removeAttribute("authuser");
        session.invalidate();
        return "redirect:index";

    }
}
