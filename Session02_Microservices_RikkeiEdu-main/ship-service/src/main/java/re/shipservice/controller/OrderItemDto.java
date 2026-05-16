package re.shipservice.controller;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDto {

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;
}
