<template>
  <div class="mx-auto max-w-md px-4 py-8">
    <div class="rounded-xl bg-white shadow-xl dark:bg-gray-800">
      <div class="p-6 sm:p-8">
        <h1 class="mb-8 text-3xl font-bold text-gray-800 dark:text-white">
          Create Account
        </h1>

        <form @submit.prevent="handleRegister" class="space-y-6">
          <div>
            <input
              v-model.trim="userData.username"
              type="text"
              placeholder="Username"
              required
              class="w-full rounded-lg border border-gray-200 bg-gray-50 p-4 text-gray-700 transition-all focus:border-indigo-500 focus:bg-white focus:ring-2 focus:ring-indigo-200 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
            />
          </div>

          <div>
            <input
              v-model.trim="userData.fullName"
              type="text"
              placeholder="Full Name"
              required
              class="w-full rounded-lg border border-gray-200 bg-gray-50 p-4 text-gray-700 transition-all focus:border-indigo-500 focus:bg-white focus:ring-2 focus:ring-indigo-200 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
            />
          </div>

          <div>
            <input
              v-model.trim="userData.email"
              type="email"
              placeholder="Email Address"
              required
              class="w-full rounded-lg border border-gray-200 bg-gray-50 p-4 text-gray-700 transition-all focus:border-indigo-500 focus:bg-white focus:ring-2 focus:ring-indigo-200 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
            />
          </div>

          <div>
            <input
              v-model="userData.password"
              type="password"
              placeholder="Password"
              required
              class="w-full rounded-lg border border-gray-200 bg-gray-50 p-4 text-gray-700 transition-all focus:border-indigo-500 focus:bg-white focus:ring-2 focus:ring-indigo-200 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
            />
          </div>

          <button
            type="submit"
            :disabled="loading"
            class="flex h-12 w-full items-center justify-center rounded-lg bg-indigo-600 font-medium text-white transition-all hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-70"
          >
            <span v-if="!loading">Get Started</span>
            <svg
              v-else
              class="h-5 w-5 animate-spin text-white"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
            >
              <circle
                class="opacity-25"
                cx="12"
                cy="12"
                r="10"
                stroke="currentColor"
                stroke-width="4"
              ></circle>
              <path
                class="opacity-75"
                fill="currentColor"
                d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
              ></path>
            </svg>
          </button>
        </form>

        <p class="mt-6 text-center text-sm text-gray-600 dark:text-gray-400">
          Already have an account?
          <router-link
            to="/login"
            class="font-medium text-indigo-600 underline-offset-4 hover:underline dark:text-indigo-400"
          >
            Sign in
          </router-link>
        </p>
      </div>
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