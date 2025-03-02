<template>
  <div class="max-w-3xl mx-auto p-8 bg-white shadow-2xl rounded-xl my-8">
    <div class="flex items-center mb-6">
      <button 
        @click="router.push('/admin-panel/content')" 
        class="flex items-center text-gray-600 hover:text-blue-600 mr-4 transition-colors"
      >
        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <path d="M19 12H5M12 19l-7-7 7-7"/>
        </svg>
      </button>
      <h2 class="text-2xl font-bold text-gray-800">Edit Content</h2>
    </div>
    
    <form @submit.prevent="updateContent" class="space-y-6">
      <div class="space-y-1">
        <label class="block text-gray-700 font-semibold">
          <div class="flex items-center mb-1">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-blue-500" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"/>
              <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"/>
            </svg>
            Title
          </div>
        </label>
        <input 
          v-model="form.title" 
          type="text" 
          class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all" 
          required 
          placeholder="Enter content title"
        />
      </div>
      
      <div class="space-y-1">
        <label class="block text-gray-700 font-semibold">
          <div class="flex items-center mb-1">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-blue-500" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/>
              <polyline points="14 2 14 8 20 8"/>
              <line x1="16" y1="13" x2="8" y2="13"/>
              <line x1="16" y1="17" x2="8" y2="17"/>
              <polyline points="10 9 9 9 8 9"/>
            </svg>
            Description
          </div>
        </label>
        <textarea 
          v-model="form.description" 
          class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all min-h-32" 
          required 
          placeholder="Enter content description"
        ></textarea>
      </div>
      
      <div class="space-y-1">
        <label class="block text-gray-700 font-semibold">
          <div class="flex items-center mb-1">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-blue-500" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <rect x="3" y="3" width="18" height="18" rx="2" ry="2"/>
              <circle cx="8.5" cy="8.5" r="1.5"/>
              <polyline points="21 15 16 10 5 21"/>
            </svg>
            Image URL
          </div>
        </label>
        <input 
          v-model="form.imageUrl" 
          type="text" 
          class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all" 
          required 
          placeholder="Enter image URL"
        />
        
        <div v-if="form.imageUrl" class="mt-2">
          <p class="text-sm text-gray-500 mb-2">Image Preview:</p>
          <img :src="form.imageUrl" alt="Preview" class="max-h-40 rounded border border-gray-200" />
        </div>
      </div>
      
      <div class="flex items-center space-x-4 pt-4">
        <button 
          type="button" 
          @click="router.push('/admin-panel/content')" 
          class="px-6 py-3 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-100 transition-colors"
        >
          Cancel
        </button>
        <button 
          type="submit" 
          class="flex items-center justify-center px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"/>
            <polyline points="17 21 17 13 7 13 7 21"/>
            <polyline points="7 3 7 8 15 8"/>
          </svg>
          Update Content
        </button>
      </div>
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
    
    return { form, updateContent, router };
  },
};
</script>

<style scoped>
/* Tailwind should handle most styling */
</style>