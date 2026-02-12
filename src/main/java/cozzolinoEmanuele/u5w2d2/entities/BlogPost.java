package cozzolinoEmanuele.u5w2d2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogPost {
	@Id
	@GeneratedValue
	@Setter(AccessLevel.NONE)
	private UUID id;

	private String title;
	private String content;

	@Column(name = "reading_time")
	private int readingTime;

	@Column(name = "cover_url")
	private String coverURL;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

	public BlogPost(String title, String content, int readingTime, Author author) {
		this.title = title;
		this.content = content;
		this.readingTime = readingTime;
		this.author = author;
	}
}
