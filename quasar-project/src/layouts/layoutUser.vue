<template>
  <q-layout view="hHh lpR fFf">

    <q-header elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

        <q-toolbar-title>
          <q-avatar>
            <img src="https://tse1.mm.bing.net/th/id/OIG4.EEw2kRfT4lkfBJYbiCmF?pid=ImgGn">
          </q-avatar>
          KJShop3D
        </q-toolbar-title>

        <q-btn dense flat round icon="menu" @click="toggleRightDrawer" />
      </q-toolbar>

      
    </q-header>

    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" bordered>
      <!-- drawer content -->
      <!-- drawer content -->
      <q-list>
        <q-item to="/showProducts" clickable v-riper>
          <q-item-section avatar>
            <q-icon name="home" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Home</q-item-label>
          </q-item-section>
        </q-item>

        <q-item to="/showCategories" clickable v-riper>
          <q-item-section avatar>
            <q-icon name="category" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Categorias</q-item-label>
          </q-item-section>
        </q-item>


      </q-list>

    </q-drawer>

    <q-drawer show-if-above v-model="rightDrawerOpen" side="right" bordered>
      <!-- drawer content -->
      <q-list>
        <q-item clickable v-riper>
          <q-item-section>
            <q-item-label>{{ userName }}</q-item-label>
          </q-item-section>
        </q-item>
        <q-separator />
    
        <q-item to="/account" clickable v-riper>
          <q-item-section avatar>
            <q-icon name="person" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Cuenta</q-item-label>
          </q-item-section>
        </q-item>
    
        <q-item to="/settings" clickable v-riper>
          <q-item-section avatar>
            <q-icon name="settings" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Configuración</q-item-label>
          </q-item-section>
        </q-item>

        <q-item to="/cart" clickable v-riper>
          <q-item-section avatar>
            <q-icon name="shopping_cart" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Carrito</q-item-label>
          </q-item-section>
        </q-item>
        <q-separator />
    
        <q-item to="/" clickable v-ripple>
          <q-item-section avatar>
            <q-icon name="arrow_back" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Salir</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
import { ref } from 'vue'

export default {
  setup () {
    const leftDrawerOpen = ref(false)
    const rightDrawerOpen = ref(false)

    return {
      leftDrawerOpen,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      },

      rightDrawerOpen,
      toggleRightDrawer () {
        rightDrawerOpen.value = !rightDrawerOpen.value
      }
    }
  }
}
</script>