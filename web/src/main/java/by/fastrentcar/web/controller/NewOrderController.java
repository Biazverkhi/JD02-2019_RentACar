package by.fastrentcar.web.controller;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.model.order.OrderDTO;
import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.service.BussinesLogic;
import by.fastrentcar.service.OrderService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/userpage")
public class NewOrderController {
    private OrderService defaultOrderService;
    private BussinesLogic defaultBussinesLogic;

    public NewOrderController(OrderService defaultOrderService, BussinesLogic defaultBussinesLogic) {
        this.defaultOrderService = defaultOrderService;
        this.defaultBussinesLogic = defaultBussinesLogic;
    }

    @GetMapping
    public String viewOrderListUser(HttpServletRequest req, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        AuthUserDTO authuser = (AuthUserDTO) usernamePasswordAuthenticationToken.getPrincipal();
        Long authuserId = authuser.getId();
        List<Order> list = defaultOrderService.getListOrderByIdUser(authuserId);
        req.setAttribute("ordersuser", list);
        return "userpage";

    }

    @RequestMapping(method = RequestMethod.POST)
    public String addOrderFromUser(HttpServletRequest req, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        Long authuserId = ((AuthUserDTO) usernamePasswordAuthenticationToken.getPrincipal()).getId();
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
