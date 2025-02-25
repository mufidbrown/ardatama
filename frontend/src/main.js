import { createApp } from "vue";
import App from "./App.vue";
import { createPinia } from "pinia";
import router from "./router";
import "./assets/main.css"; // Pastikan Tailwind tetap ada

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.mount("#app");
app.use(pinia);
