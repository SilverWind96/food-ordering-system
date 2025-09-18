package domain.ports.output.message.publisher.restaurantapproval;

import event.OrderPaidEvent;
import event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
