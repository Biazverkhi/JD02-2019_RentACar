package by.fastrentcar.web.servlet;

import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.service.SecurityService;
import by.fastrentcar.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private SecurityService securityService;
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        WebUtils.forward("login", rq, rs);

    }
    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        HttpSession session = rq.getSession();
        AuthUserDTO user = securityService.login(login, password);
        if (user == null) {
            session.setAttribute("error", "login or password invalid");
            WebUtils.redirect("index", rq, rs);
            return;
        }
        log.info("user {} logged", user.getLogin());
        session.setAttribute("authuser", user);
        if (user.getRole().equals(Role.USER)) {
            WebUtils.redirect("index", rq, rs);
        } else {
            WebUtils.forward("adminview/adminpage", rq, rs);
        }
    }

}





