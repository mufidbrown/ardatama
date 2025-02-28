import { createRouter, createWebHistory } from "vue-router";
import { useAuthStore } from "@/store/auth";

// Layouts
import AdminLayout from "@/layouts/AdminLayout.vue";
import PublicLayout from "@/layouts/PublicLayout.vue";
import AuthLayout from "@/layouts/AuthLayout.vue";

import ForgotPassword from '../pages/auth/ForgotPassword.vue';
import ResetPassword from '../pages/auth/ResetPassword.vue';

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
  { path: "/login", redirect: "/auth/login" },
  { path: "/register", redirect: "/auth/register" },
  // Public Routes
  {
    path: "/",
    component: PublicLayout,
    children: [
      { path: "", component: Home },
      { path: "about", component: About },
      { path: "service", component: Service },
      { path: "portfolio", component: Portfolio },
      { path: "contact", component: Contact }
      // { path: "register", component: Register },
      // { path: "login", component: Login },
      // { path: '/forgot-password', component: ForgotPassword },
      // { path: '/reset-password', component: ResetPassword }
    ],
  },

  // Auth Routes (Login/Register terpisah dengan layout sendiri)
  {
    path: "/auth",
    component: AuthLayout, // ✅ Layout khusus untuk halaman autentikasi
    children: [
      { path: "register", component: Register },
      { path: "login", component: Login },
      { path: '/forgot-password', component: ForgotPassword },
      { path: '/reset-password', component: ResetPassword }
    ],
  },

  
  // Admin Routes (Proteksi dengan middleware)
  {
    path: "/admin-panel",
    component: AdminLayout,
    meta: { requiresAuth: true }, // ✅ Middleware
    children: [
<<<<<<< HEAD
      { path: "content/:id", component: ContentDetail },
      { path: "", component: Dashboard },
=======
      { path: "dashboard", component: Dashboard },
>>>>>>> 8eaca956ddb4700040f16d96a52030c86b6bb695
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
// router.beforeEach((to, from, next) => {
//   const authStore = useAuthStore();
//   if (to.meta.requiresAuth && !authStore.token) {
//     next("/login"); // Redirect ke login jika belum login
//   } else {
//     next(); // Lanjut ke halaman tujuan
//   }
// });


// Middleware untuk proteksi admin route dan mencegah user login ulang jika sudah login
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();

  if (to.meta.requiresAuth && !authStore.token) {
    next("/auth/login"); // Jika belum login, redirect ke login
  } else if ((to.path === "/auth/login" || to.path === "/auth/register") && authStore.token) {
    next("/admin-panel/dashboard"); // Jika sudah login, redirect ke dashboard
  } else {
    next(); // Lanjutkan navigasi
  }
});

export default router;
