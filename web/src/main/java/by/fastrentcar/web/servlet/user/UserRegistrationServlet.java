package by.fastrentcar.web.servlet.user;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import by.fastrentcar.service.UserService;
import by.fastrentcar.service.impl.DefaultUserService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class UserRegistrationServlet extends HttpServlet {
    private UserService defaultUserService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        WebUtils.forward("registration", req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
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
        WebUtils.redirect("index", req, resp);
    }
}
