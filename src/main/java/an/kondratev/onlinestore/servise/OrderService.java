package an.kondratev.onlinestore.servise;

import an.kondratev.onlinestore.model.Order;
import an.kondratev.onlinestore.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService implements OrderServiceInterface {
    OrdersRepository ordersRepository;
    UserService userService;

    @Override
    public void saveOrder(Order order) {
        order.setStatus("PENDING");

        Set<Order> userOrders = order.getUser().getOrders();
        if (userOrders == null) {
            userOrders = new HashSet<>();
            order.getUser().setOrders(userOrders);
        }

        userOrders.add(order);
        ordersRepository.save(order);
    }

}
