<template>
  <div class="q-pa-md">
    <h3>Crear Producto</h3>
    <q-form @submit="handleSubmit">
      <q-input label="Nombre" v-model="newProduct.nombreproducto" />
      <q-input
        label="Precio"
        v-model="newProduct.precio"
        type="number"
        step="0.01"
      />
      <q-input
        label="Existencias"
        v-model="newProduct.existencias"
        type="number"
      />
      <q-input label="Descripción" v-model="newProduct.descripcion" />
      <q-input label="Modelo" v-model="newProduct.modelo" />
      <q-input label="URL de la imagen" v-model="newProduct.imgurl" />

      <q-select
        v-model="selectedCategory"
        label="Categoría"
        :options="
          categories.length > 0
            ? categories.map((category) => ({
                label: `${category.nombre} (${category.idcategoria})`,
                value: category.idcategoria,
              }))
            : []
        "
      />

      <q-btn
        type="submit"
        label="Crear Producto"
        color="primary"
        class="q-mt-md"
      />
      <q-btn
        v-if="selectedCategory !== null"
        @click="resetCategory"
        label="Reiniciar Categoría"
        color="primary"
        flat
        class="q-ml-md"
      />
    </q-form>
  </div>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const router = useRouter();
    const newProduct = ref({
      nombreproducto: "",
      precio: 0,
      existencias: 0,
      descripcion: "",
      modelo: "",
      imgurl: "",
      idcategoria: null,
    });
    const categories = ref([]);
    const selectedCategory = ref(null);

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

    const createProduct = async () => {
  try {
    if (!selectedCategory.value) {
      console.error("Debe seleccionar una categoría");
      return;
    }

    const categoryId = selectedCategory.value.value; // Extraer el ID de la categoría seleccionada
    newProduct.value.idcategoria = categoryId;

    const response = await fetch("http://localhost:8080/api/productos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newProduct.value),
    });

    if (response.ok) {
      console.log("Producto creado correctamente");
      router.push("/products");
    } else {
      console.error("Error al crear el producto:", response.statusText);
    }
  } catch (error) {
    console.error("Error al crear el producto:", error);
  }
};

    const resetCategory = () => {
      selectedCategory.value = null;
    };

    const handleSubmit = () => {
      if (!selectedCategory.value) {
        console.error("Debe seleccionar una categoría");
        return;
      }

      // Obtener el ID de la categoría seleccionada
      const categoryId = selectedCategory.value;

      // Utilizar categoryId en lugar de selectedCategory.value
      createProduct(categoryId);
    };

    fetchCategories();

    return {
      newProduct,
      categories,
      selectedCategory,
      resetCategory,
      handleSubmit,
    };
  },
};
</script>

<style scoped>
</style>
