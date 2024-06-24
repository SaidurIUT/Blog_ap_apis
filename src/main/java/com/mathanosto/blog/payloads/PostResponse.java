package com.mathanosto.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	private List<PostDto> content;
	private int pageNumber;
	private long totalElements;
	private int totalPages;
	private int pageSize;
	private boolean lastpage;
}
