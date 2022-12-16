package com.amazon.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @ManyToOne()
    private UserEntity userId;

    @OneToMany(mappedBy = "OrderItemId", cascade = CascadeType.ALL)
    private List<OrderItem> productsList;

    @Column(name="Date")
    private LocalDate orderdate;

    private Double totalAmout;
}
