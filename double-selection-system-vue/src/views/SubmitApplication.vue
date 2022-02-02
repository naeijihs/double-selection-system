<template>
  <div>
    <div>
      <textarea
        cols="30"
        rows="10"
        v-model="introduction"
        placeholder="自我介绍"
      ></textarea>
    </div>
    <div>
      <a @click="submit(introduction)">submit</a>
      <router-link to="/index">close</router-link>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useRoute } from "vue-router";
import { useStore } from "vuex";
const useSubmit = (store: any, route: any) => {
  const submit = (introduction: string) => {
    store.dispatch("submit", {
      introduction,
      tid: route.params.tid,
    });
  };
  return {
    submit,
  };
};
export default defineComponent({
  setup() {
    const route = useRoute();
    const store = useStore();
    const introduction = ref("");
    const { submit } = useSubmit(store, route);
    return {
      introduction,
      submit,
    };
  },
});
</script>

<style scoped>
textarea {
  font-size: 15px;
}
a {
  text-decoration: none;
  font-size: 16.5px;
  color: gray;
  display: inline-block;
}
a:hover {
  color: black;
  transform: scale(1.1);
  cursor: pointer;
}
#close {
  display: inline-block;
  width: 70px;
  height: 35px;
}
:first-child a {
  margin-left: 45px;
  margin-right: 80px;
}
</style>
