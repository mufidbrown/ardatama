<template>
  <div class="mx-auto max-w-md px-4 py-8">
    <div class="rounded-xl bg-white shadow-xl dark:bg-gray-800 dark:shadow-gray-900/10">
      <div class="p-6 sm:p-8">
        <div class="mb-10 text-center">
          <h1 class="text-3xl font-bold text-gray-800 dark:text-white">
            Welcome Back
          </h1>
          <p class="mt-2 text-gray-500 dark:text-gray-400">
            Sign in to continue to your account
          </p>
        </div>

        <form @submit.prevent="handleLogin" class="space-y-6">
          <div class="relative">
            <div class="absolute inset-y-0 left-0 flex items-center pl-3.5 pointer-events-none">
              <svg class="h-5 w-5 text-gray-400 dark:text-gray-500" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd" />
              </svg>
            </div>
            <input
              v-model="credentials.username"
              type="text"
              placeholder="Username"
              required
              class="w-full pl-11 pr-4 py-3.5 border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-700 text-gray-700 dark:text-white rounded-lg transition-all focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 dark:focus:ring-indigo-500/30"
            />
          </div>

          <div class="relative">
            <div class="absolute inset-y-0 left-0 flex items-center pl-3.5 pointer-events-none">
              <svg class="h-5 w-5 text-gray-400 dark:text-gray-500" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z" clip-rule="evenodd" />
              </svg>
            </div>
            <input
              v-model="credentials.password"
              type="password"
              placeholder="Password"
              required
              class="w-full pl-11 pr-4 py-3.5 border border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-700 text-gray-700 dark:text-white rounded-lg transition-all focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 dark:focus:ring-indigo-500/30"
            />
          </div>

          <button
            type="submit"
            :disabled="loading"
            class="w-full py-3.5 bg-indigo-600 hover:bg-indigo-700 text-white font-medium rounded-lg transition-all focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 disabled:opacity-70 disabled:cursor-not-allowed shadow-sm hover:shadow-md"
          >
            <div class="flex items-center justify-center">
              <svg
                v-if="loading"
                class="animate-spin h-5 w-5 mr-2 text-white"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
              >
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              <span>{{ loading ? "Sedang Masuk..." : "Masuk" }}</span>
            </div>
          </button>
        </form>

        <div class="mt-8 space-y-4 text-sm">
          <div class="text-center text-gray-600 dark:text-gray-400">
            Belum punya akun? 
            <router-link
              to="/register"
              class="font-medium text-indigo-600 hover:text-indigo-800 dark:text-indigo-400 dark:hover:text-indigo-300 underline-offset-4 hover:underline"
            >
              Daftar
            </router-link>
          </div>

          <div class="flex flex-col space-y-2 items-center text-gray-600 dark:text-gray-400">
            <router-link
              to="/forgot-password"
              class="flex items-center text-indigo-600 hover:text-indigo-800 dark:text-indigo-400 dark:hover:text-indigo-300 transition-colors"
            >
              <svg class="w-4 h-4 mr-1.5" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clip-rule="evenodd" />
              </svg>
              Lupa Password?
            </router-link>
            
            <router-link
              to="/reset-password"
              class="flex items-center text-indigo-600 hover:text-indigo-800 dark:text-indigo-400 dark:hover:text-indigo-300 transition-colors"
            >
              <svg class="w-4 h-4 mr-1.5" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M4 2a1 1 0 011 1v2.101a7.002 7.002 0 0111.601 2.566 1 1 0 11-1.885.666A5.002 5.002 0 005.999 7H9a1 1 0 010 2H4a1 1 0 01-1-1V3a1 1 0 011-1zm.008 9.057a1 1 0 011.276.61A5.002 5.002 0 0014.001 13H11a1 1 0 110-2h5a1 1 0 011 1v5a1 1 0 11-2 0v-2.101a7.002 7.002 0 01-11.601-2.566 1 1 0 01.61-1.276z" clip-rule="evenodd" />
              </svg>
              Reset Password
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useLogin } from "@/composables/useLogin";

export default {
  setup() {
    return useLogin();
  },
};
</script>