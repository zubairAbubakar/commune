package com.zlab.commune.api.user.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    Environment environment;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FeignErrorDecoder(Environment environment){

        this.environment = environment;
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                return new ResponseStatusException(HttpStatus.NOT_FOUND);
                //break;
            case 404: {
                if(methodKey.contains("getAds")){
                    logger.error(response.reason());
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                            environment.getProperty("ads.exceptions.ads-not-found"));
                }
            }
            default:
                return new Exception(response.reason());
        }
        //return null;
    }
}
