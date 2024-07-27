<template>
  <div class="q-pa-md">
    <h3>Editar Producto</h3>
    <q-form @submit="updatePaqueteria" v-if="editedPaqueteria">
      <q-input label="ID" v-model="editedPaqueteria.idpaqueteria" disabled />
      <q-input label="Nombre" v-model="editedPaqueteria.nombrepaqueteria" />
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
    const paqueteriaID = router.currentRoute.value.params.idpaqueteria;
    const editedPaqueteria = ref(null);

    const getPaqueteria = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/paqueterias/${paqueteriaID}`);
        if (response.ok) {
          editedPaqueteria.value = await response.json();
        } else {
          console.error('Error al obtener la paquetería:', response.statusText);
        }
      } catch (error) {
        console.error('Error al obtener la paquetería:', error);
      }
    };

    const updatePaqueteria = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/paqueterias/${paqueteriaID}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(editedPaqueteria.value)
        });

        if (response.ok) {
          console.log('Paquetería actualizada correctamente');
          router.push('/shippingCompanies'); // Redirigir a la lista de paqueterías después de actualizar
        } else {
          console.error('Error al actualizar la paquetería:', response.statusText);
        }
      } catch (error) {
        console.error('Error al actualizar la paquetería:', error);
      }
    };

    onMounted(() => {
      getPaqueteria();
    });

    return {
      editedPaqueteria,
      updatePaqueteria,
    };
  },
};
</script>

<style scoped>
</style>
