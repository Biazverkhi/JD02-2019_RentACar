package by.fastrentcar.web.servlet;

import by.fastrentcar.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session=req.getSession();
        session.removeAttribute("authuser");
        session.invalidate();
        WebUtils.redirect("index",req,resp);

    }
}
