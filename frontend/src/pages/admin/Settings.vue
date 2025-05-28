







<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const userProfile = ref(null);
const editMode = ref(false);
const updatedProfile = ref({ fullName: "", email: "" });

onMounted(async () => {
  await fetchProfile();
});

const fetchProfile = async () => {
  try {
    const token = localStorage.getItem("token");
    const response = await axios.get("http://localhost:8080/api/user/profile", {
      headers: { Authorization: `Bearer ${token}` },
    });
    userProfile.value = response.data;
    updatedProfile.value = { ...response.data };
  } catch (error) {
    console.error("Gagal mengambil data profil:", error);
  }
};

const updateProfile = async () => {
  try {
    const token = localStorage.getItem("token");
    await axios.put("http://localhost:8080/api/user/profile", updatedProfile.value, {
      headers: { Authorization: `Bearer ${token}` },
    });
    await fetchProfile(); // Refresh data setelah update
    editMode.value = false;
  } catch (error) {
    console.error("Gagal memperbarui profil:", error);
  }
};
</script>

<template>
  <div class="p-6 bg-white shadow-md rounded-lg max-w-lg mx-auto">
    <h2 class="text-2xl font-bold mb-4">Admin Dashboard</h2>

    <div v-if="userProfile">
      <div v-if="!editMode">
        <p><strong>Username:</strong> {{ userProfile.username }}</p>
        <p><strong>Full Name:</strong> {{ userProfile.fullName }}</p>
        <p><strong>Email:</strong> {{ userProfile.email }}</p>
        <button
          @click="editMode = true"
          class="mt-3 px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-700"
        >
          Edit Profile
        </button>
      </div>

      <div v-else>
        <label class="block mb-2">Full Name:</label>
        <input
          v-model="updatedProfile.fullName"
          type="text"
          class="w-full px-3 py-2 border rounded-lg mb-2"
        />

        <label class="block mb-2">Email:</label>
        <input
          v-model="updatedProfile.email"
          type="email"
          class="w-full px-3 py-2 border rounded-lg mb-2"
        />

        <button
          @click="updateProfile"
          class="mt-3 px-4 py-2 bg-green-500 text-white rounded-lg hover:bg-green-700"
        >
          Save Changes
        </button>
        <button
          @click="editMode = false"
          class="mt-3 ml-2 px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-700"
        >
          Cancel
        </button>
      </div>
    </div>

    <div v-else>
      <p>Loading profile...</p>
    </div>
  </div>
</template>
