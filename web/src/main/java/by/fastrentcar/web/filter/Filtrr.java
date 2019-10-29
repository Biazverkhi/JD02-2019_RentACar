package by.fastrentcar.web.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class Filtrr implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
//        Optional<Cookie> user = Arrays.stream(cookies)
//                .filter(c -> c.getName().equals("user"))
//                .findAny();
//
//        if (user.isPresent()) {
//            chain.doFilter(request, response);
//            response.getWriter().write("<h2>user  " + user.get().getValue() + "</h2>");
//
//        } else {
//            response.getWriter().write("<h2>Go to Login</h2>");
//        }
    }
}
