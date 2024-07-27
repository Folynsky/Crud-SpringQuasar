<template>
  <div class="q-pa-md">
    <h3>Categorías</h3>
    <div class="q-mt-md">
      <div class="row">
        <div
          v-for="categoria in displayedCategories"
          :key="categoria.id"
          class="col-md-4 cursor-pointer q-pa-2"
          @click="showCategory(categoria)"
        >
          <router-link :to="'/categoryProduct/' + categoria.id">
            <q-card class="full-width">
              <q-card-section>
                <img :src="categoria.image" alt="category image" class="category-image" />
              </q-card-section>
              <q-card-section>
                <div class="category-info">
                  <div class="category-name">{{ categoria.name }}</div>
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
import FacebookTooltip from 'pages/estilo/FacebookToolTip.vue';
import LinkedInTooltip from 'pages/estilo/LinkedInToolTip.vue';
import InstagramTooltip from 'pages/estilo/InstagramToolTip.vue';

export default {
  components: {
    FacebookTooltip,
    InstagramTooltip,
    LinkedInTooltip,
  },
  data() {
    return {
      categories: [],
      currentPage: 1,
      categoriesPerPage: 6
    };
  },
  computed: {
    displayedCategories() {
      const startIndex = (this.currentPage - 1) * this.categoriesPerPage;
      const endIndex = startIndex + this.categoriesPerPage;
      return this.categories.slice(startIndex, endIndex);
    },
    maxPages() {
      return Math.ceil(this.categories.length / this.categoriesPerPage);
    }
  },
  mounted() {
    fetch('http://localhost:8080/api/categorias')
      .then(response => response.json())
      .then(data => {
        this.categories = data.map(categoria => ({
          id: categoria.idcategoria,
          name: categoria.nombre,
          image: categoria.imgurl
        }));
      })
      .catch(error => {
        console.error('Error al obtener las categorías:', error);
      });
  },
  methods: {
    showCategory(category) {
      // Implementa la lógica para mostrar los detalles de la categoría
    },
    goToPage(page) {
      this.currentPage = page;
    }
  }
};
</script>

<style scoped>
.full-width {
  width: 100%;
}
.category-image {
  width: 100%;
  height: 250px; /* Altura fija para las imágenes, igual que los productos */
  object-fit: cover; /* Recorta la imagen para que se ajuste al contenedor */
}
.category-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.category-name {
  font-weight: bold;
  margin-bottom: 5px;
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
</style>
