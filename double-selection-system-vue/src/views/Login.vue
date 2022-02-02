<template>
  <div id="login">
    <input type="text" placeholder="账号" v-model="number" />
    <input type="password" placeholder="密码" v-model="password" />
    <div id="btn">
      <button @click="login(number, password)">登录</button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useStore } from "vuex";
const useLogin = (store: any) => {
  const login = (number: number, password: string) => {
    store.dispatch("login", {
      number,
      password,
    });
  };
  return {
    login,
  };
};
export default defineComponent({
  setup() {
    let number = ref("");
    let password = ref("");
    const store = useStore();
    const { login } = useLogin(store);
    return {
      number,
      password,
      login,
    };
  },
});
</script>

<style scoped>
#login {
  width: 400px;
  margin: 140px auto;
  border: 1px solid gray;
  padding: 0 10px;
  border-radius: 6px;
}
input {
  width: 300px;
  height: 30px;
  font-size: 17px;
  margin-left: 46px;
  margin-top: 50px;
  border: 1px solid silver;
  border-radius: 6px;
  padding-left: 7px;
}
input:focus {
  outline: none;
  border: 1px solid black;
}
button {
  width: 70px;
  height: 35px;
  font-size: 16.5px;
  cursor: pointer;
  border: 1px solid silver;
  color: gray;
  border-radius: 6px;
  background-color: white;
  margin-left: 160px;
}
button:hover {
  background-color: rgb(79, 166, 236);
  border: 1px solid rgb(79, 166, 236);
  color: white;
}
button:focus {
  outline: none;
}
#btn {
  margin-top: 32px;
  margin-bottom: 28px;
}
</style>