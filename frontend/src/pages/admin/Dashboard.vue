<template>
  <div class="py-8 flex items-center justify-center">
    <div class="w-full max-w-md mx-auto animate-fadeIn">
      <!-- Card Container -->
      <div class="bg-white rounded-xl shadow-lg overflow-hidden transform transition duration-500 hover:shadow-2xl">
        <!-- Header -->
        <div class="bg-gradient-to-r from-blue-600 to-blue-800 px-6 py-4">
          <div class="flex items-center justify-between">
            <h2 class="text-2xl font-bold text-white flex items-center transform transition hover:scale-105 duration-300">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A13.937 13.937 0 0112 16c2.5 0 4.847.655 6.879 1.804M15 10a3 3 0 11-6 0 3 3 0 016 0zm6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              Admin Dashboard
            </h2>
            <span class="text-sm bg-blue-100 text-blue-800 px-2 py-1 rounded-full font-medium animate-pulse">
              Profile
            </span>
          </div>
        </div>

        <!-- Content -->
        <div class="p-6">
          <div v-if="isLoading" class="flex justify-center py-8">
            <svg class="animate-spin h-8 w-8 text-blue-600" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
              <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
              <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
            </svg>
          </div>

          <div v-else-if="error" class="bg-red-50 text-red-700 p-4 rounded-lg mb-4 flex items-center animate-slideIn">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7 4a1 1 0 11-2 0 1 1 0 012 0zm-1-9a1 1 0 00-1 1v4a1 1 0 102 0V6a1 1 0 00-1-1z" clip-rule="evenodd" />
            </svg>
            {{ error }}
          </div>

          <div v-else-if="userProfile">
            <!-- View Mode -->
            <div v-if="!editMode" class="space-y-4 animate-slideIn">
              <div class="flex justify-center mb-6">
                <div class="h-24 w-24 bg-gray-200 rounded-full flex items-center justify-center text-gray-500 transform transition duration-300 hover:scale-110 hover:bg-blue-100 hover:text-blue-500">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                  </svg>
                </div>
              </div>
              
              <div class="rounded-lg bg-gray-50 p-4 transform transition hover:bg-blue-50 duration-300">
                <div class="flex items-center border-b border-gray-200 pb-3 mb-3 transform transition duration-300 hover:translate-x-1">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-500 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                  </svg>
                  <div class="flex-1">
                    <p class="text-sm text-gray-500">Username</p>
                    <p class="font-medium">{{ userProfile.username }}</p>
                  </div>
                </div>
                
                <div class="flex items-center border-b border-gray-200 pb-3 mb-3 transform transition duration-300 hover:translate-x-1">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-500 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A13.937 13.937 0 0112 16c2.5 0 4.847.655 6.879 1.804M15 10a3 3 0 11-6 0 3 3 0 016 0zm6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                  <div class="flex-1">
                    <p class="text-sm text-gray-500">Full Name</p>
                    <p class="font-medium">{{ userProfile.fullName }}</p>
                  </div>
                </div>
                
                <div class="flex items-center transform transition duration-300 hover:translate-x-1">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-500 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                  </svg>
                  <div class="flex-1">
                    <p class="text-sm text-gray-500">Email</p>
                    <p class="font-medium">{{ userProfile.email }}</p>
                  </div>
                </div>
              </div>
              
              <button
                @click="enableEditMode"
                class="mt-4 w-full flex items-center justify-center px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors transform hover:scale-105 duration-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
              >
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                </svg>
                Edit Profile
              </button>
            </div>

            <!-- Edit Mode -->
            <div v-else class="space-y-4 animate-slideIn">
              <h3 class="text-lg font-medium text-gray-900 mb-4 flex items-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-blue-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                </svg>
                Edit Your Profile
              </h3>
              
              <div class="space-y-4">
                <div class="transform transition duration-300 hover:translate-y-1">
                  <label class="block text-sm font-medium text-gray-700 mb-1">Full Name</label>
                  <div class="relative rounded-md shadow-sm">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                      </svg>
                    </div>
                    <input
                      v-model="updatedProfile.fullName"
                      type="text"
                      class="block w-full pl-10 pr-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 transition-all duration-300 focus:shadow-lg"
                    />
                  </div>
                </div>
                
                <div class="transform transition duration-300 hover:translate-y-1">
                  <label class="block text-sm font-medium text-gray-700 mb-1">Email</label>
                  <div class="relative rounded-md shadow-sm">
                    <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                      </svg>
                    </div>
                    <input
                      v-model="updatedProfile.email"
                      type="email"
                      class="block w-full pl-10 pr-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 transition-all duration-300 focus:shadow-lg"
                    />
                  </div>
                </div>
              </div>
              
              <div class="flex space-x-3 pt-4">
                <button
                  @click="updateProfile"
                  class="flex-1 flex items-center justify-center px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-colors transform hover:scale-105 duration-300 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2"
                  :disabled="isLoading"
                >
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                  </svg>
                  Save Changes
                </button>
                
                <button
                  @click="cancelEdit"
                  class="flex-1 flex items-center justify-center px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors transform hover:scale-105 duration-300 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2"
                >
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                  Cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useProfileManagement } from "@/composables/useProfileManagement";

const {
  userProfile,
  updatedProfile,
  editMode,
  isLoading,
  error,
  fetchProfile,
  updateProfile,
  enableEditMode,
  cancelEdit
} = useProfileManagement();

onMounted(async () => {
  await fetchProfile();
});
</script>

<style>
@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

@keyframes slideIn {
  from { transform: translateY(20px); opacity: 0; }
  to { transform: translateY(0); opacity: 1; }
}

.animate-fadeIn {
  animation: fadeIn 0.5s ease-out;
}

.animate-slideIn {
  animation: slideIn 0.4s ease-out;
}
</style>