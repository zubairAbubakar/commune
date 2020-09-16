package com.zlab.commune.api.user.dao;

import com.zlab.commune.api.user.model.AdResponseModel;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "ad-service", fallbackFactory = AdsFallbackFactory.class)
public interface AdServiceClient {

    @GetMapping("/users/{userId}/ads")
    public List<AdResponseModel> getAds(@PathVariable String userId);
}

@Component
class AdsFallbackFactory implements FallbackFactory<AdServiceClient> {

    @Override
    public AdServiceClient create(Throwable cause) {

        return new AdsServiceClientFallBack(cause);
    }
}

class AdsServiceClientFallBack implements AdServiceClient{

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Throwable cause;

    public AdsServiceClientFallBack(Throwable cause){
        this.cause = cause;
    }

    @Override
    public List<AdResponseModel> getAds(String userId) {

        if(cause instanceof FeignException && ((FeignException) cause).status() == 404 ){
            logger.error("404 error took place when getAds was called with userId: "
                    +userId+". Error Message: "
                    +cause.getLocalizedMessage());
        }
        else {
            logger.error("Other error took place: "+cause.getLocalizedMessage());
        }

        return new ArrayList<>();
    }
}

