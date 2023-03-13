package net.javaguides.emailservice.modal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEvent {
    private String status;
    private String message;
    private Order order;
}
