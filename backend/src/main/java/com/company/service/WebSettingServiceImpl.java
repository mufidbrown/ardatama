package com.company.service;

import com.company.entity.WebSettingEntity;
import com.company.payload.WebSettingRequest;
import com.company.payload.WebSettingResponse;
import com.company.repository.WebSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSettingServiceImpl implements WebSettingService {

    @Autowired
    private WebSettingRepository repository;

    @Override
    public WebSettingResponse updateSetting(WebSettingRequest request) {
        WebSettingEntity setting = repository.findByKey(request.getKey())
                .orElse(new WebSettingEntity());

        setting.setKey(request.getKey());
        setting.setValue(request.getValue());

        repository.save(setting);

        return new WebSettingResponse(setting.getKey(), setting.getValue());
    }

    @Override
    public WebSettingResponse getSetting(String key) {
        WebSettingEntity setting = repository.findByKey(key)
                .orElseThrow(() -> new RuntimeException("Setting not found"));

        return new WebSettingResponse(setting.getKey(), setting.getValue());
    }
}

