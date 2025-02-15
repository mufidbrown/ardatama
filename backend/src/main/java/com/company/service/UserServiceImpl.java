package com.company.service;

import com.company.entity.User;
import com.company.exception.ResourceNotFoundException;
import com.company.payload.UserProfileResponse;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;  // Menyimpan data pengguna ke database


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Mendapatkan profil pengguna berdasarkan username.
     *
     * @param username Username pengguna
     * @return UserProfileResponse berisi data profil pengguna
     */

    @Override
    public UserProfileResponse getUserProfile(String username) {
        // Mencari user berdasarkan username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return new UserProfileResponse(user.getUsername(), user.getFullName(), user.getEmail());
    }


   /* @Override
    public UserProfileResponse getUserProfile(String username) {
        // Mengambil data pengguna dari database berdasarkan username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Mengonversi entity User ke UserProfileResponse
        return new UserProfileResponse(user.getUsername(), user.getFullName(), user.getEmail());
    }*/

    /**
     * Memperbarui profil pengguna.
     *
     * @param username Username pengguna
     * @param profile Data profil yang diperbarui
     */
    @Override
    public void updateUserProfile(String username, UserProfileResponse profile) {
        // Mengambil data pengguna dari database berdasarkan username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Memperbarui profil pengguna dengan data baru
        user.setFullName(profile.getFullName());
        user.setEmail(profile.getEmail());
        /*user.setPhoneNumber(profile.getPhoneNumber());
        user.setAddress(profile.getAddress());*/

        // Menyimpan perubahan profil ke database
        userRepository.save(user);
    }
}

