package com.rotimi.learningspringboot.controller;


import com.rotimi.learningspringboot.model.UserProfile;
import com.rotimi.learningspringboot.service.UserProfileService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

  private UserProfileService userProfileService;

  @Autowired
  public UserController(UserProfileService userProfileService){
    this.userProfileService = userProfileService;
  }

 @GetMapping
  public List<UserProfile> getUserProfile() {
    return userProfileService.selectAllUsers();
  }

 @RequestMapping
     (method = RequestMethod.GET,
     path = "{userUId}")
  public ResponseEntity<?> getUserProfileByUserId(@PathVariable UUID userUId){
    Optional<UserProfile> UserProfileOpt = userProfileService.selectUserByUserUid(userUId);
    if (UserProfileOpt.isPresent()) {
      return  ResponseEntity.ok(UserProfileOpt.get());
    }
   return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user" + userUId + "was not found");
  }

//  @GetMapping
//      (path = "{userUId}")
//  public UserProfile getUserProfileByUserId(@PathVariable UUID userUId){
//    return userProfileService.selectUserByUserUid(userUId).get();
//
//  }

}
