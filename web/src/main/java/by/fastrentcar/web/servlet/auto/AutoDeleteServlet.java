package by.fastrentcar.web.servlet.auto;

import by.fastrentcar.service.AutoService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminview/deleteauto")
public class AutoDeleteServlet extends HttpServlet {
    private AutoService defaultAutoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultAutoService.deleteAuto(id);
        WebUtils.redirect("adminview/autoadmin", req, resp);
    }
}
