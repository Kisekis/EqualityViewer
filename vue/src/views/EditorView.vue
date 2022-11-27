<template>
  <div style="padding: 10px" >
    <div style="margin: 0 0 10px">
      <!--      Please select a new result! :-->
      <el-button type="primary" plain>Equal</el-button>
      <el-button type="danger" plain>Inequal</el-button>
      <el-button type="success" plain>Same</el-button>
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
      tableHeight: 0,
      tableData:[
        {
          code1: 'AAA',
          code2: 'BBB',
          result: 'Equal'
        }
      ],
      content1: '',
      content2: ''
    }
  },methods : {
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
        readOnly: true,
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
      this.diffCode()
    }
}
</script>
