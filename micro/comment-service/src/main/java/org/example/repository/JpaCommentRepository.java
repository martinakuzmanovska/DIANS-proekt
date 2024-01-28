package org.example.repository;

import org.example.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}
