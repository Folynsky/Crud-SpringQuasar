<template>
  <div class="q-pa-md">
    <h3>Registra Paqueteria</h3>
    <q-form @submit="createPaqueteria">
      <q-input label="Nombre" v-model="newPaqueteria.nombrepaqueteria" />
      <q-btn type="submit" label="Registrar paqueteria" color="primary" class="q-mt-md" />
    </q-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newPaqueteria: {
        nombrepaqueteria: '',

      },
    };
  },
  methods: {
    async createPaqueteria() {
      try {
        const response = await fetch('http://localhost:8080/api/paqueterias', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.newPaqueteria),
        });
        if (response.ok) {
          console.log('Categoria registrada correctamente');
          this.$router.push('/shippingCompanies'); // Redirigir a la página principal después de crear el usuario
        } else {
          console.error('Error al registrar la paqueteria:', response.statusText);
        }
      } catch (error) {
        console.error('Error al registrar la paqueteria:', error);
      }
    },
  },
};
</script>

<style scoped>
</style>
