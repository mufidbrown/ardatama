<template>
  <div class="auth-container">
    <div class="auth-box">
      <router-view />
    </div>
  </div>
</template>

<style scoped>
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background: #1e1e2e; /* Dark Mode */
}

.auth-box {
  background: #ffffff; /* Pastikan background putih */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  width: 400px;
  text-align: center;
}
</style>
