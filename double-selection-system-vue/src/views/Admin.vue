<template>
  <div v-loading="tag">
    <div id="file">
      <input type="file" @change="read" style="cursor: pointer" />
    </div>
    <el-space wrap>
      <el-card class="box-card" style="width: 48vw">
        <el-table :data="students" style="width: 100%" max-height="550">
          <el-table-column
            fixed
            prop="user.name"
            label="student-name"
            width="250"
            align="center"
          />
          <el-table-column
            prop="user.number"
            label="student-number"
            width="250"
            align="center"
          />
        </el-table>
      </el-card>
      <el-card class="box-card" style="width: 48vw">
        <el-table :data="teachers" style="width: 100%" max-height="550">
          <el-table-column
            fixed
            prop="user.name"
            label="teacher-name"
            width="250"
            align="center"
          />
          <el-table-column
            prop="user.number"
            label="teacher-number"
            width="250"
            align="center"
          />
        </el-table>
      </el-card>
    </el-space>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, inject, ref } from "vue";
import { useStore } from "vuex";
import { readFile } from "../util/excelUtils";
const useAdmin = (store: any, onRefresh: any, tag: any) => {
  const getAdminIndex = () => {
    store.dispatch("getAdminIndex");
  };
  const read = async (event: any) => {
    tag.value = true;
    const file = event.target.files[0];
    const data = await readFile(file);
    store.dispatch("addTS", data);
    setTimeout(onRefresh, 1000);
  };
  return {
    getAdminIndex,
    read,
  };
};
export default defineComponent({
  setup() {
    const tag = ref(false);
    const store = useStore();
    const students = computed(() => store.state.students);
    const teachers = computed(() => store.state.teachers);
    const onRefresh = inject("reload");
    const { getAdminIndex, read } = useAdmin(store, onRefresh, tag);
    getAdminIndex();
    return {
      students,
      teachers,
      read,
      tag,
    };
  },
});
</script>

<style scoped>
#file {
  width: 150px;
  height: 50px;
  margin: 0 auto;
}
input {
  margin-top: 17px;
}
</style>
