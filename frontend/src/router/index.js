import { createRouter, createWebHistory } from "vue-router";

// Layouts
import AdminLayout from "@/layouts/AdminLayout.vue";
import PublicLayout from "@/layouts/PublicLayout.vue";

// Public Pages
import Home from "@/pages/public/Home.vue";
import About from "@/pages/public/About.vue";
import Service from "@/pages/public/Service.vue";
import Portfolio from "@/pages/public/Portfolio.vue";
import Contact from "@/pages/public/Contact.vue";

// Admin Pages
import Dashboard from "@/pages/admin/Dashboard.vue";
import Content from "@/pages/admin/Content.vue";
import Users from "@/pages/admin/Users.vue";
import Settings from "@/pages/admin/Settings.vue";

const routes = [
    // Public Routes
    {
      path: "/",
      component: PublicLayout,
      children: [
        { path: "", component: Home },
        { path: "about", component: About },
        { path: "service", component: Service },
        { path: "portfolio", component: Portfolio },
        { path: "contact", component: Contact },
      ],
    },
    // Admin Routes
    {
      path: "/admin-panel", // ✅ Gunakan path baru
      component: AdminLayout,
      children: [
        { path: "", component: Dashboard },
        { path: "content", component: Content },
        { path: "users", component: Users },
        { path: "settings", component: Settings }, // Pastikan ini ada
      ],
    },
  ];
  
  const router = createRouter({
    history: createWebHistory(),
    routes,
  });
  
  export default router;
  