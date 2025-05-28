<template>
  <div class="min-h-screen">
    <!-- Navigation -->
    <nav class="bg-white shadow-md fixed w-full z-10">
      <div class="container mx-auto px-4">
        <div class="flex justify-between items-center h-16">
          <!-- Logo -->
          <div class="flex-shrink-0">
            <a href="#">
              <img src="@/assets/images/logo.jpg" alt="Ardatama Logo" class="h-10">
            </a>
          </div>

          
          <!-- Desktop Menu -->
          <div class="hidden md:block">
            <div class="ml-10 flex items-center space-x-4">
              <a href="#" class="text-amber-600 font-medium">Home</a>
              <a href="#" class="text-gray-700 hover:text-amber-600 font-medium transition duration-300">Projects</a>
              <a href="#" class="text-gray-700 hover:text-amber-600 font-medium transition duration-300">Services</a>
              <a href="#" class="text-gray-700 hover:text-amber-600 font-medium transition duration-300">About</a>
              <a href="#" class="text-gray-700 hover:text-amber-600 font-medium transition duration-300">Contact</a>
              <a href="#" class="bg-amber-600 hover:bg-amber-700 text-white py-2 px-4 rounded transition duration-300">Free Consultation</a>
            </div>
          </div>
          
          <!-- Mobile menu button -->
          <div class="md:hidden">
            <button @click="isOpen = !isOpen" class="text-gray-700 hover:text-amber-600 focus:outline-none">
              <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <!-- show when menu is closed -->
                <path v-if="!isOpen" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                <!-- show when menu is open -->
                <path v-if="isOpen" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          </div>
        </div>
      </div>
      
      <!-- Mobile Menu -->
      <div v-if="isOpen" class="md:hidden">
        <div class="px-2 pt-2 pb-3 space-y-1 sm:px-3 bg-white shadow-lg">
          <a href="#" class="block px-3 py-2 text-amber-600 font-medium">Home</a>
          <a href="#" class="block px-3 py-2 text-gray-700 hover:text-amber-600 font-medium transition duration-300">Projects</a>
          <a href="#" class="block px-3 py-2 text-gray-700 hover:text-amber-600 font-medium transition duration-300">Services</a>
          <a href="#" class="block px-3 py-2 text-gray-700 hover:text-amber-600 font-medium transition duration-300">About</a>
          <a href="#" class="block px-3 py-2 text-gray-700 hover:text-amber-600 font-medium transition duration-300">Contact</a>
          <a href="#" class="block px-3 py-2 mt-4 bg-amber-600 hover:bg-amber-700 text-white text-center rounded transition duration-300">Free Consultation</a>
        </div>
      </div>
    </nav>

    <!-- Hero Section -->
    <section class="relative h-screen pt-16">
      <!-- Hero Background Image -->
      <div class="absolute inset-0">
        <img src="@/assets/images/hero-property.jpg" alt="Modern Architecture" class="w-full h-full object-cover" />
        <div class="absolute inset-0 bg-black opacity-50"></div>
      </div>
      
      <!-- Hero Content -->
      <div class="relative container mx-auto px-4 h-full flex flex-col justify-center">
        <h1 class="text-3xl md:text-4xl lg:text-6xl font-bold text-white mb-4">Building Dreams Into Reality</h1>
        <p class="text-lg md:text-xl text-white mb-8 max-w-xl">Expert construction and architectural solutions for residential and commercial projects with uncompromising quality.</p>
        
        <!-- Search Bar -->
        <div class="bg-white p-4 rounded-lg shadow-lg max-w-4xl">
          <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Project Type</label>
              <select class="w-full p-2 border rounded">
                <option value="">Any Type</option>
                <option value="residential">Residential</option>
                <option value="commercial">Commercial</option>
                <option value="industrial">Industrial</option>
              </select>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Service</label>
              <select class="w-full p-2 border rounded">
                <option value="">All Services</option>
                <option value="architecture">Architecture</option>
                <option value="construction">Construction</option>
                <option value="renovation">Renovation</option>
              </select>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Budget Range</label>
              <select class="w-full p-2 border rounded">
                <option value="">Any Budget</option>
                <option value="0-50000">$0 - $50,000</option>
                <option value="50000-200000">$50,000 - $200,000</option>
                <option value="200000+">$200,000+</option>
              </select>
            </div>
            <div class="flex items-end">
              <button class="w-full bg-amber-600 hover:bg-amber-700 text-white py-2 px-4 rounded transition duration-300">
                Find Projects
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Featured Projects Section -->
    <section class="py-16 bg-gray-50">
      <div class="container mx-auto px-4">
        <div class="text-center mb-12">
          <h2 class="text-3xl font-bold mb-2">Featured Projects</h2>
          <p class="text-gray-600">Explore our portfolio of exceptional constructions and designs</p>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          <!-- Project Card 1 -->
          <div class="bg-white rounded-lg overflow-hidden shadow-md hover:shadow-xl transition duration-300">
            <div class="relative">
              <img src="@/assets/images/rumah1.webp" alt="Modern House" class="w-full h-56 object-cover" />
              <div class="absolute top-4 left-4 bg-amber-600 text-white py-1 px-3 rounded">Residential</div>
            </div>
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">Modern Minimalist House</h3>
              <p class="text-gray-500 mb-4">Pacific Heights, San Francisco</p>
              <div class="flex flex-wrap justify-between text-sm mb-4">
                <div class="flex items-center mr-2 mb-2 md:mb-0">
                  <i class="fas fa-ruler-combined mr-1"></i>
                  <span>3,500 sqft</span>
                </div>
                <div class="flex items-center mr-2 mb-2 md:mb-0">
                  <i class="fas fa-calendar-alt mr-1"></i>
                  <span>6 months</span>
                </div>
                <div class="flex items-center">
                  <i class="fas fa-tag mr-1"></i>
                  <span>New Build</span>
                </div>
              </div>
              <div class="flex flex-col sm:flex-row justify-between items-center">
                <div class="text-xl font-bold text-amber-600 mb-2 sm:mb-0">$1.2M Project</div>
                <button class="w-full sm:w-auto bg-amber-600 hover:bg-amber-700 text-white py-2 px-4 rounded transition duration-300">
                  View Details
                </button>
              </div>
            </div>
          </div>

          <!-- Project Card 2 -->
          <div class="bg-white rounded-lg overflow-hidden shadow-md hover:shadow-xl transition duration-300">
            <div class="relative">
              <img src="@/assets/images/hero-property.jpg" alt="Office Building" class="w-full h-56 object-cover" />
              <div class="absolute top-4 left-4 bg-blue-600 text-white py-1 px-3 rounded">Commercial</div>
            </div>
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">Tech Hub Office Complex</h3>
              <p class="text-gray-500 mb-4">Downtown, Seattle</p>
              <div class="flex flex-wrap justify-between text-sm mb-4">
                <div class="flex items-center mr-2 mb-2 md:mb-0">
                  <i class="fas fa-ruler-combined mr-1"></i>
                  <span>18,000 sqft</span>
                </div>
                <div class="flex items-center mr-2 mb-2 md:mb-0">
                  <i class="fas fa-calendar-alt mr-1"></i>
                  <span>12 months</span>
                </div>
                <div class="flex items-center">
                  <i class="fas fa-tag mr-1"></i>
                  <span>Commercial</span>
                </div>
              </div>
              <div class="flex flex-col sm:flex-row justify-between items-center">
                <div class="text-xl font-bold text-blue-600 mb-2 sm:mb-0">$4.5M Project</div>
                <button class="w-full sm:w-auto bg-amber-600 hover:bg-amber-700 text-white py-2 px-4 rounded transition duration-300">
                  View Details
                </button>
              </div>
            </div>
          </div>

          <!-- Project Card 3 -->
          <div class="bg-white rounded-lg overflow-hidden shadow-md hover:shadow-xl transition duration-300">
            <div class="relative">
              <img src="@/assets/images/rumah.jpg" alt="Home Renovation" class="w-full h-56 object-cover" />
              <div class="absolute top-4 left-4 bg-green-600 text-white py-1 px-3 rounded">Renovation</div>
            </div>
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">Historic Home Renovation</h3>
              <p class="text-gray-500 mb-4">Mission District, San Francisco</p>
              <div class="flex flex-wrap justify-between text-sm mb-4">
                <div class="flex items-center mr-2 mb-2 md:mb-0">
                  <i class="fas fa-ruler-combined mr-1"></i>
                  <span>2,800 sqft</span>
                </div>
                <div class="flex items-center mr-2 mb-2 md:mb-0">
                  <i class="fas fa-calendar-alt mr-1"></i>
                  <span>4 months</span>
                </div>
                <div class="flex items-center">
                  <i class="fas fa-tag mr-1"></i>
                  <span>Renovation</span>
                </div>
              </div>
              <div class="flex flex-col sm:flex-row justify-between items-center">
                <div class="text-xl font-bold text-green-600 mb-2 sm:mb-0">$650K Project</div>
                <button class="w-full sm:w-auto bg-amber-600 hover:bg-amber-700 text-white py-2 px-4 rounded transition duration-300">
                  View Details
                </button>
              </div>
            </div>
          </div>
        </div>
        
        <div class="text-center mt-12">
          <router-link to="/projects" class="inline-block bg-amber-600 hover:bg-amber-700 text-white py-3 px-6 rounded-lg font-medium transition duration-300">
            View All Projects
          </router-link>
        </div>
      </div>
    </section>

    <!-- Services Section -->
    <section class="py-16 bg-white">
      <div class="container mx-auto px-4">
        <div class="text-center mb-12">
          <h2 class="text-3xl font-bold mb-2">Our Services</h2>
          <p class="text-gray-600">Comprehensive construction and architectural solutions for every need</p>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
          <!-- Service 1 -->
          <div class="text-center p-6">
            <div class="w-16 h-16 mx-auto mb-4 bg-amber-100 text-amber-600 rounded-full flex items-center justify-center">
              <i class="fas fa-drafting-compass text-2xl"></i>
            </div>
            <h3 class="text-xl font-semibold mb-2">Architectural Design</h3>
            <p class="text-gray-600">Custom architectural designs that blend aesthetics with functionality, creating spaces that inspire and endure.</p>
          </div>
          
          <!-- Service 2 -->
          <div class="text-center p-6">
            <div class="w-16 h-16 mx-auto mb-4 bg-amber-100 text-amber-600 rounded-full flex items-center justify-center">
              <i class="fas fa-hard-hat text-2xl"></i>
            </div>
            <h3 class="text-xl font-semibold mb-2">Construction</h3>
            <p class="text-gray-600">Expert construction services with meticulous attention to detail, ensuring your project is built to the highest standards.</p>
          </div>
          
          <!-- Service 3 -->
          <div class="text-center p-6">
            <div class="w-16 h-16 mx-auto mb-4 bg-amber-100 text-amber-600 rounded-full flex items-center justify-center">
              <i class="fas fa-tools text-2xl"></i>
            </div>
            <h3 class="text-xl font-semibold mb-2">Renovation</h3>
            <p class="text-gray-600">Transform existing spaces with our renovation expertise, breathing new life into older structures while respecting their history.</p>
          </div>
        </div>
        
        <div class="text-center mt-12">
          <router-link to="/services" class="inline-block bg-transparent border-2 border-amber-600 text-amber-600 hover:bg-amber-600 hover:text-white py-3 px-6 rounded-lg font-medium transition duration-300">
            Learn More About Our Services
          </router-link>
        </div>
      </div>
    </section>

    <!-- Testimonials Section -->
    <section class="py-16 bg-gray-50">
      <div class="container mx-auto px-4">
        <div class="text-center mb-12">
          <h2 class="text-3xl font-bold mb-2">Client Testimonials</h2>
          <p class="text-gray-600">What our clients say about our work</p>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
          <!-- Testimonial 1 -->
          <div class="bg-white p-8 rounded-lg shadow-md">
            <div class="flex items-center mb-4">
              <div class="text-yellow-400 flex">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
            </div>
            <p class="text-gray-600 mb-6">"BuildArch transformed our vision into reality with precision and creativity. Their attention to detail and commitment to quality exceeded our expectations."</p>
            <div class="flex items-center">
              <img src="@/assets/images/client.jpg" alt="Client" class="w-12 h-12 rounded-full mr-4" />
              <div>
                <h4 class="font-semibold">David Miller</h4>
                <p class="text-sm text-gray-500">Residential Client</p>
              </div>
            </div>
          </div>
          
          <!-- Testimonial 2 -->
          <div class="bg-white p-8 rounded-lg shadow-md">
            <div class="flex items-center mb-4">
              <div class="text-yellow-400 flex">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
              </div>
            </div>
            <p class="text-gray-600 mb-6">"As a commercial developer, I've worked with many firms, but none match the professionalism and innovation of BuildArch. They delivered our office complex on time and on budget."</p>
            <div class="flex items-center">
              <img src="@/assets/images/client.jpg" alt="Client" class="w-12 h-12 rounded-full mr-4" />
              <div>
                <h4 class="font-semibold">Jennifer Chen</h4>
                <p class="text-sm text-gray-500">Commercial Developer</p>
              </div>
            </div>
          </div>
          
          <!-- Testimonial 3 -->
          <div class="bg-white p-8 rounded-lg shadow-md">
            <div class="flex items-center mb-4">
              <div class="text-yellow-400 flex">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star-half-alt"></i>
              </div>
            </div>
            <p class="text-gray-600 mb-6">"The renovation of our historic home was a delicate project. BuildArch preserved its character while modernizing it beautifully. We couldn't be happier with the results."</p>
            <div class="flex items-center">
              <img src="@/assets/images/client.jpg" alt="Client" class="w-12 h-12 rounded-full mr-4" />
              <div>
                <h4 class="font-semibold">Mark & Laura Wilson</h4>
                <p class="text-sm text-gray-500">Renovation Client</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA Section -->
    <section class="py-16 bg-amber-600 text-white">
      <div class="container mx-auto px-4 text-center">
        <h2 class="text-2xl md:text-3xl font-bold mb-4">Ready to Start Your Project?</h2>
        <p class="text-lg md:text-xl mb-8 max-w-3xl mx-auto">Contact our team today for a free consultation and take the first step towards bringing your vision to life.</p>
        <div class="flex flex-col sm:flex-row justify-center gap-4">
          <router-link to="/contact" class="bg-white text-amber-600 hover:bg-gray-100 py-3 px-8 rounded-lg font-medium transition duration-300">
            Contact Us
          </router-link>
          <router-link to="/projects" class="bg-transparent border-2 border-white hover:bg-white hover:text-amber-600 py-3 px-8 rounded-lg font-medium transition duration-300">
            View Projects
          </router-link>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data() {
    return {
      isOpen: false
    }
  },
  metaInfo: {
    title: 'Home - BuildArch Construction & Architecture'
  }
}
</script>

<style>
html {
  scroll-behavior: smooth;
}

@media (max-width: 768px) {
  .container {
    padding-left: 1rem;
    padding-right: 1rem;
  }
}
</style>