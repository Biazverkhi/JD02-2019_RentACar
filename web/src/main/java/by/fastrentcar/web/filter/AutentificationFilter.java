package by.fastrentcar.web.filter;


import by.fastrentcar.web.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter({"/*"})
public class AutentificationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String indexURI = req.getContextPath() + "/index";
        String loginURI = req.getContextPath() + "/login";
        String regisrtationURI = req.getContextPath() + "/registration";
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean indexRequest = req.getRequestURI().equals(indexURI);
        boolean registrationRequest = req.getRequestURI().equals(regisrtationURI);
        boolean logged = session != null && session.getAttribute("authuser") != null;
        //пропускает если:
        //или пользователь незалогинен и запрос с логина или регистрации
        //или запрос с index
        //пользователь залогинен и запрос не с регистрации и логина
        if ((!logged && (loginRequest || registrationRequest))
                || indexRequest
                || logged && (!loginRequest && !registrationRequest)) {
            chain.doFilter(req, res);
        } else {
            WebUtils.redirect("index", req, res);
        }


    }
}


