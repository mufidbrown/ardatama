<template>
  <div class="sidebar-container">
    <!-- Centered Hamburger Toggle Button -->
    <div
      v-if="!isSidebarOpen"
      class="fixed top-0 left-0 w-16 h-16 flex items-center justify-center z-50 lg:hidden"
    >
      <button
        @click="openSidebar"
        class="hamburger-btn p-2 rounded-md bg-indigo-600 text-white shadow-lg hover:bg-indigo-700 transition-all duration-200"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          viewBox="0 0 24 24"
          fill="none"
          stroke="currentColor"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
          class="w-6 h-6"
        >
          <line x1="3" y1="12" x2="21" y2="12"></line>
          <line x1="3" y1="6" x2="21" y2="6"></line>
          <line x1="3" y1="18" x2="21" y2="18"></line>
        </svg>
      </button>
    </div>

    <!-- Sidebar -->
    <aside
      :class="[
        'sidebar w-72 h-screen bg-gradient-to-b from-gray-900 to-gray-800 text-white p-5 transition-all duration-300 ease-in-out fixed left-0 top-0 z-40 shadow-xl overflow-y-auto',
        isSidebarOpen ? 'translate-x-0' : '-translate-x-full lg:translate-x-0',
      ]"
    >
      <div class="flex items-center justify-between mb-8">
        <div class="flex items-center space-x-3">
          <div class="bg-indigo-600 p-2 rounded-lg">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
              stroke-linecap="round"
              stroke-linejoin="round"
              class="w-5 h-5"
            >
              <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline>
            </svg>
          </div>
          <h2 class="text-xl font-bold">Admin Panel</h2>
        </div>
        <button
          @click="closeSidebar"
          class="p-1 rounded-md hover:bg-gray-700 lg:hidden transition-colors"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="w-5 h-5"
          >
            <line x1="18" y1="6" x2="6" y2="18"></line>
            <line x1="6" y1="6" x2="18" y2="18"></line>
          </svg>
        </button>
      </div>

      <div class="mb-6">
        <div class="bg-gray-700 bg-opacity-40 rounded-xl p-3 flex items-center">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="w-5 h-5 text-gray-400 mr-3"
          >
            <circle cx="11" cy="11" r="8"></circle>
            <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
          </svg>
          <input
            v-model="searchQuery"
            @input="handleSearch"
            type="text"
            placeholder="Search..."
            class="bg-transparent focus:outline-none w-full text-sm"
          />
        </div>
      </div>

      <p class="text-xs uppercase font-semibold text-gray-400 mb-2 ml-2">
        Main Menu
      </p>

      <ul class="space-y-1 mb-8">
        <li v-for="(item, index) in filteredMainMenu" :key="index">
          <template v-if="item.name === 'Manage Content'">
            <div>
              <button
                @click="toggleContentMenu"
                class="w-full flex items-center justify-between px-4 py-3 rounded-lg hover:bg-indigo-500 hover:bg-opacity-20 transition-colors group"
                :class="{ 'bg-indigo-500 bg-opacity-20': showContentSubMenu }"
              >
                <div class="flex items-center">
                  <component
                    :is="item.icon"
                    class="w-5 h-5 mr-3 text-indigo-400 group-hover:text-indigo-300"
                  ></component>
                  <span class="group-hover:translate-x-1 transition-transform">{{ item.name }}</span>
                </div>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  class="w-4 h-4 transition-transform duration-200"
                  :class="{ 'rotate-180': showContentSubMenu }"
                >
                  <polyline points="6 9 12 15 18 9"></polyline>
                </svg>
              </button>
              
              <!-- Content Sub-menu (for mobile) -->
              <div
                v-if="showContentSubMenu && !isLargeScreen"
                class="mt-1 ml-10 space-y-1 border-l-2 border-indigo-500 pl-3"
              >
                <div v-for="(category, catIndex) in contentCategories" :key="catIndex" class="py-2">
                  <div class="text-indigo-400 font-medium mb-2">{{ category.name }}</div>
                  <a
                    v-for="(subItem, subIndex) in category.items"
                    :key="subIndex"
                    :href="subItem.path"
                    class="block py-1.5 px-3 rounded-md hover:bg-indigo-500 hover:bg-opacity-20 transition-colors text-gray-300 hover:text-white text-sm"
                  >
                    {{ subItem.name }}
                  </a>
                </div>
              </div>
            </div>
          </template>
          <template v-else>
            <a
              :href="item.path"
              class="flex items-center px-4 py-3 rounded-lg hover:bg-indigo-500 hover:bg-opacity-20 transition-colors group"
            >
              <component
                :is="item.icon"
                class="w-5 h-5 mr-3 text-indigo-400 group-hover:text-indigo-300"
              ></component>
              <span class="group-hover:translate-x-1 transition-transform">{{
                item.name
              }}</span>
            </a>
          </template>
        </li>
      </ul>

      <p class="text-xs uppercase font-semibold text-gray-400 mb-2 ml-2">
        Management
      </p>

      <ul class="space-y-1">
        <li v-for="(item, index) in filteredManagementMenu" :key="index">
          <a
            :href="item.path"
            class="flex items-center px-4 py-3 rounded-lg hover:bg-indigo-500 hover:bg-opacity-20 transition-colors group"
          >
            <component
              :is="item.icon"
              class="w-5 h-5 mr-3 text-indigo-400 group-hover:text-indigo-300"
            ></component>
            <span class="group-hover:translate-x-1 transition-transform">{{
              item.name
            }}</span>
          </a>
        </li>
      </ul>
    </aside>

    <!-- Content menu panel (appears next to sidebar on larger screens) -->
    <div
      v-if="showContentSubMenu && isLargeScreen"
      class="fixed left-72 top-0 h-screen w-64 bg-gray-800 shadow-xl p-5 z-30 transition-all duration-300 ease-in-out overflow-y-auto content-panel"
    >
      <div class="mb-6">
        <div class="flex items-center justify-between mb-4">
          <h3 class="text-lg font-semibold text-white">Content Management</h3>
          <button
            @click="toggleContentMenu"
            class="p-1 rounded-md hover:bg-gray-700 transition-colors"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
              stroke-linecap="round"
              stroke-linejoin="round"
              class="w-5 h-5"
            >
              <line x1="18" y1="6" x2="6" y2="18"></line>
              <line x1="6" y1="6" x2="18" y2="18"></line>
            </svg>
          </button>
        </div>
        <div class="space-y-6">
          <div v-for="(category, catIndex) in contentCategories" :key="catIndex" class="border-b border-gray-700 pb-5">
            <h4 class="text-indigo-400 font-medium mb-3">{{ category.name }}</h4>
            <ul class="space-y-2">
              <li v-for="(item, itemIndex) in category.items" :key="itemIndex">
                <a 
                  :href="item.path" 
                  class="block text-gray-300 hover:text-white py-2 px-3 rounded-md hover:bg-gray-700 transition-colors"
                >
                  {{ item.name }}
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <!-- Overlay for mobile -->
    <div
      @click="closeSidebar"
      :class="[
        'fixed inset-0 bg-black bg-opacity-60 backdrop-blur-sm z-30 lg:hidden transition-opacity duration-300',
        isSidebarOpen ? 'opacity-100' : 'opacity-0 pointer-events-none',
      ]"
    ></div>
    
    <!-- Additional overlay for content panel on smaller screens -->
    <div
      v-if="showContentSubMenu && isLargeScreen"
      @click="toggleContentMenu"
      class="fixed inset-0 bg-black bg-opacity-30 backdrop-blur-sm z-20 hidden md:block lg:hidden transition-opacity duration-300"
    ></div>
  </div>
</template>

<script>
// Define menu item icons as components for better organization
const DashboardIcon = {
  template: `
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="w-5 h-5">
      <rect x="3" y="3" width="7" height="7"></rect>
      <rect x="14" y="3" width="7" height="7"></rect>
      <rect x="14" y="14" width="7" height="7"></rect>
      <rect x="3" y="14" width="7" height="7"></rect>
    </svg>
  `,
};

const ContentIcon = {
  template: `
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="w-5 h-5">
      <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
      <polyline points="14 2 14 8 20 8"></polyline>
      <line x1="16" y1="13" x2="8" y2="13"></line>
      <line x1="16" y1="17" x2="8" y2="17"></line>
      <polyline points="10 9 9 9 8 9"></polyline>
    </svg>
  `,
};

const SettingsIcon = {
  template: `
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="w-5 h-5">
      <circle cx="12" cy="12" r="3"></circle>
      <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path>
    </svg>
  `,
};

const UsersIcon = {
  template: `
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="w-5 h-5">
      <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
      <circle cx="9" cy="7" r="4"></circle>
      <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
      <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
    </svg>
  `,
};

const AnalyticsIcon = {
  template: `
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="w-5 h-5">
      <line x1="18" y1="20" x2="18" y2="10"></line>
      <line x1="12" y1="20" x2="12" y2="4"></line>
      <line x1="6" y1="20" x2="6" y2="14"></line>
      <line x1="3" y1="20" x2="21" y2="20"></line>
    </svg>
  `,
};

const ReportsIcon = {
  template: `
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="w-5 h-5">
      <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
      <path d="M14 2v6h6"></path>
      <path d="M16 13H8"></path>
      <path d="M16 17H8"></path>
      <path d="M10 9H8"></path>
    </svg>
  `,
};

export default {
  name: "Sidebar",
  components: {
    DashboardIcon,
    ContentIcon,
    SettingsIcon,
    UsersIcon,
    AnalyticsIcon,
    ReportsIcon,
  },
  data() {
    return {
      isSidebarOpen: false,
      autoHideTimer: null,
      searchQuery: "",
      showContentSubMenu: false,
      isLargeScreen: false,
      mainMenu: [
        {
          name: "Dashboard",
          path: "/admin-panel/dashboard",
          icon: DashboardIcon,
        },
        {
          name: "Manage Content",
          path: "/admin-panel/content",
          icon: ContentIcon,
        },
        { name: "Settings", path: "/admin-panel/settings", icon: SettingsIcon },
      ],
      managementMenu: [
        { name: "Users", path: "/admin-panel/users", icon: UsersIcon },
        {
          name: "Analytics",
          path: "/admin-panel/analytics",
          icon: AnalyticsIcon,
        },
        { name: "Reports", path: "/admin-panel/reports", icon: ReportsIcon },
      ],
      contentCategories: [
        {
          id: 1, 
          name: "Home Page",
          items: [
            { id: 1, name: "Title & Main Description", path: "/admin-panel/content/home/title" },
            { id: 2, name: "Hero/Banner Image", path: "/admin-panel/content/home/banner" },
            { id: 3, name: "CTA Text", path: "/admin-panel/content/home/cta" },
            { id: 4, name: "Customer Testimonials", path: "/admin-panel/content/home/testimonials" },
            { id: 5, name: "Statistics/Achievements", path: "/admin-panel/content/home/statistics" }
          ]
        },
        {
          id: 2,
          name: "Projects/Portfolio",
          items: [
            { id: 1, name: "All Projects", path: "/admin-panel/content/projects/all" },
            { id: 2, name: "Residential", path: "/admin-panel/content" },
            { id: 3, name: "Commercial", path: "/admin-panel/content" },
            { id: 4, name: "Rennovation", path: "/admin-panel/content" },
          ]
        },
        {
          id: 3,
          name: "Services Page",
          items: [
            { id: 1, name: "Services List", path: "/admin-panel/content/services/list" },
            { id: 2, name: "Service Details", path: "/admin-panel/content/services/details" },
            { id: 3, name: "Service Images/Icons", path: "/admin-panel/content/services/media" }
          ]
        }
      ]
    };
  },
  computed: {
    filteredMainMenu() {
      if (!this.searchQuery) return this.mainMenu;
      return this.mainMenu.filter((item) =>
        item.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
    filteredManagementMenu() {
      if (!this.searchQuery) return this.managementMenu;
      return this.managementMenu.filter((item) =>
        item.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }
  },
  methods: {
    openSidebar() {
      this.isSidebarOpen = true;
      // Clear any existing timers
      if (this.autoHideTimer) {
        clearTimeout(this.autoHideTimer);
      }
    },
    closeSidebar() {
      this.isSidebarOpen = false;
    },
    toggleContentMenu() {
      this.showContentSubMenu = !this.showContentSubMenu;
      this.resetAutoHideTimer();
    },
    handleSearch() {
      // Reset auto-hide timer when searching
      this.resetAutoHideTimer();
    },
    setupAutoHide() {
      // Setup auto-hide after inactivity (30 seconds)
      document.addEventListener("mousemove", this.resetAutoHideTimer);
      document.addEventListener("keydown", this.resetAutoHideTimer);
      document.addEventListener("click", this.resetAutoHideTimer);
      this.resetAutoHideTimer();
    },
    resetAutoHideTimer() {
      // Clear existing timer
      if (this.autoHideTimer) {
        clearTimeout(this.autoHideTimer);
      }

      // Set new timer for auto-hiding after inactivity
      this.autoHideTimer = setTimeout(() => {
        if (this.isSidebarOpen) {
          this.closeSidebar();
        }
      }, 30000); // 30 seconds of inactivity
    },
    clearEventListeners() {
      document.removeEventListener("mousemove", this.resetAutoHideTimer);
      document.removeEventListener("keydown", this.resetAutoHideTimer);
      document.removeEventListener("click", this.resetAutoHideTimer);
    },
    checkScreenSize() {
      this.isLargeScreen = window.innerWidth >= 1024;
      // If screen size changes and becomes small, we might need to adjust UI
      if (!this.isLargeScreen && this.showContentSubMenu) {
        // Could choose to close content panel here, but we'll leave it open and 
        // show it in mobile-friendly way inside the sidebar
      }
    }
  },
  mounted() {
    this.setupAutoHide();
    this.checkScreenSize();
    window.addEventListener('resize', this.checkScreenSize);
  },
  beforeUnmount() {
    this.clearEventListeners();
    window.removeEventListener('resize', this.checkScreenSize);
    if (this.autoHideTimer) {
      clearTimeout(this.autoHideTimer);
    }
  },
};
</script>

<style scoped>
/* Base styles */
.sidebar-container {
  position: relative;
}

/* Responsive behavior */
@media (min-width: 1024px) {
  .sidebar-container {
    margin-left: 18rem; /* 288px = 18rem, same as w-72 */
  }

  .hamburger-btn {
    display: none;
  }
}

/* Additional spacing when content panel is open */
@media (min-width: 1024px) {
  .sidebar-container.content-panel-open {
    margin-left: 34rem; /* 18rem + 16rem for both panels */
  }
}

/* Transition effect for sidebar */
.sidebar {
  box-shadow: 5px 0 25px rgba(0, 0, 0, 0.3);
}

/* Transition effect for content panel */
.content-panel {
  box-shadow: 5px 0 15px rgba(0, 0, 0, 0.2);
  transform: translateX(0);
  transition: transform 0.3s ease-in-out;
}

/* Active state for menu items */
.router-link-active {
  background-color: rgba(99, 102, 241, 0.2); /* bg-indigo-500 with opacity */
  font-weight: 600;
  color: white;
}

/* Custom scrollbar */
.sidebar::-webkit-scrollbar,
.content-panel::-webkit-scrollbar {
  width: 5px;
}

.sidebar::-webkit-scrollbar-track,
.content-panel::-webkit-scrollbar-track {
  background: transparent;
}

.sidebar::-webkit-scrollbar-thumb,
.content-panel::-webkit-scrollbar-thumb {
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 20px;
}
</style>