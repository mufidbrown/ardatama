import { createRouter, createWebHistory } from "vue-router";
import { useAuthStore } from "@/store/auth";

// Layouts
import AdminLayout from "@/layouts/AdminLayout.vue";
import PublicLayout from "@/layouts/PublicLayout.vue";

// Public Pages
import Home from "@/pages/public/Home.vue";
import About from "@/pages/public/About.vue";
import Service from "@/pages/public/Service.vue";
import Portfolio from "@/pages/public/Portfolio.vue";
import Contact from "@/pages/public/Contact.vue";
import Login from "@/pages/auth/Login.vue";
import Register from "@/pages/auth/Register.vue";

// Admin Pages
import Dashboard from "@/pages/admin/Dashboard.vue";
import Content from "@/pages/admin/Content.vue";
import Users from "@/pages/admin/Users.vue";
import Settings from "@/pages/admin/Settings.vue";
import ContentDetail from "@/pages/admin/ContentDetail.vue";

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
      { path: "login", component: Login },
      { path: "register", component: Register },
    ],
  },
  // Admin Routes (Proteksi dengan middleware)
  {
    path: "/admin-panel",
    component: AdminLayout,
    meta: { requiresAuth: true }, // ✅ Middleware
    children: [
      { path: "content/:id", component: ContentDetail },
      { path: "", component: Dashboard },
      { path: "content", component: Content },
      { path: "users", component: Users },
      { path: "settings", component: Settings },
    ],
  },
];

// Buat router
const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Middleware untuk proteksi admin route
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  if (to.meta.requiresAuth && !authStore.token) {
    next("/login"); // Redirect ke login jika belum login
  } else {
    next(); // Lanjut ke halaman tujuan
  }
});

export default router;
