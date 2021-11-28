package com.example.tadoblog.service;

import com.example.tadoblog.data.Comment;
import com.example.tadoblog.exeption.CardNotExistExeption;
import com.example.tadoblog.repository.JPACommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {
    private final JPACommentRepository jpaCommentRepository;

    public CommentService(JPACommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }
    public void saveComment(Comment comment){
        jpaCommentRepository.save(comment);
    }
    public Comment getComment(UUID uuid){
      return  jpaCommentRepository.findById(uuid).orElseThrow(()-> new CardNotExistExeption(uuid));
    }
}
