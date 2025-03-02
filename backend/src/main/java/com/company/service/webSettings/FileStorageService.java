package com.company.service.webSettings;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file); // return URL/path
}

