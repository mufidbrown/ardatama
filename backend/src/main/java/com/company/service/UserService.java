package com.company.service;

import com.company.payload.UserProfileResponse;

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
     * @param profile Data profil yang diperbarui
     */
    void updateUserProfile(String username, UserProfileResponse profile);
}

