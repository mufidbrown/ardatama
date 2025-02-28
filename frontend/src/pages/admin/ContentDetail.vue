<template>
  <div class="max-w-3xl mx-auto p-6 bg-white shadow-lg rounded-lg">
    <div v-if="isLoading">
      <p class="text-center text-gray-600">Loading...</p>
    </div>
    <div v-else-if="content">
      <h2 class="text-2xl font-semibold mb-4">{{ content.title }}</h2>
      <img :src="content.imageUrl" alt="Content Image" class="w-full h-auto rounded-lg mb-4">
      <p class="text-gray-700">{{ content.description }}</p>

      <!-- Status Publish -->
      <p class="mt-4 font-semibold">
        Status: 
        <span :class="content.published ? 'text-green-500' : 'text-red-500'">
          {{ content.published ? "Published" : "Not Published" }}
        </span>
      </p>

      <!-- Tombol Back -->
      <button 
        @click="goBack" 
        class="mt-6 px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-600 transition"
      >
        Back
      </button>
    </div>
    <div v-else>
      <p class="text-center text-red-500">Content not found</p>
    </div>
  </div>
</template>

<script setup>
import { useContentDetails } from "@/composables/useContentDetails";

const { isLoading, content, goBack } = useContentDetails();
</script>
