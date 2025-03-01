import { ref } from "vue";
import axios from "axios";

export function useProfileManagement() {
  const userProfile = ref(null);
  const editMode = ref(false);
  const isLoading = ref(true);
  const error = ref(null);
  const updatedProfile = ref({
    fullName: "",
    email: "",
  });

  const fetchProfile = async () => {
    isLoading.value = true;
    error.value = null;
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get("http://localhost:8080/api/user/profile", {
        headers: { Authorization: `Bearer ${token}` },
      });
      userProfile.value = response.data;
      updatedProfile.value = { ...response.data };
    } catch (err) {
      console.error("Gagal mengambil data profil:", err);
      error.value = "Gagal memuat profil pengguna";
    } finally {
      isLoading.value = false;
    }
  };

  const updateProfile = async () => {
    isLoading.value = true;
    error.value = null;
    try {
      const token = localStorage.getItem("token");
      await axios.put(
        "http://localhost:8080/api/user/profile",
        updatedProfile.value,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      await fetchProfile();
      editMode.value = false;
    } catch (err) {
      console.error("Gagal memperbarui profil:", err);
      error.value = "Gagal menyimpan perubahan";
    } finally {
      isLoading.value = false;
    }
  };

  const enableEditMode = () => {
    editMode.value = true;
  };

  const cancelEdit = () => {
    updatedProfile.value = { ...userProfile.value };
    editMode.value = false;
  };

  return {
    userProfile,
    updatedProfile,
    editMode,
    isLoading,
    error,
    fetchProfile,
    updateProfile,
    enableEditMode,
    cancelEdit,
  };
}