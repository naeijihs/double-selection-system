<template >
  <el-dialog v-model="dialogVisible" title="Tips" width="36%" center="true">
    <span>{{ exception }}</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="close">Confirm</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { computed, defineComponent, ref, watch } from "vue";
import { useStore } from "vuex";
const useDialog = (store: any) => {
  const close = () => {
    store.commit("close");
  };
  return {
    close,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const dialogVisible = ref(false);
    const exception = computed(() => store.state.exception);
    watch(exception, (newValue) => {
      if (newValue == "") dialogVisible.value = false;
      else dialogVisible.value = true;
    });
    const { close } = useDialog(store);
    return {
      dialogVisible,
      close,
      exception,
    };
  },
});
</script>

