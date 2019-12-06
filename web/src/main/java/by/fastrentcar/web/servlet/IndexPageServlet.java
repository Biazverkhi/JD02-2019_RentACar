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

@WebServlet(urlPatterns = {"/index", "/prev", "/next"})
public class IndexPageServlet extends HttpServlet {
    private AutoService defaultAutoService;
    private static final Logger log = LoggerFactory.getLogger(IndexPageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        log.info("start servler");
        Page p;
        if (req.getSession().getAttribute("page") == null) {
            p = new Page();
            log.info("New page " + p.toString());
        } else {

            p = (Page) req.getSession().getAttribute("page");
            log.info("page from atribute " + p.toString());
            req.removeAttribute("page");
        }
        p.countRowAll = (int) defaultAutoService.getCountAuto();
        p.numPageAll = 1 + p.countRowAll / p.maxResult;
        String contextpath_next = req.getContextPath() + "/next";
        String contextpath_prev = req.getContextPath() + "/prev";
        String requestURI = req.getRequestURI();

        if (contextpath_next.equals(requestURI)) {
            log.info("/next");
            ++p.numPage;
            log.info("page " + p.numPage);

        }

        if (contextpath_prev.equals(requestURI)) {
            log.info("/prev");
            p.numPage -= 1;
            log.info("page " + p.numPage);

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
        p.startPos = (p.numPage - 1) * p.maxResult;

        List<Auto> list = defaultAutoService.getListAuto(p.startPos, p.maxResult);
        req.setAttribute("autos", list);
        log.info("page to atribute " + p.toString());

        req.getSession().setAttribute("page", p);
        WebUtils.forward("index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }

    private class Page {
        int numPage = 1;
        int numPageAll;
        int countRowAll;
        int startPos;
        int maxResult = 10;

        @Override
        public String toString() {
            return "Page {numPage:" + numPage + " startPos: " + startPos;
        }
    }
}
