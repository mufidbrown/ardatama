<template>
  <nav class="bg-gray-800 text-white p-4 flex justify-between items-center">
    <h1 class="text-lg font-bold">Admin Panel</h1>
    <li v-if="authStore.token">
      <button @click="handleLogout" class="hover:underline bg-red-500 px-3 py-1 rounded">Logout</button>
    </li>
  </nav>
</template>

<script setup>
import { useAdminNavbar } from "@/composables/useAdminNavbar"; // Import composable

const { authStore, handleLogout } = useAdminNavbar();
</script>
