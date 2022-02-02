import axios from "../axios";
import router from "../router/index"
import { ActionTree, createStore, MutationTree } from "vuex";
import { State, Teacher } from "../type";
//State
const state: State = {
  students: [],
  teachers: [],
  application: {},
  unsettledApplications: [],
  selectedStudents: [],
  role: "",
  exception: ""
};
//Actions
const actions: ActionTree<State, State> = {
  login: async ({ commit }, { number, password }) => {
    try {
      const res = await axios.post("/login", {
        number,
        password
      });
      commit("login", res.data);
      sessionStorage.setItem("role", res.data.role);
      sessionStorage.setItem("auth", res.headers.authorization);
    } catch (error) {
      if (error) {
        throw error;
      }
    }
  },
  modify: async ({ commit }, { password }) => {
    try {
      const res = await axios.post("/common/modifyPassword", {
        password
      });
      if (res.data.data == "success")
        alert("密码修改成功！");
      else
        alert("服务器端错误");
    } catch (error) {
      if (error)
        throw error;
    }
  },
  getAdminIndex: async ({ commit }) => {
    try {
      const res = await axios.get("/admin/index");
      commit("adminIndex", res.data);
    } catch (error) {
      if (error) {
        throw error;
      }
    }
  },
  addTS: async ({ commit }, { students, teachers }) => {
    try {
      await axios.post("/admin/addStudents", students);
      await axios.post("/admin/addTeachers", teachers);
    } catch (error) {
      if (error)
        throw error;
    }
  },
  getTeachers: async ({ commit }) => {
    try {
      const res = await axios.get("/student/index");
      commit("getTeachers", res.data);
    } catch (error) {
      if (error) {
        throw error;
      }
    }
  },
  submit: async ({ commit }, { introduction, tid }) => {
    try {
      const res = await axios.post("/student/submitApplication", {
        selfIntroduction: introduction,
        teacher: {
          id: tid
        }
      })
      commit("submit", res.data);
      router.push({
        name: "application",
        params: {
          tid
        }
      });
    } catch (error) {
      if (error) {
        throw error;
      }
    }
  },
  check: async ({ commit }, { tid }) => {
    try {
      const res = await axios.get("/student/checkApplication/" + tid);
      commit("check", res.data);
    } catch (error) {
      if (error)
        throw error;
    }
  },
  getTeacherIndex: async ({ commit }) => {
    try {
      const res = await axios.get("/teacher/index");
      commit("getTeacherIndex", res.data);
    } catch (error) {
      if (error)
        throw error;
    }
  },
  agree: async ({ commit }, { aid }) => {
    try {
      await axios.get("/teacher/agree/" + aid);
    } catch (error) {
      if (error)
        throw error;
    }
  },
  refuse: async ({ commit }, { aid }) => {
    try {
      await axios.get("/teacher/refuse/" + aid);
    } catch (error) {
      if (error)
        throw error;
    }
  }
};
//Mutations
const mutations: MutationTree<State> = {
  login: (state: State, data) => {
    state.role = data.role;
    if (data.selectedStudent) {
      sessionStorage.setItem("selectedStudent", data.selectedStudent.user.name);
      sessionStorage.setItem("selectedTeacher", data.selectedStudent.teacher.user.name);
    }

  },
  adminIndex: (state: State, data) => {
    state.students = data.students;
    const teachers = data.teachers.filter((t: Teacher) => { return t.user && t.user.number != 1001 });
    state.teachers = teachers;
  },
  getTeachers: (state: State, data) => {
    const teachers = data.teachers.filter((t: Teacher) => { return t.user && t.user.number != 1001 && t.currentNum != 10 });
    state.teachers = teachers;
  },
  submit: (state: State, data) => {
    state.application = data.application;
  },
  check: (state: State, data) => {
    if (data.application)
      state.application = data.application;
    else
      state.application = {}
  },
  getTeacherIndex: (state: State, data) => {
    state.unsettledApplications = data.unsettledApplications;
    state.selectedStudents = data.selectedStudents;
  },
  open: (state: State, data) => {
    state.exception = data;
  },
  close: (state: State) => {
    state.exception = "";
  },
  unlogin: (state: State) => {
    state.role = "";
  }
};
export default createStore({
  state,
  mutations,
  actions
});
