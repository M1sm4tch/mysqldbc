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

        do{
            System.out.println("Choose your operation: \n\t1. Read all records \n\t2. Insert a record\n\t3. Find a record(Update/Delete/Read Detail)");
            x = sc.nextInt();
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
                case 3 -> {
                    int id;
                    System.out.println("Enter employee id: ");
                    id = sc.nextInt();
                    ResultSet f = stmt.executeQuery("select * from employee where id ="+id);
                    System.out.println("Results: ");
                    while (f.next())
                        System.out.println("1. ID: "+f.getInt("id")+" 2. Name: "+f.getString("name")+" 3. Insurance: "+f.getString(3)+ " 4. Dept: "+f.getString(4)+" 5. Location: "+f.getString(5));
                    System.out.println("Do you want to \n\t1. delete this record or \n\t2. update it?");
                    int y = sc.nextInt();
                    switch(y){
                        case 1->{
                            int res = stmt.executeUpdate("delete from employee where id ="+id);
                            System.out.println("Deleted." + res);
                        }
                        case 2->{
                            System.out.println("Which column would you like to update? (Enter the number)");
                            int c = sc.nextInt();
                            ResultSetMetaData fMetaData = f.getMetaData();
                            String label = fMetaData.getColumnLabel(c);
                            System.out.println("Enter the new value: ");
                            sc.nextLine();
                            String value = sc.nextLine();
                            int res = stmt.executeUpdate("update employee set "+label+" = '"+ value+ "' WHERE id = " + id);
                            System.out.println("Updated" + res);
                        }
                    }
                }
                default -> {
                    System.out.println("Wrong choice! do u wish to quit?(enter 0)");
                    x = sc.nextInt();
                }
            }
        }while(x != 0);


        con.close();
    }


    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) throws Exception
    {
        new crud();
    }
}

