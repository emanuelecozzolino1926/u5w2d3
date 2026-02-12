package cozzolinoEmanuele.u5w2d2.repositories;

import cozzolinoEmanuele.u5w2d2.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, UUID> {
	Optional<Author> findByEmail(String email);
}
