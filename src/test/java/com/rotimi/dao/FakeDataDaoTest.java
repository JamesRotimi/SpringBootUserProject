package com.rotimi.dao;


import com.rotimi.model.UserProfile;
import com.rotimi.model.UserProfile.Gender;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
  void shouldSelectUserByUserUid() {
    UUID AnnaUid = UUID.randomUUID();
    UserProfile userProfile = new UserProfile(AnnaUid,"anna",
        "hannah", 12,"anna@gmail.com",Gender.FEMALE);
        fakeDataDao.insertUser(AnnaUid,userProfile);
        assertThat(fakeDataDao.selectAllUsers().size(),equalTo(2));
    Optional<UserProfile> AnnaOptional = fakeDataDao.selectUserByUserUid(AnnaUid);

    assertThat(fakeDataDao.selectUserByUserUid(AnnaUid).isPresent(),equalTo(true));
    assertThat(fakeDataDao.selectUserByUserUid(AnnaUid),equalTo(AnnaOptional));
    assertThat(AnnaOptional.get(),equalTo(userProfile));
  }

  @Test
  void shouldNotSelectUserByRandomUserUid() {
    Optional<UserProfile> userProfile = fakeDataDao.selectUserByUserUid(UUID.randomUUID());
    assertThat(userProfile.isPresent(),equalTo(false));
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