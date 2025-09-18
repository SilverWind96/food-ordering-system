package entity;

import lombok.Getter;
import valueobject.Money;
import valueobject.ProductId;

@Getter
public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;

    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }
}
