import router from "@/router";
import axios from "axios";
import store from "../store"
axios.defaults.baseURL = "http://localhost:8080";
axios.interceptors.request.use(
    req => {
        const auth = sessionStorage.getItem("auth");
        if (auth && req.headers) {
            req.headers.authorization = auth;
        }
        return req;
    },
    err => {
        return Promise.reject(err);
    });
axios.interceptors.response.use(
    res => {
        return res;
    },
    err => {
        const json = JSON.stringify(err);
        const data = JSON.parse(json);
        const status = data.status;
        if (status == 401) {
            store.commit("open", "您的账号密码输入有误或您处于未登录状态");
            sessionStorage.clear();
            store.commit("unlogin");
            router.push("/index");
        }
        else if (status == 403) {
            store.commit("open", "抱歉的告诉您 您没有权限访问");
            sessionStorage.clear();
            store.commit("unlogin");
            router.push("/index");
        }
        else if (status >= 500 && status < 600) {
            store.commit("open", "服务器端出现错误 非常抱歉影响到您的体验");
        }
        else {
            store.commit("open", "访问错误 非常抱歉影响到您的体验");
        }
        return Promise.reject(err);
    });
export default axios;