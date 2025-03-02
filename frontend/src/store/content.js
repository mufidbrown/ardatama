import axios from "axios";
import { defineStore } from "pinia";
import api from "@/api/api"; // Import API helper

export const useContentStore = defineStore("content", {
  state: () => ({
    contents: [],
    content: null, // State untuk satu konten detail
    loading: false,
  }),

  actions: {
    // Mengambil semua konten
    async fetchContents() {
      this.loading = true;
      try {
        const response = await api.get("/api/content/all");
        console.log("API Response:", response.data); // Debug JSON
        this.contents = response.data; // Karena data langsung array
      } catch (error) {
        console.error("Error fetching contents:", error);
      } finally {
        this.loading = false;
      }
    },

    // Mengambil detail konten berdasarkan ID
    async fetchContentById(id) {
      this.loading = true;
      try {
        const response = await api.get(`/api/content/${id}`);
        console.log("Content Detail:", response.data); // Debug response
        this.content = response.data;
        return response.data;
      } catch (error) {
        console.error("Error fetching content by ID:", error);
        this.content = null;
        return null;
      } finally {
        this.loading = false;
      }
    },

    async createContent(newContent) {
      try {
        const token = localStorage.getItem("token");
        if (!token) {
          alert("❌ No token found, please login again.");
          return;
        }

        console.log("🔹 Sending request with token:", token);

        const response = await api.post("/api/content/create", newContent, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        // Refresh list setelah tambah konten
        await this.fetchContents();
      } catch (error) {
        console.error("❌ Error creating content:", error);
        alert("❌ Failed to create content. Check console for details.");
      }
    },

    async deleteContent(id) {
      const token = localStorage.getItem("token"); // Ambil token dari localStorage

      if (!token) {
        console.error(
          "❌ Token tidak ditemukan! Pastikan pengguna sudah login."
        );
        alert("Unauthorized: No token found.");
        return;
      }

      console.log("🔹 Token yang dikirim:", token); // Debugging token

      try {
        const response = await api.delete(`/api/content/${id}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        console.log("✅ Content deleted successfully:", response.data);

        // Hapus konten dari state setelah sukses dihapus
        this.contents = this.contents.filter((content) => content.id !== id);

        alert("✅ Content deleted successfully!");
      } catch (error) {
        console.error("❌ Error deleting content:", error);

        // Debug lebih lanjut jika ada response error dari server
        if (error.response) {
          console.error("🔹 Server Response:", error.response.data);
          alert(
            `Failed to delete content: ${
              error.response.data.message || "Unknown error"
            }`
          );
        } else {
          alert("❌ Failed to delete content. Check console for details.");
        }
      }
    },

    async updateContent(id, updatedContent) {
      const token = localStorage.getItem("token"); // Ambil token dari localStorage

      if (!token) {
        console.error(
          "❌ Token tidak ditemukan! Pastikan pengguna sudah login."
        );
        alert("Unauthorized: No token found.");
        return;
      }

      console.log("🔹 Token yang dikirim:", token); // Debugging token

      try {
        const response = await api.put(
          `/api/content/${id}`, // Endpoint update
          {
            title: updatedContent.title,
            description: updatedContent.description,
            imageUrl: updatedContent.imageUrl,
          },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        console.log("✅ Content updated successfully:", response.data);

        // Perbarui state dengan data yang baru
        this.contents = this.contents.map((content) =>
          content.id === id ? response.data : content
        );

        alert("✅ Content updated successfully!");
      } catch (error) {
        console.error("❌ Error updating content:", error);

        if (error.response) {
          console.error("🔹 Server Response:", error.response.data);
          alert(
            `Failed to update content: ${
              error.response.data.message || "Unknown error"
            }`
          );
        } else {
          alert("❌ Failed to update content. Check console for details.");
        }
      }
    },
  },
});
