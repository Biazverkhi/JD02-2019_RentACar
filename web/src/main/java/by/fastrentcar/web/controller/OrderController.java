package by.fastrentcar.web.controller;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/adminview")
public class OrderController {
    private OrderService defaultOrderService;

    public OrderController(OrderService defaultOrderService) {
        this.defaultOrderService = defaultOrderService;
    }

    @GetMapping("/orderadmin")
    public String viewOrder(HttpServletRequest req) {
        List<Order> list = defaultOrderService.getListOrder();
        req.setAttribute("orders", list);
        return "/adminview/orderadmin";
    }

    @PostMapping("/addorder")
    public String addOrder(HttpServletRequest req) {
        Long authuserId = Long.valueOf(req.getParameter("authuserId"));
        Long autoId = Long.valueOf(req.getParameter("autoId"));
        LocalDateTime createOrderDate = LocalDateTime.parse(req.getParameter("createOrderDate"));
        LocalDateTime startOrderDate = LocalDateTime.parse(req.getParameter("startOrderDate"));
        LocalDateTime stopOrderDate = LocalDateTime.parse(req.getParameter("stopOrderDate"));
        String comment = req.getParameter("commentary");
        String reservStatus = req.getParameter("reservStatus");
        Double priceArend = Double.valueOf(req.getParameter("priceArend"));
        Order order = new Order(null, authuserId, autoId,
                createOrderDate, startOrderDate, stopOrderDate,
                comment, reservStatus, priceArend);
        defaultOrderService.addOrder(order);
        return "redirect:/adminview/orderadmin";
    }

    @PostMapping("/updateorder")
    public String updateOrder(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        Long authuserId = Long.valueOf(req.getParameter("authuserId"));
        Long autoId = Long.valueOf(req.getParameter("autoId"));
        LocalDateTime createOrderDate = LocalDateTime.parse(req.getParameter("createOrderDate"));
        LocalDateTime startOrderDate = LocalDateTime.parse(req.getParameter("startOrderDate"));
        LocalDateTime stopOrderDate = LocalDateTime.parse(req.getParameter("stopOrderDate"));
        String comment = req.getParameter("commentary");
        String reservStatus = req.getParameter("reservStatus");
        Double priceArend = Double.valueOf(req.getParameter("priceArend"));
        Order order = new Order(id, authuserId, autoId,
                createOrderDate, startOrderDate, stopOrderDate,
                comment, reservStatus, priceArend);
        defaultOrderService.updateOrder(order);
        return "redirect:/adminview/orderadmin";
    }

    @PostMapping("/deleteorder")
    public String deleteOrder(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        defaultOrderService.deleteOrder(id);
        return "redirect:/adminview/orderadmin";
    }
}
