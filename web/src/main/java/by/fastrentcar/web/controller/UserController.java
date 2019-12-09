package by.fastrentcar.web.controller;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import by.fastrentcar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/adminview")
public class UserController {
    private UserService defaultUserService;

    public UserController(UserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    @GetMapping("/useradmin")
    public String viewUser(HttpServletRequest req) {
        List<AuthUserUserDTO> list = defaultUserService.getListAuthUserUserDTO();
        req.setAttribute("users", list);
        return "/adminview/useradmin";
    }

    @PostMapping("/add")
    public String addUser(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        Role role = Role.valueOf(req.getParameter("role"));
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String passport_number = req.getParameter("passport_number");
        String passport_data = req.getParameter("passport_data");
        String passport_authority = req.getParameter("passport_authority");
        User user = new User(null, firstname, lastname, phone, email, passport_number, passport_data, passport_authority);
        AuthUser authUser = new AuthUser(null, login, password, role, null);
        defaultUserService.addCustomer(authUser, user);
        return "redirect:/adminview/useradmin";
    }

    @PostMapping("/update")
    public String updateUser(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Long userid = Long.valueOf(req.getParameter("user_id"));
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        Role role = Role.valueOf(req.getParameter("role"));
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String passport_number = req.getParameter("passport_number");
        String passport_data = req.getParameter("passport_data");
        String passport_authority = req.getParameter("passport_authority");

        User user = new User(userid, firstname, lastname, phone, email, passport_number, passport_data, passport_authority);
        AuthUser authUser = new AuthUser(id, login, password, role, userid);
        defaultUserService.updateCustomer(authUser, user);
        return "redirect:/adminview/useradmin";
    }

    @PostMapping("/delete")
    public String deleteUser(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultUserService.deleteCutomer(id);
        return "redirect:/adminview/useradmin";
    }
}
