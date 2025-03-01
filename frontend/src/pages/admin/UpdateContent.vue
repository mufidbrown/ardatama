<template>
    <div class="max-w-2xl mx-auto p-6 bg-white shadow-lg rounded-xl">
      <h2 class="text-2xl font-bold text-gray-800 mb-4">Edit Content</h2>
  
      <form @submit.prevent="updateContent">
        <div class="mb-4">
          <label class="block text-gray-700 font-semibold">Title</label>
          <input v-model="form.title" type="text" class="w-full px-4 py-2 border rounded-lg" required />
        </div>
  
        <div class="mb-4">
          <label class="block text-gray-700 font-semibold">Description</label>
          <textarea v-model="form.description" class="w-full px-4 py-2 border rounded-lg" required></textarea>
        </div>
        
        <div class="mb-4">
          <label class="block text-gray-700 font-semibold">Image URL</label>
          <input v-model="form.imageUrl" type="text" class="w-full px-4 py-2 border rounded-lg" required />
        </div>
  
        <button type="submit" class="bg-blue-500 text-white px-6 py-2 rounded-lg hover:bg-blue-600">
          Update Content
        </button>
      </form>
    </div>
  </template>
  
  <script>
  import { useContentStore } from "@/store/content";
  import { useRoute, useRouter } from "vue-router";
  import { ref, onMounted } from "vue";
  
  export default {
    setup() {
      const contentStore = useContentStore();
      const route = useRoute();
      const router = useRouter();
      const form = ref({
        title: "",
        description: "",
        imageUrl: "",
      });
  
      // Ambil data konten yang akan diedit berdasarkan ID dari URL
      onMounted(async () => {
        const contentId = route.params.id;
        const content = await contentStore.fetchContentById(contentId);
        if (content) {
          form.value = { ...content };
        }
      });
  
      const updateContent = async () => {
        const contentId = route.params.id;
        await contentStore.updateContent(contentId, form.value);
        router.push("/admin-panel/content"); // Redirect ke halaman utama setelah update
      };
  
      return { form, updateContent };
    },
  };
  </script>
  
  <style scoped>
  /* Styling tambahan */
  </style>
  