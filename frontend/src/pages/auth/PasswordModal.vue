<template>
  <div class="modal" v-if="isOpen">
    <div class="modal-content">
      <h3>Reset Password</h3>
      <label>Password Baru</label>
      <input v-model="newPassword" type="password" required />
      <button @click="resetPassword">Simpan</button>
      <button @click="closeModal">Tutup</button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['isOpen'],
  data() {
    return { newPassword: '' };
  },
  methods: {
    resetPassword() {
      alert('Password berhasil diubah!');
      this.$emit('close');
    },
    closeModal() {
      this.$emit('close');
    }
  }
};
</script>

<style scoped>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
}
</style>
