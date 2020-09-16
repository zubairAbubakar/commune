package com.zlab.commune.api.user.service;

import com.zlab.commune.api.user.dao.AdServiceClient;
import com.zlab.commune.api.user.dao.UserRepository;
import com.zlab.commune.api.user.entity.UserEntity;
import com.zlab.commune.api.user.model.AdResponseModel;
import com.zlab.commune.api.user.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class userServiceImpl implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //RestTemplate restTemplate;
    Environment environment;
    AdServiceClient adServiceClient;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public userServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           Environment environment, AdServiceClient adServiceClient) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.environment = environment;
        //this.restTemplate = restTemplate;
        this.adServiceClient = adServiceClient;

    }

    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity =  modelMapper.map(userDetails, UserEntity.class);

        userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        /*
        String adsUrl = String.format(environment.getProperty("ads.url"), userId);

        ResponseEntity<List<AdResponseModel>> adListResponse = restTemplate.exchange(adsUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AdResponseModel>>() {});

        List<AdResponseModel> adList = adListResponse.getBody();
        */
        logger.info("Before calling ads microservice");
        List<AdResponseModel> adList = adServiceClient.getAds(userId);
        logger.info("After calling ads microservice");

        userDto.setAds(adList);

        return userDto;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(username);

        if(userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true,
                true, true, new ArrayList<>());
    }
}
