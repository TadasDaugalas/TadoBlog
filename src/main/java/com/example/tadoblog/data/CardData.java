package com.example.tadoblog.data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Setter
@Getter
@Entity
@Table(name = "Cards")
public class CardData {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar(36)",nullable = false,updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @NotBlank
    private String cardTitle;
    @NotBlank
    private String cardText;
    private String cardComments;
    @Nullable
    private int cardCommentsCounter;
    private String cardReplies;
}
