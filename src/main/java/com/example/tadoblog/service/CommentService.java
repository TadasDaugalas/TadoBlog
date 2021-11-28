package com.example.tadoblog.service;

import com.example.tadoblog.data.Comment;
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
    public Optional<Comment> getComment(UUID uuid){
      return   jpaCommentRepository.findById(uuid);
    }
}
