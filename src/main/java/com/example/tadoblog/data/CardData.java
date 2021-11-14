package com.example.tadoblog.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Setter
@Getter
@Entity
@Table(name = "Cards")
public class CardData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String cardTitle;
    @NotBlank
    private String cardText;
    private String cardComments;
    private String cardReplies;
}
