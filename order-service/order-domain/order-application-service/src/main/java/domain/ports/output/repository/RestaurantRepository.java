package domain.ports.output.repository;

import entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInfo(Restaurant restaurant);
}
