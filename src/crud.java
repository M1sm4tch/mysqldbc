import java.sql.*;
import java.util.Scanner;

public class crud {
    crud() throws Exception
    {
        int x;
        Scanner sc = new Scanner(System.in);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","samad153");
        Statement stmt = con.createStatement();
        System.out.println("Choose: ");
        x = sc.nextInt();
        while(x!=10) {
            switch (x) {
                case 1 -> {
                    ResultSet rs = stmt.executeQuery("select * from employee");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt(1) + " Name: " + rs.getString(2));
                    }
                }
                case 2 -> {
                    System.out.println("Enter employee details: ");
                    System.out.println("Name: ");
                    sc.nextLine();
                    String Name = sc.nextLine();
                    System.out.println("ID: ");
                    int id = sc.nextInt();
                    System.out.println("Insurance id: ");
                    sc.nextLine();
                    String insurance = sc.nextLine();
                    System.out.println("Department: ");
                    sc.nextLine();
                    String dept = sc.nextLine();
                    System.out.println("Location: ");
                    String loc = sc.nextLine();
                    int count = stmt.executeUpdate("insert into employee values (" + id + ",'" + Name + "','" + insurance + "','" + dept + "','" + loc + "')");
                    System.out.println(count);
                }
                default -> {
                    System.out.println("Choose: ");
                    x = sc.nextInt();
                }
            }
        }


        con.close();
    }


    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) throws Exception
    {
        new crud();
    }
}

