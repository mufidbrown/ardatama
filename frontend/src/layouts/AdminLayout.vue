








<template>
  <div class="flex">
    <Sidebar />
    <div class="flex-1 p-6">
      <Navbar />
      <router-view />
    </div>
  </div>
</template>

<script>
import Sidebar from "@/components/Sidebar.vue";
import Navbar from "@/components/AdminNavbar.vue";

export default {
  components: {
    Sidebar,
    Navbar,
  },
};
</script>
