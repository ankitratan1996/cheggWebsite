package com.CheggWebsite.controller.restApi;

import com.CheggWebsite.model.request.CreateUserRequestDto;
import com.CheggWebsite.model.request.UpdateUserRequest;
import com.CheggWebsite.sevice.UserService;
import com.CheggWebsite.utilities.ResponseGenerator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ResponseGenerator responseGenerator;
    private static final String USER_DELETED="The given user is deleted";
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAUser(@Valid @RequestBody CreateUserRequestDto userInfo) {
        log.info("Create User Request Received {} ", userInfo);
        return responseGenerator.generateResponse(userService.CreateUser(userInfo),HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAUser(@RequestParam(value = "email") String email) {
        log.info("Get User Request Received {} ", email);
        return responseGenerator.generateResponse(userService.findExistingUserByEmail(email), HttpStatus.OK);
    }

    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAUser(@Valid @RequestBody UpdateUserRequest userInfo) {
        log.info("Update User Request Received {} ", userInfo);
        return responseGenerator.generateResponse(userService.UpdateUser(userInfo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAUser(@RequestParam(value = "email") String email) {
        log.info("Get User Request Received {} ", email);
        userService.deleteUser(email);
        return responseGenerator.generateResponse(USER_DELETED, HttpStatus.OK);
    }
}
