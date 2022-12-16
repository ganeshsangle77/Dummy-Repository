package com.amazon.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String fName;
    private String lName;
    private String password;
    private String age;
    private String mobiNumber;
    private String mail;
    @OneToMany(mappedBy = "orderId")
    private List<Order> order;
}



