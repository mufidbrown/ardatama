<template>
  <aside class="w-64 h-screen bg-gray-900 text-white p-4">
    <h2 class="text-lg font-bold mb-4">Admin Menu</h2>
    <ul>
      <li class="mb-2">
        <a href="/admin/dashboard" class="block px-3 py-2 rounded hover:bg-gray-700">Dashboard</a>
      </li>
      <li class="mb-2">
        <a href="/admin/content" class="block px-3 py-2 rounded hover:bg-gray-700">Manage Content</a>
      </li>
      <li class="mb-2">
        <a href="/admin-panel/settings" class="block px-3 py-2 rounded hover:bg-gray-700">Settings</a>
      </li>
    </ul>
  </aside>
</template>

<script>
export default {
  name: "Sidebar",
};
</script>

<style scoped>
/* Tambahkan styling tambahan jika perlu */
</style>
