package cozzolinoEmanuele.u5w2d2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorPayload {
	@NotBlank(message = "Il nome è un campo obbligatorio")
	@Size(min = 2, max = 30, message = "Il nome deve essere tra i 2 e i 30 caratteri")
	private String name;
	@NotBlank(message = "Il cognome è un campo obbligatorio")
	@Size(min = 2, max = 30, message = "Il cognome deve essere tra i 2 e i 30 caratteri")
	private String surname;
	@NotBlank(message = "L'email è obbligatoria")
	@Email(message = "L'indirizzo email inserito non è nel formato corretto!")
	private String email;
}
