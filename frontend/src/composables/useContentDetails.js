import { ref, watchEffect } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useContentStore } from "@/store/content"; 

export function useContentDetails() {
  const store = useContentStore();
  const route = useRoute();
  const router = useRouter();
  const isLoading = ref(true);
  const content = ref(null);

  // Watch perubahan ID di URL dan ambil data konten
  watchEffect(async () => {
    isLoading.value = true;
    try {
      const contentId = route.params.id;
      content.value = await store.fetchContentById(contentId);
    } catch (error) {
      console.error("Failed to fetch content:", error);
      content.value = null;
    } finally {
      isLoading.value = false;
    }
  });

  // Fungsi untuk kembali ke halaman sebelumnya
  const goBack = () => {
    router.back();
  };

  return { isLoading, content, goBack };
}
