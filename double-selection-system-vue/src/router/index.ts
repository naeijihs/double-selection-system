import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/index",
    component: () => import("../views/Index.vue")
  },
  {
    path: "/application/:tid",
    props: true,
    name: "application",
    components: {
      application: () => import("../views/Application.vue")
    }
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
