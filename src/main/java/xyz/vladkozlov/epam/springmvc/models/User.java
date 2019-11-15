package xyz.vladkozlov.epam.springmvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;

    private String password;

    private String fullName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();


    /**
     * User roles. Should contain comma separated roles
     */
    private String roles = "";

    public void addRoles(String... rls) {
        for (String role : rls) {
            if (roles.equals("")) {
                roles += role;
            } else {
                roles = roles.concat(", " + role);
            }
        }
    }

    public void addRole(String role) {
        if (roles.equals("")) {
            roles += role;
        } else {
            roles = roles.concat(", "+ role);
        }
    }
}
