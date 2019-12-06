package by.fastrentcar.web.servlet;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.service.AutoService;
import by.fastrentcar.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/page/next"})
public class Next extends HttpServlet {


    private AutoService defaultAutoService;
    private static final Logger log = LoggerFactory.getLogger(by.fastrentcar.web.servlet.IndexPageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        log.info("start servler");
        Page p;
        if (req.getAttribute("page") == null) {
            p = new Page();
        } else {
            p = (Page) req.getAttribute("page");
        }
        p.countRowAll = (int) defaultAutoService.getCountAuto();
        p.numPageAll = 1 + p.countRowAll / p.maxResult;
        p.numPage = p.startPos / p.maxResult + 1;
        String contextpath_next = req.getContextPath() + "/page/next";
        String requestURI = req.getRequestURI();
        String servletpath = req.getServletPath();
        StringBuffer requestURl = req.getRequestURL();
        log.info("contextpath_next " + contextpath_next);
        log.info("requestURI " + requestURI);
        log.info("servletpath " + servletpath);
        log.info("requestURl " + requestURl);

        if (contextpath_next.equals(requestURI)) {
            log.info("/next");

            p.startPos = p.numPage * p.maxResult;

        }

        if (p.numPage == 1) {
            req.setAttribute("prev", null);
        } else {
            req.setAttribute("prev", "prev");
        }
        if (p.numPageAll - p.numPage > 0) {
            req.setAttribute("next", "next");
        } else {
            req.setAttribute("next", null);
        }

        List<Auto> list = defaultAutoService.getListAuto(p.startPos, p.maxResult);
        req.setAttribute("autos", list);
        req.setAttribute("page", p);
        WebUtils.forward("index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        WebUtils.redirect("registration", req, resp);
    }

    private static class Page {
        int numPage;
        int numPageAll;
        int countRowAll;
        int startPos = 0;
        int maxResult = 10;
    }
}


