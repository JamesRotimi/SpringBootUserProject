package com.rotimi.dao;

import com.rotimi.model.UserProfile;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

  List<UserProfile> selectAllUsers();
  Optional <UserProfile> selectUserByUserUid(UUID userUid);
  int updateUser(UserProfile userProfile);
  int deleteUserByUserUid(UUID userUid);
  int insertUser(UUID userUid, UserProfile userProfile);


}

