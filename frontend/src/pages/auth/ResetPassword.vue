<template>
  <div class="flex min-h-screen items-center justify-center bg-gray-100">
    <div class="w-full max-w-sm bg-white p-6 rounded-2xl shadow-lg">
      <h2 class="text-2xl font-bold text-center mb-4">Reset Password</h2>
      <form @submit.prevent="handleResetPassword" class="space-y-4">
        <input
          v-model="newPassword"
          type="password"
          placeholder="Enter new password"
          required
          class="w-full px-4 py-2 border rounded-lg focus:ring focus:ring-blue-300"
        />
        <button
          type="submit"
          :disabled="loading"
          class="w-full py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:bg-gray-400"
        >
          {{ loading ? "Resetting..." : "Reset Password" }}
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
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";

export default {
  setup() {
    const route = useRoute();
    const router = useRouter();
    const token = ref("");
    const newPassword = ref("");
    const loading = ref(false);
    const message = ref("");
    const errorMessage = ref("");

    // Ambil token dari URL saat komponen dimuat
    onMounted(() => {
      token.value = route.query.token || "";
      if (!token.value) {
        errorMessage.value = "Invalid or expired reset link.";
      }
    });

    const handleResetPassword = async () => {
      if (!token.value) {
        errorMessage.value = "Reset token is missing.";
        return;
      }

      loading.value = true;
      message.value = "";
      errorMessage.value = "";

      try {
        await axios.post("http://localhost:8080/api/auth/reset-password", {
          token: token.value,
          newPassword: newPassword.value,
        });
        message.value = "Password has been successfully reset!";
        setTimeout(() => {
          router.push("/login");
        }, 2000);
      } catch (error) {
        errorMessage.value = "Failed to reset password.";
      } finally {
        loading.value = false;
      }
    };

    return { newPassword, loading, message, errorMessage, handleResetPassword };
  },
};
</script>