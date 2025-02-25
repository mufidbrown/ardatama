import { defineStore } from "pinia";
import api from "@/api/api";
import axios from "axios";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    user: null,
    token: localStorage.getItem("token") || null, // ✅ Gunakan localStorage untuk mempertahankan sesi
  }),

  actions: {
    async login(credentials) {
      try {
        const response = await axios.post("http://localhost:8080/api/auth/signin", credentials);
        console.log("✅ Response dari backend:", response.data); // Debugging
    
        if (response.data && response.data.token) {
          this.token = response.data.token;  // Simpan token di state
          localStorage.setItem("token", this.token); // Simpan token di localStorage
        } else {
          throw new Error("Token tidak ditemukan dalam response!");
        }
      } catch (error) {
        console.error("❌ Login error:", error.response?.data || error.message);
        throw error;
      }
    },    

    setToken(token) {
      this.token = token;
      localStorage.setItem("token", token);
      api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    
      console.log("🔍 Token disimpan di localStorage:", localStorage.getItem("token"));
    },

    async register(userData) {
      try {
        console.log("🔄 Sending registration data:", userData);
        await api.post("/api/auth/signup", userData);
        console.log("✅ Registration successful");
      } catch (error) {
        console.error("❌ Registration failed:", error.response?.data || error.message);
        throw error;
      }
    },

    async logout() {
      try {
        console.log("🔄 Logging out...");
        await api.post("/api/auth/signout");
        console.log("✅ Logout successful");
      } catch (error) {
        console.error("❌ Logout failed:", error.response?.data || error.message);
      } finally {
        this.clearSession(); // ✅ Pastikan sesi selalu dihapus
      }
    },

    clearSession() {
      this.token = null;
      this.user = null;
      localStorage.removeItem("token"); // ✅ Hapus token dari localStorage
      delete api.defaults.headers.common["Authorization"]; // ✅ Hapus header
    },

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

        // ✅ Jika token expired atau tidak valid, hapus sesi dan redirect ke login
        if (error.response?.status === 401) {
          this.clearSession();
          window.location.href = "/login"; // Redirect ke login
        }
      }
    },
  },
});
