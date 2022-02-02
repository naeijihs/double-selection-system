<template>
  <div id="selected">
    <span
      >恭喜{{ selectedStudent }}同学您已与{{
        selectedTeacher
      }}老师完成双选!</span
    >
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  setup() {
    const selectedStudent = sessionStorage.getItem("selectedStudent");
    const selectedTeacher = sessionStorage.getItem("selectedTeacher");
    return {
      selectedStudent,
      selectedTeacher,
    };
  },
});
</script>

<style scoped>
#selected {
  width: 50vw;
  height: 30vh;
  margin: auto;
  margin-top: 200px;
}
span {
  font-size: 30px;
}
</style>
