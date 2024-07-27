<template>
  <div class="q-pa-md">
    <q-table
      flat
      bordered
      title="Productos"
      :rows="productos"
      :columns="columns"
      row-key="idproducto"
    >
      <template v-slot:body="props">
        <q-tr :props="props" @click="onRowClick(props.row)">
          <q-td key="idproducto" :props="props">
            {{ props.row.idproducto }}
          </q-td>
          <q-td key="nombreproducto" :props="props">
            {{ props.row.nombreproducto }}
          </q-td>
          <q-td key="precio" :props="props">
            {{ props.row.precio }}
          </q-td>
          <q-td key="existencias" :props="props">
            {{ props.row.existencias }}
          </q-td>
          <q-td key="descripcion" :props="props">
            {{ props.row.descripcion }}
          </q-td>
          <q-td key="modelo" :props="props">
            {{ props.row.modelo }}
          </q-td>
          <q-td key="imgurl" :props="props">
            <img :src="props.row.imgurl" alt="imagen" style="max-width: 100px; max-height: 100px;">
          </q-td>
          <q-td key="idcategoria" :props="props">
            {{ props.row.idcategoria }}
          </q-td>
          <q-td key="actions" :props="props" class="text-center">
            <q-btn
              icon="edit"
              round
              dense
              color="yellow"
              class="q-ml-md q-mb-md"
              @click="$router.push({ name: 'updateProduct', params: { idproducto : props.row.idproducto } })"
            />
            <q-btn
              icon="delete"
              round
              dense
              color="red"
              class="q-ml-md q-mb-md"
              @click="openDeleteDialog(props.row.idproducto)"
            />
          </q-td>
        </q-tr>
      </template>
    </q-table>
    <div class="button-container">
      <q-btn icon="add" label="Crear" color="primary" class="q-ml-md q-mb-md" @click="$router.push('/createProduct')" />
      <q-btn icon="picture_as_pdf" label="Exportar PDF" color="primary" class="q-ml-md q-mb-md" @click="exportToPDF" />
      <q-btn icon="table_view" label="Exportar Excel" color="primary" class="q-ml-md q-mb-md" @click="exportToExcel" />
    </div>
    <q-dialog v-model="showDeleteDialog">
      <q-card>
        <q-card-section>
          <q-card-title>Eliminar Producto</q-card-title>
          <q-card-text>
            ¿Estás seguro de que deseas eliminar este producto?
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
            @click="deleteProducto(selectedProductId); showDeleteDialog = false"
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
      productos: [],
      columns: [
        { name: "idproducto", label: "ID" },
        { name: "nombreproducto", label: "Nombre" },
        { name: "precio", label: "Precio" },
        { name: "existencias", label: "Existencias" },
        { name: "descripcion", label: "Descripción" },
        { name: "modelo", label: "Modelo" },
        { name: "imgurl", label: "Imagen" },
        { name: "idcategoria", label: "ID de Categoría" },
        { name: "actions", label: "Acciones", align: "center" },
      ],
      showDeleteDialog: false,
      selectedProductId: null,
    };
  },

  mounted() {
    this.getProductos();
  },
  methods: {
    async getProductos() {
      const response = await fetch("http://localhost:8080/api/productos");
      this.productos = await response.json();
    },
    async editProducto(producto) {
      try {
        this.$router.push({ path: `/updateProduct/${producto.idproducto}` });
      } catch (error) {
        console.error("Error al actualizar el producto:", error);
      }
    },
    openDeleteDialog(idproducto) {
      this.selectedProductId = idproducto;
      this.showDeleteDialog = true;
    },
    async deleteProducto(idproducto) {
      try {
        const response = await fetch(
          `http://localhost:8080/api/productos/${idproducto}`,
          {
            method: "DELETE",
          }
        );

        if (response.ok) {
          console.log(`Producto con ID ${idproducto} eliminado correctamente`);
          this.getProductos();
        } else {
          console.error("Error al eliminar el producto:", response.statusText);
        }
      } catch (error) {
        console.error("Error al eliminar el producto:", error);
      }
    },
    async onRowClick(row) {},
    exportToPDF() {
      const doc = new jsPDF({ orientation: 'landscape' });
      const columns = this.columns.filter(col => col.name !== 'actions').map(col => col.label);
      const rows = this.productos.map(producto => [
        producto.idproducto,
        producto.nombreproducto,
        producto.precio,
        producto.existencias,
        producto.descripcion,
        producto.modelo,
        //producto.imgurl,
        producto.idcategoria
      ]);

      doc.autoTable(columns, rows);
      doc.save("productos.pdf");
    },
    exportToExcel() {
      const workbook = new ExcelJS.Workbook();
      const worksheet = workbook.addWorksheet("Productos");

      worksheet.columns = this.columns.filter(col => col.name !== 'actions').map(col => ({ header: col.label, key: col.name }));

      this.productos.forEach(producto => {
        worksheet.addRow(producto);
      });

      workbook.xlsx.writeBuffer().then(buffer => {
        const blob = new Blob([buffer], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
        const link = document.createElement("a");
        link.href = URL.createObjectURL(blob);
        link.download = "productos.xlsx";
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
