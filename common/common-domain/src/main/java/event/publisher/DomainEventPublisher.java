package event.publisher;

import event.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent> {
    void publish(T domainEvent);
}
