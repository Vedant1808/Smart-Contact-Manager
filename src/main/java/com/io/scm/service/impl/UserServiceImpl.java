package com.io.scm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.io.scm.entity.User;
import com.io.scm.helper.AppConstants;
import com.io.scm.helper.Helper;
import com.io.scm.helper.ResourceNotFoundException;
import com.io.scm.repository.UserRepo;
import com.io.scm.service.EmailService;
import com.io.scm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  // implement slf4j logger
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EmailService emailService;

  @Override
  public void deleteUser(String id) {
    User user1 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    userRepo.delete(user1);

  }

  @Override
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @Override
  public Optional<User> getUserById(String id) {
    return userRepo.findById(id);
  }

  @Override
  public boolean isUserExist(String userId) {
    User user1 = userRepo.findById(userId).orElse(null);
    return user1 != null ? true : false;
  }

  @Override
  public boolean isUserExistByEmail(String email) {
    User user1 = userRepo.findByEmail(email).orElse(null);
    return user1 != null ? true : false;

  }

  @Override
  public User saveUser(User user) {
    String userId = UUID.randomUUID().toString();
    user.setUserId(userId);
    // put encoded password in db
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    logger.info(user.getProvider().toString());
    // set default role of user
    user.setRoleList(List.of(AppConstants.ROLE_USER));

    String emailToken = UUID.randomUUID().toString();
    String emailLink = Helper.getLinkForEmailVerificatiton(emailToken);
    user.setEmailToken(emailToken);
    User savedUser = userRepo.save(user);
    emailService.sendEmail(savedUser.getEmail(), "Verify Account : Smart Contact Manager ", emailLink);

    return savedUser;
  }

  @Override
  public Optional<User> updateUser(User user) {
    User user1 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    user1.setName(user.getName());
    user1.setEmail(user.getEmail());
    user1.setPassword(user.getPassword());
    user1.setAbout(user.getAbout());
    user1.setPhoneNumber(user.getPhoneNumber());
    user1.setProfilePic(user.getProfilePic());
    user1.setEnabled(user.isEnabled());
    user1.setEmailVerified(user.isEmailVerified());
    user1.setPhoneVerified(user.isPhoneVerified());
    user1.setProvider(user.getProvider());
    user1.setProviderUserId(user.getProviderUserId());

    User saveUser = userRepo.save(user1);

    return Optional.ofNullable(saveUser);

  }

  @Override
  public User getUserByEmail(String email) {
    return userRepo.findByEmail(email).orElse(null);
  }

}
