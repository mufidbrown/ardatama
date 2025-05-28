import { useContentStore } from "@/store/content";
import { onMounted } from "vue";

export function useContentManagement() {
  const store = useContentStore();

  onMounted(() => {
    store.fetchContents();
  });

  const deleteContent = async (id) => {
    if (!confirm("Are you sure you want to delete this content?")) return;

    try {
      await store.deleteContent(id);
      await store.fetchContents();
      alert("Content deleted successfully");
    } catch (error) {
      console.error("Failed to delete content:", error);
      alert("Failed to delete content. Please check your authentication.");
    }
  };

  return { store, deleteContent };
}
