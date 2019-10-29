package by.fastrentcar.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {

    private static final String UTF_8 = "UTF-8";

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) rq;
        HttpServletResponse res = (HttpServletResponse) rs;
        req.setCharacterEncoding(UTF_8);
        res.setCharacterEncoding(UTF_8);


        String locale = req.getParameter("locale");

        if (locale==null){
            locale = (String) req.getSession().getAttribute("locale");
            if (locale==null) {
                req.getSession().setAttribute("locale", "ru_RU");
            }
        }else {
            req.getSession().setAttribute("locale", locale);
        }



        filterChain.doFilter(rq, rs);

    }
}
