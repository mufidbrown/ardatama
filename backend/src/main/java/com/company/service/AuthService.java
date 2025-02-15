package com.company.service;

import com.company.payload.ForgotPasswordRequest;
import com.company.payload.ResetPasswordRequest;
import com.company.payload.SigninRequest;
import com.company.payload.SignupRequest;

public interface AuthService {
    /**
     * Mendaftarkan pengguna baru.
     *
     * @param request Data pendaftaran pengguna
     */
    void signup(SignupRequest request);

    /**
     * Melakukan login untuk mendapatkan token autentikasi.
     *
     * @param request Data login pengguna
     * @return token JWT
     */
    String signin(SigninRequest request);

    /**
     * Melakukan logout dengan invalidasi token.
     *
     * @param token Token autentikasi pengguna
     */
    void signout(String token);

    /**
     * Mengirimkan email reset password ke pengguna.
     *
     * @param request Data permintaan reset password
     */
    void forgotPassword(ForgotPasswordRequest request);

    /**
     * Melakukan reset password setelah pengguna mengklik tautan reset.
     *
     * @param request Data reset password
     */
    void resetPassword(ResetPasswordRequest request);
}

