<template>
  <div
    class="max-w-6xl mx-auto p-4 sm:p-6 bg-gradient-to-r from-gray-50 to-gray-100 shadow-xl rounded-xl my-8"
  >
    <div class="flex flex-col md:flex-row justify-between items-center mb-12">
      <h1
        class="text-3xl font-bold mb-8 md:mb-0 text-transparent bg-clip-text bg-gradient-to-r from-blue-500 to-indigo-700 leading-relaxed"
      >
        Content Management
      </h1>

      <!-- Add Content Button -->
      <router-link to="/admin-panel/content/create">
        <button
          class="px-5 py-2.5 text-white bg-gradient-to-r from-green-500 to-emerald-600 rounded-lg hover:from-green-600 hover:to-emerald-700 shadow-md transition duration-300 transform hover:-translate-y-1 flex items-center"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-5 w-5 mr-2"
            viewBox="0 0 20 20"
            fill="currentColor"
          >
            <path
              fill-rule="evenodd"
              d="M10 5a1 1 0 011 1v3h3a1 1 0 110 2h-3v3a1 1 0 11-2 0v-3H6a1 1 0 110-2h3V6a1 1 0 011-1z"
              clip-rule="evenodd"
            />
          </svg>
          Add New Content
        </button>
      </router-link>
    </div>

    <!-- Loading Spinner -->
    <div v-if="store.loading" class="flex justify-center items-center p-12">
      <div
        class="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-blue-500"
      ></div>
      <span class="ml-3 text-lg font-medium text-gray-700"
        >Loading content...</span
      >
    </div>

    <!-- Content Table -->
    <div v-else class="mt-6 overflow-x-auto rounded-xl shadow-lg bg-white">
      <table class="min-w-full table-auto border-collapse">
        <thead class="bg-gradient-to-r from-gray-100 to-gray-200">
          <tr>
            <th
              class="px-4 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider"
            >
              ID
            </th>
            <th
              class="px-4 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider"
            >
              Title
            </th>
            <th
              class="px-4 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider hidden md:table-cell"
            >
              Description
            </th>
            <th
              class="px-4 py-3 text-center text-sm font-semibold text-gray-700 uppercase tracking-wider"
            >
              Image
            </th>
            <th
              class="px-4 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider"
            >
              Status
            </th>
            <th
              class="px-4 py-3 text-center text-sm font-semibold text-gray-700 uppercase tracking-wider"
            >
              Actions
            </th>
          </tr>
        </thead>
        <tbody class="divide-y divide-gray-200">
          <tr
            v-for="content in store.contents"
            :key="content.id"
            class="hover:bg-blue-50 transition-colors duration-150"
          >
            <td
              class="px-4 py-3 whitespace-nowrap text-center text-sm font-medium text-gray-800"
            >
              {{ content.id }}
            </td>
            <td
              class="px-4 py-3 whitespace-nowrap text-sm font-semibold text-blue-700"
            >
              {{ content.title }}
            </td>
            <td class="px-4 py-3 text-sm text-gray-600 hidden md:table-cell">
              {{
                content.description.length > 60
                  ? content.description.substring(0, 60) + "..."
                  : content.description
              }}
            </td>
            <td class="px-4 py-3 whitespace-nowrap text-sm align-middle">
              <div class="flex items-center justify-center h-full">
                <img
                  :src="content.imageUrl"
                  alt="Content Image"
                  class="w-20 h-20 object-cover rounded-lg border border-gray-200 shadow-sm transition duration-300 transform hover:scale-150 hover:shadow-lg"
                />
              </div>
            </td>
            <td class="px-4 py-3 whitespace-nowrap">
              <!-- Enhanced Published Status Badge -->
              <span
                :class="
                  content.published
                    ? 'text-green-800 bg-gradient-to-r from-green-100 to-green-200 px-3 py-1.5 rounded-full border border-green-300 shadow-sm'
                    : 'text-red-800 bg-gradient-to-r from-red-100 to-red-200 px-3 py-1.5 rounded-full border border-red-300 shadow-sm'
                "
                class="text-xs font-medium flex items-center justify-center gap-1.5 w-28 transition-all duration-300 hover:shadow-md"
              >
                <!-- Published Icon and Text -->
                <span v-if="content.published" class="flex items-center">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-4 w-4 mr-1 text-green-600"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"
                      clip-rule="evenodd"
                    />
                  </svg>
                  <span class="font-semibold">Published</span>
                </span>

                <!-- Unpublished Icon and Text -->
                <span v-else class="flex items-center">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-4 w-4 mr-1 text-red-600"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z"
                      clip-rule="evenodd"
                    />
                  </svg>
                  <span class="font-semibold">Unpublished</span>
                </span>
              </span>
            </td>
            <td class="px-4 py-3 whitespace-nowrap text-center">
              <div class="flex flex-wrap gap-2 justify-center">
                <!-- Edit Button -->
                <router-link :to="`/admin-panel/content/edit/${content.id}`">
                  <button
                    class="p-2 text-white bg-amber-500 rounded-lg hover:bg-amber-600 shadow-md transition duration-300 transform hover:-translate-y-1 tooltip-container"
                    aria-label="Edit content"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                    >
                      <path
                        d="M13.586 3.586a2 2 0 112.828 2.828l-.793.793-2.828-2.828.793-.793zM11.379 5.793L3 14.172V17h2.828l8.38-8.379-2.83-2.828z"
                      />
                    </svg>
                    <span class="tooltip">Edit</span>
                  </button>
                </router-link>

                <!-- Delete Button -->
                <button
                  @click="deleteContent(content.id)"
                  class="p-2 text-white bg-red-500 rounded-lg hover:bg-red-600 shadow-md transition duration-300 transform hover:-translate-y-1 tooltip-container"
                  aria-label="Delete content"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-5 w-5"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
                      clip-rule="evenodd"
                    />
                  </svg>
                  <span class="tooltip">Delete</span>
                </button>

                <!-- View Details Button -->
                <router-link :to="`/admin-panel/content/${content.id}`">
                  <button
                    class="p-2 text-white bg-blue-500 rounded-lg hover:bg-blue-600 shadow-md transition duration-300 transform hover:-translate-y-1 tooltip-container"
                    aria-label="View content details"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-5 w-5"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                    >
                      <path d="M10 12a2 2 0 100-4 2 2 0 000 4z" />
                      <path
                        fill-rule="evenodd"
                        d="M.458 10C1.732 5.943 5.522 3 10 3s8.268 2.943 9.542 7c-1.274 4.057-5.064 7-9.542 7S1.732 14.057.458 10zM14 10a4 4 0 11-8 0 4 4 0 018 0z"
                        clip-rule="evenodd"
                      />
                    </svg>
                    <span class="tooltip">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>

          <!-- Empty State -->
          <tr v-if="store.contents.length === 0">
            <td colspan="6" class="px-4 py-8 text-center text-gray-500">
              <div class="flex flex-col items-center justify-center">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="h-12 w-12 text-gray-400 mb-3"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"
                  />
                </svg>
                <p class="font-medium text-lg">No content available</p>
                <p class="mt-1">
                  Start by adding new content using the button above
                </p>
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

<style scoped>
.tooltip-container {
  position: relative;
}

.tooltip {
  visibility: hidden;
  position: absolute;
  bottom: -30px;
  left: 50%;
  transform: translateX(-50%);
  background-color: rgba(0, 0, 0, 0.8);
  color: white;
  text-align: center;
  padding: 4px 8px;
  border-radius: 4px;
  z-index: 1;
  white-space: nowrap;
  font-size: 12px;
  opacity: 0;
  transition: opacity 0.3s;
}

.tooltip-container:hover .tooltip {
  visibility: visible;
  opacity: 1;
}
</style>
