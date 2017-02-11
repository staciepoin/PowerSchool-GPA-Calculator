package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Rameez U. Saiyid on 1/12/2017.
 */


public class Calculator {

    public static String user = "";
    public static String pass = "";
    private static PSLogin client;
    private static String page;
    private static Document doc;
    public static ArrayList<Course> Courses = new ArrayList<Course>();
    private static double cGPAs1 = 0.0;
    private static double cGPAs2 = 0.0;

    public Calculator(String u, String p) {
        user = u;
        pass = p;
        client = new PSLogin(user, pass);
        client.login();
        page = client.get("https://powerschool.asd.edu.qa/guardian/home.html?showdropped=true");
        doc = Jsoup.parse(page);
        System.out.println(doc.title());
    }

    public static boolean auth() {
        return doc.title().equals("Grades and Attendance");
    }

    public static void calcGPA() {
        double GPA1 = 0.0;
        double GPA2 = 0.0;
        int numClass;

        Element table = doc.select("table").get(1);
        for (Element row : table.select("tr")) {
            Elements tds = row.select("td");
            if (tds.size() > 6) {
                System.out.println(tds.get(15).text() + " : S1 " + tds.get(18).text() + " : S2 " + tds.get(21).text());
                Courses.add(new Course(tds.get(15).text(), tds.get(18).text(), tds.get(21).text()));
            }
        }

        numClass = Courses.size();
        System.out.println("Checking for empty grades in Semester 1...");
        for (Course x : Courses) {
            System.out.println(x.getGpas1());
            if (x.getGpas1() < 0) {
                numClass--;
                System.out.println("Decreasing numClass");
                continue;
            }
            GPA1 += x.getGpas1();
        }
        System.out.println("Checking for empty grades in Semester 2...");
        setcGPAs1(GPA1 / numClass);
        numClass = Courses.size();
        for (Course x : Courses) {
            System.out.println(x.getGpas2());
            if (x.getGpas2() < 0) {
                numClass--;
                System.out.println("Decreasing numClass");
                continue;
            }
            GPA2 += x.getGpas2();
        }
        setcGPAs2(GPA2 / numClass);
        System.out.println("S1: " + getcGPAs1() + " S2: " + getcGPAs2());
    }

    public static double getcGPAs1() {
        return cGPAs1;
    }

    public static void setcGPAs1(double c) {
        cGPAs1 = c;
    }

    public static double getcGPAs2() {
        return cGPAs2;
    }

    public static void setcGPAs2(double c) {
        cGPAs2 = c;
    }
}


