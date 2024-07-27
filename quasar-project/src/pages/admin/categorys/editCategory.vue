<template>
  <div class="q-pa-md">
    <h3>Editar Categoria</h3>
    <q-form @submit="updateCategory" v-if="editedCategory">
      <q-input label="Nombre" v-model="editedCategory.nombre" />
      <q-input label="Descripcion" v-model="editedCategory.descripcion" />
      <q-input label="Imagen" v-model="editedCategory.imgurl" />
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
    const categoryID = router.currentRoute.value.params.idcategoria;
    const editedCategory = ref(null);

    const getCategory = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/categorias/${categoryID}`);
        if (response.ok) {
          editedCategory.value = await response.json();
        } else {
          console.error('Error al obtener la categoria:', response.statusText);
        }
      } catch (error) {
        console.error('Error al obtener la categoria:', error);
      }
    };

    const updateCategory = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/categorias/${categoryID}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(editedCategory.value)
        });

        if (response.ok) {
          console.log('Categoria actualizada correctamente');
          router.push('/categorys'); // Redirigir a la lista de categorías después de actualizar
        } else {
          console.error('Error al actualizar la categoría:', response.statusText);
        }
      } catch (error) {
        console.error('Error al actualizar la categoría:', error);
      }
    };

    onMounted(() => {
      getCategory();
    });

    return {
      editedCategory,
      updateCategory,
    };
  },
};
</script>

<style scoped>
</style>
