import axios from 'axios';

const API_URL = '/api/user';

export default {
  getProfile() {
    return axios.get(`${API_URL}/profile`);
  },

  updateProfile(profileData) {
    return axios.put(`${API_URL}/profile`, profileData);
  }
};
