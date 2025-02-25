<template>
  <div class="p-6 bg-gray-100 min-h-screen">
    <h1 class="text-3xl font-bold mb-4">Our Services</h1>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      <div v-for="service in services" :key="service.id" class="bg-white p-4 rounded shadow">
        <h2 class="text-lg font-semibold">{{ service.title }}</h2>
        <p class="text-gray-600">{{ service.description }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Service",
  data() {
    return {
      services: [
        { id: 1, title: "Web Development", description: "Membangun website modern dan responsif." },
        { id: 2, title: "SEO Optimization", description: "Meningkatkan peringkat website di mesin pencari." },
        { id: 3, title: "Digital Marketing", description: "Strategi pemasaran online untuk bisnis Anda." }
      ]
    };
  }
};
</script>
