import { reactive, ref } from "vue";
import { useAuthStore } from "@/store/auth";
import { useRouter } from "vue-router";

export function useRegister() {
  const authStore = useAuthStore();
  const router = useRouter();
  const userData = reactive({ username: "", fullName: "", email: "", password: "" });
  const loading = ref(false);

  const handleRegister = async () => {
    try {
      loading.value = true;
      await authStore.register(userData);
      router.push("/login");
    } catch (error) {
      alert("Registrasi gagal: " + (error.response?.data?.message || error.message));
    } finally {
      loading.value = false;
    }
  };

  return { userData, handleRegister, loading };
}
