<template>
  <el-space>
    <SelectedStudentsOfTeacher />
    <UnsettledApplications @click="onRefresh" />
  </el-space>
</template>

<script lang="ts">
import { defineComponent, inject } from "vue";
import { useStore } from "vuex";
import SelectedStudentsOfTeacher from "../views/SelectedStudentsOfTeacher.vue";
import UnsettledApplications from "../views/UnsettledApplications.vue";
const useTeacher = (store: any) => {
  const getIndex = () => {
    store.dispatch("getTeacherIndex");
  };
  return {
    getIndex,
  };
};
export default defineComponent({
  components: {
    SelectedStudentsOfTeacher,
    UnsettledApplications,
  },
  setup() {
    const store = useStore();
    const onRefresh = inject("reload");
    const { getIndex } = useTeacher(store);
    getIndex();
    return {
      onRefresh,
    };
  },
});
</script>

<style scoped>
#teacherIndex {
  display: flex;
  padding-top: 15px;
  padding-left: 10px;
  padding-right: 10px;
}
</style>
