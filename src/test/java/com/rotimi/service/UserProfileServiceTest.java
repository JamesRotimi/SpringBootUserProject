package com.rotimi.service;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.google.common.collect.ImmutableList;
import com.rotimi.dao.FakeDataDao;
import com.rotimi.model.UserProfile;
import com.rotimi.model.UserProfile.Gender;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserProfileServiceTest {

  @Mock
  private FakeDataDao fakeDataDaoMock;
  private UserProfileService userProfileService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    fakeDataDaoMock = mock(FakeDataDao.class);
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

  private void assertUserField(UserProfile userProfile) {
    assertThat(userProfile.getAge(),equalTo(12));
    assertThat(userProfile.getFirstName(),equalTo("anna"));
    assertThat(userProfile.getLastName(),equalTo("hannah"));
    assertThat(userProfile.getEmailAddress(),equalTo("anna@gmail.com"));
    assertThat(userProfile.getGender(),equalTo(Gender.FEMALE));
  }

  @Test
  void shouldSelectUserByUserUid() {

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