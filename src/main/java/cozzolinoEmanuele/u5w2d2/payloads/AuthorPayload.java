package cozzolinoEmanuele.u5w2d2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorPayload {
	private String name;
	private String surname;
	private String email;
}
