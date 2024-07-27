<template>
  <div class="login-layout">
    <div class="login-container">
      <h2>Login</h2>
      <input type="text" v-model="correo" placeholder="Correo" class="login-input">
      <input type="password" v-model="contraseña" placeholder="Contraseña" class="login-input">
      <div class="button-container">
        <button @click="login" class="login-button">Iniciar sesión</button>
        <button @click="create" class="login-button">Crear Cuenta</button>
      </div>
    </div>

    <!-- Modal de Carga y Error -->
    <q-dialog v-model="showModal">
      <q-card class="modal-card">
        <q-card-section>
          <div v-if="errorMessage" class="text-h6">Error</div>
          <div v-if="errorMessage">{{ errorMessage }}</div>
        </q-card-section>
        <q-card-section>
          <div class="container">
            <div class="tree">
              <div class="branch" style="--x:0">
                <span style="--i:0;"></span>
                <span style="--i:1;"></span>
                <span style="--i:2;"></span>
                <span style="--i:3;"></span>
              </div>
              <div class="branch" style="--x:1">
                <span style="--i:0;"></span>
                <span style="--i:1;"></span>
                <span style="--i:2;"></span>
                <span style="--i:3;"></span>
              </div>
              <div class="branch" style="--x:2">
                <span style="--i:0;"></span>
                <span style="--i:1;"></span>
                <span style="--i:2;"></span>
                <span style="--i:3;"></span>
              </div>
              <div class="branch" style="--x:3">
                <span style="--i:0;"></span>
                <span style="--i:1;"></span>
                <span style="--i:2;"></span>
                <span style="--i:3;"></span>
              </div>
              <div class="stem">
                <span style="--i:0;"></span>
                <span style="--i:1;"></span>
                <span style="--i:2;"></span>
                <span style="--i:3;"></span>
              </div>
              <span class="shadow"></span>
            </div>
          </div>
        </q-card-section>
        <q-card-actions align="right" class="card-actions-bottom">
          <q-btn flat label="Cerrar" color="primary" @click="closeModal"></q-btn>
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>


<script>
export default {
  data() {
    return {
      correo: '',
      contraseña: '',
      showModal: false, // Controla la visibilidad del modal
      errorMessage: '' // Mensaje de error
    };
  },
  methods: {
    login() {
      this.showModal = true; // Muestra el modal al iniciar sesión
      this.errorMessage = ''; // Resetea el mensaje de error

      fetch('http://localhost:8080/api/usuarios/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          correo: this.correo,
          contraseña: this.contraseña,
        })
      })
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Credenciales incorrectas');
        }
      })
      .then(data => {
        this.showModal = false; // Oculta el modal si el login es exitoso
        if (data.idtipousuario === 2) {
          this.$router.push('/main');
        } else {
          this.$router.push('/showProducts');
        }
      })
      .catch(error => {
        this.errorMessage = error.message; // Muestra el mensaje de error
      });
    },
    create() {
      this.$router.push('/register');
    },
    closeModal() {
      this.showModal = false;
    }
  }
};
</script>

<style>
.login-layout {
  background-image: url('https://cdnb.artstation.com/p/assets/images/images/049/626/965/large/terry-anderson-hunter-mortenson-makima-x-image-face.jpg?1652919064'); /* Cambia esto a la ruta de tu imagen */
  background-size: cover; /* Hace que la imagen cubra toda la página */
  background-position: center; /* Centra la imagen en la página */
  background-repeat: no-repeat; /* Evita que la imagen se repita */
  height: 100vh; /* Asegura que la imagen cubra toda la altura de la ventana */
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
  background: rgba(255, 255, 255, 0.8); /* Fondo blanco semitransparente para el contenedor de login */
}

.login-input {
  width: 100%;
  margin-bottom: 10px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.button-container {
  display: flex;
  justify-content: space-between;
}

.login-button {
  width: calc(50% - 10px); /* Ajusta el ancho para dejar un espacio entre los botones */
  padding: 8px;
  margin: 0 5px; /* Añade un margen a la izquierda y a la derecha */
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.login-button:hover {
  background-color: #0056b3;
}

.container {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.tree {
  position: relative;
  width: 50px;
  height: 50px;
  transform-style: preserve-3d;
  transform: rotateX(-20deg) rotateY(30deg);
  animation: treeAnimate 5s linear infinite;
}

@keyframes treeAnimate {
  0% {
    transform: rotateX(-20deg) rotateY(360deg);
  }
  100% {
    transform: rotateX(-20deg) rotateY(0deg);
  }
}

.tree div {
  position: absolute;
  top: -30px;
  left: 0;
  width: 100%;
  height: 100%;
  transform-style: preserve-3d;
  transform: translateY(calc(25px * var(--x))) translateZ(0px);
}

.tree div.branch span {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, #69c069, #77dd77);
  clip-path: polygon(50% 0%, 0% 100%, 100% 100%);
  border-bottom: 5px solid #00000019;
  transform-origin: bottom;
  transform: rotateY(calc(90deg * var(--i))) rotateX(30deg) translateZ(28.5px);
}

.tree div.stem span {
  position: absolute;
  top: 110px; /* Ajusta el valor top */
  left: calc(50% - 7.5px);
  width: 15px;
  height: 50%;
  background: linear-gradient(90deg, #bb4622, #df7214);
  border-bottom: 5px solid #00000019;
  transform-origin: bottom;
  transform: rotateY(calc(90deg * var(--i))) translateZ(7.5px);
}

.shadow {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  filter: blur(20px);
  transform-style: preserve-3d;
  transform: rotateX(90deg) translateZ(-65px);
}

.modal-card {
  min-width: 300px;
  min-height: 200px;
  max-width: 500px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
}

.card-actions-bottom {
  margin-top: 30px; /* Asegura que el margen superior sea lo suficiente grande para empujar el botón hacia abajo */
}

.modal-card .q-card-section:first-of-type {
  margin-bottom: 20px;
}
</style>

