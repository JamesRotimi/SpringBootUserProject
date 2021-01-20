package com.rotimi.learningspringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class UserProfile {

  private final UUID userUid;
  private final String firstName;
  private final String lastName;
  private final Integer age;
  private final String emailAddress;
  private final Gender gender;


  public UserProfile(@JsonProperty ("userUid") UUID userUid,
      @JsonProperty ("firstName") String firstName,
      @JsonProperty ("lastName") String lastName,
      @JsonProperty ("age") int age,
      @JsonProperty ("email") String emailAddress,
      @JsonProperty ("gender") Gender gender)
  {
    this.userUid = userUid;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.emailAddress = emailAddress;
    this.gender = gender;
  }


  public enum Gender {
    MALE,
    FEMALE
  }
 @JsonProperty("id")
  public UUID getUserUid() {
    return userUid;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Integer getAge() {
    return age;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public Gender getGender() {
    return gender;
  }

  public static UserProfile newUser(UUID userUid, UserProfile user) {
    return new UserProfile(userUid, user.getFirstName(),
        user.getLastName(), user.getAge(), user.getEmailAddress(),user.gender );
  }

  @Override
  public String toString() {
    return "UserProfile{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", age=" + age +
        ", emailAddress='" + emailAddress + '\'' +
        ", gender=" + gender +
        ", userUid=" + userUid +
        '}';
  }


}
