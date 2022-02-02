<template>
  <div id="modify">
    <div v-if="!isclick">
      <a @click="click">密码修改</a>
    </div>
    <div v-else>
      <input
        type="password"
        v-model="newPassword"
        placeholder="请输入新密码"
        autofocus
      />
      <button @click="modify">修改</button>
      <button @click="modifyClose">关闭</button>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, Ref, ref } from "vue";
import { useStore } from "vuex";
const useModify = (isclick: Ref, newPassword: Ref, store: any) => {
  const click = () => {
    isclick.value = true;
  };
  const modifyClose = () => {
    isclick.value = false;
    newPassword.value = "";
  };
  const modify = () => {
    if (newPassword.value.trim()) {
      store.dispatch("modify", {
        password: newPassword.value,
      });
    } else alert("密码不能为空或空格");
    newPassword.value = "";
  };
  return {
    click,
    modify,
    modifyClose,
  };
};
export default defineComponent({
  setup() {
    const isclick = ref<boolean>(false);
    const newPassword = ref("");
    const store = useStore();
    const { click, modify, modifyClose } = useModify(
      isclick,
      newPassword,
      store
    );
    return {
      isclick,
      click,
      newPassword,
      modify,
      modifyClose,
    };
  },
});
</script>

<style scoped>
#modify {
  width: 230px;
  height: 25px;
  position: fixed;
  bottom: 8px;
  left: 28px;
  z-index: 15;
  border-radius: 10px;
}
a {
  display: block;
  text-decoration: none;
  cursor: pointer;
  color: rgb(136, 136, 136);
}
a:hover {
  color: black;
}
input {
  border: 0.5px solid silver;
  color: gray;
  border-radius: 3px;
}
button {
  border: 0.5px solid silver;
  color: gray;
  border-radius: 3px;
  background-color: white;
  cursor: pointer;
}
button:hover {
  background-color: rgb(79, 166, 236);
  border: 0.5px solid rgb(79, 166, 236);
  color: white;
}
button:focus {
  outline: none;
}
</style>