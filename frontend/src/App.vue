<template>
  <div class="min-h-screen bg-white text-black">
    <nav class="p-4 bg-gray-800 text-white flex gap-4">
      <router-link to="/">Home</router-link>

      <router-link to="/portfolio">Portfolio</router-link>
      <router-link to="/service">Service</router-link>
      <router-link to="/contact">Contact</router-link>
      <router-link to="/about">About</router-link>
      <router-link to="/admin">Admin</router-link>
      <router-link to="/dashboard">Dashboard</router-link>
      <router-link to="/users">Users</router-link>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>
