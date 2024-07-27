<template>
  <div class="q-pa-md">
    <h1>Editar Usuario</h1>
    <q-form @submit.prevent="updateUser" v-if="editedUser">
      <q-input label="Nombre" v-model="editedUser.nombre" />
      <q-input label="Apellido1" v-model="editedUser.apellido1" />
      <q-input label="Apellido2" v-model="editedUser.apellido2" />
      <q-input label="Nombre de usuario" v-model="editedUser.nombreusuario" />
      <q-input label="Contraseña" v-model="editedUser.contraseña" />
      <q-input label="Teléfono" v-model="editedUser.numtel" />
      <q-input label="Correo" v-model="editedUser.correo" />
      <q-input label="RFC" v-model="editedUser.rfc" />
      <q-input label="Dirección" v-model="editedUser.iddireccion" />
      <q-input label="Tipo de usuario" v-model="editedUser.idtipousuario" />
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
    const userId = router.currentRoute.value.params.idusuario;
    const editedUser = ref(null);

    const getUser = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/usuarios/${userId}`);
        if (response.ok) {
          editedUser.value = await response.json();
        } else {
          console.error('Error al obtener el usuario:', response.statusText);
        }
      } catch (error) {
        console.error('Error al obtener el usuario:', error);
      }
    };

    const updateUser = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/usuarios/${userId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(editedUser.value),
        });

        if (response.ok) {
          console.log('Usuario actualizado correctamente');
          router.push('/users'); // Redirigir a la página de usuarios después de actualizar el usuario
        } else {
          console.error('Error al actualizar el usuario:', response.statusText);
        }
      } catch (error) {
        console.error('Error al actualizar el usuario:', error);
      }
    };

    onMounted(() => {
      getUser();
    });

    return {
      editedUser,
      updateUser,
    };
  },
};
</script>

<style scoped>
</style>
