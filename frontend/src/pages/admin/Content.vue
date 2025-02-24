<template>
  <div>
    <h1 class="text-2xl font-bold">Manage Content</h1>
    <button @click="fetchData">Fetch Content</button>
    <ul>
      <li v-for="item in contents" :key="item.id">{{ item.title }}</li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from "vue";
import api from "@/api/api"; 

const contents = ref([]);

const fetchData = async () => {
  const response = await api.get("/content");
  contents.value = response.data;
};
</script>
