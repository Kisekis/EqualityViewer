<template>
  <div style="padding: 10px" >
    <div style="margin: 0 0 10px">
      <!--      Please select a new result! :-->
      <el-button type="primary" plain @click="handleClick('EQUAL')">Equal</el-button>
      <el-button type="danger" plain @click="handleClick('INEQUAL')">Inequal</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%" :max-height="tableHeight">
      <el-table-column prop="code1" label="Code1" style="width: 50%"/>
      <el-table-column prop="code2" label="Code2" style="width: 50%"/>
    </el-table>
    <div id="code-diff-box">
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import CodeMirror from 'codemirror'
import DiffMatchPatch from 'diff-match-patch';
import 'codemirror/lib/codemirror.css'
import 'codemirror/addon/merge/merge.js'
import 'codemirror/addon/merge/merge.css'
import { useRoute } from 'vue-router'
import request from "@/utils/request";
import router from '@/router/index.js'
window.diff_match_patch = DiffMatchPatch
window.DIFF_DELETE = -1
window.DIFF_INSERT = 1
window.DIFF_EQUAL = 0
export default {
  name: 'EditorView',
  components: {
  },
  data() {
    return {
      carryID: 0,
      carryCurrentRowCode: {},
      tableHeight: 0,
      tableData:[
      ],
      content1: '',
      content2: 'this is a sample code \n implements code difference'
    }
  },methods : {
    handleClick(str) {
      this.tableData[0].result = str
      let obj = {
        code1: {
          path: this.tableData[0].code1,
          id: this.tableData[0].id1
        },
        code2: {
          path: this.tableData[0].code2,
          id: this.tableData[0].id2
        },
        result: str,
        level: "RELIABLE",
        id: this.tableData[0].id
      }
      request.put("api/codes/"+this.tableData[0].id,obj)
      const routers = router.push({
        path: "/table",
      });
    },
    load(carryID) {
      request.get("api/codes/"+carryID).then(
          entry=>{
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
            request.get("api/code/"+entry.code1.id).then(res=>{this.content1 = res;this.diffCode()})
            request.get("api/code/"+entry.code2.id).then(res=>{this.content2 = res;this.diffCode()})

          }
      );
    },
    diffCode(){
      let target = document.getElementById("code-diff-box");
      target.innerHTML = "";
      CodeMirror.MergeView(target, {
        value:this.content1,
        origRight: null,
        orig:this.content2,
        lineNumbers: true,
        mode: "text/html",
        highlightDifferences: true,
        // connect: 'align',
        readOnly: false,
      });
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

    },
  created() {
    const route = useRoute()
    this.load(route.query.currentID)
  }
}
</script>
