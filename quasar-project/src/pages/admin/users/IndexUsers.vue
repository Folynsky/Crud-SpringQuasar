<template>
  <div class="q-pa-md">
    <q-table
      flat
      bordered
      title="Usuarios"
      :rows="users"
      :columns="columns"
      row-key="idusuario"
    >
      <template v-slot:body="props">
        <q-tr :props="props" @click="onRowClick(props.row)">
          <q-td key="idusuario" :props="props">
            {{ props.row.idusuario }}
          </q-td>
          <q-td key="nombre" :props="props">
            {{ props.row.nombre }}
          </q-td>
          <q-td key="apellido1" :props="props">
            {{ props.row.apellido1 }}
          </q-td>
          <q-td key="apellido2" :props="props">
            {{ props.row.apellido2 }}
          </q-td>
          <q-td key="nombreusuario" :props="props">
            {{ props.row.nombreusuario }}
          </q-td>
          <q-td key="contraseña" :props="props">
            {{ props.row.contraseña }}
          </q-td>
          <q-td key="numtel" :props="props">
            {{ props.row.numtel }}
          </q-td>
          <q-td key="correo" :props="props">
            {{ props.row.correo }}
          </q-td>
          <q-td key="rfc" :props="props">
            {{ props.row.rfc }}
          </q-td>
          <q-td key="iddireccion" :props="props">
            {{ props.row.iddireccion }}
          </q-td>
          <q-td key="idtipousuario" :props="props">
            {{ props.row.idtipousuario }}
          </q-td>
          <q-td key="actions" :props="props" class="text-center">
            <q-btn
              icon="edit"
              round
              dense
              color="yellow"
              class="q-ml-md q-mb-md"
              @click="$router.push({ name: 'editUser', params: {idusuario : props.row.idusuario } })"
            />
            <q-btn
              icon="delete"
              round
              dense
              color="red"
              class="q-ml-md q-mb-md"
              @click="openDeleteDialog(props.row.idusuario)"
            />
          </q-td>
        </q-tr>
      </template>
    </q-table>
    <div class="button-container">
      <q-btn icon="add" label="Crear" color="primary" class="q-ml-md q-mb-md" @click="$router.push('/createUser')" />
      <q-btn icon="picture_as_pdf" label="Exportar PDF" color="primary" class="q-ml-md q-mb-md" @click="exportToPDF" />
      <q-btn icon="table_view" label="Exportar Excel" color="primary" class="q-ml-md q-mb-md" @click="exportToExcel" />
    </div>
    <q-dialog v-model="showDeleteDialog">
      <q-card>
        <q-card-section>
          <q-card-title>Eliminar Usuario</q-card-title>
          <q-card-text>
            ¿Estás seguro de que deseas eliminar este usuario?
          </q-card-text>
        </q-card-section>
        <q-card-actions align="right">
          <q-btn
            label="Cancelar"
            color="primary"
            @click="showDeleteDialog = false"
          />
          <q-btn
            label="Eliminar"
            color="negative"
            @click="deleteUser(selectedUserId); showDeleteDialog = false"
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import jsPDF from "jspdf";
import "jspdf-autotable";
import ExcelJS from "exceljs";

export default {
  data() {
    return {
      users: [],
      columns: [
        { name: "idusuario", label: "ID" },
        { name: "nombre", label: "Nombre" },
        { name: "apellido1", label: "Apellido1" },
        { name: "apellido2", label: "Apellido2" },
        { name: "nombreusuario", label: "Nombre de usuario" },
        { name: "contraseña", label: "Contraseña" },
        { name: "numtel", label: "Teléfono" },
        { name: "correo", label: "Correo" },
        { name: "rfc", label: "RFC" },
        { name: "iddireccion", label: "Dirección" },
        { name: "idtipousuario", label: "Tipo de usuario" },
        { name: "actions", label: "Acciones", align: "center" },
      ],
      showDeleteDialog: false,
      selectedUserId: null,
    };
  },

  mounted() {
    this.getUsers();
  },

  methods: {
    async getUsers() {
      const response = await fetch("http://localhost:8080/api/usuarios");
      this.users = await response.json();
    },

    async editUser(user) {
      try {
        this.$router.push({ path: `/editUser/${user.idusuario}` });
      } catch (error) {
        console.error("Error al redirigir a la página de edición:", error);
      }
    },

    openDeleteDialog(idusuario) {
      this.selectedUserId = idusuario;
      this.showDeleteDialog = true;
    },

    async deleteUser(userId) {
      try {
        const response = await fetch(
          `http://localhost:8080/api/usuarios/${userId}`,
          {
            method: "DELETE",
          }
        );
        if (response.ok) {
          console.log(`Usuario con ID ${userId} eliminado correctamente`);
          this.getUsers();
        } else {
          console.error("Error al eliminar el usuario:", response.statusText);
        }
      } catch (error) {
        console.error("Error al eliminar el usuario:", error);
      }
    },

    async onRowClick(row) {},

    exportToPDF() {
      const doc = new jsPDF({ orientation: 'landscape' });
      const columns = this.columns.filter(col => col.name !== 'actions').map(col => col.label);
      const rows = this.users.map(user => [
        user.idusuario,
        user.nombre,
        user.apellido1,
        user.apellido2,
        user.nombreusuario,
        user.contraseña,
        user.numtel,
        user.correo,
        user.rfc,
        user.iddireccion,
        user.idtipousuario
      ]);

      doc.autoTable(columns, rows);
      doc.save("usuarios.pdf");
    },

    exportToExcel() {
      const workbook = new ExcelJS.Workbook();
      const worksheet = workbook.addWorksheet("Usuarios");

      worksheet.columns
      worksheet.columns = this.columns.filter(col => col.name !== 'actions').map(col => ({ header: col.label, key: col.name }));

      this.users.forEach(user => {
        worksheet.addRow(user);
      });

      workbook.xlsx.writeBuffer().then(buffer => {
        const blob = new Blob([buffer], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
        const link = document.createElement("a");
        link.href = URL.createObjectURL(blob);
        link.download = "usuarios.xlsx";
        link.click();
      });
    }
  }
};
</script>

<style scoped>
.text-center {
  text-align: center;
}

.button-container {
  display: flex;
  justify-content: space-between;
}
</style>
