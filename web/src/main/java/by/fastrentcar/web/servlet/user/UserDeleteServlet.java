package by.fastrentcar.web.servlet.user;

import by.fastrentcar.service.UserService;
import by.fastrentcar.service.impl.DefaultUserService;
import by.fastrentcar.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class UserDeleteServlet extends HttpServlet {
    private UserService defaultUserService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultUserService.deleteCutomer(id);
        WebUtils.redirect("useradmin", req, resp);
    }
}
