package by.fastrentcar.web.filter;


import by.fastrentcar.web.WebUtils;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter({"/adminPage", "/userpage","/useradmin","/autoadmin","/orderadmin"})
public class AutentificationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig)  {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse)  response;
        HttpSession session = req.getSession();
        Object authUser=session.getAttribute("authuser");
        if (authUser==null) {
            WebUtils.forward( "index", req, res);
            return;
        }
            chain.doFilter(req, response);

        //тут допишу роли


    }
}


