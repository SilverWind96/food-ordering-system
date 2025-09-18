import entity.Order;
import entity.Product;
import entity.Restaurant;
import event.OrderCancelledEvent;
import event.OrderCreatedEvent;
import event.OrderPaidEvent;
import exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    private static final ZoneId UTC = ZoneId.of("UTC");

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order has been initialized with orderId: {}", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(UTC));
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        Map<String, Product> dict = new HashMap<>();
        restaurant.getProducts().forEach(product ->
                dict.put(product.getName() + "_" + product.getPrice(), product)
        );
        order.getItems().forEach(orderItem -> {
            Product currProduct = orderItem.getProduct();
            Product product = dict.get(currProduct.getName() + "_" + currProduct.getPrice());
            currProduct.updateWithConfirmedNameAndPrice(product.getName(), product.getPrice());
        });
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant is not active with id: " + restaurant.getId().getValue());
        }
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order has been paid with orderId: {}", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(UTC));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order has been approved with orderId: {}", order.getId().getValue());

    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order has been initialized to cancel with orderId: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(UTC));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order has been cancelled with orderId: {}", order.getId().getValue());
    }
}
