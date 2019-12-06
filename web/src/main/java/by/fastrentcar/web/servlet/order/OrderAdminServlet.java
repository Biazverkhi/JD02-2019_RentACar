package by.fastrentcar.web.servlet.order;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.service.OrderService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/adminview/orderadmin")
public class OrderAdminServlet extends HttpServlet {
    private OrderService defaultOrderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        List<Order> list = defaultOrderService.getListOrder();
        req.setAttribute("orders", list);

        WebUtils.forward("adminview/orderadmin", req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
    }
}
