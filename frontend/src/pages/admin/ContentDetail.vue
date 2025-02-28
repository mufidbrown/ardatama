<template>
    <div v-if="content">
      <h2>{{ content.title }}</h2>
      <p>{{ content.description }}</p>
    </div>
    <div v-else-if="isLoading">
      <p>Loading...</p>
    </div>
    <div v-else>
      <p>Content not found</p>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from "vue";
  import { useContentStore } from "@/store/content"; // Import store Pinia
  
  export default {
    setup() {
      const store = useContentStore();
      const contentId = ref(null);
      const isLoading = ref(true);
  
      onMounted(async () => {
        contentId.value = this.$route.params.id; // Ambil ID dari URL
        try {
          // Ambil data konten berdasarkan ID dari store
          await store.fetchContentById(contentId.value);
        } catch (error) {
          console.error("Failed to fetch content:", error);
        } finally {
          isLoading.value = false;
        }
      });
  
      return { content: store.content, isLoading }; // Menggunakan data dari store
    },
  };
  </script>
  