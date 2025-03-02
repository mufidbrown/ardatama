package com.company.service;

import com.company.dto.LogoResponseDTO;
import com.company.entity.LogoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface LogoService {
    LogoResponseDTO uploadLogo(MultipartFile file) throws IOException;
    Optional<LogoResponseDTO> getLogo();
    void deleteLogo();
}
