<template>
  <div class="q-pa-md">
    <h3>Productos</h3>
    <div class="q-mt-md">
      <!-- Filtro de categorías -->
      <q-select
  v-model="selectedCategory"
  :options="categoryOptions"
  option-value="nombre"
  option-label="nombre"
  label="Filtrar por categoría"
  clearable
  @update:model-value="filterProducts"
/>

      <div class="row q-mt-md">
        <div
          v-for="producto in displayedProducts"
          :key="producto.id"
          class="col-md-4 cursor-pointer q-pa-2"
          @click="showProduct(producto)"
        >
          <router-link :to="'/individualProduct/' + producto.id">
            <q-card class="full-width">
              <q-card-section>
                <img :src="producto.image" alt="product image" class="product-image" />
              </q-card-section>
              <q-card-section>
                <div class="product-info">
                  <div class="product-name">{{ producto.name }}</div>
                  <div class="product-price">{{ producto.price }}</div>
                  <div class="product-category">{{ getCategoryName(producto.idCategoria) }}</div>
                </div>
              </q-card-section>
            </q-card>
          </router-link>
        </div>
      </div>
    </div>
    <q-pagination
      v-model="currentPage"
      :max="maxPages"
      :input="true"
      @input="goToPage"
    />
    <!-- Footer Section -->
    <footer class="footer">
      <div class="footer-content">
        <!-- Aquí puedes agregar enlaces a redes sociales y otra información -->
        <FacebookTooltip />
        <LinkedInTooltip />
        <InstagramTooltip />
      </div>
      <div class="footer-text">
        <p>&copy; 2024 KJShop3D. Todos los derechos reservados.</p>
      </div>
    </footer>
  </div>
</template>
<script>
import FacebookTooltip from 'components/FacebookToolTip.vue';
import LinkedInTooltip from 'components/LinkedInToolTip.vue';
import InstagramTooltip from 'components/InstagramToolTip.vue';

export default {
  components: {
    FacebookTooltip,
    InstagramTooltip,
    LinkedInTooltip,
  },
  data() {
    return {
      products: [],
      categories: [],
      selectedCategory: null,
      currentPage: 1,
      productsPerPage: 6,
    };
  },
  computed: {
    displayedProducts() {
      const startIndex = (this.currentPage - 1) * this.productsPerPage;
      const endIndex = startIndex + this.productsPerPage;
      return this.filteredProducts.slice(startIndex, endIndex);
    },
    filteredProducts() {
      if (!this.selectedCategory) {
        console.log('No category selected, displaying all products');
        return this.products;
      }
      console.log(`Filtering products by category name: ${this.selectedCategory}`);
      return this.products.filter(product => this.getCategoryName(product.idcategoria) === this.selectedCategory);
    },
    maxPages() {
      return Math.ceil(this.filteredProducts.length / this.productsPerPage);
    },
    categoryOptions() {
      return this.categories.map(category => ({
        id: category.idcategoria,
        nombre: category.nombre
      }));
    },
  },
  mounted() {
    this.fetchProducts();
    this.fetchCategories();
  },
  methods: {
    fetchProducts() {
      fetch('http://localhost:8080/api/productos')
        .then(response => response.json())
        .then(data => {
          this.products = data.map(producto => ({
            id: producto.idproducto,
            name: producto.nombreproducto,
            price: producto.precio,
            image: producto.imgurl,
            idCategoria: producto.idcategoria,
          }));
          console.log('Productos fetched:', this.products);
        })
        .catch(error => {
          console.error('Error al obtener los productos:', error);
        });
    },
    fetchCategories() {
      fetch('http://localhost:8080/api/categorias')
        .then(response => response.json())
        .then(data => {
          this.categories = data;
          console.log('Categorias fetched:', this.categories);
        })
        .catch(error => {
          console.error('Error al obtener las categorías:', error);
        });
    },
    showProduct(product) {
      // Logic to display product details
    },
    goToPage(page) {
      this.currentPage = page;
    },
    filterProducts() {
      console.log('Filtering triggered.');
    },
    getCategoryName(categoryId) {
      const category = this.categories.find(cat => cat.idcategoria === categoryId);
      return category ? category.nombre : 'Categoría desconocida';
    }
  },
};
</script>


<style scoped>
.full-width {
  width: 100%;
}
.product-image {
  width: 100%;
  height: 250px; /* Altura fija para las imágenes */
  object-fit: cover; /* Recorta la imagen para que se ajuste al contenedor */
}
.product-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.product-name {
  font-weight: bold;
  margin-bottom: 5px;
}
.product-price {
  color: #777;
}
.product-category {
  color: #555;
}
.footer {
  margin-top: 20px;
  padding: 20px;
  background-color: #f1f1f1;
  text-align: center;
}
.footer-content {
  max-width: 1200px;
  margin: 0 auto;
}
.footer-text {
  margin-top: 40px;
}
.footer-link {
  margin: 0 10px;
  color: #007bff;
  text-decoration: none;
}
.footer-link:hover {
  text-decoration: underline;
}
</style>

