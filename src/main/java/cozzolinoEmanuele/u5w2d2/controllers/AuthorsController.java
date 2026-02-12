package cozzolinoEmanuele.u5w2d2.controllers;

import cozzolinoEmanuele.u5w2d2.entities.Author;
import cozzolinoEmanuele.u5w2d2.exceptions.ValidationException;
import cozzolinoEmanuele.u5w2d2.payloads.AuthorPayload;
import cozzolinoEmanuele.u5w2d2.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
	public Author createAuthor(@RequestBody @Validated AuthorPayload payload, BindingResult validationResult) {
		if (validationResult.hasErrors()) {
			List<String> errorsList = validationResult.getFieldErrors()
					.stream()
					.map(fieldError -> fieldError.getDefaultMessage())
					.toList();
			throw new ValidationException(errorsList);
		}
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
	public Author findByIdAndUpdate(@PathVariable UUID authorId, @RequestBody @Validated AuthorPayload payload, BindingResult validationResult) {
		if (validationResult.hasErrors()) {
			List<String> errorsList = validationResult.getFieldErrors()
					.stream()
					.map(fieldError -> fieldError.getDefaultMessage())
					.toList();
			throw new ValidationException(errorsList);
		}
		return this.authorsService.findByIdAndUpdate(authorId, payload);
	}

	// 5. DELETE http://localhost:3001/authors/{authorId}
	@DeleteMapping("/{authorId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findByIdAndDelete(@PathVariable UUID authorId) {
		this.authorsService.findByIdAndDelete(authorId);
	}

	// 6. PATCH http://localhost:3001/authors/{authorId}/avatar
	@PatchMapping("/{authorId}/avatar")
	public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID authorId) {
		return this.authorsService.uploadAvatar(authorId, file);
	}
}
