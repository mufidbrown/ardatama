<template>
  <div class="max-w-3xl mx-auto p-6 bg-white shadow-lg rounded-lg">
    <h2 class="text-2xl font-semibold text-gray-800 mb-6">
      Create New Content
    </h2>

    <form @submit.prevent.stop="createNewContent" class="space-y-4">
      <!-- Title Input -->
      <div>
        <label class="block text-gray-700 font-medium mb-1">Title:</label>
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
        <label class="block text-gray-700 font-medium mb-1">Description:</label>
        <textarea
          v-model="newContent.description"
          rows="4"
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300 outline-none"
          placeholder="Enter description"
        ></textarea>
      </div>

      <!-- Image URL Input -->
      <div>
        <label class="block text-gray-700 font-medium mb-1">Image URL:</label>
        <input
          v-model="newContent.imageUrl"
          type="text"
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300 outline-none"
          placeholder="Enter image URL"
        />
      </div>

      <!-- Image Preview -->
      <div v-if="newContent.imageUrl" class="mt-4">
        <p class="text-gray-600 text-sm mb-2">Image Preview:</p>
        <img
          :src="newContent.imageUrl"
          alt="Preview"
          class="w-48 h-48 object-cover rounded-lg shadow-md border"
        />
      </div>

      <!-- Publish Toggle -->
      <div class="flex items-center mt-4">
        <input type="checkbox" v-model="newContent.isPublished" class="mr-2" />
        <label class="text-gray-700">Publish Now</label>
      </div>

      <!-- Buttons -->
      <div class="flex justify-between mt-6">
        <router-link to="/admin-panel/content">
          <button
            type="button"
            class="px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-600 transition"
          >
            Cancel
          </button>
        </router-link>

        <button
          type="submit"
          :disabled="isSubmitting"
          class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition disabled:bg-gray-400"
        >
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
