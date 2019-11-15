package xyz.vladkozlov.epam.springmvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phone_numbers")
public class PhoneNumber extends BaseEntity {

    private String company;

    private String number;

}
