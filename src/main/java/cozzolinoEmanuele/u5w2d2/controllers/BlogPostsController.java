package cozzolinoEmanuele.u5w2d2.controllers;

import cozzolinoEmanuele.u5w2d2.entities.BlogPost;
import cozzolinoEmanuele.u5w2d2.payloads.BlogPostPayload;
import cozzolinoEmanuele.u5w2d2.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {
	private final BlogPostsService blogPostsService;

	@Autowired
	public BlogPostsController(BlogPostsService blogPostsService) {
		this.blogPostsService = blogPostsService;
	}

	// 1. POST http://localhost:3001/blogPosts (+ Payload)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BlogPost createBlogPost(@RequestBody BlogPostPayload payload) {
		return this.blogPostsService.save(payload);
	}

	// 2. GET http://localhost:3001/blogPosts
	@GetMapping
	public Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int page,
	                               @RequestParam(defaultValue = "10") int size,
	                               @RequestParam(defaultValue = "title") String orderBy,
	                               @RequestParam(defaultValue = "asc") String sortCriteria) {
		return this.blogPostsService.findAll(page, size, orderBy, sortCriteria);
	}

	// 3. GET http://localhost:3001/blogPosts/{blogPostId}
	@GetMapping("/{blogPostId}")
	public BlogPost findById(@PathVariable UUID blogPostId) {
		return this.blogPostsService.findById(blogPostId);
	}

	// 4. PUT http://localhost:3001/blogPosts/{blogPostId}
	@PutMapping("/{blogPostId}")
	public BlogPost findByIdAndUpdate(@PathVariable UUID blogPostId, @RequestBody BlogPostPayload payload) {
		return this.blogPostsService.findByIdAndUpdate(blogPostId, payload);
	}

	// 5. DELETE http://localhost:3001/blogPosts/{blogPostId}
	@DeleteMapping("/{blogPostId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findByIdAndDelete(@PathVariable UUID blogPostId) {
		this.blogPostsService.findByIdAndDelete(blogPostId);
	}
}
