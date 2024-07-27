<template>
  <div class="q-pa-md">
    <q-table
      flat
      bordered
      title="Paqueterías"
      :rows="paqueterias"
      :columns="columns"
      row-key="idpaqueteria"
    >
      <template v-slot:body="props">
        <q-tr :props="props" @click="onRowClick(props.row)">
          <q-td key="idpaqueteria" :props="props">
            {{ props.row.idpaqueteria }}
          </q-td>
          <q-td key="nombre" :props="props">
            {{ props.row.nombrepaqueteria }}
          </q-td>
          <q-td key="actions" :props="props" class="text-center">
            <q-btn
              icon="edit"
              round
              dense
              color="yellow"
              class="q-ml-md q-mb-md"
              @click="
                $router.push({
                  name: 'updateCompany',
                  params: { idpaqueteria: props.row.idpaqueteria },
                })
              "
            />
            <q-btn
              icon="delete"
              round
              dense
              color="red"
              class="q-ml-md q-mb-md"
              @click="openDeleteDialog(props.row.idpaqueteria)"
            />
          </q-td>
        </q-tr>
      </template>
    </q-table>
    <div class="button-container">
      <q-btn
        icon="add"
        label="Crear"
        color="primary"
        class="q-ml-md q-mb-md"
        @click="$router.push('/createCompany')"
      />
      <q-btn
        icon="picture_as_pdf"
        label="Exportar PDF"
        color="primary"
        class="q-ml-md q-mb-md"
        @click="exportToPDF"
      />
      <q-btn
        icon="table_view"
        label="Exportar Excel"
        color="primary"
        class="q-ml-md q-mb-md"
        @click="exportToExcel"
      />
    </div>
    <q-dialog v-model="showDeleteDialog">
      <q-card>
        <q-card-section>
          <q-card-title>Eliminar Paquetería</q-card-title>
          <q-card-text>
            ¿Estás seguro de que deseas eliminar esta paquetería?
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
            @click="
              deletePaqueteria(selectedPaqueteriaId);
              showDeleteDialog = false;
            "
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
      paqueterias: [],
      columns: [
        { name: "idpaqueteria", label: "ID" },
        { name: "nombre", label: "Nombre" },
        { name: "actions", label: "Acciones", align: "center" },
      ],
      showDeleteDialog: false,
      selectedPaqueteriaId: null,
    };
  },

  mounted() {
    this.getPaqueterias();
  },

  methods: {
    async getPaqueterias() {
      try {
        const response = await fetch("http://localhost:8080/api/paqueterias");
        if (response.ok) {
          this.paqueterias = await response.json();
        } else {
          console.error(
            "Error al obtener las paqueterías:",
            response.statusText
          );
        }
      } catch (error) {
        console.error("Error al obtener las paqueterías:", error);
      }
    },

    async updateCompany(paqueteria) {
      try {
        this.$router.push({ path: `/updateCompany/${paqueteria.id}` });
      } catch (error) {
        console.error("Error al actualizar paquetería:", error);
      }
    },

    openDeleteDialog(idpaqueteria) {
      this.selectedPaqueteriaId = idpaqueteria;
      this.showDeleteDialog = true;
    },

    async deletePaqueteria(idpaqueteria) {
      try {
        const response = await fetch(
          `http://localhost:8080/api/paqueterias/${idpaqueteria}`,
          {
            method: "DELETE",
          }
        );
        if (response.ok) {
          console.log(
            `Paquetería con ID ${idpaquueteria} eliminada correctamente`
          );
          this.getPaqueterias(); // Actualizar la lista de paqueterías
        } else {
          console.error(
            "Error al eliminar la paquetería:",
            response.statusText
          );
        }
      } catch (error) {
        console.error("Error al eliminar la paquetería:", error);
      }
    },
    async onRowClick(row) {},

    exportToPDF() {
      const doc = new jsPDF({ orientation: "landscape" });
      const columns = this.columns
        .filter((col) => col.name !== "actions")
        .map((col) => col.label);
      const rows = this.paqueterias.map((paquete) => [
        paquete.idpaqueteria,
        paquete.nombrepaqueteria,
      ]);

      doc.autoTable(columns, rows);
      doc.save("paqueterias.pdf");
    },

    exportToExcel() {
      const workbook = new ExcelJS.Workbook();
      const worksheet = workbook.addWorksheet("Paqueterias");

      worksheet.columns = this.columns
        .filter((col) => col.name !== "actions")
        .map((col) => ({ header: col.label, key: col.name }));

      this.paqueterias.forEach((paquete) => {
        worksheet.addRow(paquete);
      });

      workbook.xlsx.writeBuffer().then((buffer) => {
        const blob = new Blob([buffer], {
          type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
        });
        const link = document.createElement("a");
        link.href = URL.createObjectURL(blob);
        link.download = "paqueterias.xlsx";
        link.click();
      });
    },
  },
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

