package com.mappling.repostory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mappling.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
