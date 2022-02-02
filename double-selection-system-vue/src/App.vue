<template>
  <div>
    <Index v-if="showPage" />
    <Dialog />
  </div>
</template>  

<script lang="ts">
import {
  defineAsyncComponent,
  defineComponent,
  nextTick,
  provide,
  ref,
} from "vue";
import Index from "../src/views/Index.vue";
const Dialog = defineAsyncComponent(
  () => import("../src/components/Dialog.vue")
);
export default defineComponent({
  components: {
    Index,
    Dialog,
  },
  setup() {
    let showPage = ref<boolean>(true);
    const onRefresh = () => {
      setTimeout(() => {
        showPage.value = false;
        nextTick(() => (showPage.value = true));
      }, 60);
    };
    provide("reload", onRefresh);
    return {
      showPage,
    };
  },
});
</script>


<style>
</style>
