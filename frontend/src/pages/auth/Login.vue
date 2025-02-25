<template>
  <div class="flex min-h-screen items-center justify-center bg-gray-100">
    <div class="w-full max-w-sm bg-white p-6 rounded-2xl shadow-lg">
      <h2 class="text-2xl font-bold text-center mb-4">Login</h2>
      <form @submit.prevent="handleLogin" class="space-y-4">
        <input
          v-model="credentials.username"
          type="text"
          placeholder="Username"
          required
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
        />
        <input
          v-model="credentials.password"
          type="password"
          placeholder="Password"
          required
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
        />
        <button
          type="submit"
          :disabled="loading"
          class="w-full py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:bg-gray-400"
        >
          {{ loading ? "Logging in..." : "Login" }}
        </button>
      </form>
      <p class="text-center mt-4">
        Belum punya akun?
        <router-link to="/register" class="text-blue-600 hover:underline"
          >Daftar</router-link
        >
      </p>
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
