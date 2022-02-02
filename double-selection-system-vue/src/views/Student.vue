<template>
  <div id="studentIndex">
    <el-table :data="teachers" style="width: 66%" max-height="653">
      <el-table-column
        fixed
        prop="user.name"
        label="teacher name"
        width="200"
        align="center"
      />
      <el-table-column
        prop="currentNum"
        label="currentNum"
        width="200"
        align="center"
      />
      <el-table-column
        prop="maxNum"
        label="maxNum"
        width="200"
        align="center"
      />
      <el-table-column
        fixed="right"
        label="Operation"
        width="200"
        align="center"
      >
        <template #default="scope">
          <el-button
            type="text"
            size="small"
            @click.prevent="toApplication(scope.row.id)"
          >
            to detail
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <router-view name="application" />
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, inject } from "vue";
import { Router, useRouter } from "vue-router";
import { useStore } from "vuex";
const useStudent = (store: any, router: Router, onRefresh: any) => {
  const getTeachers = () => {
    store.dispatch("getTeachers");
  };
  const toApplication = (tid: number) => {
    router.push({
      name: "application",
      params: {
        tid,
      },
    });
    onRefresh();
  };
  return {
    getTeachers,
    toApplication,
  };
};
export default defineComponent({
  setup() {
    const onRefresh = inject("reload");
    const router = useRouter();
    const store = useStore();
    const teachers = computed(() => store.state.teachers);
    const { getTeachers, toApplication } = useStudent(store, router, onRefresh);
    getTeachers();
    return {
      teachers,
      toApplication,
    };
  },
});
</script>

<style scoped>
#studentIndex {
  display: flex;
  padding-top: 10px;
}
</style>
