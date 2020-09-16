package com.zlab.commune.api.ads.controller;

import com.google.common.reflect.TypeToken;
import com.zlab.commune.api.ads.entity.AdEntity;
import com.zlab.commune.api.ads.model.AdResponseModel;
import com.zlab.commune.api.ads.service.AdService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/ads")
public class AdController {

    @Autowired
    AdService adService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<AdResponseModel> userAlbums(@PathVariable String userId) {

        List<AdResponseModel> adResponse = new ArrayList<>();
        List<AdEntity> ads = adService.getAds(userId);

        if(ads == null || ads.isEmpty())
        {
            return adResponse;
        }

        Type listType = new TypeToken<List<AdResponseModel>>(){}.getType();

        adResponse = new ModelMapper().map(ads, listType);
        logger.info("Returning " + adResponse.size() + " ads");
        return adResponse;

    }
}
