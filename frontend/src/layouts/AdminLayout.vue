<template>
  <div class="flex flex-col lg:flex-row min-h-screen bg-gray-100">
    <!-- Sidebar component -->
    <Sidebar />
    
    <!-- Main content container -->
    <div class="flex-1 flex flex-col">
      <!-- Navbar component -->
      <Navbar />
      
      <!-- Content area -->
      <main class="flex-1 p-4 md:p-6 overflow-auto">
        <!-- Router view for displaying the current page content -->
        <router-view />
      </main>
      
      <!-- Footer (optional) -->
      <footer class="py-4 px-6 bg-white border-t border-gray-200">
        <div class="container mx-auto">
          <p class="text-sm text-gray-600 text-center">
            &copy; {{ new Date().getFullYear() }} Your Company. All rights reserved.
          </p>
        </div>
      </footer>
    </div>
  </div>
</template>

<script>
import Sidebar from "@/components/Sidebar.vue";
import Navbar from "@/components/AdminNavbar.vue";

export default {
  name: "MainLayout",
  components: {
    Sidebar,
    Navbar,
  },
  data() {
    return {
      // Any layout-level state can be added here
    };
  },
};
</script>

<style scoped>
/* You can add any additional custom styles here if needed */
/* Most styling is handled by Tailwind classes */
</style>