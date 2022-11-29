<template>
  <div style="padding: 10px">
    <!--    功能区域-->
<!--    <div style="display: flex">-->
<!--      <el-upload-->
<!--          class="upload-demo"-->
<!--          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"-->
<!--          style="margin-right: 10px"-->
<!--      >-->
<!--        <el-button type="primary">Upload</el-button>-->
<!--      </el-upload>-->
<!--      <el-button type="primary">Download</el-button>-->
<!--    </div>-->
    <el-table :data="tableData" border style="width: 100%" :max-height="tableHeight" :row-class-name="tableRowClassName">
      <el-table-column prop="id" label="ID" width="80" sortable/>
      <el-table-column prop="id1" label="ID1" width="50"/>
      <el-table-column prop="code1" label="Code1" width="290"/>
      <el-table-column prop="id2" label="ID2" width="50"/>
      <el-table-column prop="code2" label="Code2" width="290" />
      <el-table-column
          prop="result"
          label="Result"
          width="155"
          :filters="[
            { text: 'EQUAL', value: 'EQUAL' },
            { text: 'INEQUAL', value: 'INEQUAL' },
            { text: 'SAME', value: 'SAME' },
          ]"
          :filter-method="filterResult"
          filter-placement="bottom-end">
        <template #default="scope">
          <el-tag
              :type="getType(scope.row.result)"
              disable-transitions
              style="width: 130px"
          >{{ scope.row.result }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="Operations">
        <el-button @click="handleChange()">Change result</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
// @ is an alias to /src
import { UploadFilled } from '@element-plus/icons-vue'
import { ref } from 'vue'
import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {UploadFilled},
  data() {
    return {
      visible : ref(false),
      tableHeight: 0,
      tableData:[],
      search:'',
      currentPage:1,
      total:10,
      tableRowClassName: ({row,rowIndex}) => {
        switch(row.level) {
          case "UNRELIABLE":
            return 'warning-row'
          case "RELIABLE":
            return 'success-row'
          case "SUSPICIOUS":
            return 'danger-row'
        }
      }
    }
  },
  created() {
    this.load()
  },
  methods : {
    load() {
      request.get("api/codes").then(
          res=>{
            console.log(res)
            for (let entry of res) {
              this.tableData.push(
                  {
                    id: entry.id,
                    id1: entry.code1.id,
                    id2: entry.code2.id,
                    code1: entry.code1.path,
                    code2: entry.code2.path,
                    result: entry.result,
                    level: entry.level
                  }
              );
            }
          }
      );
    },
    handleChange() {

    },
    filterResult(value,row) {
      return row.result === value
    },
    getType(str) {
      switch (str) {
        case 'EQUAL':
          return '';
        case 'INEQUAL':
          return 'danger';
        case 'SAME':
          return 'success';
      }
    }
  },
    mounted() {
      this.tableHeight = window.innerHeight - 115;
    }
}
</script>
<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}
.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
.el-table .danger-row {
  --el-table-tr-bg-color: var(--el-color-danger-light-9);
}
</style>