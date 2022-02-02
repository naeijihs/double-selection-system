import xlsx from "xlsx";
export function readFile(file: File) {
    return new Promise(resolve => {
        const students: { user: { name: string; number: number; }; }[] = [];
        const teachers: { user: { name: string; number: number; }; }[] = [];
        const reader = new FileReader();
        reader.readAsBinaryString(file);
        reader.onload = event => {
            const data = event.target?.result;
            const workbook = xlsx.read(data, { type: "binary" });
            const studentSheet = workbook.Sheets[workbook.SheetNames[0]];
            const teacherSheet = workbook.Sheets[workbook.SheetNames[1]];
            xlsx.utils.sheet_to_json(studentSheet).forEach((s: any) => {
                const stu = {
                    user: {
                        name: s.name as string,
                        number: s.number as number
                    }
                };
                students.push(stu);
            });
            xlsx.utils.sheet_to_json(teacherSheet).forEach((t: any) => {
                const teac = {
                    user: {
                        name: t.name as string,
                        number: t.number as number
                    }
                };
                teachers.push(teac);
            })
        };
        reader.onloadend = () => {
            resolve({
                students,
                teachers
            })
        }
    });
}