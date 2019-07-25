public class test2 {
    public static void main(String[] args) {
        int a[] = {1,5,18,3,12,36};
        for(int i = 0;i < a.length ; i++){
            for (int j=0;j<a.length;j++){
                if (a[i] < a[j]){ //由小到大 第一个最小，最后一个最大
                    int b = a[i];
                    a[i] = a[j];
                    a[j] = b;
                }
            }
        }
        System.out.println("排序结果:"+a[4]);
        for (int x = 0;x < a.length; x++){
            System.out.print(a[x]+"\t");
        }
    }
//    List<String> teacherGrade1 = allTeas.stream().map(QkcTeacher::getTeacherGrade).collect(Collectors.toList());

}
