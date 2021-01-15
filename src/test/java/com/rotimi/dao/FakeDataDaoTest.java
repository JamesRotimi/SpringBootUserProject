package com.rotimi.dao;


import com.rotimi.model.UserProfile;
import com.rotimi.model.UserProfile.Gender;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FakeDataDaoTest {

  private FakeDataDao fakeDataDao;

  @BeforeEach
  void setUp() {
    fakeDataDao = new FakeDataDao();
  }

  @Test
  void shouldSelectAllUsers() {
    List <UserProfile> userProfiles = fakeDataDao.selectAllUsers();

    UserProfile userProfile = userProfiles.get(0);
    Assertions.assertSame(userProfile.getFirstName(),"Test");
    Assertions.assertSame(userProfile.getLastName(),"Tester");
    Assertions.assertSame(userProfile.getEmailAddress(),"test@test.com");
    Assertions.assertSame(userProfile.getAge(),12);
    Assertions.assertSame(userProfile.getGender(), Gender.MALE);
    Assertions.assertNotNull(userProfile.getUserUid());
  }

  @Test
  void selectUserByUserUid() {
  }

  @Test
  void updateUser() {
  }

  @Test
  void deleteUserByUserUid() {
  }

  @Test
  void insertUser() {
  }
}