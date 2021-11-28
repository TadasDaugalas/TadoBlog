package com.example.tadoblog.repository;

import com.example.tadoblog.data.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPACommentRepository extends JpaRepository<Comment, UUID> {
}
