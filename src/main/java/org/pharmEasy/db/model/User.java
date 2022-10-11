package org.pharmEasy.db.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    // == private fields ==
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull    // search for difference b/w lombok and spring version of NonNull
    private String name;
    @NotBlank(message = "This field cannot be empty")
    @Email
    private String email;
    @NonNull
    
    private String password;

    private char gender;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @OneToMany
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private List<Prescription> prescriptions;

}
