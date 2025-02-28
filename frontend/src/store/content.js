import { defineStore } from "pinia";
import api from "@/api/api";  // Import api.js di sini

export const useContentStore = defineStore("content", {
  state: () => ({
    contents: [],
    loading: false,
  }),

  actions: {
    // Mengambil semua konten
    async fetchContents() {
      this.loading = true;
      try {
        const response = await api.get("/api/content/all");
        console.log("API Response:", response.data);  // Debug JSON
        this.contents = response.data;  // Karena data langsung array
      } catch (error) {
        console.error("Error fetching contents:", error);
      } finally {
        this.loading = false;
      }
    },
  },
});
