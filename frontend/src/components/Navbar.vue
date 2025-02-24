<template>
  <nav class="bg-blue-600 text-white p-4 flex justify-between items-center">
    <h1 class="text-lg font-bold">My Website</h1>
    <ul class="flex space-x-4">
      <li><a href="/" class="hover:underline">Home</a></li>
      <li><a href="/about" class="hover:underline">About</a></li>
      <li><a href="/services" class="hover:underline">Services</a></li>
      <li><a href="/contact" class="hover:underline">Contact</a></li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "Navbar",
};
</script>

<style scoped>
/* Tambahkan styling tambahan jika perlu */
</style>
