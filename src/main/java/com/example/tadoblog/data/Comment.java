package com.example.tadoblog.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varchar(36)", nullable = false, updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    private String commentText;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="card_id", nullable=false)
    private Card card;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_comment_id", referencedColumnName = "id")
    private Comment parentComment;

    }

