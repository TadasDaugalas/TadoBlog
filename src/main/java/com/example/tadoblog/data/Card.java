package com.example.tadoblog.data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;


@Setter
@Getter
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varchar(36)", nullable = false, updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @NotBlank
    private String cardTitle;
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String cardText;
    @OneToMany(mappedBy = "card" ,fetch = FetchType.EAGER ,cascade = CascadeType.REMOVE)
    private Set<Comment> comments;
}
