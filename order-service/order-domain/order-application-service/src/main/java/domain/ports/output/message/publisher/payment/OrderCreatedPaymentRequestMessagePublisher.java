package domain.ports.output.message.publisher.payment;

import event.OrderCreatedEvent;
import event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
