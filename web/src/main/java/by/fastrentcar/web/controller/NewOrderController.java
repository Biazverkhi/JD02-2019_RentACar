package by.fastrentcar.web.controller;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.model.order.OrderDTO;
import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.service.BussinesLogic;
import by.fastrentcar.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/userpage")
public class NewOrderController {
    private OrderService defaultOrderService;
    private BussinesLogic defaultBussinesLogic;

    public NewOrderController(OrderService defaultOrderService, BussinesLogic defaultBussinesLogic) {
        this.defaultOrderService = defaultOrderService;
        this.defaultBussinesLogic = defaultBussinesLogic;
    }

    @GetMapping
    public String viewOrderListUser(HttpServletRequest req) {
        AuthUserDTO authuser = (AuthUserDTO) (req.getSession().getAttribute("authuser"));
        Long authuserId = authuser.getId();
        List<Order> list = defaultOrderService.getListOrderByIdUser(authuserId);
        req.setAttribute("ordersuser", list);
        return "userpage";

    }

    @PostMapping
    public String addOrderFromUser(HttpServletRequest req) {
        Long authuserId = Long.valueOf(req.getParameter("authuserId"));
        Long autoId = Long.valueOf(req.getParameter("autoId"));
        LocalDateTime startOrderDate = LocalDateTime.parse(req.getParameter("startOrderDate"));
        LocalDateTime stopOrderDate = LocalDateTime.parse(req.getParameter("stopOrderDate"));
        Double price = Double.valueOf(req.getParameter("price"));
        LocalDateTime createOrderDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        OrderDTO orderDTO = new OrderDTO(startOrderDate, stopOrderDate, price);
        Double priceArend = defaultBussinesLogic.getPriceArend(orderDTO);
        Order order = new Order(null, authuserId, autoId,
                createOrderDate, startOrderDate, stopOrderDate,
                null, "открыт", priceArend);
        defaultOrderService.addOrder(order);
        return "redirect:userpage";
    }
}
