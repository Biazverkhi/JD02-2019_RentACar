package by.fastrentcar.web.autoservices;

import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.service.AutoServicesService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/adminview/autoservicesadmin")
public class AutoServicesAdminServlet extends HttpServlet {
    private AutoServicesService defaultAutoServicesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<AutoServices> list = defaultAutoServicesService.getListAutoServicesT();
        req.setAttribute("services", list);
        WebUtils.forward("adminview/autoservicesadmin", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}
