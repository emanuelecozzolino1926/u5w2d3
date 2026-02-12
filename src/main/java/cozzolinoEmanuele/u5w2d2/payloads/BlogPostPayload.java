package cozzolinoEmanuele.u5w2d2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class BlogPostPayload {
	private String title;
	private String content;
	private int readingTime;
	private UUID authorId;
}
