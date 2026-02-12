package cozzolinoEmanuele.u5w2d2.services;

import cozzolinoEmanuele.u5w2d2.entities.Author;
import cozzolinoEmanuele.u5w2d2.exceptions.BadRequestException;
import cozzolinoEmanuele.u5w2d2.exceptions.NotFoundException;
import cozzolinoEmanuele.u5w2d2.payloads.AuthorPayload;
import cozzolinoEmanuele.u5w2d2.repositories.AuthorsRepository;
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
public class AuthorsService {
	private final AuthorsRepository authorsRepository;

	@Autowired
	public AuthorsService(AuthorsRepository authorsRepository) {
		this.authorsRepository = authorsRepository;
	}

	public Author save(AuthorPayload payload) {
		this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
			throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");
		});

		Author newAuthor = new Author(payload.getName(), payload.getSurname(), payload.getEmail());
		newAuthor.setAvatarURL("https://ui-avatars.com/api?name=" + payload.getName() + "+" + payload.getSurname());

		Author savedAuthor = this.authorsRepository.save(newAuthor);

		log.info("L'autore con id " + savedAuthor.getId() + " è stato salvato correttamente!");

		return savedAuthor;
	}

	public Page<Author> findAll(int page, int size, String orderBy, String sortCriteria) {
		if (size > 100 || size < 0) size = 10;
		if (page < 0) page = 0;
		Pageable pageable = PageRequest.of(page, size, sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
		return this.authorsRepository.findAll(pageable);
	}

	public Author findById(UUID authorId) {
		return this.authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
	}

	public Author findByIdAndUpdate(UUID authorId, AuthorPayload payload) {
		Author found = this.findById(authorId);

		if (!found.getEmail().equals(payload.getEmail())) {
			this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
				throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");
			});
		}

		found.setName(payload.getName());
		found.setSurname(payload.getSurname());
		found.setEmail(payload.getEmail());
		found.setAvatarURL("https://ui-avatars.com/api?name=" + payload.getName() + "+" + payload.getSurname());

		Author modifiedAuthor = this.authorsRepository.save(found);

		log.info("L'autore con id " + modifiedAuthor.getId() + " è stato modificato correttamente");

		return modifiedAuthor;
	}

	public void findByIdAndDelete(UUID authorId) {
		Author found = this.findById(authorId);
		this.authorsRepository.delete(found);
	}
}
