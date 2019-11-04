package by.fastrentcar.web.servlet.user;

import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.service.UserService;
import by.fastrentcar.service.impl.DefaultUserService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/adminview/useradmin")
public class UserAdminServlet extends HttpServlet {
   private UserService defaultUserService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        List<AuthUserUserDTO> list = defaultUserService.getListAuthUserUserDTO();
        req.setAttribute("users", list);
        WebUtils.forward("adminview/useradmin", req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
    }
}
