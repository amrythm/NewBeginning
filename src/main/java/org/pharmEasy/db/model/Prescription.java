package org.pharmEasy.db.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column(name="image_url")
    private String imageUrl;

    @NonNull
    @Column(name="user_id")
    private int userId;

}
