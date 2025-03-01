<template>
  <div class="flex min-h-screen items-center justify-center bg-gray-100">
    <div class="w-full max-w-sm bg-white p-6 rounded-2xl shadow-lg">
      <h2 class="text-2xl font-bold text-center mb-4">Forgot Password</h2>
      <form @submit.prevent="handleForgotPassword" class="space-y-4">
        <input
          v-model="email"
          type="email"
          placeholder="Enter your email"
          required
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
        />
        <button
          type="submit"
          :disabled="loading"
          class="w-full py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:bg-gray-400"
        >
          {{ loading ? "Sending..." : "Send Reset Link" }}
        </button>
      </form>
      <p class="text-center mt-4">
        <router-link to="/login" class="text-blue-600 hover:underline">
          Back to Login
        </router-link>
      </p>
      <p v-if="message" class="text-green-600 text-center mt-2">
        {{ message }}
      </p>
      <p v-if="errorMessage" class="text-red-600 text-center mt-2">
        {{ errorMessage }}
      </p>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";

export default {
  setup() {
    const email = ref("");
    const loading = ref(false);
    const message = ref("");
    const errorMessage = ref("");

    const handleForgotPassword = async () => {
      loading.value = true;
      message.value = "";
      errorMessage.value = "";

      try {
        await axios.post("http://localhost:8080/api/auth/forgot-password", { email: email.value });
        message.value = "A password reset link has been sent to your email.";
      } catch (error) {
        errorMessage.value = "Failed to send reset link.";
      } finally {
        loading.value = false;
      }
    };

    return { email, loading, message, errorMessage, handleForgotPassword };
  },
};
</script>