// LogoService.js - Shared service for logo operations
import axios from 'axios';

const LogoService = {
  /**
   * Fetch the current logo
   * @returns {Promise} - Promise with logo data
   */
  fetchLogo() {
    return axios.get('/api/logo')
      .then(response => {
        return response.data;
      });
  },
  
  /**
   * Upload a new logo
   * @param {File} file - The logo file to upload
   * @returns {Promise} - Promise with upload response
   */
  uploadLogo(file) {
    const formData = new FormData();
    formData.append('file', file);
    
    // Add authorization header if needed
    const headers = {
      'Content-Type': 'multipart/form-data',
      'Authorization': `Bearer ${authToken}`
    };
    
    // Check if auth token exists and add it
    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      headers['Authorization'] = `Bearer ${authToken}`;
    }
    
    return axios.post('/api/logo/upload', formData, { headers })
      .then(response => {
        return response.data;
      });
  },
  
  /**
   * Delete the current logo
   * @returns {Promise} - Promise with delete response
   */
  deleteLogo() {
    // Add authorization header if needed
    const headers = {};
    const authToken = localStorage.getItem('authToken');
    if (authToken) {
      headers['Authorization'] = `Bearer ${authToken}`;
    }
    
    return axios.delete('/api/logo/delete', { headers })
      .then(response => {
        return response.data;
      });
  }
};

export default LogoService;