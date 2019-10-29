package by.fastrentcar.web.servlet.order;

import by.fastrentcar.model.Order;
import by.fastrentcar.service.OrderService;
import by.fastrentcar.service.impl.DefaultOrderService;
import by.fastrentcar.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@WebServlet("/addorder")
public class OrderAddServlet extends HttpServlet {
    private OrderService defaultOrderService = DefaultOrderService.getInstance();
    // private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long authuserId = Long.valueOf(req.getParameter("authuserId"));
        Long autoId = Long.valueOf(req.getParameter("autoId"));
        LocalDateTime createOrderDate = LocalDateTime.parse(req.getParameter("createOrderDate"));
        LocalDateTime startOrderDate =LocalDateTime.parse(req.getParameter("startOrderDate")) ;
        LocalDateTime stopOrderDate = LocalDateTime.parse(req.getParameter("stopOrderDate"));
        String  comment = req.getParameter("commentary");
        String reservStatus = req.getParameter("reservStatus");
        Double priceArend = Double.valueOf(req.getParameter("priceArend"));
        Order order = new Order(null, authuserId, autoId,
                createOrderDate, startOrderDate, stopOrderDate,
                comment, reservStatus,priceArend);
        defaultOrderService.addOrder(order);
        WebUtils.redirect("orderadmin", req, resp);
    }
}
