<template>
  <div class="p-6 bg-gray-100 min-h-screen">
    <h1 class="text-3xl font-bold mb-4">Contact Us</h1>
    <div class="bg-white p-6 rounded shadow-md">
      <form @submit.prevent="submitForm">
        <div class="mb-4">
          <label class="block text-gray-700">Name</label>
          <input type="text" v-model="formData.name" class="w-full mt-1 p-2 border rounded" required />
        </div>
        <div class="mb-4">
          <label class="block text-gray-700">Email</label>
          <input type="email" v-model="formData.email" class="w-full mt-1 p-2 border rounded" required />
        </div>
        <div class="mb-4">
          <label class="block text-gray-700">Message</label>
          <textarea v-model="formData.message" class="w-full mt-1 p-2 border rounded" required></textarea>
        </div>
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
          Send Message
        </button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Contact",
  data() {
    return {
      formData: {
        name: "",
        email: "",
        message: "",
      },
    };
  },
  methods: {
    submitForm() {
      console.log("Form Submitted:", this.formData);
      // Tambahkan logika pengiriman ke backend di sini
    },
  },
};
</script>
