package com.zlab.commune.api.user.controller;

import com.zlab.commune.api.user.entity.UserEntity;
import com.zlab.commune.api.user.model.AdResponseModel;
import com.zlab.commune.api.user.model.CreateUserRequestModel;
import com.zlab.commune.api.user.model.CreateUserResponseModel;
import com.zlab.commune.api.user.model.UserResponseModel;
import com.zlab.commune.api.user.service.UserService;
import com.zlab.commune.api.user.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    UserService userService;

    @GetMapping("/status/check")
    public String status(){

        return "Working on port: " +env.getProperty("local.server.port") +", with token = "
                +env.getProperty("token.secret");

    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto =  modelMapper.map(userDetails, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);

        CreateUserResponseModel responseModel = modelMapper.map(createdUser, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }


    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {

        UserDto userDto = userService.getUserByUserId(userId);
        UserResponseModel response = new ModelMapper().map(userDto, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
