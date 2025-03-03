package com.chi.spring.swagger.callcard.model;


import java.util.Date;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class UserDTO {
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "User資料庫ID")
	private Long id;
	
	@PastOrPresent
	@NotNull(message = "Application date cannot be empty.")
	@Schema(description = "申請日期",example = "2025-03-02T06:01:56.001Z")
	private Date applicationDate = null;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "建檔日期")
	private Date recordDate = null;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "會員編號")
	private String membershipNumber = "";
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "進件來源")
	private String sourceOfSubmission = "PAPER"; // 紙本
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "會員狀態")
	private String membershipStatus = "VALID"; // 有效
	
	@NotEmpty(message = "ID Number cannot be empty.")
	@Length(max = 10, message = "The maximum length of ID Number is 10.")
	@Schema(description = "身分證統一編號",example = "A123456789")
	private String idNumber = "";
	
	@NotEmpty(message = "Username cannot be empty.")
	@Length(max = 40, message = "The maximum length of username is 40.")
	@Schema(description = "姓名",example = "Username")
	private String username = "";
	
	@PastOrPresent
	@NotNull(message = "Date of Birth cannot be empty.")
	@Schema(description = "出生日期",example = "2025-03-02T06:01:56.001Z")
	private Date dateOfBirth = null;
	
	@NotEmpty(message = "Nationality cannot be empty.")
	@Schema(description = "國籍",example = "TW")
	private String nationality = "TW";
	
	@Length(max = 20, message = "The maximum length of contact phone number is 20.")
	@Schema(description = "連絡電話",example = "02123456789")
	private String contactPhoneNumber = "";
	
	@NotEmpty(message = "Mobile phone number cannot be empty.")
	@Length(max = 20, message = "The maximum length of mobile phone number is 20.")
	@Schema(description = "行動電話",example = "09123456789")
	private String mobilePhoneNumber = "";
	
	@NotEmpty(message = "Postal code cannot be empty.")
	@Length(max = 6, message = "The maximum length of postalCode is 6.")
	@Schema(description = "郵遞區號",example = "11111")
	private String postalCode = "";
	
	@NotEmpty(message = "Correspondence address cannot be empty.")
	@Length(max = 80, message = "The maximum length of correspondence address is 80.")
	@Schema(description = "通訊地址",example = "台北市士林區")
	private String correspondenceAddress = "";
	
	@Length(max = 50, message = "The maximum length of email is 50.")
	@Email(message = "Email format is incorrect.")
	@Schema(description = "E-mail",example = "test@test.com")
	private String email = "";
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "生肖通知",example = "Y")
	private String isZodiacNotification = "";
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "自保件",example = "Y")
	private String isSelfInsurance = "";
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "申請電子保單",example = "Y")
	private String isApplyForElectronicPolicy = "";
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "行動服務確認同意書",example = "Y")
	private String isMobileServiceConsentForm = "";
	
	@Length(max = 1)
	@Pattern(regexp = "^$|[NY]", message = "Accept only [Y/N].")
	@Schema(description = "不便險",example = "Y")
	private String isInconvenienceInsurance = "";
	
	@Length(max = 300, message = "The maximum length of remarks is 300.")
	@Schema(description = "備註",example = "加入備註")
	private String remarks = "";
	
//	private String channelCategory = null;
//	
//	private String branchOffice = null;
	
}
