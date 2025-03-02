package com.company.service;

import com.company.payload.UserProfileResponse;
import com.company.payload.UserProfileUpdateRequest;

public interface UserService {
    /**
     * Mendapatkan profil pengguna berdasarkan username.
     *
     * @param username Username pengguna
     * @return UserProfileResponse berisi data profil pengguna
     */
    UserProfileResponse getUserProfile(String username);

    /**
     * Memperbarui profil pengguna.
     *
     * @param username Username pengguna
     */
    UserProfileResponse updateUserProfile(String username, UserProfileUpdateRequest request);
}

