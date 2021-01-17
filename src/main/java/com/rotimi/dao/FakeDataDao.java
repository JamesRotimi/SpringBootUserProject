package com.rotimi.dao;


import com.rotimi.model.UserProfile;
import com.rotimi.model.UserProfile.Gender;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class FakeDataDao implements UserDao {

  private Map<UUID, UserProfile> database;

   public FakeDataDao () {
     database = new HashMap<>();
     UUID key = UUID.randomUUID();
     database.put(key, new UserProfile(key, "Test", "Tester", 12, "test@test.com", Gender.MALE));
   }

  @Override
  public List<UserProfile> selectAllUsers() {
    return new ArrayList<>(database.values());
  }

  @Override
  public Optional<UserProfile> selectUserByUserUid(UUID userUid) {
    return Optional.ofNullable(database.get(userUid));
  }

  @Override
  public int updateUser(UserProfile userProfile) {
    database.put(userProfile.getUserUid(), userProfile);
    return 1;
  }

  @Override
  public int deleteUserByUserUid(UUID userUid) {
    database.remove(userUid);
    return 1;
  }

  @Override
  public int insertUser(UUID userUid, UserProfile userProfile) {
    database.put(userUid,userProfile);
    return 1;
  }

}
