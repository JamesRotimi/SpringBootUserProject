package com.rotimi.service;

import com.rotimi.dao.UserDao;
import com.rotimi.model.UserProfile;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

  @Autowired
  private UserDao userDao;

  public UserProfileService(UserDao userDao) {
    this.userDao = userDao;
  }


  public List<UserProfile> selectAllUsers() {
    return  userDao.selectAllUsers();
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
    userProfile.setUserUid(userUid);
    return userDao.insertUser(userUid,userProfile);
  }

}


