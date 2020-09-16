package com.zlab.commune.api.ads.service;

import com.zlab.commune.api.ads.entity.AdEntity;

import java.util.List;

public interface AdService {

    List<AdEntity> getAds(String userId);
}
