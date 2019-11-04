package by.fastrentcar.web.servlet.order;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.model.order.OrderDTO;
import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.service.BussinesLogic;
import by.fastrentcar.service.OrderService;
import by.fastrentcar.service.impl.DefaultBussinesLogic;
import by.fastrentcar.service.impl.DefaultOrderService;
import by.fastrentcar.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/userpage")
public class OrderNewFromUserPageServlet extends HttpServlet {
    private OrderService defaultOrderService = DefaultOrderService.getInstance();
    private BussinesLogic defaultBussinesLogic= DefaultBussinesLogic.getInstance();
    // private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        AuthUserDTO authuser = (AuthUserDTO) (req.getSession().getAttribute("authuser"));
        Long authuserId=authuser.getId();
        List<Order> list = defaultOrderService.getListOrderByIdUser(authuserId);
        req.setAttribute("ordersuser", list);
        WebUtils.forward("userpage", req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Long authuserId = Long.valueOf(req.getParameter("authuserId"));
        Long autoId = Long.valueOf(req.getParameter("autoId"));
        LocalDateTime startOrderDate = LocalDateTime.parse(req.getParameter("startOrderDate"));
       LocalDateTime stopOrderDate = LocalDateTime.parse(req.getParameter("stopOrderDate"));
        Double price = Double.valueOf(req.getParameter("price"));
        LocalDateTime createOrderDate=LocalDateTime.now() ;
        OrderDTO orderDTO = new OrderDTO(startOrderDate,stopOrderDate,price);
        Double priceArend=defaultBussinesLogic.getPriceArend(orderDTO);
        Order order = new Order(null, authuserId, autoId,
                createOrderDate, startOrderDate, stopOrderDate,
                null, "открыт",priceArend);
        defaultOrderService.addOrder(order);
        WebUtils.redirect("userpage", req, resp);
    }
}
