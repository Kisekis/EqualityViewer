<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="display: flex">
      <el-upload
          class="upload-demo"
          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
          style="margin-right: 10px"
      >
        <el-button type="primary">Upload</el-button>
      </el-upload>
      <el-button type="primary">Download</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%" :max-height="tableHeight">
      <el-table-column prop="code1" label="Code1" width="380"/>
      <el-table-column prop="code2" label="Code2" width="380" />
      <el-table-column
          prop="result"
          label="Result"
          width="155"
          :filters="[
            { text: 'Equal', value: 'Equal' },
            { text: 'Inequal', value: 'Inequal' },
            { text: 'Same', value: 'Same' },
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
      <el-table-column fixed="right" label="Operations">
        <el-button @click="handleChange()">Change result</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
// @ is an alias to /src
import { UploadFilled } from '@element-plus/icons-vue'
import { ref } from 'vue'

export default {
  name: 'HomeView',
  components: {UploadFilled},
  data() {
    return {
      visible : ref(false),
      tableHeight: 0,
      tableData:[
        {
          code1: 'AAA',
          code2: 'BBB',
          result: 'Equal'
        },
        {
          code1: 'AAAA',
          code2: 'BBBB',
          result: 'Inequal'
        },
        {
          code1: 'AAAAA',
          code2: 'BBBBB',
          result: 'Same'
        },
      ],
      search:'',
      currentPage:1,
      total:10
    }
  },methods : {
    handleChange() {

    },
    filterResult(value,row) {
      return row.result === value
    },
    getType(str) {
      switch (str) {
        case 'Equal':
          return '';
        case 'Inequal':
          return 'danger';
        case 'Same':
          return 'success';
      }
    }
  },
    mounted() {
      this.tableHeight = window.innerHeight - 115;
    }
}
</script>
