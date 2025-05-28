<template>
  <nav class="bg-blue-600 text-white p-4 flex justify-between items-center">
    <h1 class="text-lg font-bold">My Website</h1>
    <ul class="flex space-x-4">
      <li><router-link to="/" class="hover:underline">Home</router-link></li>
      <li><router-link to="/about" class="hover:underline">About</router-link></li>
      <li><router-link to="/services" class="hover:underline">Services</router-link></li>
      <li><router-link to="/contact" class="hover:underline">Contact</router-link></li>
     
    </ul>
  </nav>
</template>

<script>
import { useAuthStore } from "@/store/auth";

export default {
  setup() {
    const authStore = useAuthStore();
    return { authStore };
  },
  methods: {
    async handleLogout() {
      await this.authStore.logout();
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
/* Tambahkan styling tambahan jika perlu */
</style>
