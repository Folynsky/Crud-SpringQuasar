<template>
  <div class="q-pa-md">
    <h1 class="text-h5 text-center">Crear Usuario</h1>
    <q-form @submit.prevent="createUser" class="q-gutter-md">
      <q-input filled label="Nombre" v-model="newUser.nombre" />
      <q-input filled label="Primer Apellido" v-model="newUser.apellido1" />
      <q-input filled label="Segundo Apellido" v-model="newUser.apellido2" />
      <q-input filled label="Nombre de Usuario" v-model="newUser.nombreusuario" />
      <q-input filled type="password" label="Contraseña" v-model="newUser.contraseña" />
      <q-input
        filled
        type="tel"
        label="Número de Teléfono"
        v-model="newUser.numtel"
        :rules="[ val => (val && val.length === 10 && /^\d+$/.test(val)) || 'El número de teléfono debe tener 10 dígitos.']"
      />
      <q-input
        filled
        type="email"
        label="Correo Electrónico"
        v-model="newUser.correo"
        :rules="[ val => /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(val) || 'Por favor ingrese una dirección de correo válida.']"
      />
      <q-btn type="submit" label="Crear Usuario" color="primary" class="full-width" />
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
        idtipousuario: 1,  // Tipo de usuario por defecto
      },
    };
  },
  methods: {
    async createUser() {
      try {
        const response = await fetch('http://localhost:8080/api/usuarios', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.newUser),
        });
        if (response.ok) {
          console.log('Usuario creado correctamente');
          this.$router.push('/'); // Redirigir a la página principal después de crear el usuario
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
.full-width {
  width: 100%;
}
.text-center {
  text-align: center;
}
</style>
