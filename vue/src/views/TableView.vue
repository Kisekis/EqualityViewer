<template>
  <div style="padding: 10px">
    <div style="display: flex">
      <el-button type="primary" style="margin-bottom: 10px" @click="handleWrite()">Write</el-button>
      <el-button type="primary" style="margin-bottom: 10px" @click="handleNext()">Next</el-button>
    </div>
    <el-table :data="tableData" border style="width: 100%" :max-height="tableHeight" :row-class-name="tableRowClassName">
      <el-table-column prop="id" label="ID" width="80" sortable/>
      <el-table-column prop="id1" label="ID1" width="75" sortable/>
      <el-table-column prop="code1" label="Code1" width="255"/>
      <el-table-column prop="id2" label="ID2" width="75" sortable/>
      <el-table-column prop="code2" label="Code2" width="255" />
      <el-table-column
          prop="result"
          label="Result"
          width="155"
          :filters="[
            { text: 'EQUAL', value: 'EQUAL' },
            { text: 'INEQUAL', value: 'INEQUAL' },
            { text: 'SAME', value: 'SAME' },
            { text: 'UNCERTAIN', value: 'UNCERTAIN' },
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
        <template #default="scope">
        <el-button @click="handleChange(scope.row)">Change result</el-button>
        </template>
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
// @ is an alias to /src
import { UploadFilled } from '@element-plus/icons-vue'
import { ref } from 'vue'
import request from "@/utils/request";
import router from '@/router/index.js'

export default {
  name: 'HomeView',
  components: {UploadFilled},
  data() {
    return {
      carryCurrentRowCode: {
        questionTypeId: "",
        serial: "",
        questionClassifyId: "",
        questionContent: "",
        degreeInitial: ""
      },
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
    // this.load()
  },
  methods : {
    load() {
      request.get("api/codes").then(
          res=>{
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
    handleWrite() {
      request.get("api/write").then(res => {
        alert("Success")
      })
    },
    handleNext() {
      for(let entry of this.tableData) {
        if(entry.level === "SUSPICIOUS") {
          let currentID = entry.id
          const routers = router.push({
            path: "/editor",
            query: {
              currentID
            }
          });
          return;
        }
      }
      for(let entry of this.tableData) {
        if(entry.level === "UNRELIABLE") {
          let currentID = entry.id
          const routers = router.push({
            path: "/editor",
            query: {
              currentID
            }
          });
          return;
        }
      }
      alert("Already finished")
    },
    handleChange(row) {
      // const router = useRouter()
      let currentID = row.id;
      const routers = router.push({
        path: "/editor",
        query: {
          currentID
        }
      });
      // window.open(routers.href, '_blank');
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
        case 'UNCERTAIN':
          return 'info';
      }
    },
      getUnionfind() {
        request.get("api/unionfind")
      }
  },
  mounted() {
      this.tableHeight = window.innerHeight - 115;
      this.getUnionfind()
      this.load()
    },
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