package by.fastrentcar.web.controller;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.service.AutoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/adminview")
public class AutoController {
    private AutoService defaultAutoService;

    public AutoController(AutoService defaultAutoService) {
        this.defaultAutoService = defaultAutoService;
    }

    @GetMapping("/autoadmin")
    public String viewAuto(HttpServletRequest req) {
        List<Auto> list = defaultAutoService.getListAuto();
        req.setAttribute("autos", list);
        return "/adminview/autoadmin";
    }

    @PostMapping("/addauto")
    public String addAuto(HttpServletRequest req) {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String fuel = req.getParameter("fuel");
        String date = req.getParameter("date");
        Double price = Double.valueOf(req.getParameter("price"));
        String status = req.getParameter("status");
        Auto auto = new Auto(null, brand, model, fuel, date, price, status);
        defaultAutoService.addAuto(auto);
        return "redirect:/adminview/autoadmin";
    }

    @PostMapping("/updateauto")
    public String updateAuto(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String fuel = req.getParameter("fuel");
        String date = req.getParameter("date");
        Double price = Double.valueOf(req.getParameter("price"));
        String status = req.getParameter("status");
        Auto auto = new Auto(id, brand, model, fuel, date, price, status);
        defaultAutoService.updateAuto(auto);
        return "redirect:/adminview/autoadmin";
    }

    @PostMapping("/deleteauto")
    public String deleteAuto(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultAutoService.deleteAuto(id);
        return "redirect:/adminview/autoadmin";
    }

    @PostMapping("/services")
    public String viewAutoServiser(HttpServletRequest req) {
        Long autoId = Long.valueOf(req.getParameter("id"));
        List<AutoServices> list = defaultAutoService.getAutoServicesByAutoIdT(autoId);
        req.setAttribute("servicer", list);
        req.setAttribute("id1", autoId);
        return "/adminview/autoserviser";
    }
}

