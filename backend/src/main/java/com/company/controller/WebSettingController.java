package com.company.controller;

import com.company.payload.WebSettingRequest;
import com.company.payload.WebSettingResponse;
import com.company.service.WebSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class WebSettingController {

    @Autowired
    private WebSettingService settingService;

    @PostMapping("/upload")
    public WebSettingResponse update(@RequestBody WebSettingRequest request) {
        return settingService.updateSetting(request);
    }

    @GetMapping("/{key}")
    public WebSettingResponse get(@PathVariable String key) {
        return settingService.getSetting(key);
    }

    /*@GetMapping("/api/settings/{key}")
    public ResponseEntity<SettingResponse> getSettingByKey(@PathVariable String key) {
        Setting setting = settingService.getByKey(key);
        SettingResponse response = new SettingResponse(setting.getKey(), setting.getValue());
        return ResponseEntity.ok(response);
    }*/

}

