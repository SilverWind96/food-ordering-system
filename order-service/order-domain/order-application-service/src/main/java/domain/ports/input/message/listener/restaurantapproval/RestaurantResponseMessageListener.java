package domain.ports.input.message.listener.restaurantapproval;

import domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantResponseMessageListener {
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
