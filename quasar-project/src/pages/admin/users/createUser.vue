<template>
  <div class="q-pa-md">
    <h1>Crear Usuario</h1>
    <q-form @submit="createUser">
      <q-input label="Nombre" v-model="newUser.nombre" />
      <q-input label="Apellido1" v-model="newUser.apellido1" />
      <q-input label="Apellido2" v-model="newUser.apellido2" />
      <q-input label="Nombre de Usuario" v-model="newUser.nombreusuario" />
      <q-input label="Contraseña" v-model="newUser.contraseña" />
      <q-input label="Número de Teléfono" v-model="newUser.numtel" />
      <q-input label="Correo Electrónico" v-model="newUser.correo" />
      <q-input label="RFC" v-model="newUser.rfc" />
      <q-input label="ID de Dirección" v-model="newUser.iddireccion" />
      <q-input label="ID de Tipo de Usuario" v-model="newUser.idtipousuario" />
      <q-btn type="submit" label="Crear Usuario" color="primary" class="q-mt-md" />
    </q-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newUser: {
        nombre: '',
        apellido1: '',
        apellido2: '',
        nombreusuario: '',
        contraseña: '',
        numtel: '',
        correo: '',
        rfc: '',  // Inicializado a cadena vacía; si no se ingresa nada, sigue siendo una cadena vacía
        iddireccion: '',  // Inicializado a cadena vacía, pero convertiremos a null si está vacío al enviar
        idtipousuario: '',
      },
    };
  },
  methods: {
    async createUser() {
      // Verificar y ajustar los valores antes de enviar
      const preparedUser = {
        ...this.newUser,
        iddireccion: this.newUser.iddireccion || null, // Convertir a null si está vacío
      };

      try {
        const response = await fetch('http://localhost:8080/api/usuarios', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(preparedUser), // Usar el usuario preparado con el valor ajustado
        });
        if (response.ok) {
          console.log('Usuario creado correctamente');
          this.$router.push('/users'); // Redirigir a la página principal después de crear el usuario
        } else {
          console.error('Error al crear usuario:', response.statusText);
        }
      } catch (error) {
        console.error('Error al crear usuario:', error);
      }
    },
  },
};
</script>


<style scoped>
</style>
