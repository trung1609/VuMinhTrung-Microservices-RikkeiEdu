package re.shipservice.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonIgnoreProperties({"userId"})
public class Orders {

    private String orderId;
    private String userId;

    private BigDecimal totalAmount;

    private re.shipservice.controller.OrderStatus status;
    private LocalDateTime createdAt;

}
