<template>
  <div class="p-6 bg-gray-100 min-h-screen">
    <h1 class="text-3xl font-bold mb-4">Our Portfolio</h1>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      <div v-for="project in portfolio" :key="project.id" class="bg-white p-4 rounded shadow">
        <img :src="project.image" :alt="project.title" class="w-full h-48 object-cover rounded">
        <h2 class="text-lg font-semibold mt-2">{{ project.title }}</h2>
        <p class="text-gray-600">{{ project.description }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Portfolio",
  data() {
    return {
      portfolio: [
        { id: 1, title: "Project 1", description: "Deskripsi project pertama", image: "https://via.placeholder.com/300" },
        { id: 2, title: "Project 2", description: "Deskripsi project kedua", image: "https://via.placeholder.com/300" },
        { id: 3, title: "Project 3", description: "Deskripsi project ketiga", image: "https://via.placeholder.com/300" }
      ]
    };
  }
};
</script>
