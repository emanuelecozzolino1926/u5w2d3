package cozzolinoEmanuele.u5w2d2.controllers;

import cozzolinoEmanuele.u5w2d2.entities.Author;
import cozzolinoEmanuele.u5w2d2.payloads.AuthorPayload;
import cozzolinoEmanuele.u5w2d2.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
	private final AuthorsService authorsService;

	@Autowired
	public AuthorsController(AuthorsService authorsService) {
		this.authorsService = authorsService;
	}

	// 1. POST http://localhost:3001/authors (+ Payload)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Author createAuthor(@RequestBody AuthorPayload payload) {
		return this.authorsService.save(payload);
	}

	// 2. GET http://localhost:3001/authors
	@GetMapping
	public Page<Author> findAll(@RequestParam(defaultValue = "0") int page,
	                             @RequestParam(defaultValue = "10") int size,
	                             @RequestParam(defaultValue = "surname") String orderBy,
	                             @RequestParam(defaultValue = "asc") String sortCriteria) {
		return this.authorsService.findAll(page, size, orderBy, sortCriteria);
	}

	// 3. GET http://localhost:3001/authors/{authorId}
	@GetMapping("/{authorId}")
	public Author findById(@PathVariable UUID authorId) {
		return this.authorsService.findById(authorId);
	}

	// 4. PUT http://localhost:3001/authors/{authorId}
	@PutMapping("/{authorId}")
	public Author findByIdAndUpdate(@PathVariable UUID authorId, @RequestBody AuthorPayload payload) {
		return this.authorsService.findByIdAndUpdate(authorId, payload);
	}

	// 5. DELETE http://localhost:3001/authors/{authorId}
	@DeleteMapping("/{authorId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findByIdAndDelete(@PathVariable UUID authorId) {
		this.authorsService.findByIdAndDelete(authorId);
	}
}
