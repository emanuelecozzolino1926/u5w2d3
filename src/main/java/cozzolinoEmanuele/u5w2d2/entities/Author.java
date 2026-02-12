package cozzolinoEmanuele.u5w2d2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Author {
	@Id
	@GeneratedValue
	@Setter(AccessLevel.NONE)
	private UUID id;

	private String name;
	private String surname;
	private String email;

	@Column(name = "avatar_url")
	private String avatarURL;

	public Author(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
}
