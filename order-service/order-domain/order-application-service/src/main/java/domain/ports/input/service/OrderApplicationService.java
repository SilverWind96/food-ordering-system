package domain.ports.input.service;

import domain.dto.create.CreateOrderCommand;
import domain.dto.create.CreateOrderResponse;
import domain.dto.track.TrackOrderQuery;
import domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);


}
