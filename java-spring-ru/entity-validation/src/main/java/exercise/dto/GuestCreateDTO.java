package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {

    @NotBlank
    private String name;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "\\+\\d{9,12}")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}")
    private String clubCard;

    @FutureOrPresent
    private LocalDate cardValidUntil;
}
// END
