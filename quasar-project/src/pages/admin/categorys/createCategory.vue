<template>
    <div class="q-pa-md">
      <h3>Registra Categoria</h3>
      <q-form @submit="createCategory">
        <q-input label="Nombre" v-model="newCategoria.nombre" />
        <q-input label="Descripcion" v-model="newCategoria.descripcion" />
        <q-btn type="submit" label="Registrar categoria" color="primary" class="q-mt-md" />
      </q-form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        newCategoria: {
          nombre: '',
          descripcion: '',

        },
      };
    },
    methods: {
      async createCategory() {
        try {
          const response = await fetch('http://localhost:8080/api/categorias', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(this.newCategoria),
          });
          if (response.ok) {
            console.log('Categoria registrada correctamente');
            this.$router.push('/categorys'); // Redirigir a la página principal después de crear el usuario
          } else {
            console.error('Error al registrar la categoria:', response.statusText);
          }
        } catch (error) {
          console.error('Error al registrar la categoria:', error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  </style>
  