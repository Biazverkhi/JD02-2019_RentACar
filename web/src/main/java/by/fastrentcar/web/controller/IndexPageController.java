package by.fastrentcar.web.controller;

import by.fastrentcar.model.page.PageAuto;
import by.fastrentcar.service.AutoService;
import by.fastrentcar.web.page.PageAutoProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"index", "prev", "next", ""})
public class IndexPageController {

    private final AutoService defaultAutoService;
    private PageAutoProperty prop;

    public IndexPageController(AutoService defaultAutoService, PageAutoProperty prop) {
        this.defaultAutoService = defaultAutoService;
        this.prop = prop;
    }

    private static final Logger log = LoggerFactory.getLogger(IndexPageController.class);

    @GetMapping()
    public String getIndexPage(HttpServletRequest req, ModelMap model, HttpSession session, @RequestParam(value = "sort", required = false) String sort) {
        log.info("ufdyj");
        PageAuto p;
        p = (session.getAttribute("page") == null) ? new PageAuto(prop.getSize()) : (PageAuto) session.getAttribute("page");
        sort(p, sort);
        pagination(p, req);
        Map<String, List<String>> auto = new HashMap<>();
        if (session.getAttribute("autofromfiltr") != null) {
            auto = (Map<String, List<String>>) session.getAttribute("autofromfiltr");
        }
        p = auto.isEmpty() ? defaultAutoService.getListAuto(p) : defaultAutoService.getListAutoFiltr(p, auto);
        model.addAttribute("prev", p.getPage() == 0 ? null : "prev");
        model.addAttribute("next", p.getNumPageAll() - p.getPage() - 1 > 0 ? "next" : null);
        session.setAttribute("page", p);
        session.setAttribute("auto", defaultAutoService.getCheckBoxColumnAuto());
        return "index";
    }

    private static void sort(PageAuto p, String sort) {
        if (p.getColumnName() == null && sort != null) {
            p.setColumnName(sort);
            p.setSort(Sort.Direction.ASC);
            p.setPage(0);
        } else if (p.getColumnName() != null && sort != null) {
            p.setSort(p.getSort() == Sort.Direction.ASC ? Sort.Direction.DESC : Sort.Direction.ASC);
            p.setPage(0);
        }
    }

    private static void pagination(PageAuto p, HttpServletRequest req) {
        String contextpath_next = req.getContextPath() + "/next";
        String contextpath_prev = req.getContextPath() + "/prev";
        String requestURI = req.getRequestURI();

        if (contextpath_next.equals(requestURI)) {
            p.setPage(p.getPage() + 1);
        }
        if (contextpath_prev.equals(requestURI)) {
            p.setPage(p.getPage() - 1);
        }
    }

    @PostMapping(value = {"/filtr"})
    public String filtr(HttpSession session, @RequestParam(value = "model", required = false) String[] model, @RequestParam(value = "brand", required = false) String[] brand) {
        Map<String, List<String>> autoCheck = new HashMap<>();
        if (model != null) {
            autoCheck.put("model", Arrays.asList(model));
        }
        if (brand != null) {
            autoCheck.put("brand", Arrays.asList(brand));
        }
        session.setAttribute("autofromfiltr", autoCheck);
        return "redirect:/index";
    }
}
