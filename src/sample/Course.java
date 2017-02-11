package sample;

/**
 * Created by 17rsaiyid on 1/11/2017.
 */
public class Course {


    private String cname = "";
    private String grade1 = "";
    private String grade2 = "";
    private int cType = 0;
    private String raw = "";
    private double gpas1 = 0.0;
    private double gpas2 = 0.0;

    private final String[] typeL = {"AP", "IB", "H. ", "Honors"};
    private int[] typeN = {1, 2, 3, 4, 5}; // 1: AP; 2: IBHL; 3: IBSL; 4: H.; 5: Regular


    public Course(String c, String g, String g2){
        raw = c;
        for(int x = 0; x < raw.length(); x++){
            if(x > 5){
                if(raw.charAt(x) == 't' && raw.charAt(x-1) == 'e' && raw.charAt(x-2) == 'D'){
                    cname = raw.substring(0, x-4);
                    break;
                }
            }
        }

        for(int x = 0; x < g.length(); x++){
            if(g.charAt(x) == ' '){
                grade1 = g.substring(0,x);
            }
        }
        for(int x = 0; x < g2.length(); x++){
            if(g2.charAt(x) == ' '){
                grade1 = g.substring(0,x);
            }
        }
        System.out.println("grade1 : " + grade1);
        System.out.println("grade2 : " + grade2);
        cClassify();
        gpaCalc(grade1, 1);
        gpaCalc(grade2, 2);

    }

    public void cClassify(){
        System.out.println("cname : " + cname.substring(0,2));
        switch (cname.substring(0,2)){
            case "AP": //AP
                cType = typeN[0]; // 1
                break;
            case "IB": //IBHL
                for(int y = cname.length()-1; y > 0; y--){
                    if(cname.charAt(y) == ' '){
                        if(cname.substring(y+1, y+3).equals("HL")){
                            cType = typeN[1]; // 2
                        }
                        else if(cname.substring(y+1, y+3).equals("SL")){
                            cType = typeN[2]; // 3
                        }
                    }
                }
                break;
            case "H.": //H.
            case "Honors": //Honors
                cType = typeN[3]; // 4
                break;
            default:
                cType = typeN[4]; // 5
                break;

        }
    }

    public void gpaCalc(String g, int c){
        double rawgpa = 0.0;
        switch(g){
            case "A+":
                rawgpa = 4.3;
                break;
            case "A":
                rawgpa = 4.0;
                break;
            case "A-":
                rawgpa = 3.7;
                break;
            case "B+":
                rawgpa = 3.3;
                break;
            case "B":
                rawgpa = 3.0;
                break;
            case "B-":
                rawgpa = 2.7;
                break;
            case "C+":
                rawgpa = 2.3;
                break;
            case "C":
                rawgpa = 2.0;
                break;
            case "C-":
                rawgpa = 1.7;
                break;
            case "D+":
                rawgpa = 1.3;
                break;
            case "D":
                rawgpa = 1.0;
                break;
            case "D-":
                rawgpa = 0.7;
                break;
            case "F":
                rawgpa = 0.0;
                break;
            case "P":
            default:
                rawgpa = -500.0;
                break;
        }

        System.out.println("cType : " + cType);
        switch(cType){
            case 1:
            case 2:
                rawgpa += 0.5;
                break;
            case 3:
            case 4:
                rawgpa += 0.3;
                break;
            case 5:
                break;
        }
        System.out.println("g : " + rawgpa);
        switch(c){
            case 1:
                setGpas1(rawgpa);
                break;
            case 2:
                setGpas2(rawgpa);
        }
    }

    public String getCname() {
        return cname;
    }

    public void setcName(String cName) {
        this.cname = cName;
    }

    public String getGrade1() {
        return grade1;
    }

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public String getGrade2() {
        return grade2;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public void setcType(int cType) {
        this.cType = cType;
    }

    public int getcType() {
        return cType;
    }

    public double getGpas1() {
        return gpas1;
    }

    public void setGpas1(double gpas1) {
        this.gpas1 = gpas1;
    }

    public double getGpas2() {
        return gpas2;
    }

    public void setGpas2(double gpas2) {
        this.gpas2 = gpas2;
    }
}
