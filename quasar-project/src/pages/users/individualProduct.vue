<template>
  <div class="q-pa-md">
    <h3 class="text-h6">Detalles del Producto</h3>
    <div v-if="product" class="q-mt-md">
      <div class="row q-gutter-md">
        <div class="col-md-6">
          <q-card class="full-width">
            <q-card-section>
              <img :src="product.imgurl" alt="product image" class="product-image">
            </q-card-section>
          </q-card>
        </div>
        <div class="col-md-6">
          <q-card class="full-width">
            <q-card-section>
              <div class="product-info">
                <div><strong>Nombre:</strong> {{ product.nombreproducto }}</div>
                <div><strong>Precio:</strong> {{ product.precio }}</div>
                <div><strong>Existencias:</strong> {{ product.existencias }}</div>
                <div><strong>Descripción:</strong> {{ product.descripcion }}</div>
                <div><strong>Modelo:</strong> {{ product.modelo }}</div>
                <div v-if="product.idcategoria"><strong>Categoría:</strong> {{ getCategoryName(product.idcategoria) }}</div>
                <div v-else><strong>Categoría:</strong> Sin categoría</div>
              </div>
              <!-- Botones de agregar al carrito y a favoritos -->
              <div class="q-mt-md q-gutter-sm">
                <q-btn label="Agregar a favoritos" icon="favorite" color="red" @click="addToFavorites(product.idproducto)" flat></q-btn>
                <q-btn label="Agregar al carrito" icon="shopping_cart" color="primary" @click="addToCart(product.idproducto)" flat></q-btn>
              </div>
            </q-card-section>
          </q-card>
        </div>
      </div>
    </div>
    <div v-else>
      <p>Cargando...</p>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const router = useRouter();
    const productID = ref(null);
    const product = ref(null);
    const categories = ref([]);

    const getProduct = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/productos/${productID.value}`);
        if (response.ok) {
          const data = await response.json();
          product.value = data;
        } else {
          console.error('Error al obtener el producto:', response.statusText);
        }
      } catch (error) {
        console.error('Error al obtener el producto:', error);
      }
    };

    const fetchCategories = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/categorias");
        if (response.ok) {
          categories.value = await response.json();
        } else {
          console.error("Error al obtener las categorías:", response.statusText);
        }
      } catch (error) {
        console.error("Error al obtener las categorías:", error);
      }
    };

    const getCategoryName = (categoryId) => {
      const category = categories.value.find(cat => cat.idcategoria === categoryId);
      return category ? category.nombre : 'Categoría desconocida';
    };

    onMounted(() => {
      productID.value = router.currentRoute.value.params.idproducto;
      getProduct();
      fetchCategories();
    });

    return {
      product,
      getCategoryName
    };
  },
};
</script>

<style scoped>
.full-width {
  width: 100%;
}
.product-image {
  width: 100%;
  height: 400px;
  object-fit: cover;
}
.product-info {
  padding: 16px;
}
</style>
