package by.fastrentcar.web.servlet.auto;

import by.fastrentcar.model.Auto;
import by.fastrentcar.service.AutoService;
import by.fastrentcar.service.impl.DefaultAutoService;
import by.fastrentcar.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addauto")
public class AutoAddServlet extends HttpServlet {
    private AutoService defaultAutoService = DefaultAutoService.getInstance();
   // private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String fuel = req.getParameter("fuel");
        String date = req.getParameter("date");
        Double price = Double.valueOf(req.getParameter("price"));
        String status = req.getParameter("status");
        Auto auto = new Auto(null, brand, model,fuel,date,price,status);
        defaultAutoService.addAuto(auto);
        WebUtils.redirect("autoadmin", req, resp);
    }
}
