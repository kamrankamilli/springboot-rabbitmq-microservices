package net.javaguides.stockservice.modal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String name;
    private int quantity;
    private double price;

}
