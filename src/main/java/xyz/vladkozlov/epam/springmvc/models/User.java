package xyz.vladkozlov.epam.springmvc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    private String fullName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private UserAccount userAccount;

    public User() {
        userAccount = new UserAccount();
    }

    /**
     * User roles. Should contain comma separated roles
     */
    @NotNull
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
