package com.bike.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bike.dao.AddressDao;
import com.bike.dao.UserDao;
import com.bike.dto.EditAdressDTO;
import com.bike.dto.EditProfileDTO;
import com.bike.dto.ResponseUserDTO;
import com.bike.dto.SignInDTO;
import com.bike.dto.SignUpDTO;
import com.bike.entities.Address;
import com.bike.entities.Role;
import com.bike.entities.User;
import com.bike.exceptions.ResourceNotFoundException;
import com.bike.exceptions.UserAlreadyExistsException;

@org.springframework.transaction.annotation.Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AddressDao addressDao;
	
	@Value("$spring.mail.username")
	private String senderMail;
	
	@Override
	public ResponseEntity<?> SignInUser(SignInDTO signin) {
		User user = userDao.findByEmailAndPassword(signin.getEmail(), signin.getPassword());
		if (user == null ) {
			throw new ResourceNotFoundException("Invalid Credentials!! Please try again...");
		}
		else if(user.isBlockStatus()) {
			String blockMessage = "Sorry, your account has been blocked. Please contact us for further details.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(blockMessage);
		}
		else if(user.isDeleteStatus()) {
			String deleteMessage = "Sorry, your account has been deleted.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body(deleteMessage);
		}
		else 
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.map(user, ResponseUserDTO.class));
	}

	@Override
	public ResponseEntity<?> registerUser(int role, @Valid EditProfileDTO signUp) {
		if(userDao.findByEmail(signUp.getEmail()) == null) {
			Role role1 = Role.values()[role];
			SignUpDTO dto = new SignUpDTO(signUp.getFirstName(), signUp.getLastName(), signUp.getEmail(), signUp.getPassword(), signUp.getMobile(), role1);
			User user = userDao.save(mapper.map(dto, User.class));
			if (user.getId() != 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body(user);
			}
			else {
				throw new UserAlreadyExistsException("User does not exist but some sql error occurred.");
			}
		}  
		else {
			throw new UserAlreadyExistsException("User Already Exist, Please login or try with another email");
		}
	}
	
	@Override
	public EditProfileDTO EditProfileService(@NotNull Long id) {
		User user = userDao.findById(id).get();
		if (user != null) {
			return mapper.map(user, EditProfileDTO.class);
		}
		else {
			throw new ResourceNotFoundException("Invalid Id provided. No such customer Exists.");
		}
	}

	@Override
	public ResponseEntity<?> UpdateProfileService(@NotNull EditProfileDTO editDTO, Long id) {
		User user = userDao.findById(id).get();
		user.setFirstName(editDTO.getFirstName());
		user.setLastName(editDTO.getLastName());
		user.setMobile(editDTO.getMobile());
		user.setEmail(editDTO.getEmail());
		user.setPassword(editDTO.getPassword());
		if (userDao.save(user) != null) {
			return ResponseEntity.status(HttpStatus.OK).body(mapper.map(user, EditProfileDTO.class));
		}
		else {
			throw new UserAlreadyExistsException("Some sql error happened while updation.");
		}
	}

	@Override
	public EditAdressDTO EditAddressService(Long id) {
		User user = userDao.findById(id).get();
		System.out.println("In Service method of UserService. The value of user is " + user);
		List<Address> addresses = addressDao.findByThisUser(user);
		if (addresses != null) { 
			return mapper.map(addresses.get(0), EditAdressDTO.class);
		}
		else {
			throw new ResourceNotFoundException("Invalid Id provided. No such customer Exists.");
		}
	}

	@Override
	public ResponseEntity<?> updateAddressService(Long id, EditAdressDTO addrDTO) {
		List<Address> addresses = addressDao.findByThisUser(userDao.findById(id).get());
		if (userDao.findById(id) != null) {
			if (addresses.isEmpty()) {
				Address address = mapper.map(addrDTO, Address.class);
				User user = userDao.findById(id).get();
				user.addAddress(address);
				Address detachedAddress = addressDao.save(address);
				return ResponseEntity.status(HttpStatus.OK).body(mapper.map(detachedAddress, EditAdressDTO.class));
			}
			else {	
				System.out.println("In updateService method.");
				Address address = addresses.get(0);
				address.setHouseNo(addrDTO.getHouseNo());
				address.setApartmentName(addrDTO.getApartmentName());
				address.setArea(addrDTO.getArea());
				address.setCity(addrDTO.getCity());
				address.setPincode(addrDTO.getPincode());
				address.setState(addrDTO.getState());
				address.setStreet(addrDTO.getStreet());
				Address detachedAddress = addressDao.save(address);
				return ResponseEntity.status(HttpStatus.OK).body(mapper.map(detachedAddress, EditAdressDTO.class));
			}
		}
		else {
			throw new ResourceNotFoundException("Invalid Id!! No such user exists.");
		}
	}

	@Override
	public ResponseEntity<String> forgotPasswordService(String email) {
		int leftLimit = 48;
	    int rightLimit = 122;
	    int targetStringLength = 10;
	    Random random = new Random();
	    String otp = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	     
	    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	    simpleMailMessage.setFrom(senderMail);
	    simpleMailMessage.setSubject("OTP for Password Reset");
	    String message = "Your One Time Password is " + otp;
	    simpleMailMessage.setText(message);
	    simpleMailMessage.setTo(email);
	    
		javaMailSender.send(simpleMailMessage);
		userDao.findByEmail(email).setExtraStringColumnOne(otp);
		String message2 = "OTP sent and also saved in Database.";
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(message2);
	}

	@Override
	public ResponseEntity<String> verifyOTPService(String otp, String email) {
		User user = userDao.findByEmail(email);
		if (otp.equals(user.getExtraStringColumnOne())){
			String message = "String matched successfully.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		else {
			throw new ResourceNotFoundException("No such user exists (otp validation failed)");
		}
	}

	@Override
	public ResponseEntity<String> resetPasswordService(SignInDTO signInDTO) {
		User user = userDao.findByEmail(signInDTO.getEmail());
		if (user != null) {
			user.setPassword(signInDTO.getPassword());
			
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		    simpleMailMessage.setFrom(senderMail);
		    simpleMailMessage.setSubject("Account Security");
		    String message = "Your Password has been reset successfully. This was done at " + LocalDateTime.now() + ". PLEASE INFORM US IF THIS WAS NOT YOU!";
		    simpleMailMessage.setText(message);
		    simpleMailMessage.setTo(signInDTO.getEmail());
		    
			javaMailSender.send(simpleMailMessage);
			String message2 = "Password reset successfully.";
			return ResponseEntity.status(HttpStatus.OK).body(message2);
		}
		else {
			throw new ResourceNotFoundException("No such user exists.");
		}
				
				
	}
	

	
	
}
