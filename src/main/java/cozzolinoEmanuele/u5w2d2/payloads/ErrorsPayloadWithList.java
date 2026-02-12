package cozzolinoEmanuele.u5w2d2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorsPayloadWithList {
	private String message;
	private LocalDateTime timestamp;
	private List<String> errors;
}
