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
    UUID peterUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
    UserProfile userProfilePeter = new UserProfile(peterUid,"peter",
        "john", 122,"peter@gmail.com",Gender.FEMALE);
    fakeDataDao.updateUser(userProfilePeter);

    Optional<UserProfile> user = fakeDataDao.selectUserByUserUid(peterUid);
    assertThat(fakeDataDao.selectUserByUserUid(peterUid).isPresent(),equalTo(true));
    assertThat(user.get(),equalTo(userProfilePeter));


  }

  @Test
  void deleteUserByUserUid() {
    UUID terUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
    UserProfile userProfilePeter = new UserProfile(terUid,"peter",
        "john", 122,"peter@gmail.com",Gender.FEMALE);
    fakeDataDao.insertUser(terUid,userProfilePeter);

    Optional<UserProfile> user = fakeDataDao.selectUserByUserUid(terUid);
    assertThat(fakeDataDao.selectUserByUserUid(terUid).isPresent(),equalTo(true));
    int deleteUserByUserUid = fakeDataDao.deleteUserByUserUid(terUid);
    assertThat(fakeDataDao.selectUserByUserUid(terUid).isEmpty(),equalTo(true));
    assertThat(fakeDataDao.selectAllUsers().isEmpty(),equalTo(true));
  }

  @Test
  void insertUser() {
    UUID iterUid = UUID.randomUUID();
    UserProfile userProfilePeter = new UserProfile(iterUid,"wayne",
        "james", 122,"james@gmail.com",Gender.FEMALE);
    fakeDataDao.insertUser(iterUid,userProfilePeter);

    Optional<UserProfile> user = fakeDataDao.selectUserByUserUid(iterUid);
    assertThat(fakeDataDao.selectUserByUserUid(iterUid).isPresent(),equalTo(true));
    assertThat(fakeDataDao.selectAllUsers().isEmpty(),equalTo(false));
    assertThat(fakeDataDao.selectAllUsers().size(),equalTo(2));
  }
}