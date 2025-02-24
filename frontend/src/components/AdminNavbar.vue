<template>
  <nav class="bg-gray-800 text-white p-4 flex justify-between items-center">
    <h1 class="text-lg font-bold">Admin Panel</h1>
    <button @click="logout" class="bg-red-500 px-4 py-2 rounded hover:bg-red-600">
      Logout
    </button>
  </nav>
</template>

<script>
export default {
  name: "AdminNavbar",
  methods: {
    logout() {
      console.log("Logout clicked");
      // Tambahkan logika logout di sini
    },
  },
};
</script>

<style scoped>
/* Tambahkan styling tambahan jika perlu */
</style>
