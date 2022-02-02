<template>
  <div>
    <Login v-if="vrole == '' && srole == null" />
    <Admin
      v-else-if="
        vrole == 'ba66e5f7e0f2ccae835d' || srole == 'ba66e5f7e0f2ccae835d'
      "
    />
    <Teacher
      v-else-if="
        vrole == '6983f953b49c88210cb9' || srole == '6983f953b49c88210cb9'
      "
    />
    <Student
      v-else-if="
        vrole == 'bb63e5f7e0f2ffae845c' || srole == 'bb63e5f7e0f2ffae845c'
      "
    />
    <SelectedStudent
      v-else-if="
        vrole == '7543f954c49c97510cb0' || srole == '7543f954c49c97510cb0'
      "
    />
    <ModifyPassword v-if="vrole != '' || srole != null" />
  </div>
</template>

<script lang="ts">
import {
  computed,
  defineAsyncComponent,
  defineComponent,
  inject,
  ref,
  watch,
} from "vue";
import { useStore } from "vuex";
const SelectedStudent = defineAsyncComponent(
  () => import("../views/SelectedStudent.vue")
);
const ModifyPassword = defineAsyncComponent(
  () => import("../components/ModifyPassword.vue")
);
const Login = defineAsyncComponent(() => import("../views/Login.vue"));
const Student = defineAsyncComponent(() => import("../views/Student.vue"));
const Admin = defineAsyncComponent(() => import("../views/Admin.vue"));
const Teacher = defineAsyncComponent(() => import("../views/Teacher.vue"));
export default defineComponent({
  components: {
    Login,
    SelectedStudent,
    Admin,
    Teacher,
    Student,
    ModifyPassword,
  },
  setup() {
    const store = useStore();
    const onRefresh: any = inject("reload");
    let vrole = computed(() => store.state.role);
    const srole = ref(sessionStorage.getItem("role"));
    watch(vrole, (newValue) => {
      if (newValue == "") onRefresh();
    });
    return {
      vrole,
      srole,
    };
  },
});
</script>

<style scoped>
</style>
