package com.company.service;

import com.company.payload.WebSettingRequest;
import com.company.payload.WebSettingResponse;

public interface WebSettingService {
    WebSettingResponse updateSetting(WebSettingRequest request);
    WebSettingResponse getSetting(String key);
}

