import { defineStore } from "pinia";
import api from "@/api/api";
import axios from "axios";


export const useAuthStore = defineStore("auth", {
  state: () => ({
    user: null,
    token: localStorage.getItem("token") || null, // Cek token di localStorage pada saat aplikasi dimulai
  }),

  actions: {
    // Fungsi login
    async login(credentials) {
      try {
        const response = await axios.post("http://localhost:8080/api/auth/signin", credentials);
        console.log("✅ Response dari backend:", response.data);

        if (response.data && response.data.token) {
          this.setToken(response.data.token); // Menyimpan token
          return true; // Login berhasil
        } else {
          throw new Error("Token tidak ditemukan dalam response!");
        }
      } catch (error) {
        console.error("❌ Login error:", error.response?.data || error.message);
        throw error; // Tangani error jika login gagal
      }
    },

    // Fungsi untuk menyimpan token dan menetapkan header di axios
    setToken(token) {
      this.token = token;
      localStorage.setItem("token", token); // Simpan token di localStorage
      api.defaults.headers.common["Authorization"] = `Bearer ${token}`; // Set header global untuk axios
      console.log("🔍 Token disimpan di localStorage:", localStorage.getItem("token"));
    },

    // Fungsi logout
    async logout() {
      try {
        console.log("🔄 Logging out...");
        await api.post("/api/auth/signout"); // Logout di backend jika diperlukan
      } catch (error) {
        console.error("❌ Logout failed:", error.response?.data || error.message);
      } finally {
        this.clearSession(); // Hapus sesi setelah logout
        window.location.href = "/auth/login"; // Redirect ke halaman login
      }
    },

    // Fungsi untuk menghapus sesi (token dan user)
    clearSession() {
      this.token = null;
      this.user = null;
      localStorage.removeItem("token"); // Hapus token dari localStorage
      delete api.defaults.headers.common["Authorization"]; // Hapus header Authorization di axios
    },

    // Fungsi untuk mengambil profil pengguna
    async fetchUser() {
      if (!this.token) {
        console.warn("⚠️ No token found, skipping fetchUser");
        return;
      }
      try {
        console.log("🔄 Fetching user profile...");
        const response = await api.get("/api/auth/profile");
        this.user = response.data;
        console.log("✅ User fetched successfully:", this.user);
      } catch (error) {
        console.error("❌ Fetching user failed:", error.response?.data || error.message);

        // Tangani jika token tidak valid atau kadaluarsa
        if (error.response?.status === 401) {
          this.clearSession(); // Clear sesi jika token tidak valid
          window.location.href = "/auth/login"; // Redirect ke halaman login
        }
      }
    },
  },
});