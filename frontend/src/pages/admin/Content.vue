<template>
  <div class="max-w-4xl mx-auto p-6 bg-white shadow-lg rounded-lg">
    <h1 class="text-3xl font-semibold mb-6 text-gray-800">
      Content Management
    </h1>

    <!-- Loading Spinner -->
    <div v-if="store.loading" class="text-center p-4">Loading...</div>

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
          <tr
            v-for="content in store.contents"
            :key="content.id"
            class="hover:bg-gray-50 transition"
          >
            <td class="border border-gray-300 px-4 py-2 text-center text-sm">{{ content.id }}</td>
            <td class="border border-gray-300 px-4 py-2 text-sm">{{ content.title }}</td>
            <td class="border border-gray-300 px-4 py-2 text-sm">{{ content.description }}</td>
            <td class="border border-gray-300 px-4 py-2 text-sm">
              <img
                :src="content.imageUrl"
                alt="Image"
                class="w-16 h-16 object-cover rounded-lg"
              />
            </td>
            <td class="border border-gray-300 px-4 py-2 text-sm">
              <span
                :class="content.published ? 'text-green-500' : 'text-red-500'"
                class="font-semibold"
              >
                {{ content.published ? "Published" : "Not Published" }}
              </span>
            </td>
            <td class="border border-gray-300 px-4 py-2 text-center">
              <button
                @click="editContent(content)"
                class="px-3 py-1 text-white bg-blue-500 rounded-lg hover:bg-blue-600 transition"
              >
                Edit
              </button>
              <button
                @click="deleteContent(content.id)"
                class="px-3 py-1 text-white bg-red-500 rounded-lg hover:bg-red-600 transition"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { useContentStore } from "@/store/content";
import { onMounted } from "vue";

// Ambil store
const store = useContentStore();

// Panggil fetchContents() saat komponen dimuat
onMounted(() => {
  store.fetchContents();
});

// Fungsi untuk mengedit konten
const editContent = (content) => {
  console.log("Edit content", content);
  // Implementasi untuk edit konten bisa dilakukan di sini
};

// Fungsi untuk menghapus konten
const deleteContent = async (id) => {
  if (confirm("Are you sure you want to delete this content?")) {
    await store.deleteContent(id);
    alert("Content deleted successfully");
  }
};
</script>
