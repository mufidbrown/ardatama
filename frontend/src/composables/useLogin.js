import { reactive, ref } from "vue";
import { useAuthStore } from "@/store/auth";
import { useRouter } from "vue-router";

export function useLogin() {
  const authStore = useAuthStore();
  const router = useRouter();
  const credentials = reactive({ username: "", password: "" });
  const loading = ref(false);

  const handleLogin = async () => {
    try {
      loading.value = true;
      await authStore.login(credentials);
      console.log("✅ Auth Store setelah login:", authStore); // Debugging

      if (authStore.token) {
        router.push("/admin-panel/"); // ✅ Redirect ke Admin Panel setelah login sukses
      } else {
        console.error("❌ Token tidak tersimpan di authStore!");
      }
    } catch (error) {
      console.error("❌ Login gagal:", error.response?.data || error.message);
      alert(error.response?.data?.message || "Login gagal, coba lagi!");
    } finally {
      loading.value = false;
    }
  };

  return { credentials, handleLogin, loading };
}
