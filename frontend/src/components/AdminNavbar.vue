<template>
  <nav class="bg-gradient-to-r from-indigo-900 to-purple-800 text-white shadow-lg">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex justify-between h-16 items-center">
        <!-- Logo -->
        <div class="flex items-center space-x-4">
          <svg class="h-8 w-8 text-indigo-400 hover:text-indigo-300 transition-colors duration-200" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
          </svg>
          <span class="font-bold text-xl hidden md:block">Dashboard</span>
        </div>
        
        <!-- User profile dropdown -->
        <div class="relative">
          <button 
            @click="isProfileOpen = !isProfileOpen" 
            class="flex items-center space-x-3 text-sm focus:outline-none focus:ring-2 focus:ring-indigo-300 rounded-full p-1 transition-all duration-200"
          >
            <div class="h-10 w-10 rounded-full bg-gradient-to-r from-indigo-500 to-purple-600 flex items-center justify-center font-semibold text-white shadow-md hover:shadow-lg transition-all duration-200">
              {{ userInitials }}
            </div>
            <div class="hidden lg:block text-left">
              <span class="font-medium text-white">{{ userName }}</span>
              <p class="text-indigo-200 text-xs">{{ userEmail }}</p>
            </div>
            <svg 
              class="h-5 w-5 text-indigo-200" 
              :class="{'rotate-180': isProfileOpen}"
              fill="none" 
              viewBox="0 0 24 24" 
              stroke="currentColor"
            >
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
            </svg>
          </button>
          
          <!-- Dropdown menu with animation -->
          <div 
            v-if="isProfileOpen" 
            class="origin-top-right absolute right-0 mt-2 w-64 rounded-lg shadow-xl bg-white ring-1 ring-black ring-opacity-5 divide-y divide-gray-100 transform transition-all duration-200 z-50"
          >
            <div class="py-3 px-4 text-gray-700">
              <p class="font-semibold text-gray-800">{{ userName }}</p>
              <p class="text-xs text-gray-500">{{ userEmail }}</p>
              <div class="mt-1 text-xs font-medium bg-indigo-100 text-indigo-800 px-2 py-0.5 rounded-full inline-block">
                {{ userRole }}
              </div>
            </div>
            <div class="py-1">
              <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 flex items-center">
                <svg class="mr-2 h-4 w-4 text-gray-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
                Profile
              </a>
              <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 flex items-center">
                <svg class="mr-2 h-4 w-4 text-gray-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
                Settings
              </a>
            </div>
            <div class="py-1">
              <button 
                @click="handleLogout" 
                class="w-full text-left px-4 py-2 text-sm text-red-600 hover:bg-red-50 flex items-center font-medium transition-colors duration-200"
              >
                <svg class="mr-2 h-4 w-4 text-red-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                </svg>
                Logout
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue';
import axios from 'axios';
import { useAdminNavbar } from "@/composables/useAdminNavbar";

const { authStore, handleLogout } = useAdminNavbar();
const isProfileOpen = ref(false);
const isMenuOpen = ref(false);
const userName = ref('User');
const userEmail = ref('');
const userRole = ref('User');

// API URL dari environment variable atau default fallback
const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

const fetchUserProfile = async () => {
  try {
    const token = authStore.token;
    if (!token) return;
    
    const response = await axios.get(`${API_BASE_URL}/user/profile`, {
      headers: { Authorization: `Bearer ${token}` }
    });
    
    const userData = response.data;
    if (userData) {
      userName.value = userData.username || 'User';
      userEmail.value = userData.email || '';
      userRole.value = userData.role || 'User';
    }
  } catch (error) {
    console.error("Failed to fetch user profile:", error);
  }
};

onMounted(() => {
  fetchUserProfile();
  document.addEventListener('click', handleClickOutside);
  document.addEventListener('keydown', handleKeyDown);
});

onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside);
  document.removeEventListener('keydown', handleKeyDown);
});

// Compute user initials untuk avatar
const userInitials = computed(() => {
  const names = userName.value.split(' ');
  return names.length > 1
     ? `${names[0].charAt(0)}${names[1].charAt(0)}`.toUpperCase()
     : names[0].charAt(0).toUpperCase();
});

// Menutup dropdown saat klik di luar area
function handleClickOutside(event) {
  if (isProfileOpen.value && !event.target.closest('.relative')) {
    isProfileOpen.value = false;
  }
}

// Menutup dropdown saat menekan tombol Escape
function handleKeyDown(e) {
  if (e.key === 'Escape') {
    isProfileOpen.value = false;
    isMenuOpen.value = false;
  }
}
</script>