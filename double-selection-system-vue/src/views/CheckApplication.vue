<template>
  <div>
    <div>
      <span style="color: rgb(65, 135, 192)"> 申请单处理状态：</span
      ><span style="color: orange">{{ application.state }} </span>
    </div>
    <div id="stateClose">
      <router-link to="/index">close</router-link>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  setup() {
    const store = useStore();
    const application = computed(() => store.state.application);
    return {
      application,
    };
  },
});
</script>

<style scoped>
a {
  text-decoration: none;
  font-size: 16.5px;
  color: rgb(146, 145, 145);
  display: inline-block;
}
a:hover {
  color: black;
  transform: scale(1.1);
  cursor: pointer;
}
#stateClose {
  position: fixed;
  right: 30px;
  top: 28vh;
}
</style>
