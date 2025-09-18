package domain.ports.output.message.publisher.payment;

import event.OrderCancelledEvent;
import event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
