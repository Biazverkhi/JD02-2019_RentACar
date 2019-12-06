package by.fastrentcar.web.servlet.auto;

import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.service.AutoService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/adminview/services")
public class AutoServiserViewServlet extends HttpServlet {
    private AutoService defaultAutoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long autoId = Long.valueOf(req.getParameter("id"));
        List<AutoServices> list = defaultAutoService.getAutoServicesByAutoIdT(autoId);
        req.setAttribute("servicer", list);
        req.setAttribute("id1", autoId);
        WebUtils.forward("adminview/autoserviser", req, resp);


    }
}
