package com.amazon.module.product;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int userId;
    private String fName;
    private String lName;
    private String password;
    private String age;
    private String mobiNumber;
    private String mail;
}



