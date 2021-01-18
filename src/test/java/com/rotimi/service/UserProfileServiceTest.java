package com.rotimi.service;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.google.common.collect.ImmutableList;
import com.rotimi.dao.FakeDataDao;
import com.rotimi.model.UserProfile;
import com.rotimi.model.UserProfile.Gender;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserProfileServiceTest {

  @Mock
  private FakeDataDao fakeDataDaoMock;
  private UserProfileService userProfileService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    userProfileService = new UserProfileService(fakeDataDaoMock);
  }

  @Test
  void shouldSelectAllUsers() {

    UUID AnnaUid = UUID.randomUUID();
    UserProfile userProfileAnna = new UserProfile(AnnaUid,"anna",
        "hannah", 12,"anna@gmail.com", Gender.FEMALE);

    ImmutableList<UserProfile> userProfiles = new ImmutableList.Builder<UserProfile>()
        .add(userProfileAnna)
        .build();

    given(fakeDataDaoMock.selectAllUsers()).willReturn(userProfiles);

    List<UserProfile> allUserProfiles = userProfileService.selectAllUsers();

    assertThat(allUserProfiles.get(0),equalTo(userProfileAnna));

    UserProfile userProfile = allUserProfiles.get(0);

    assertUserField(userProfile);

  }


  @Test
  void shouldSelectUserByUserUid() {

    UUID AnnaUid = UUID.randomUUID();
    UserProfile userProfileAnna = new UserProfile(AnnaUid,"anna",
        "hannah", 12,"anna@gmail.com", Gender.FEMALE);

    given(fakeDataDaoMock.selectUserByUserUid(AnnaUid)).willReturn(
        java.util.Optional.of(userProfileAnna));

    Optional<UserProfile> userProfileOptional = userProfileService.selectUserByUserUid(AnnaUid);

    UserProfile userProfile = userProfileOptional.get();

    assertUserField(userProfile);
    assertThat(userProfileOptional.isPresent(),equalTo(true));
    assertThat(fakeDataDaoMock.selectUserByUserUid(AnnaUid).get().getAge(),equalTo(12));
    verify(fakeDataDaoMock,times(2)).selectUserByUserUid(AnnaUid);
  }

  @Test
  void shouldUpdateUser() {

    UUID AnnaUid = UUID.randomUUID();
    UserProfile userProfileAnna = new UserProfile(AnnaUid,"anna",
        "hannah", 12,"anna@gmail.com", Gender.FEMALE);

    given(fakeDataDaoMock.selectUserByUserUid(AnnaUid)).willReturn(
        java.util.Optional.of(userProfileAnna));
    given(fakeDataDaoMock.updateUser(userProfileAnna)).willReturn(1);

    ArgumentCaptor<UserProfile> captor = ArgumentCaptor.forClass(UserProfile.class);

    int updateUser = userProfileService.updateUser(userProfileAnna);

    verify(fakeDataDaoMock).selectUserByUserUid(AnnaUid);
    verify(fakeDataDaoMock).updateUser(captor.capture());

    UserProfile value = captor.getValue();
    assertUserField(value);
    assertThat(updateUser,equalTo(1));

  }

  @Test
  void shouldDeleteUserByUserUid() {

    UUID AnnaUid = UUID.randomUUID();
    UserProfile userProfileAnna = new UserProfile(AnnaUid,"anna",
        "hannah", 12,"anna@gmail.com", Gender.FEMALE);

    given(fakeDataDaoMock.selectUserByUserUid(AnnaUid)).willReturn(
        java.util.Optional.of(userProfileAnna));
    given(fakeDataDaoMock.deleteUserByUserUid(AnnaUid)).willReturn(1);

    ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);

    int deleteUserByUserUid = userProfileService.deleteUserByUserUid(AnnaUid);

    verify(fakeDataDaoMock).selectUserByUserUid(AnnaUid);
    verify(fakeDataDaoMock).deleteUserByUserUid(captor.capture());

    assertThat(deleteUserByUserUid,equalTo(1));

    UUID capturedAnnaUuid = captor.getValue();
    assertThat(capturedAnnaUuid,equalTo(AnnaUid));

  }

  @Test
  void ShouldInsertUser() {
    UUID AnnaUid = UUID.randomUUID();
    UserProfile userProfileAnna = new UserProfile(null,"anna",
        "hannah", 12,"anna@gmail.com", Gender.FEMALE);

    given(fakeDataDaoMock.insertUser(ArgumentMatchers.any(UUID.class),eq(userProfileAnna))).willReturn(1);

    ArgumentCaptor<UserProfile> captor = ArgumentCaptor.forClass(UserProfile.class);

    int insertUser = userProfileService.insertUser(userProfileAnna);

    verify(fakeDataDaoMock).insertUser(ArgumentMatchers.any(UUID.class),captor.capture());

    UserProfile value = captor.getValue();
    assertUserField(value);
    assertThat(insertUser,equalTo(1));
    assertUserField(userProfileAnna);

  }

  private void assertUserField(UserProfile userProfile) {
    assertThat(userProfile.getAge(),equalTo(12));
    assertThat(userProfile.getFirstName(),equalTo("anna"));
    assertThat(userProfile.getLastName(),equalTo("hannah"));
    assertThat(userProfile.getEmailAddress(),equalTo("anna@gmail.com"));
    assertThat(userProfile.getGender(),equalTo(Gender.FEMALE));
    assertNotNull(userProfile.getUserUid());
    assertThat(userProfile.getUserUid(),instanceOf(UUID.class));
  }

}