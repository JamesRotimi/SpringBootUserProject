package com.rotimi.model;

import java.util.UUID;

public class UserProfile {

  private  UUID userUid;
  private final String firstName;
  private final String lastName;
  private final Integer age;
  private final String emailAddress;
  private final Gender gender;



  public UserProfile(UUID userUid, String firstName, String lastName, int age,
      String emailAddress, Gender gender) {
    this.userUid = userUid;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.emailAddress = emailAddress;
    this.gender = gender;
  }

  public void setUserUid(UUID userUid) {
    this.userUid = userUid;
  }

  public enum Gender {
    MALE,
    FEMALE
  }

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
