package com.amazon.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer OrderItemId;

    @ManyToOne
    private UserEntity user;

    @OneToOne
    private ProductEntity product;

    @ManyToOne
//    @MapsId
    private Order orderId;

    private Integer itemQty;

}
