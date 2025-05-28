import { ref } from "vue";
import { useContentStore } from "@/store/content";
import { useRouter } from "vue-router";

export function useCreateContent() {
  console.log("✅ useCreateContent.js loaded!"); // Tambahkan ini untuk debugging

  const store = useContentStore();
  const router = useRouter();

  const newContent = ref({
    title: "",
    description: "",
    imageUrl: "",
    isPublished: false,
    userId: null,
  });

  const isSubmitting = ref(false);

  const createNewContent = async () => {
    console.log("🔹 Submitting content:", newContent.value);
    if (isSubmitting.value) return;
    try {
      isSubmitting.value = true;
      const token = localStorage.getItem("token");
      if (!token) {
        alert("❌ No token found, please login again.");
        return;
      }

      const payload = JSON.parse(atob(token.split(".")[1])); 
      newContent.value.userId = payload.user_id;

      await store.createContent(newContent.value);
      alert("✅ Content created successfully!");
      newContent.value = { title: "", description: "", imageUrl: "", isPublished: false, userId: null };
      router.push("/admin-panel/content");
    } catch (error) {
      alert("❌ Failed to create content.");
    } finally {
      isSubmitting.value = false;
    }
  };

  return { newContent, isSubmitting, createNewContent };
}
