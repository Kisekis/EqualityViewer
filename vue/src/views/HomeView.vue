<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 0 0 10px">
      <el-button type="primary">Import</el-button>
      <el-button type="primary">Export</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%" :max-height="tableHeight">
      <el-table-column prop="code1" label="Code1" width="380"/>
      <el-table-column prop="code2" label="Code2" width="380" />
      <el-table-column
          prop="result"
          label="Result"
          width="160"
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
          >{{ scope.row.result }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="Operations">

        <template #default>
          <el-button size="small" @click="handleClick">Change result</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
// @ is an alias to /src


export default {
  name: 'HomeView',
  components: {},
  data() {
    return {
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
    handleClick() {

    },
    handleSizeChange() {

    },
    handleCurrentChange() {

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
