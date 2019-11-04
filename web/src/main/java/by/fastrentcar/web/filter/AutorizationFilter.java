package by.fastrentcar.web.filter;


import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.web.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/adminview/*")
public class AutorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean logged = session != null && session.getAttribute("authuser") != null;
        if (logged) {
            AuthUserDTO authUserDTO = (AuthUserDTO) session.getAttribute("authuser");
            Role role = authUserDTO.getRole();
            if (role.equals(Role.ADMIN)) {
                chain.doFilter(req, res);
            } else {
                WebUtils.redirect("index", req, res);
            }
        }


    }
}


