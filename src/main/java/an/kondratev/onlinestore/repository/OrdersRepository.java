package an.kondratev.onlinestore.repository;

import an.kondratev.onlinestore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
