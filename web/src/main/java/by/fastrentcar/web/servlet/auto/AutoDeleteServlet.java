package by.fastrentcar.web.servlet.auto;

import by.fastrentcar.service.AutoService;
import by.fastrentcar.service.impl.DefaultAutoService;
import by.fastrentcar.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteauto")
public class AutoDeleteServlet extends HttpServlet {
    private AutoService defaultAutoService = DefaultAutoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultAutoService.deleteAuto(id);
        WebUtils.redirect("autoadmin", req, resp);
    }
}
