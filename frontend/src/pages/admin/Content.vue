<template>
  <div class="max-w-4xl mx-auto p-6 bg-white shadow-lg rounded-lg">
    <h1 class="text-3xl font-semibold mb-6 text-gray-800">
      Content Management
    </h1>

    <!-- 🔹 Tombol ke Halaman Create Content -->
    <div class="mb-4">
      <router-link to="/admin-panel/content/create">
        <button
          class="px-5 py-2 text-white bg-green-600 rounded-lg hover:bg-green-700 shadow-md transition transform hover:scale-105"
        >
          ➕ Add New Content
        </button>
      </router-link>
    </div>

    <!-- Loading Spinner -->
    <div
      v-if="store.loading"
      class="text-center p-4 text-lg font-semibold text-gray-600"
    >
      Loading...
    </div>

    <!-- List Content -->
    <div v-else class="mt-6 overflow-x-auto">
      <table class="min-w-full table-auto border-collapse border border-gray-200 rounded-lg">
        <thead class="bg-gray-100">
          <tr>
            <th class="border border-gray-300 px-4 py-2 text-left text-sm font-semibold text-gray-700">ID</th>
            <th class="border border-gray-300 px-4 py-2 text-left text-sm font-semibold text-gray-700">Title</th>
            <th class="border border-gray-300 px-4 py-2 text-left text-sm font-semibold text-gray-700">Description</th>
            <th class="border border-gray-300 px-4 py-2 text-left text-sm font-semibold text-gray-700">Image</th>
            <th class="border border-gray-300 px-4 py-2 text-left text-sm font-semibold text-gray-700">Publish Status</th>
            <th class="border border-gray-300 px-4 py-2 text-center text-sm font-semibold text-gray-700">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="content in store.contents" :key="content.id" class="hover:bg-gray-50 transition">
            <td class="border border-gray-300 px-4 py-2 text-center text-sm">{{ content.id }}</td>
            <td class="border border-gray-300 px-4 py-2 text-sm font-semibold">{{ content.title }}</td>
            <td class="border border-gray-300 px-4 py-2 text-sm">{{ content.description }}</td>
            <td class="border border-gray-300 px-4 py-2 text-sm">
              <img :src="content.imageUrl" alt="Image" class="w-16 h-16 object-cover rounded-lg border border-gray-300 shadow-sm" />
            </td>
            <td class="border border-gray-300 px-4 py-2 text-sm font-semibold">
              <span
                :class="content.published ? 'text-green-600 bg-green-100 px-2 py-1 rounded-full' : 'text-red-600 bg-red-100 px-2 py-1 rounded-full'"
              >
                {{ content.published ? "Published" : "Not Published" }}
              </span>
            </td>
            <td class="border border-gray-300 px-4 py-2 text-center">
              <div class="flex flex-wrap gap-2 justify-center">
                <!-- 🔹 Edit Button -->
                <router-link :to="`/admin-panel/content/edit/${content.id}`">
                  <button class="px-4 py-2 text-white bg-yellow-500 rounded-lg hover:bg-yellow-600 shadow-md transition transform hover:scale-105">
                    ✏️
                  </button>
                </router-link>

                <!-- 🔹 Delete Button -->
                <button
                  @click="deleteContent(content.id)"
                  class="px-4 py-2 text-white bg-black-100 rounded-lg hover:bg-red-600 shadow-md transition transform hover:scale-105"
                >
                  ❌
                </button>

                <!-- 🔹 View Details Button -->
                <router-link :to="`/admin-panel/content/${content.id}`">
                  <button class="px-4 py-2 text-white bg-blue-500 rounded-lg hover:bg-blue-600 shadow-md transition transform hover:scale-105">
                    👁️
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { useContentManagement } from "@/composables/useContentManagement";

const { store, deleteContent } = useContentManagement();
</script>
