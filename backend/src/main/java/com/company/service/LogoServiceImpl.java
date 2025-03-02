package com.company.service;

import com.company.dto.LogoResponseDTO;
import com.company.entity.LogoEntity;
import com.company.entity.User;
import com.company.repository.LogoRepository;
import com.company.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class LogoServiceImpl implements LogoService {

    private final LogoRepository logoRepository;
    private final UserRepository userRepository;

    public LogoServiceImpl(LogoRepository logoRepository, UserRepository userRepository) {
        this.logoRepository = logoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public LogoResponseDTO uploadLogo(MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LogoEntity logo = new LogoEntity();
        logo.setImage(file.getBytes());
        logo.setContentType(file.getContentType());
        logo.setUser(user);
        LogoEntity savedLogo = logoRepository.save(logo);

        return new LogoResponseDTO(
                savedLogo.getId(),
                Base64.getEncoder().encodeToString(savedLogo.getImage()),
                savedLogo.getContentType(),
                savedLogo.getUser().getUsername()
        );
    }

    @Override
    public Optional<LogoResponseDTO> getLogo() {
        return logoRepository.findById(1L)
                .map(logo -> new LogoResponseDTO(
                        logo.getId(),
                        Base64.getEncoder().encodeToString(logo.getImage()),
                        logo.getContentType(),
                        logo.getUser().getUsername()
                ));
    }

    @Override
    public void deleteLogo() {
        logoRepository.deleteById(1L);
    }
}


