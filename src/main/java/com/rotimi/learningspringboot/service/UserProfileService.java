package com.rotimi.learningspringboot.service;

import com.rotimi.learningspringboot.dao.UserDao;
import com.rotimi.learningspringboot.model.UserProfile;
import com.rotimi.learningspringboot.model.UserProfile.Gender;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

  @Autowired
  private UserDao userDao;

  public UserProfileService(UserDao userDao) {
    this.userDao = userDao;
  }


  public List<UserProfile> selectAllUsers(Optional<String> gender) {
    List<UserProfile> userProfiles = userDao.selectAllUsers();
    if (!gender.isPresent()) {
      return userProfiles;
    } try {
      Gender gender1 = Gender.valueOf(gender.get().toUpperCase(Locale.ROOT));
      return userProfiles.stream()
          .filter(userProfile -> userProfile.getGender().equals(gender1))
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new IllegalArgumentException("Not found",e);
    }

  }

  public Optional<UserProfile> selectUserByUserUid(UUID userUid) {
    return userDao.selectUserByUserUid(userUid);
  }

  public int updateUser(UserProfile userProfile) {
    Optional<UserProfile> optionalUserProfile = selectUserByUserUid(userProfile.getUserUid());
    if (optionalUserProfile.isPresent()) {
      return userDao.updateUser(userProfile);
    }
    return -1;
  }

  public int deleteUserByUserUid(UUID userUid) {
    Optional<UserProfile> optionalUserProfile = selectUserByUserUid(userUid);
    if(optionalUserProfile.isPresent()) {
      return userDao.deleteUserByUserUid(userUid);
    }
    return -1;
  }

  public int insertUser(UserProfile userProfile) {
    UUID userUid = UUID.randomUUID();
    return userDao.insertUser(userUid,UserProfile.newUser(userUid, userProfile));
  }

}


