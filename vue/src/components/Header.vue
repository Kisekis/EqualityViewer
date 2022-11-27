<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">EqualityViewer</div>
    <div style="flex: 1"></div>
  </div>
</template>

<script>
export default {
  name: "Header",
  components: {
    ArrowDown
  }
}
import { ArrowDown } from '@element-plus/icons-vue'
</script>

<style>
.el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>

