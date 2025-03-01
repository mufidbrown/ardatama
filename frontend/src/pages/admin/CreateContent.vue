<template>
  <div class="max-w-3xl mx-auto p-6 bg-white shadow-lg rounded-lg">
    <h2 class="text-2xl font-semibold text-gray-800 mb-6 text-center flex items-center justify-center">
      <span class="mr-2">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="inline-block">
          <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
          <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
        </svg>
      </span>
      Create New Content
    </h2>
    
    <form @submit.prevent.stop="createNewContent" class="space-y-4">
      <!-- Title Input -->
      <div>
        <label class="block text-gray-700 font-medium mb-1 flex items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2">
            <path d="M4 9h16"></path>
            <path d="M4 15h16"></path>
          </svg>
          Title:
        </label>
        <input
          v-model="newContent.title"
          type="text"
          required
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300 outline-none"
          placeholder="Enter title"
        />
      </div>
      
      <!-- Description Input -->
      <div>
        <label class="block text-gray-700 font-medium mb-1 flex items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2">
            <line x1="17" y1="10" x2="3" y2="10"></line>
            <line x1="21" y1="6" x2="3" y2="6"></line>
            <line x1="21" y1="14" x2="3" y2="14"></line>
            <line x1="17" y1="18" x2="3" y2="18"></line>
          </svg>
          Description:
        </label>
        <textarea
          v-model="newContent.description"
          rows="4"
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300 outline-none"
          placeholder="Enter description"
        ></textarea>
      </div>
      
      <!-- Image URL Input -->
      <div>
        <label class="block text-gray-700 font-medium mb-1 flex items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2">
            <rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect>
            <circle cx="8.5" cy="8.5" r="1.5"></circle>
            <polyline points="21 15 16 10 5 21"></polyline>
          </svg>
          Image URL:
        </label>
        <input
          v-model="newContent.imageUrl"
          type="text"
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300 outline-none"
          placeholder="Enter image URL"
        />
      </div>
      
      <!-- Image Preview -->
      <div v-if="newContent.imageUrl" class="mt-4 flex justify-center">
        <div class="text-center">
          <p class="text-gray-600 text-sm mb-2">Image Preview:</p>
          <img
            :src="newContent.imageUrl"
            alt="Preview"
            class="w-48 h-48 object-cover rounded-lg shadow-md border"
          />
        </div>
      </div>
      
      <!-- Publish Toggle -->
      <div class="flex items-center mt-4 justify-center">
        <input type="checkbox" v-model="newContent.isPublished" class="mr-2" />
        <label class="text-gray-700 flex items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2">
            <path d="M9 10a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v10a1 1 0 0 1-1 1h-4a1 1 0 0 1-1-1V10z"></path>
            <path d="M4 8h2v12H4z"></path>
            <path d="M18 8h2v12h-2z"></path>
            <path d="M4 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v4H4V4z"></path>
          </svg>
          Publish Now
        </label>
      </div>
      
      <!-- Buttons -->
      <div class="flex justify-center gap-4 mt-6">
        <router-link to="/admin-panel/content">
          <button
            type="button"
            class="px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-600 transition flex items-center"
          >
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2">
              <line x1="18" y1="6" x2="6" y2="18"></line>
              <line x1="6" y1="6" x2="18" y2="18"></line>
            </svg>
            Cancel
          </button>
        </router-link>
        
        <button
          type="submit"
          :disabled="isSubmitting"
          class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition disabled:bg-gray-400 flex items-center"
        >
          <svg v-if="!isSubmitting" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2">
            <path d="M20 6L9 17l-5-5"></path>
          </svg>
          <svg v-else xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2 animate-spin">
            <line x1="12" y1="2" x2="12" y2="6"></line>
            <line x1="12" y1="18" x2="12" y2="22"></line>
            <line x1="4.93" y1="4.93" x2="7.76" y2="7.76"></line>
            <line x1="16.24" y1="16.24" x2="19.07" y2="19.07"></line>
            <line x1="2" y1="12" x2="6" y2="12"></line>
            <line x1="18" y1="12" x2="22" y2="12"></line>
            <line x1="4.93" y1="19.07" x2="7.76" y2="16.24"></line>
            <line x1="16.24" y1="7.76" x2="19.07" y2="4.93"></line>
          </svg>
          {{ isSubmitting ? "Creating..." : "Create" }}
        </button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { useCreateContent } from "@/composables/useCreateContent";

const { newContent, isSubmitting, createNewContent } = useCreateContent();
</script>