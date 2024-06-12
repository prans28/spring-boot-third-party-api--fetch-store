package com.mappling.entity;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Post {
	@Id
	private Integer userId;
	private Integer id;
	private String title;
	private String body;
}
