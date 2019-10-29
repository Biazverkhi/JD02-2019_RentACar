package by.fastrentcar.web.servlet.order;

import by.fastrentcar.service.OrderService;
import by.fastrentcar.service.impl.DefaultOrderService;
import by.fastrentcar.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteorder")
public class OrderDeleteServlet extends HttpServlet {
    private OrderService defaultOrderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultOrderService.deleteOrder(id);
        WebUtils.redirect("orderadmin", req, resp);
    }
}
