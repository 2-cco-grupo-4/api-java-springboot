package com.example.picmejava.repository;

import com.example.picmejava.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag, Long> {
}
