<template>
  <div id="application">
    <CheckApplication v-if="application.id" />
    <SubmitApplication v-else />
  </div>
</template>

<script lang="ts">
import { computed, defineAsyncComponent, defineComponent } from "vue";
import { useRoute } from "vue-router";
import { useStore } from "vuex";
const CheckApplication = defineAsyncComponent(
  () => import("../views/CheckApplication.vue")
);
const SubmitApplication = defineAsyncComponent(
  () => import("../views/SubmitApplication.vue")
);
const useApplication = (store: any, route: any) => {
  const check = () => {
    store.dispatch("check", {
      tid: route.params.tid,
    });
  };
  return {
    check,
  };
};
export default defineComponent({
  components: {
    CheckApplication,
    SubmitApplication,
  },
  setup() {
    const store = useStore();
    const route = useRoute();
    const application = computed(() => store.state.application);
    const { check } = useApplication(store, route);
    check();
    return {
      application,
    };
  },
});
</script>

<style scoped>
#application {
  position: fixed;
  right: 30px;
  top: 25vh;
}
</style>
