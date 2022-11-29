<template>
  <div>
    <el-row class="tac">
      <el-col :span="12">
        <h5 class="mb-2"></h5>
        <el-menu
            router
            default-active="home"
            class="el-menu-vertical-demo">
          <el-menu-item index="home">首页</el-menu-item>
          <el-sub-menu index="1-2">
            <template #title>可视化</template>
            <el-menu-item index="table">Table view</el-menu-item>
            <el-menu-item index="graph">Graph view</el-menu-item>
          </el-sub-menu>
<!--          <el-menu-item index="1-2"></el-menu-item>-->
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Aside",
  components: {
    Document,
    IconMenu,
    Location,
    Setting,
  }
}
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
</script>

<style scoped>
.el-menu-vertical-demo {
  width: 200px;
  min-height: calc(100vh - 50px);
}
</style>