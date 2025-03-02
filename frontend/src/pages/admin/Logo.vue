<!-- LogoUploader.vue - Admin Component -->
<template>
  <div>
    <h2 class="text-2xl font-bold mb-6">Logo Management</h2>
    
    <!-- Logo Upload Form -->
    <div class="bg-white rounded-lg shadow p-6 mb-8">
      <h3 class="text-lg font-semibold mb-4">{{ logo ? 'Update Logo' : 'Add New Logo' }}</h3>
      
      <div class="mb-6">
        <label class="block text-gray-700 font-medium mb-2">Logo Image</label>
        <div class="flex items-center">
          <div 
            class="relative border-2 border-dashed border-gray-300 rounded-lg p-4 w-full flex flex-col items-center justify-center cursor-pointer hover:bg-gray-50"
            @click="triggerFileInput"
          >
            <input 
              type="file" 
              ref="fileInput" 
              class="hidden" 
              accept="image/*" 
              @change="handleFileChange"
            >
            
            <div v-if="logoPreview" class="mb-4">
              <img :src="logoPreview" alt="Logo Preview" class="h-32 object-contain">
            </div>
            <div v-else-if="logo" class="mb-4">
              <img :src="logo.imageUrl" alt="Current Logo" class="h-32 object-contain">
            </div>
            <div v-else class="text-center">
              <svg class="mx-auto h-12 w-12 text-gray-400" stroke="currentColor" fill="none" viewBox="0 0 48 48">
                <path d="M28 8H12a4 4 0 00-4 4v20m32-12v8m0 0v8a4 4 0 01-4 4H12a4 4 0 01-4-4v-4m32-4l-3.172-3.172a4 4 0 00-5.656 0L28 28M8 32l9.172-9.172a4 4 0 015.656 0L28 28m0 0l4 4m4-24h8m-4-4v8m-12 4h.02" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
              </svg>
              <p class="mt-1 text-sm text-gray-500">Click to upload or drag and drop</p>
              <p class="text-xs text-gray-500">PNG, JPG, GIF up to 5MB</p>
            </div>
          </div>
          
          <button 
            v-if="logoPreview || logo" 
            @click="removeImage" 
            type="button"
            class="ml-4 p-2 bg-red-100 rounded-full text-red-600 hover:bg-red-200"
          >
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z" clip-rule="evenodd" />
            </svg>
          </button>
        </div>
      </div>
      
      <div class="flex items-center space-x-4">
        <button
          @click="uploadLogo"
          :disabled="isLoading || !selectedFile"
          :class="[
            'py-2 px-6 rounded font-medium transition duration-300',
            !isLoading && selectedFile
              ? 'bg-amber-600 hover:bg-amber-700 text-white'
              : 'bg-gray-300 text-gray-500 cursor-not-allowed'
          ]"
        >
          <span v-if="isLoading">Uploading...</span>
          <span v-else>{{ logo ? 'Update Logo' : 'Upload Logo' }}</span>
        </button>
        
        <button
          v-if="logo"
          @click="confirmDelete"
          :disabled="isLoading"
          class="py-2 px-6 bg-red-600 hover:bg-red-700 text-white rounded font-medium transition duration-300"
        >
          <span v-if="isDeleting">Deleting...</span>
          <span v-else>Delete Logo</span>
        </button>
      </div>
    </div>
    
    <!-- Status Message -->
    <div v-if="message" :class="[
      'mb-6 p-4 rounded-lg',
      messageType === 'success' ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
    ]">
      {{ message }}
    </div>
    
    <!-- Current Logo Display -->
    <div v-if="logo && !isLoadingLogo" class="bg-white rounded-lg shadow p-6">
      <h3 class="text-lg font-semibold mb-4">Current Logo</h3>
      
      <div class="border rounded-lg p-4 flex items-center">
        <div class="h-16 w-16 flex-shrink-0">
          <img :src="logo.imageUrl" alt="Logo" class="h-full w-full object-contain">
        </div>
        <div class="ml-4">
          <p class="text-gray-600 text-sm">Uploaded: {{ formatDate(logo.uploadedAt) }}</p>
          <p class="text-gray-600 text-sm">File name: {{ logo.fileName }}</p>
        </div>
      </div>
    </div>
    
    <!-- Loading State -->
    <div v-if="isLoadingLogo" class="bg-white rounded-lg shadow p-6 flex justify-center">
      <div class="animate-spin rounded-full h-10 w-10 border-t-2 border-b-2 border-amber-600"></div>
    </div>
    
    <!-- No Logo State -->
    <div v-if="!logo && !isLoadingLogo" class="bg-white rounded-lg shadow p-6 text-center py-8 text-gray-500">
      No logo has been uploaded yet. Upload your first logo above.
    </div>
    
    <!-- Delete Confirmation Modal -->
    <div v-if="showDeleteModal" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 max-w-md w-full">
        <h3 class="text-lg font-bold mb-4">Confirm Delete</h3>
        <p>Are you sure you want to delete the current logo? This action cannot be undone.</p>
        
        <div class="mt-6 flex justify-end space-x-4">
          <button 
            @click="showDeleteModal = false" 
            class="py-2 px-4 bg-gray-200 hover:bg-gray-300 rounded transition duration-300"
          >
            Cancel
          </button>
          
          <button 
            @click="deleteLogo" 
            class="py-2 px-4 bg-red-600 hover:bg-red-700 text-white rounded transition duration-300"
            :disabled="isDeleting"
          >
            <span v-if="isDeleting">Deleting...</span>
            <span v-else>Delete Logo</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'LogoUploader',
  data() {
    return {
      logo: null,
      selectedFile: null,
      logoPreview: null,
      isLoading: false,
      isLoadingLogo: false,
      isDeleting: false,
      message: '',
      messageType: '',
      showDeleteModal: false
    }
  },
  created() {
    this.fetchLogo();
  },
  methods: {
    // Fetch the current logo
    fetchLogo() {
      this.isLoadingLogo = true;
      
      axios.get('/api/logo/get')
        .then(response => {
          if (response.data) {
            this.logo = response.data;
          } else {
            this.logo = null;
          }
        })
        .catch(error => {
          console.error('Error fetching logo:', error);
          this.setMessage('Failed to load logo.', 'error');
        })
        .finally(() => {
          this.isLoadingLogo = false;
        });
    },
    
    // Trigger file input click
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
    
    // Handle file selection
    handleFileChange(event) {
      const file = event.target.files[0];
      if (!file) return;
      
      // Validate file type
      if (!file.type.match('image.*')) {
        this.setMessage('Please select an image file.', 'error');
        return;
      }
      
      // Validate file size (5MB)
      if (file.size > 5 * 1024 * 1024) {
        this.setMessage('Image size should not exceed 5MB.', 'error');
        return;
      }
      
      this.selectedFile = file;
      
      // Create preview
      const reader = new FileReader();
      reader.onload = e => {
        this.logoPreview = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    
    // Remove the selected image
    removeImage() {
      this.selectedFile = null;
      this.logoPreview = null;
      this.$refs.fileInput.value = '';
    },
    
    // Upload logo
    uploadLogo() {
      if (!this.selectedFile) {
        this.setMessage('Please select a logo image to upload.', 'error');
        return;
      }
      
      this.isLoading = true;
      this.message = '';
      
      // Create FormData for file upload
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      
      // Add authorization header if needed
      const headers = {
        'Content-Type': 'multipart/form-data'
      };
      
      // Check if auth token exists and add it
      const authToken = localStorage.getItem('authToken');
      if (authToken) {
        headers['Authorization'] = `Bearer ${authToken}`;
      }
      
      axios.post('/api/logo/upload', formData, {
    headers: {
        'Authorization': `Bearer ${localStorage.getItem("token")}`,
        'Content-Type': 'multipart/form-data'
    },
    onUploadProgress: (progressEvent) => {
        let percent = Math.round((progressEvent.loaded * 100) / progressEvent.total);
        console.log(`Upload Progress: ${percent}%`);
    }
})
.then(response => {
    console.log('Logo uploaded successfully', response.data);
})
.catch(error => {
    console.error('Error uploading logo:', error);
});


    },
    
    // Confirm deletion
    confirmDelete() {
      this.showDeleteModal = true;
    },
    
    // Delete logo
    deleteLogo() {
      this.isDeleting = true;
      
      // Add authorization header if needed
      const headers = {};
      const authToken = localStorage.getItem('authToken');
      if (authToken) {
        headers['Authorization'] = `Bearer ${authToken}`;
      }
      
      axios.delete('/api/logo/delete', { headers })
        .then(() => {
          this.setMessage('Logo deleted successfully!', 'success');
          this.logo = null;
          this.showDeleteModal = false;
        })
        .catch(error => {
          console.error('Error deleting logo:', error);
          this.setMessage('Failed to delete logo. Please try again.', 'error');
        })
        .finally(() => {
          this.isDeleting = false;
        });
    },
    
    // Reset the form
    resetForm() {
      this.selectedFile = null;
      this.logoPreview = null;
      
      if (this.$refs.fileInput) {
        this.$refs.fileInput.value = '';
      }
    },
    
    // Format date
    formatDate(dateString) {
      if (!dateString) return 'N/A';
      
      const date = new Date(dateString);
      return date.toLocaleString();
    },
    
    // Set a message with auto-clear
    setMessage(text, type) {
      this.message = text;
      this.messageType = type;
      
      // Clear message after 5 seconds
      setTimeout(() => {
        this.message = '';
      }, 5000);
    }
  }
}
</script>