<template>
  <div class="p-6 bg-gray-100 min-h-screen">
    <h1 class="text-2xl font-bold mb-4">Settings</h1>
    
    <div class="bg-white p-4 rounded shadow">
      <h2 class="text-lg font-semibold mb-2">General Settings</h2>
      <label class="block mb-2">
        <span class="text-gray-700">Website Title</span>
        <input type="text" v-model="settings.title" class="w-full mt-1 p-2 border rounded" />
      </label>

      <label class="block mb-2">
        <span class="text-gray-700">Admin Email</span>
        <input type="email" v-model="settings.email" class="w-full mt-1 p-2 border rounded" />
      </label>

      <button @click="saveSettings" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 mt-4">
        Save Changes
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Settings",
  data() {
    return {
      settings: {
        title: "My Website",
        email: "admin@example.com",
      },
    };
  },
  methods: {
    saveSettings() {
      console.log("Settings saved:", this.settings);
      // Tambahkan logika penyimpanan ke backend di sini
    },
  },
};
</script>
