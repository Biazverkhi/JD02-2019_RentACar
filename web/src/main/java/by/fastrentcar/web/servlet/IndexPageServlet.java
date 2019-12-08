package by.fastrentcar.web.servlet;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.service.AutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping({"/index", "/prev", "/next"})
public class IndexPageServlet {
    private final AutoService defaultAutoService;

    public IndexPageServlet(AutoService defaultAutoService) {
        this.defaultAutoService = defaultAutoService;
    }
    private static final Logger log = LoggerFactory.getLogger(IndexPageServlet.class);

    @GetMapping()
    public String doGet(HttpServletRequest req) {
        Page p;
        String contextpath_next = req.getContextPath() + "/next";
        String contextpath_prev = req.getContextPath() + "/prev";
        String requestURI = req.getRequestURI();
        if (req.getSession().getAttribute("page") == null) {
            p = new Page();
        } else {
            p = (Page) req.getSession().getAttribute("page");
            req.getSession().removeAttribute("page");
        }
        p.countRowAll = (int) defaultAutoService.getCountAuto();
        p.numPageAll = p.countRowAll % p.size == 0 ? p.countRowAll / p.size : p.countRowAll / p.size + 1;
        if (contextpath_next.equals(requestURI)) {
            ++p.page;
            log.info("next");
        }
        if (contextpath_prev.equals(requestURI)) {
            --p.page;
            log.info("prev");
        }
        req.setAttribute("prev", p.page == 0 ? null : "prev");
        req.setAttribute("next", p.numPageAll - p.page - 1 > 0 ? "next" : null);
        List<Auto> list = defaultAutoService.getListAuto(p.page, p.size);
        req.setAttribute("autos", list);
        req.getSession().setAttribute("page", p);
        return "index";
    }
    private class Page {
        int page = 0;
        int numPageAll;
        int countRowAll;
        int size = 10;
        public String toString() {
            return "Page {numPage:" + page;
        }
    }
}
