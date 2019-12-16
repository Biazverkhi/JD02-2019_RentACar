package by.fastrentcar.web.controller;

import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.auto.Services;
import by.fastrentcar.service.AutoServicesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
//@RequestMapping("/adminview")
public class AutoServicesController {
    private AutoServicesService defaultAutoServicesService;

    public AutoServicesController(AutoServicesService defaultAutoServicesService) {
        this.defaultAutoServicesService = defaultAutoServicesService;
    }

    @GetMapping("/autoservicesadmin")
    public String viewAutoServices(HttpServletRequest req) {
        List<AutoServices> list = defaultAutoServicesService.getListAutoServicesT();
        req.setAttribute("services", list);
        return "autoservicesadmin";
    }

    @PostMapping("/addservices")
    public String addAutoServices(HttpServletRequest req) {
        String services = req.getParameter("services");
        AutoServices autoServices = new AutoServices(null, Services.valueOf(services));
        defaultAutoServicesService.addAutoServicesT(autoServices);
        return "redirect:autoservicesadmin";
    }


    @PostMapping("/updateautoservices")
    public String updateAutoServices(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        String services = req.getParameter("services");
        AutoServices autoservices = new AutoServices(id, Services.valueOf(services));
        defaultAutoServicesService.updateAutoServicesT(autoservices);
        return "redirect:autoservicesadmin";
    }

    @PostMapping("/deleteautoservices")
    public String deleteAutoServices(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultAutoServicesService.deleteAutoServicesT(id);
        return "redirect:autoservicesadmin";
    }

    @PostMapping("/addservicestoauto")
    public String addAutoServicesToAuto(HttpServletRequest req) {
        Long autoId = Long.valueOf(req.getParameter("autoId"));
        Long servicesId = Long.valueOf(req.getParameter("servicesId"));
        defaultAutoServicesService.addAutoServicesToAuto(autoId, servicesId);
        return "redirect:autoservicesadmin";
    }

}
