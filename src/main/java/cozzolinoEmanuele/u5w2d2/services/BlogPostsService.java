package cozzolinoEmanuele.u5w2d2.services;

import cozzolinoEmanuele.u5w2d2.entities.Author;
import cozzolinoEmanuele.u5w2d2.entities.BlogPost;
import cozzolinoEmanuele.u5w2d2.exceptions.NotFoundException;
import cozzolinoEmanuele.u5w2d2.payloads.BlogPostPayload;
import cozzolinoEmanuele.u5w2d2.repositories.BlogPostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BlogPostsService {
	private final BlogPostsRepository blogPostsRepository;
	private final AuthorsService authorsService;

	@Autowired
	public BlogPostsService(BlogPostsRepository blogPostsRepository, AuthorsService authorsService) {
		this.blogPostsRepository = blogPostsRepository;
		this.authorsService = authorsService;
	}

	public BlogPost save(BlogPostPayload payload) {
		Author author = this.authorsService.findById(payload.getAuthorId());

		BlogPost newBlogPost = new BlogPost(payload.getTitle(), payload.getContent(), payload.getReadingTime(), author);

		newBlogPost.setCoverURL("https://picsum.photos/200/300");

		BlogPost savedBlogPost = this.blogPostsRepository.save(newBlogPost);

		log.info("Il blog post con id " + savedBlogPost.getId() + " è stato salvato correttamente!");

		return savedBlogPost;
	}

	public Page<BlogPost> findAll(int page, int size, String orderBy, String sortCriteria) {
		if (size > 100 || size < 0) size = 10;
		if (page < 0) page = 0;
		Pageable pageable = PageRequest.of(page, size, sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
		return this.blogPostsRepository.findAll(pageable);
	}

	public BlogPost findById(UUID blogPostId) {
		return this.blogPostsRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
	}

	public BlogPost findByIdAndUpdate(UUID blogPostId, BlogPostPayload payload) {

		BlogPost found = this.findById(blogPostId);

		Author author = this.authorsService.findById(payload.getAuthorId());

		found.setTitle(payload.getTitle());
		found.setContent(payload.getContent());
		found.setReadingTime(payload.getReadingTime());
		found.setAuthor(author);

		BlogPost modifiedBlogPost = this.blogPostsRepository.save(found);

		log.info("Il blog post con id " + modifiedBlogPost.getId() + " è stato modificato correttamente");

		return modifiedBlogPost;
	}

	public void findByIdAndDelete(UUID blogPostId) {
		BlogPost found = this.findById(blogPostId);
		this.blogPostsRepository.delete(found);
	}
}
