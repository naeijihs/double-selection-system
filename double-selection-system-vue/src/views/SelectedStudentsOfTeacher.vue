<template>
  <el-card class="box-card" style="width: 32vw">
    <el-table :data="selectedStudents" style="width: 100%" max-height="650">
      <el-table-column
        prop="user.name"
        label="selectedStudent name"
        width="200"
        align="center"
      />
      <el-table-column
        prop="user.number"
        label="selectedStudent number"
        width="200"
        align="center"
      />
    </el-table>
  </el-card>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  setup() {
    const store = useStore();
    const selectedStudents = computed(() => store.state.selectedStudents);
    return {
      selectedStudents,
    };
  },
});
</script>

<style scoped>
</style>
