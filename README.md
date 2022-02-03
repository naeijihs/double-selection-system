# double-selection-system
## 简介
### 为学生与毕业设计指导老师之间的双选提供平台。系统初始化管理员角色，由管理员导入学生和老师名单，学生选择老师提出申请，老师处理申请
## 功能
### 公共功能：登录 密码修改
### 管理员：浏览学生和老师列表 导入学生和老师名单
### 学生：浏览老师列表 提交申请/查看申请
### 老师：浏览学生列表 处理申请
## 技术
### 前端：Vue3.0+TypeScript+ElementPlus+axios+xlsx
### 后端：SpringBoot
### 数据库：MySQL
## 实现
### 前端：项目借助VueCli搭建，通过Vue3.0+TS完成页面交互和数据在视图的展示，借助ElementPlus中的表格和对话框组件展示数据和进行异常提示，使用sessionStorage+vuex维持登录状态，通过axios
