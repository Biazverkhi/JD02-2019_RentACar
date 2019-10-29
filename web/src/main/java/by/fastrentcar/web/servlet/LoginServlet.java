package by.fastrentcar.web.servlet;

import by.fastrentcar.model.AuthUser;
import by.fastrentcar.model.Role;
import by.fastrentcar.service.SecurityService;
import by.fastrentcar.service.impl.DefaultSecurityService;
import by.fastrentcar.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SecurityService securityService = DefaultSecurityService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
//        Object authUser = rq.getSession().getAttribute("authUser");
//        if (authUser == null) {
//            WebUtils.forward("login", rq, rs);
//            return;
//        }
//        WebUtils.redirect("userpage", rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        AuthUser user = securityService.login(login, password);
        if (user == null) {
            rq.getSession().setAttribute("error", "login or password invalid");
            WebUtils.redirect("index", rq, rs);
            return;
        }
        log.info("user {} logged", user.getLogin());
        rq.getSession().setAttribute("authuser", user);
        if (user.getRole().equals(Role.USER)){
            WebUtils.redirect("index", rq, rs);
    } else {
        WebUtils.redirect("adminpage.jsp", rq, rs);}

}

    }





