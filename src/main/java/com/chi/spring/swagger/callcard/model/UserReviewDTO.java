package com.chi.spring.swagger.callcard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserReviewDTO {

	@NotNull(message = "Membership status cannot be empty.")
	@Pattern(regexp = "VALID|INVALID|CANCELLED|DISABLED|APPROVED|UNDER_REVIEW|NOT_APPROVED",
			 message = "Membership Status Type Error.")
	@Schema(description = "會員編號",example = "VALID")
	private String membershipStatus = "VALID"; // 有效
}
