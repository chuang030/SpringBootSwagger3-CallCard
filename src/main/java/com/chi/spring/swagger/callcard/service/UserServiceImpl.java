package com.chi.spring.swagger.callcard.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chi.spring.swagger.callcard.model.User;
import com.chi.spring.swagger.callcard.model.UserDTO;
import com.chi.spring.swagger.callcard.model.UserReviewDTO;
import com.chi.spring.swagger.callcard.model.UserUpdateDTO;
import com.chi.spring.swagger.callcard.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO findById(Long id) {
		UserDTO userDTO = convertToDTO(userRepository.findById(id).orElseThrow());		
		return userDTO;
	}

	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO save(UserDTO userDTO) {		
		User user = convertToEntity(userDTO);
		User saveUser = userRepository.save(user);
		return convertToDTO(saveUser);
	}

	@Override
	public UserDTO update(Long id, UserUpdateDTO userUpdateDTO) {
		User user = userRepository.findById(id).orElseThrow();
		if (userUpdateDTO.getApplicationDate() != null)
			user.setApplicationDate(userUpdateDTO.getApplicationDate());
		if (userUpdateDTO.getIdNumber() != null)
			user.setIdNumber(userUpdateDTO.getIdNumber());
		if (userUpdateDTO.getUsername() != null)
			user.setUsername(userUpdateDTO.getUsername());
		if (userUpdateDTO.getDateOfBirth() != null)
			user.setDateOfBirth(userUpdateDTO.getDateOfBirth());
		if (userUpdateDTO.getNationality() != null)
			user.setNationality(this.convertToLocaleDisplayCountry(userUpdateDTO.getNationality()));
		if (userUpdateDTO.getContactPhoneNumber() != null)
			user.setContactPhoneNumber(userUpdateDTO.getContactPhoneNumber());
		if (userUpdateDTO.getMobilePhoneNumber() != null)
			user.setMobilePhoneNumber(userUpdateDTO.getMobilePhoneNumber());
		if (userUpdateDTO.getPostalCode() != null)
			user.setPostalCode(userUpdateDTO.getPostalCode());
		if (userUpdateDTO.getCorrespondenceAddress() != null)
			user.setCorrespondenceAddress(userUpdateDTO.getCorrespondenceAddress());
		if (userUpdateDTO.getEmail() != null)
			user.setEmail(userUpdateDTO.getEmail());
		if (userUpdateDTO.getIsZodiacNotification() != null)
			user.setIsZodiacNotification(userUpdateDTO.getIsZodiacNotification());
		if (userUpdateDTO.getIsSelfInsurance() != null)
			user.setIsSelfInsurance(userUpdateDTO.getIsSelfInsurance());
		if (userUpdateDTO.getIsApplyForElectronicPolicy() != null)
			user.setIsApplyForElectronicPolicy(userUpdateDTO.getIsApplyForElectronicPolicy());
		if (userUpdateDTO.getIsMobileServiceConsentForm() != null)
			user.setIsMobileServiceConsentForm(userUpdateDTO.getIsMobileServiceConsentForm());
		if (userUpdateDTO.getIsInconvenienceInsurance() != null)
			user.setIsInconvenienceInsurance(userUpdateDTO.getIsInconvenienceInsurance());
		if (userUpdateDTO.getRemarks() != null)
			user.setRemarks(userUpdateDTO.getRemarks());
		
		User updateUser = userRepository.save(user);
		return convertToDTO(updateUser);
	}

	@Override
	public boolean deleteById(Long id) {
		if (!userRepository.existsById(id)) 
			return false;
		
		try {
			userRepository.deleteById(id);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	@Override
	public UserDTO review(Long id, UserReviewDTO userReviewDTO) {
		User user = userRepository.findById(id).orElseThrow();
		user.setMembershipStatus(MembershipStatus.valueOf(userReviewDTO.getMembershipStatus()).getStatusValue());
		User updateUser = userRepository.save(user);
		
		return convertToDTO(updateUser);
	}

	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setApplicationDate(user.getApplicationDate());
		userDTO.setRecordDate(user.getRecordDate());
		userDTO.setMembershipNumber(user.getMembershipNumber());
		userDTO.setSourceOfSubmission(user.getSourceOfSubmission());
		userDTO.setMembershipStatus(user.getMembershipStatus());
		userDTO.setIdNumber(user.getIdNumber());
		userDTO.setUsername(user.getUsername());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setNationality(user.getNationality());
		userDTO.setContactPhoneNumber(user.getContactPhoneNumber());
		userDTO.setMobilePhoneNumber(user.getMobilePhoneNumber());
		userDTO.setPostalCode(user.getPostalCode());
		userDTO.setCorrespondenceAddress(user.getCorrespondenceAddress());
		userDTO.setEmail(user.getEmail());
		userDTO.setIsZodiacNotification(user.getIsZodiacNotification());
		userDTO.setIsSelfInsurance(user.getIsSelfInsurance());
		userDTO.setIsApplyForElectronicPolicy(user.getIsApplyForElectronicPolicy());
		userDTO.setIsMobileServiceConsentForm(user.getIsMobileServiceConsentForm());
		userDTO.setIsInconvenienceInsurance(user.getIsInconvenienceInsurance());
		userDTO.setRemarks(user.getRemarks());
		return userDTO;
	}
	
	private User convertToEntity(UserDTO userDTO) {
		return User.builder()
				.applicationDate(userDTO.getApplicationDate())
				.recordDate(this.getCurrentDate())
				// 通路別：1, 分公司：234
				.membershipNumber("1" + "234" + (LocalDate.now().getYear() - 1911) + this.getSerialNumber(6))
				.sourceOfSubmission(SourceOfSubmission.valueOf(userDTO.getSourceOfSubmission()).getSourceValue())
				.membershipStatus(MembershipStatus.valueOf(userDTO.getMembershipStatus()).getStatusValue())
				.idNumber(userDTO.getIdNumber())
				.username(userDTO.getUsername())
				.dateOfBirth(userDTO.getDateOfBirth())
				.nationality(this.convertToLocaleDisplayCountry(userDTO.getNationality()))
				.contactPhoneNumber(userDTO.getContactPhoneNumber())
				.mobilePhoneNumber(userDTO.getMobilePhoneNumber())
				.postalCode(userDTO.getPostalCode())
				.correspondenceAddress(userDTO.getCorrespondenceAddress())
				.email(userDTO.getEmail())
				.isZodiacNotification(userDTO.getIsZodiacNotification())
				.isSelfInsurance(userDTO.getIsSelfInsurance())
				.isApplyForElectronicPolicy(userDTO.getIsApplyForElectronicPolicy())
				.isMobileServiceConsentForm(userDTO.getIsMobileServiceConsentForm())
				.isInconvenienceInsurance(userDTO.getIsInconvenienceInsurance())
				.remarks(userDTO.getRemarks())
				.build();
	}
	
	private Date getCurrentDate() {
		LocalDate localDate = LocalDate.now();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	private String getSerialNumber(int length) {
		List<User> currentMembers = userRepository.findAll();
		// 以最後1位user id + 1當作流水號，並補0至指定長度
		Long currentMemberCount = 1L;
		if (currentMembers.size() != 0)
			currentMemberCount = currentMembers.get(currentMembers.size() - 1).getId() + 1;
		
		return String.format("%0" + length + "d", Long.valueOf(currentMemberCount));
	}
	
	private String convertToLocaleDisplayCountry(String country) {
		// country為空或TW時回傳中華民國
		if (country.equals("TW") || country.isEmpty()) {
			return "中華民國";
		}
		
		String displeyName = new Locale("", country).getDisplayCountry();
		// 找不到代碼的國家/地區，回傳中華民國
		if (displeyName.equals(country)) {
			return "中華民國";
		}
		
		return displeyName;
	}

//		// 交給Spring boot Validation~
//	private boolean userDTOValidation(UserDTO userDTO) {
//		if (userDTO.getApplicationDate() == null) 
//			return false;
//		if (userDTO.getIdNumber().isEmpty())
//			return false;
//		if (userDTO.getUsername().isEmpty())
//			return false;
//		if (userDTO.getDateOfBirth() == null)
//			return false;
//		if (userDTO.getNationality().isEmpty())
//			return false;
//		if (userDTO.getMobilePhoneNumber().isEmpty())
//			return false;
//		if (userDTO.getPostalCode().isEmpty())
//			return false;
//		if (userDTO.getCorrespondenceAddress().isEmpty())
//			return false;
//		

//		if (userDTO.getIdNumber().length() > 10)
//			return false;
//		if (userDTO.getUsername().length() > 40)
//			return false;
//		if (userDTO.getContactPhoneNumber().length() > 20)
//			return false;
//		if (userDTO.getMobilePhoneNumber().length() > 20)
//			return false;
//		if (userDTO.getPostalCode().length() > 6)
//			return false;
//		if (userDTO.getCorrespondenceAddress().length() > 80)
//			return false;
//		if (userDTO.getEmail().length() > 50)
//			return false;
//		
//		return false;
//	}
}
