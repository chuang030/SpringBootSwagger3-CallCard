package com.chi.spring.swagger.callcard.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "call_card")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date applicationDate;

	@Temporal(TemporalType.DATE)
	private Date recordDate;

	@Column(length = 13)
	private String membershipNumber;

	private String sourceOfSubmission;

	private String membershipStatus;

	@Column(nullable = false, length = 10)
	private String idNumber;

	@Column(nullable = false, length = 40)
	private String username;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(nullable = false, length = 20)
	private String nationality;

	@Column(length = 20)
	private String contactPhoneNumber;

	@Column(nullable = false, length = 20)
	private String mobilePhoneNumber;

	@Column(nullable = false, length = 6)
	private String postalCode;

	@Column(nullable = false, length = 80)
	private String correspondenceAddress;

	@Column(length = 50)
	private String email;

	@Column(length = 1)
	private String isZodiacNotification;

	@Column(length = 1)
	private String isSelfInsurance;

	@Column(length = 1)
	private String isApplyForElectronicPolicy;

	@Column(length = 1)
	private String isMobileServiceConsentForm;

	@Column(length = 1)
	private String isInconvenienceInsurance;

	@Column(length = 300)
	private String remarks;

}
