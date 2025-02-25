import { useAuthStore } from "@/store/auth"; // Import Pinia store
import { useRouter } from "vue-router"; // Import Vue Router

export function useAdminNavbar() {
  const authStore = useAuthStore();
  const router = useRouter();

  const handleLogout = () => {
    authStore.logout(); // Panggil fungsi logout dari authStore
    router.push("/login"); // Redirect ke halaman login
  };

  return { authStore, handleLogout };
}
