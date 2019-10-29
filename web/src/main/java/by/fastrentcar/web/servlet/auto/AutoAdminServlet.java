package by.fastrentcar.web.servlet.auto;

import by.fastrentcar.model.Auto;
import by.fastrentcar.service.AutoService;
import by.fastrentcar.service.impl.DefaultAutoService;
import by.fastrentcar.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/autoadmin")
public class AutoAdminServlet extends HttpServlet {
    private AutoService defaultAutoService = DefaultAutoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        List<Auto> list = defaultAutoService.getListAuto();
        req.setAttribute("autos", list);
       WebUtils.forward("autoadmin", req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
    }
}
