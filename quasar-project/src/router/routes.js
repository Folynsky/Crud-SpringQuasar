const routes = [
  {
    path: '/login',
  component: () => import('layouts/MainLayout.vue'),
  meta: { requiresAuth: true },
  children: [
    { path: '', component: () => import('pages/IndexPage.vue') },

      //paths apartado usuarios
      { path: '/users', component: () => import('pages/admin/users/IndexUsers.vue') },
      { path: '/createUser', component: () => import('pages/admin/users/createUser.vue') },
      {
        path: '/editUser/:idusuario',
        name: 'editUser',
        component: () => import('pages/admin/users/editUser.vue')
      },

      //paths apartado categorias
      { path: '/categorys', component: () => import('pages/admin/categorys/IndexCategorys.vue') },
      { path: '/createCategory', component: () => import('pages/admin/categorys/createCategory.vue') },
      {
        path: '/updateCategory/:idcategoria',
        name: 'updateCategory',
        component: () => import('pages/admin/categorys/editCategory.vue')
      },



      //paths apartado productos
      { path: '/products', component: () => import('pages/admin/products/IndexProducts.vue') },
      { path: '/createProduct', component: () => import('pages/admin/products/createProduct.vue') },
      {
        path: '/updateProduct/:idproducto',
        name: 'updateProduct',
        component: () => import('pages/admin/products/editProduct.vue')
      },

      //paths apartado paqueterias
      { path: '/shippingCompanies', component: () => import('pages/admin/shippingCompanies/IndexCompanies.vue') },
      { path: '/createCompany', component: () => import('pages/admin/shippingCompanies/createCompany.vue') },
      {
        path: '/updateCompany/:idpaqueteria',
        name: 'updateCompany',
        component: () => import('pages/admin/shippingCompanies/updateShippingCompany.vue')
      }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
