package com.rotimi.learningspringboot.controller;


import com.rotimi.learningspringboot.model.UserProfile;
import com.rotimi.learningspringboot.service.UserProfileService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
  public List<UserProfile> getUserProfile(@QueryParam("gender") String gender) {
    return userProfileService.selectAllUsers(Optional.ofNullable(gender));
  }

 @RequestMapping
     (method = RequestMethod.GET,
     path = "{userUId}")
  public ResponseEntity<?> getUserProfileByUserId(@PathVariable UUID userUId){
    Optional<UserProfile> UserProfileOpt = userProfileService.selectUserByUserUid(userUId);
    if (UserProfileOpt.isPresent()) {
      return  ResponseEntity.ok(UserProfileOpt.get());
    }
   return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("user not found"));
  }

  @RequestMapping
      (method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_JSON)
  public ResponseEntity<Integer> createUserProfile(@RequestBody UserProfile createUserProfile){
    int updateUser1 = userProfileService.insertUser(createUserProfile);
    return getIntegerResponseEntity(updateUser1);
  }

  @RequestMapping
      (method = RequestMethod.PUT,
          consumes = MediaType.APPLICATION_JSON)
  public ResponseEntity<Integer> updateUser(@RequestBody UserProfile updateUser){
    int updateUser1 = userProfileService.updateUser(updateUser);
    return getIntegerResponseEntity(updateUser1);
  }

  @RequestMapping
      (method = RequestMethod.DELETE,
       path = "{userUId}")
  public ResponseEntity<Integer> deleteUserProfile(@PathVariable UUID userUId){
    int deleteUser1 = userProfileService.deleteUserByUserUid(userUId);
    return getIntegerResponseEntity(deleteUser1);
  }


  private ResponseEntity<Integer> getIntegerResponseEntity(int updateUser1) {
    if (updateUser1 == 1) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }

  class ErrorMessage {

    private String message;

    public ErrorMessage(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

//  @GetMapping
//      (path = "{userUId}")
//  public UserProfile getUserProfileByUserId(@PathVariable UUID userUId){
//    return userProfileService.selectUserByUserUid(userUId).get();
//
//  }

}
