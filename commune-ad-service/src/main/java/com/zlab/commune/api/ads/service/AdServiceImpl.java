package com.zlab.commune.api.ads.service;

import com.zlab.commune.api.ads.entity.AdEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {


    @Override
    public List<AdEntity> getAds(String userId) {

        List<AdEntity> ads = new ArrayList<>();

        AdEntity ad1 = new AdEntity();
        AdEntity ad2 = new AdEntity();
        ad1.setId(1l);
        ad1.setAdId("adId1");
        ad1.setUserId(userId);
        ad1.setName("ad 1 name");
        ad1.setDescription("ad 1 description");

        ad2.setId(2l);
        ad2.setAdId("adId2");
        ad2.setUserId(userId);
        ad2.setName("ad 2 name");
        ad2.setDescription("ad 2 description");

        ads.add(ad1);
        ads.add(ad2);

        return ads;
    }

}
