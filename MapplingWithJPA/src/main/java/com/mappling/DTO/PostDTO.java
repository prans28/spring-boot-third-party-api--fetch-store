package com.mappling.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDTO {
	private Integer userId;
	private Integer id;
	private String title;
	private String body;
}