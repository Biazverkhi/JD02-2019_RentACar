package by.fastrentcar.web.autoservices;

import by.fastrentcar.service.AutoServicesService;
import by.fastrentcar.service.impl.DefaultAutoServicesService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminview/addservicestoauto")
public class AutoServicesAddToAutoServlet extends HttpServlet {
    private AutoServicesService defaultAutoServicesService = DefaultAutoServicesService.getInstance();
    // private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long autoId = Long.valueOf(req.getParameter("autoId"));
        Long servicesId = Long.valueOf(req.getParameter("servicesId"));
        defaultAutoServicesService.addAutoServicesToAuto(autoId, servicesId);
        WebUtils.redirect("adminview/autoservicesadmin", req, resp);
    }
}
