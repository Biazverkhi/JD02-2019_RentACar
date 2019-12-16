package by.fastrentcar.web.controller;

import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final SecurityService securityService;

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public String doGet() {
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest rq) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        AuthUserDTO user = securityService.login(login, password);
        if (user == null) {
            rq.setAttribute("error", "login or password invalid");
            return "redirect:index";
        }
        log.info("user {} logged", user.getLogin());

        rq.getSession().setAttribute("authuser", user);

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return user.getRole().equals(Role.USER) ? "redirect:index" : "adminpage";
    }


    private List<GrantedAuthority> getAuthorities() {
        return Arrays.asList((GrantedAuthority) () -> "ROLE_USER",
                (GrantedAuthority) () -> "ROLE_ADMIN");
    }

}





