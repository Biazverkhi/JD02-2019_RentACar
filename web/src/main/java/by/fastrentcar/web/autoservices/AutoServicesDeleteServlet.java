package by.fastrentcar.web.autoservices;

import by.fastrentcar.service.AutoServicesService;
import by.fastrentcar.service.impl.DefaultAutoServicesService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminview/deleteautoservices")
public class AutoServicesDeleteServlet extends HttpServlet {
    private AutoServicesService defaultAutoServicesService = DefaultAutoServicesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultAutoServicesService.deleteAutoServicesT(id);
        WebUtils.redirect("adminview/autoservicesadmin", req, resp);
    }
}
