<template>
  <div class="q-pa-md">
    <h3>Editar Producto</h3>
    <q-form @submit="updateProduct" v-if="editedProduct">
      <q-input label="Nombre" v-model="editedProduct.nombreproducto" />
      <q-input label="Precio" v-model="editedProduct.precio" type="number" step="0.01" />
      <q-input label="Existencias" v-model="editedProduct.existencias" type="number" />
      <q-input label="Descripción" v-model="editedProduct.descripcion" />
      <q-input label="Modelo" v-model="editedProduct.modelo" />
      <q-input label="URL de la imagen" v-model="editedProduct.imgurl" />
      <q-select
        v-model="selectedCategory"
        label="Categoría"
        :options="categories.map(category => ({ label: category.nombre, value: category.idcategoria }))"
      />
      <q-btn type="submit" label="Guardar Cambios" color="primary" class="q-mt-md" />
    </q-form>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const router = useRouter();
    const productID = router.currentRoute.value.params.idproducto;
    const editedProduct = ref(null);
    const selectedCategory = ref(null);
    const categories = ref([]);

    const getProduct = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/productos/${productID}`);
        if (response.ok) {
          editedProduct.value = await response.json();
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
          console.error(
            "Error al obtener las categorías:",
            response.statusText
          );
        }
      } catch (error) {
        console.error("Error al obtener las categorías:", error);
      }
    };

    const updateProduct = async () => {
      try {
        if (!selectedCategory.value) {
          console.error("Debe seleccionar una categoría");
          return;
        }

        const categoryId = selectedCategory.value.value; // Extraer el ID de la categoría seleccionada
        editedProduct.value.idcategoria = categoryId;

        const response = await fetch(`http://localhost:8080/api/productos/${productID}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(editedProduct.value)
        });

        if (response.ok) {
          console.log('Producto actualizado correctamente');
          router.push('/products'); // Redirigir a la lista de productos después de actualizar
        } else {
          console.error('Error al actualizar el producto:', response.statusText);
        }
      } catch (error) {
        console.error('Error al actualizar el producto:', error);
      }
    };

    onMounted(() => {
      getProduct();
      fetchCategories();
    });

    return {
      editedProduct,
      selectedCategory,
      categories,
      updateProduct,
    };
  },
};
</script>

<style scoped>
</style>
