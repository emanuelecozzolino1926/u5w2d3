package cozzolinoEmanuele.u5w2d2.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class BlogPostPayload {
	@NotBlank(message = "Il titolo è un campo obbligatorio")
	@Size(min = 2, max = 100, message = "Il titolo deve essere tra i 2 e i 100 caratteri")
	private String title;
	@NotBlank(message = "Il contenuto è un campo obbligatorio")
	@Size(min = 10, message = "Il contenuto deve avere almeno 10 caratteri")
	private String content;
	@Min(value = 1, message = "Il tempo di lettura deve essere almeno 1 minuto")
	private int readingTime;
	@NotNull(message = "L'id dell'autore è obbligatorio")
	private UUID authorId;
}
