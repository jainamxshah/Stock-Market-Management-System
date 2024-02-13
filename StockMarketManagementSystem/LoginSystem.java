import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LoginSystem {

    String name;
    String id;
    String password;
    Statement st;
    Scanner sc = new Scanner(System.in);

    LoginSystem() throws Exception{
        String dburl1= "jdbc:mysql://localhost:3306/stockList";
        String dbuser= "root";
        String dbpassword="";
        String driver= "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        Connection conn=DriverManager.getConnection(dburl1, dbuser, dbpassword);
        st=conn.createStatement();
    }

    public void login() throws SQLException {
        System.out.println("\n========== Login ==========");
        System.out.println("Enter user name: ");
        name=sc.next();
        try {
            ResultSet rs=st.executeQuery("Select * from accounts where name='"+name+"'");
            while(rs.next()){
                id=rs.getString("loginid");
                password=rs.getString("password");
            }

        } catch (Exception e) {
            System.out.println("\u001B[31m"+"NO such user exist! signup!"+"\u001B[0m");
            signup(); 
        }
        int attemptsLeft = 3;
        while (attemptsLeft > 0) {
            System.out.print("Enter Email id : ");
            String email_login = sc.next();
            System.out.print("Enter Password : ");
            String password_login = sc.next();
            if(email_login.equals(id) && password_login.equals(password)){
                System.out.println("Login successful!");
                break;
            } else {
                attemptsLeft--;
                System.out.println("\u001B[31m"+"Incorrect password!"+"\u001B[0m"+" Attempts left: " + attemptsLeft);
                if (attemptsLeft == 0) {
                    System.out.println("\u001B[31m"+"\nYou have been locked out!\nExiting..."+"\u001B[0m");
                    System.exit(0);
                }
            }
        }
    }

    void signup() throws SQLException{
        System.out.println("\n========== Signup ==========");
        System.out.print("Enter Name : ");
        String name = sc.nextLine();
        System.out.print("Enter id : ");
        String email1 = sc.next();
        System.out.print("Enter Password : ");
        String password1 = sc.next();
        while(!isStrongPassword(password1)){
            System.out.println("Password enter is not Strong!");
            password1 = sc.next();
        }
        st.executeUpdate("insert into accounts values (7,'"+name+"','"+email1+"','"+password1+"',0)");
        login();
    }

    public static boolean isStrongPassword(String password) {
        // Check length (at least 8 characters)
        if (password.length() < 8) {
            return false;
        }

        // Check for at least one uppercase letter
        Pattern uppercasePattern = Pattern.compile("[A-Z]");
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        if (!uppercaseMatcher.find()) {
            return false;
        }

        // Check for at least one lowercase letter
        Pattern lowercasePattern = Pattern.compile("[a-z]");
        Matcher lowercaseMatcher = lowercasePattern.matcher(password);
        if (!lowercaseMatcher.find()) {
            return false;
        }

        // Check for at least one digit
        Pattern digitPattern = Pattern.compile("\\d");
        Matcher digitMatcher = digitPattern.matcher(password);
        if (!digitMatcher.find()) {
            return false;
        }

        // Check for at least one special character
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
        Matcher specialCharMatcher = specialCharPattern.matcher(password);
        if (!specialCharMatcher.find()) {
            return false;
        }

        return true;
    }
}
