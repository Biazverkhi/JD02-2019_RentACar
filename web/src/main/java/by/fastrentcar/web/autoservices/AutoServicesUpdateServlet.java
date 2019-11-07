package by.fastrentcar.web.autoservices;

import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.auto.Services;
import by.fastrentcar.service.AutoServicesService;
import by.fastrentcar.service.impl.DefaultAutoServicesService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminview/updateautoservices")
public class AutoServicesUpdateServlet extends HttpServlet {
    private AutoServicesService defaultAutoServicesService = DefaultAutoServicesService.getInstance();
    // private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.valueOf(req.getParameter("id"));
        String services = req.getParameter("services");

        AutoServices autoservices = new AutoServices(id, Services.valueOf(services));
        defaultAutoServicesService.updateAutoServicesT(autoservices);
        WebUtils.redirect("adminview/autoservicesadmin", req, resp);
    }
}

