package com.chi.spring.swagger.callcard.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserUpdateDTO {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "User資料庫ID")
	private Long id;
	
	@PastOrPresent
	@NotNull(message = "Application date cannot be empty.")
	@Schema(description = "申請日期",example = "2025-03-02T06:01:56.001Z")
	private Date applicationDate = null;
	
	@Length(max = 10, min = 1, message = "The length of ID Number is 1 ~ 10.")
	@Schema(description = "身分證統一編號",example = "A123456789")
	private String idNumber = null;
	
	@Length(max = 40, min = 1, message = "The length of username is 1 ~ 40.")
	@Schema(description = "姓名",example = "Username")
	private String username = null;
	
	@PastOrPresent
	@Schema(description = "出生日期",example = "2025-03-02T06:01:56.001Z")
	private Date dateOfBirth = null;
	
	@Schema(description = "國籍",example = "TW")
	private String nationality = null;
	
	@Length(max = 20, min = 1, message = "The length of contact phone number is 1 ~ 20.")
	@Schema(description = "連絡電話",example = "02123456789")
	private String contactPhoneNumber = null;
	
	@Length(max = 20, min = 1, message = "The length of mobile phone number is 1 ~ 20.")
	@Schema(description = "行動電話",example = "09123456789")
	private String mobilePhoneNumber = null;
	
	@Length(max = 6, min = 1, message = "The length of postalCode is 1 ~ 6.")
	@Schema(description = "郵遞區號",example = "11111")
	private String postalCode = null;
	
	@Length(max = 80, min = 1, message = "The length of correspondence address is 1 ~ 80.")
	@Schema(description = "通訊地址",example = "台北市士林區")
	private String correspondenceAddress = null;
	
	@Length(max = 50, message = "The maximum length of email is 50.")
	@Email(message = "Email format is incorrect.")
	@Schema(description = "E-mail",example = "test@test.com")
	private String email = null;
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "生肖通知",example = "Y")
	private String isZodiacNotification = null;
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "自保件",example = "Y")
	private String isSelfInsurance = null;
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "申請電子保單",example = "Y")
	private String isApplyForElectronicPolicy = null;
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "行動服務確認同意書",example = "Y")
	private String isMobileServiceConsentForm = null;
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "不便險",example = "Y")
	private String isInconvenienceInsurance = null;
	
	@Length(max = 300, message = "The maximum length of remarks is 300.")
	@Schema(description = "備註",example = "加入備註")
	private String remarks = null;
}
