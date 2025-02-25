<template>
  <div class="flex min-h-screen items-center justify-center bg-gray-100">
    <div class="w-full max-w-md rounded-2xl bg-white p-6 shadow-lg">
      <h2 class="mb-4 text-center text-2xl font-semibold text-gray-700">Register</h2>
      
      <form @submit.prevent="handleRegister" class="space-y-4">
        <input 
          v-model.trim="userData.username" 
          type="text" 
          placeholder="Username" 
          required 
          class="w-full rounded-lg border border-gray-300 px-4 py-2 text-gray-700 shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-300"
        />
        
        <input 
          v-model.trim="userData.fullName" 
          type="text" 
          placeholder="Full Name" 
          required 
          class="w-full rounded-lg border border-gray-300 px-4 py-2 text-gray-700 shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-300"
        />
        
        <input 
          v-model.trim="userData.email" 
          type="email" 
          placeholder="Email" 
          required 
          class="w-full rounded-lg border border-gray-300 px-4 py-2 text-gray-700 shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-300"
        />
        
        <input 
          v-model="userData.password" 
          type="password" 
          placeholder="Password" 
          required 
          class="w-full rounded-lg border border-gray-300 px-4 py-2 text-gray-700 shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-300"
        />

        <button 
          type="submit" 
          :disabled="loading"
          class="w-full rounded-lg bg-indigo-600 px-4 py-2 font-semibold text-white transition-all duration-300 hover:bg-indigo-700 disabled:opacity-50"
        >
          {{ loading ? "Registering..." : "Register" }}
        </button>
      </form>

      <p class="mt-4 text-center text-sm text-gray-600">
        Sudah punya akun? 
        <router-link to="/login" class="font-semibold text-indigo-600 hover:underline">Login</router-link>
      </p>
    </div>
  </div>
</template>

<script>
import { useRegister } from "@/composables/useRegister";

export default {
  setup() {
    return useRegister();
  },
};
</script>
