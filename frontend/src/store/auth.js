import { defineStore } from "pinia";
import api from "@/api/api";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    user: null,
    token: null,
  }),
  actions: {
    async login(credentials) {
      const response = await api.post("/auth/login", credentials);
      this.token = response.data.token;
      this.user = response.data.user;
    },
    logout() {
      this.token = null;
      this.user = null;
    },
  },
});
