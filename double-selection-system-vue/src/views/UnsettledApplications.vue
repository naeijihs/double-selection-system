<template>
  <el-card class="box-card" style="width: 65vw">
    <el-table
      :data="unsettledApplications"
      style="width: 100%"
      max-height="650"
    >
      <el-table-column
        fixed
        prop="student.user.name"
        label="student name"
        width="200"
        align="center"
      />
      <el-table-column
        prop="selfIntroduction"
        label="selfIntroduction"
        width="200"
        align="center"
      />
      <el-table-column prop="state" label="state" width="200" align="center" />
      <el-table-column
        fixed="right"
        label="Operations"
        width="200"
        align="center"
      >
        <template #default="scope">
          <el-button
            type="text"
            size="small"
            @click.prevent="agree(scope.row.id)"
          >
            agree
          </el-button>
          <el-button
            type="text"
            size="small"
            @click.prevent="refuse(scope.row.id)"
          >
            refuse
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script lang="ts">
import { defineComponent, computed } from "vue";
import { useStore } from "vuex";
const useUA = (store: any) => {
  const agree = (aid: number) => {
    store.dispatch("agree", {
      aid,
    });
  };
  const refuse = (aid: number) => {
    store.dispatch("refuse", {
      aid,
    });
  };
  return {
    agree,
    refuse,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const unsettledApplications = computed(
      () => store.state.unsettledApplications
    );
    const { agree, refuse } = useUA(store);
    return {
      unsettledApplications,
      agree,
      refuse,
    };
  },
});
</script>

<style scoped>
</style>
