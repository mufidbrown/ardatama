<template>
  <div class="p-6 bg-gray-100 min-h-screen">
    <h1 class="text-2xl font-bold mb-4">User Management</h1>

    <div class="bg-white p-4 rounded shadow">
      <h2 class="text-lg font-semibold mb-2">User List</h2>

      <table class="w-full border-collapse border border-gray-300">
        <thead>
          <tr class="bg-gray-200">
            <th class="border p-2">#</th>
            <th class="border p-2">Name</th>
            <th class="border p-2">Email</th>
            <th class="border p-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(user, index) in users" :key="user.id" class="text-center">
            <td class="border p-2">{{ index + 1 }}</td>
            <td class="border p-2">{{ user.name }}</td>
            <td class="border p-2">{{ user.email }}</td>
            <td class="border p-2">
              <button 
                @click="editUser(user)" 
                class="bg-yellow-500 text-white px-2 py-1 rounded hover:bg-yellow-600">
                Edit
              </button>
              <button 
                @click="deleteUser(user.id)" 
                class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600 ml-2">
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      users: [
        { id: 1, name: "John Doe", email: "john@example.com" },
        { id: 2, name: "Jane Smith", email: "jane@example.com" },
        { id: 3, name: "Alice Johnson", email: "alice@example.com" }
      ]
    };
  },
  methods: {
    editUser(user) {
      alert(`Edit user: ${user.name}`);
    },
    deleteUser(userId) {
      this.users = this.users.filter(user => user.id !== userId);
    }
  }
};
</script>

<style scoped>
table {
  width: 100%;
}
th, td {
  text-align: center;
  padding: 8px;
}
</style>
