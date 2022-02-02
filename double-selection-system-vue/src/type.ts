export interface State {
    students: Student[]
    teachers: Teacher[]
    application: Application
    unsettledApplications: Application[]
    selectedStudents: Student[]
    role: string
    exception: string
}
export interface Student {
    id?: number
    user?: User
    teacher?: Teacher
    applications?: Application[]
}
export interface User {
    id?: number
    number?: number
    password?: number
    name?: string
    role?: string
}
export interface Teacher {
    id?: number
    user?: User
    currentNum?: number
    maxNum?: number
    selectedStudents?: Student[]
    applications?: Application[]
}
export interface Application {
    id?: number
    selfIntroduction?: string
    teacher?: Teacher
    student?: Student
    state?: string
}