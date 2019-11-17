package xyz.vladkozlov.epam.springmvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAccount extends  BaseEntity{

    @OneToOne(mappedBy = "userAccount")
    private User user;

    private String phoneNumber;

    private String cellOperator;

    private long balance;
}
