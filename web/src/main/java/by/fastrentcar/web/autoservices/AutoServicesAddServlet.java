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

@WebServlet("/adminview/addservices")
public class AutoServicesAddServlet extends HttpServlet {
    private AutoServicesService defaultAutoServicesService = DefaultAutoServicesService.getInstance();
    // private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String services = req.getParameter("services");
        AutoServices autoServices = new AutoServices(null, Services.valueOf(services));
        defaultAutoServicesService.addAutoServicesT(autoServices);
        WebUtils.redirect("adminview/autoservicesadmin", req, resp);
    }
}
