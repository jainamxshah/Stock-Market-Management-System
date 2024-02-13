import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;
import net.efabrika.util.DBTablePrinter;


public class StockMarketManagementSystem {
    static Thread t = new MyThread();
    
    public static void main(String[] args) throws Exception{
        String dburl1= "jdbc:mysql://localhost:3306/stockList";
        String dbuser= "root";
        String dbpassword="";
        String driver= "com.mysql.cj.jdbc.Driver";

        MyThread t=new MyThread();
        t.start();
        
        Class.forName(driver);
        Connection conn=DriverManager.getConnection(dburl1, dbuser, dbpassword);
        Statement st=conn.createStatement();
        LoginSystem ls=new LoginSystem();
        Scanner sc=new Scanner(System.in);
        System.out.println("\n\t\t\t\t\u001B[34m==========================================================\u001B[m");
        System.out.println("\t\t\t\t\t\u001B[38;5;208m      STOCK MARKET MANAGEMENT SYSTEM");
        System.out.println("\t\t\t\t\u001B[34m==========================================================\u001B[m");

        System.out.println("\n\u001B[34m╔══════════════════════════════════════╗\u001B[m");
        System.out.println("║\u001B[38;5;208m          Choose Interface For        \u001B[34m║");
        System.out.println("╟──────────────────────────────────────╢");
        System.out.println("║\u001B[38;5;208m    [1] Admin         [2] Customer    \u001B[34m║");
        System.out.println("╚══════════════════════════════════════╝\u001B[0m");
        int choice;
        while(true) {
            try {
                System.out.print("Enter Choice : ");
                choice=sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Enter Valid Input!!!");
                sc.nextLine();
            }
        }  
        switch(choice){
            case 1:
                ls.login();
                //add if condition
                DBTablePrinter.printTable(conn, "stocks",150,20);
                
                boolean flag=true;
                while(flag){
                    System.out.println("\n\u001B[34m╔══════════════════════╗");
                    System.out.println("║\u001B[38;5;208m      Main Menu       \u001B[34m║");
                    System.out.println("╠══════════════════════╣");
                    System.out.println("║\u001B[38;5;208m [1] Add Stock        \u001B[34m║");
                    System.out.println("║\u001B[38;5;208m [2] Delete Stock     \u001B[34m║");
                    System.out.println("║\u001B[38;5;208m [3] Update Stock     \u001B[34m║");
                    System.out.println("║\u001B[38;5;208m [4] View Stock       \u001B[34m║");
                    System.out.println("║\u001B[38;5;208m [5] Exit             \u001B[34m║");
                    System.out.println("╚══════════════════════╝\u001B[0m");
                    int ch;
                    while(true) {
                        try {
                            System.out.print("Enter Choice : ");
                            ch=sc.nextInt();
                            break;        
                        } catch (Exception e) {
                            System.out.println("Enter Valid input!!!");
                            sc.nextLine();
                        }
                    }
                    switch(ch){
                        case 1:
                        {
                            int id;
                            double price, week_52_high, week_52_low, pe_ratio, pb_ratio, roi, market_capitalization, dividend;
                            String symbol, name;
                            while (true) {
                                try {
                                    System.out.print("\nEnter Stock  details:");
                                    System.out.print("Stock id: ");
                                    id=sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Symbol: ");
                                    symbol = sc.nextLine();
                                    System.out.print("Name: ");
                                    name = sc.nextLine();
                                    System.out.print("Price: ");
                                    price = sc.nextDouble();
                                    System.out.print("52 Week High: ");
                                    week_52_high = sc.nextDouble();
                                    System.out.print("52 Week Low: ");
                                    week_52_low = sc.nextDouble();
                                    System.out.print("PE Ratio: ");
                                    pe_ratio = sc.nextDouble();
                                    System.out.print("PB Ratio: ");
                                    pb_ratio = sc.nextDouble();
                                    System.out.print("ROI: ");
                                    roi = sc.nextDouble();
                                    System.out.print("Market Capitalization: ");
                                    market_capitalization = sc.nextDouble();
                                    System.out.print("Dividend: ");
                                    dividend = sc.nextDouble();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Enter Valid Input!!!");
                                    sc.nextLine();
                                }
                            }
                            sc.nextLine();
                            try {
                                
                                String s1="Insert into stocks values(?,?,?,?,?,?,?,?,?,?,?);";
                                PreparedStatement pst=conn.prepareCall(s1);
                                pst.setInt(1,id);
                                pst.setString(2, symbol);
                                pst.setString(3, name);
                                pst.setDouble(4, price);
                                pst.setDouble(5, week_52_high);
                                pst.setDouble(6, week_52_low);
                                pst.setDouble(7, pe_ratio);
                                pst.setDouble(8, pb_ratio);
                                pst.setDouble(9, roi);
                                pst.setDouble(10, market_capitalization);
                                pst.setDouble(11,dividend);
                                pst.executeUpdate();
                                System.out.println("Stock Added!");
                            } catch (Exception e) {
                                System.out.println("Stock with stockid "+id+" already exist!");
                            }
                            break;
                        }
                        case 2:
                            System.out.print("\nEnter company symbol");
                            String name1=sc.next();
                            String s2="delete from stocks where symbol='"+name1+"'";
                            st.executeUpdate(s2);
                            System.out.println("Stock deleted!");
                            break;
                        case 3:
                        {
                            int id;
                            double price, week_52_high, week_52_low, pe_ratio, pb_ratio, roi, market_capitalization, dividend;
                            String symbol, name;
                            while (true) {
                                try {
                                    System.out.print("\nEnter Stock  details:");
                                    System.out.print("Stock id: ");
                                    id=sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Symbol: ");
                                    symbol = sc.nextLine();
                                    System.out.print("Name: ");
                                    name = sc.nextLine();
                                    System.out.print("Price: ");
                                    price = sc.nextDouble();
                                    System.out.print("52 Week High: ");
                                    week_52_high = sc.nextDouble();
                                    System.out.print("52 Week Low: ");
                                    week_52_low = sc.nextDouble();
                                    System.out.print("PE Ratio: ");
                                    pe_ratio = sc.nextDouble();
                                    System.out.print("PB Ratio: ");
                                    pb_ratio = sc.nextDouble();
                                    System.out.print("ROI: ");
                                    roi = sc.nextDouble();
                                    System.out.print("Market Capitalization: ");
                                    market_capitalization = sc.nextDouble();
                                    System.out.print("Dividend: ");
                                    dividend = sc.nextDouble();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Enter Valid Input!!!");
                                    sc.nextLine();
                                }
                            }
                            sc.nextLine();
                            String s1="Update stocks set divided=?,symbol=?,name=?,price=?, week_52_high=?, week_52_low=?, pe_ratio=?, pb_ratio=?, roi=?, market_capitalization=? where stoc_id=?;";
                            PreparedStatement pst=conn.prepareCall(s1);
                            pst.setInt(11,id);
                            pst.setString(2, symbol);
                            pst.setString(3, name);
                            pst.setDouble(4, price);
                            pst.setDouble(5, week_52_high);
                            pst.setDouble(6, week_52_low);
                            pst.setDouble(7, pe_ratio);
                            pst.setDouble(8, pb_ratio);
                            pst.setDouble(9, roi);
                            pst.setDouble(10, market_capitalization);
                            pst.setDouble(1,dividend);
                            pst.executeUpdate();
                        break;
                        }
                        case 4:
                            DBTablePrinter.printTable(conn, "stocks",200,20);
                        break;
                        case 5:
                            flag=false;
                            System.exit(0);
                        break;
                        default:
                            System.out.println("Enter valid Number!");
                        break;
                    }
                }
            break;
            case 2:
            System.out.println("\n\u001B[34m╔══════════════════════════════════════╗");
            System.out.println("║\u001B[38;5;208m          Choose Login Option         \u001B[34m║");
            System.out.println("╟──────────────────────────────────────╢");
            System.out.println("║\u001B[38;5;208m    [1] Login         [2] Sign-up     \u001B[34m║");
            System.out.println("╚══════════════════════════════════════╝\u001B[0m"); 
            int ch1;
            while (true) {
                try {
                    System.out.print("Enter Choice : ");
                    ch1 = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Enter Valid Input!!!");
                    sc.nextLine();
                }
            }
            switch(ch1){
                case 1:{
                    ls.login();
                    break;
                }
                case 2:{
                    ls.signup();
                    break;
                }
                default:{
                    System.out.println("Enter valid Number!!");
                    break;
                }
            }
            boolean flag1=true;
            while(flag1){
                System.out.println("\n\u001B[34m╔════════════════════════╗");
                System.out.println("║\u001B[38;5;208m        Main Menu       \u001B[34m║");
                System.out.println("╠════════════════════════╣");
                System.out.println("║\u001B[38;5;208m [1] Stock List         \u001B[34m║");
                System.out.println("║\u001B[38;5;208m [2] Portfolio          \u001B[34m║");
                System.out.println("║\u001B[38;5;208m [3] Wishlist           \u001B[34m║");
                System.out.println("║\u001B[38;5;208m [4] Buy/Sell Stock     \u001B[34m║");
                System.out.println("║\u001B[38;5;208m [5] Manage Profile     \u001B[34m║");
                System.out.println("║\u001B[38;5;208m [6] Exit               \u001B[34m║");
                System.out.println("╚════════════════════════╝\u001B[0m");
                int ch;
                while (true) {
                    try {
                        System.out.print("Enter Choice : ");
                        ch=sc.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter Valid Input!!!");
                        sc.nextLine();
                    }
                }
                switch(ch){
                    case 1:
                        DBTablePrinter.printTable(conn, "stocks",100,20);
                        ResultSet rs;
                        boolean flag2=true;
                        while(flag2){
                            System.out.println("\n\u001B[34m╔════════════════════════════════════════════════════╗");
                            System.out.println("║\u001B[38;5;208m                    Sort Options                    \u001B[34m║");
                            System.out.println("╠════════════════════════════════════════════════════╣");
                            System.out.println("║\u001B[38;5;208m [1] Sort by Price                                  \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [2] Sort by 52 Week High                           \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [3] Sort by 52 Week Low                            \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [4] Sort by PE Ratio                               \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [5] Sort by PB Ratio                               \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [6] Sort by ROI                                    \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [7] Sort by Market Capitalisation                  \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [8] Sort by Dividend                               \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [9] Exit                                           \u001B[34m║");
                            System.out.println("╚════════════════════════════════════════════════════╝\u001B[0m");
                            int ch2;
                            while (true) {
                                try {
                                    System.out.print("Enter Choice : ");
                                    ch2=sc.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Enter Valid Input!!!");
                                    sc.nextLine();
                                }
                            }
                            sc.nextLine();  
                            switch(ch2){
                                case 1:
                                    rs=st.executeQuery("Select * from stocks order by price desc");
                                    DBTablePrinter.printResultSet(rs, 20);
                                break;
                                case 2:
                                    rs=st.executeQuery("Select * from stocks order by week_52_high desc");
                                    DBTablePrinter.printResultSet(rs, 20);
                                break;
                                case 3:
                                    rs=st.executeQuery("Select * from stocks order by week_52_low desc");
                                    DBTablePrinter.printResultSet(rs, 20);
                                break;
                                case 4:
                                    rs=st.executeQuery("Select * from stocks order by pe_ratio desc");
                                    DBTablePrinter.printResultSet(rs, 20);
                                break;
                                case 5:
                                    rs=st.executeQuery("Select * from stocks order by pb_ratio desc");
                                    DBTablePrinter.printResultSet(rs, 20);
                                break;
                                case 6:
                                    rs=st.executeQuery("Select * from stocks order by roi desc");
                                    DBTablePrinter.printResultSet(rs, 20);
                                break;
                                case 7:
                                    rs=st.executeQuery("Select * from stocks order by market_capitalization desc");
                                    DBTablePrinter.printResultSet(rs, 20);
                                break;
                                case 8:
                                    rs=st.executeQuery("Select * from stocks order by dividend desc");
                                    DBTablePrinter.printResultSet(rs, 20);
                                break;
                                case 9:
                                    flag2=false;
                                break;
                            }
                        }
                        break;
                        case 2:
                        DatabaseMetaData metaData = conn.getMetaData();
                        boolean tableNotExists = false;
                        try {
                            metaData.getTables(null, null, ls.name, null);
                            tableNotExists = true;
                        } catch (SQLException e) {}
                        if(tableNotExists){
                            DBTablePrinter.printTable(conn, ls.name+"Portfolio", 100, 20); 
                        }
                        else{
                            System.out.println("\u001B[31m"+"You have not bought any stocks!"+"\u001B[0m");
                        }
                        break;
                        case 3: 
                            try {
                                st.executeUpdate("CREATE TABLE "+ls.name+"Wishlist (stock_id int(11) NOT NULL,symbol varchar(10) NOT NULL,name varchar(255) NOT NULL,price decimal(10,2) DEFAULT NULL,week_52_high decimal(10,2) DEFAULT NULL,week_52_low decimal(10,2) DEFAULT NULL,pe_ratio decimal(10,2) DEFAULT NULL,pb_ratio decimal(10,2) DEFAULT NULL,roi decimal(5,2) DEFAULT NULL,market_capitalization decimal(20,2) DEFAULT NULL,dividend decimal(5,2) DEFAULT NULL)");
                            } catch (Exception e) {}
                            System.out.println("\n\u001B[34m╔══════════════════════════════════════╗");
                            System.out.println("║\u001B[38;5;208m           Wishlist Options           \u001B[34m║");
                            System.out.println("╠══════════════════════════════════════╣");
                            System.out.println("║\u001B[38;5;208m [1] Add Stock to Wishlist            \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [2] View Wishlist                    \u001B[34m║");
                            System.out.println("╚══════════════════════════════════════╝\u001B[0m");
                            int c;
                            while (true) {
                                try {
                                    System.out.print("Enter Choice : ");
                                    c=sc.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Enter Valid Input!!!");
                                    sc.nextLine();
                                }
                            }
                            if(c==1){
                                //create table code
                                System.out.print("Enter symbol: ");
                                String sym=sc.next();
                                st.executeUpdate("Insert into "+ls.name+"Wishlist select * FROM STOCKS WHERE symbol='"+sym+"'");
                            }
                            else if(c==2){
                                DBTablePrinter.printTable(conn, ls.name+"Wishlist",100, 20);
                            }
                            else{
                                System.out.println("Enter correct option!");
                            }
                        break;
                        case 4:
                            try {
                                st.execute("Create table "+ls.name+"Portfolio (sr INT PRIMARY KEY AUTO_INCREMENT,symbol varchar(20),quantity INT NOT NULL,price DECIMAL(10, 2) NOT NULL,date DATE NOT NULL)");
                                
                            } catch (Exception e) {}
                            File f=new File(ls.name+".txt");
                            f.createNewFile();
                            BufferedWriter bfw=new BufferedWriter(new FileWriter(f));
                            System.out.println("\n\u001B[34m╔════════════════════════════════════════╗");
                            System.out.println("║\u001B[38;5;208m            Transaction Options         \u001B[34m║");
                            System.out.println("╠════════════════════════════════════════╣");
                            System.out.println("║\u001B[38;5;208m [1] Buy Stock                          \u001B[34m║");
                            System.out.println("║\u001B[38;5;208m [2] Sell Stoc                          \u001B[34m║");
                            System.out.println("╚════════════════════════════════════════╝\u001B[0m");
                            int ch3;
                            while (true) {
                                try {
                                    System.out.print("Enter Choice : ");
                                    ch3=sc.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Enter Valid Input!!!");
                                    sc.nextLine();
                                }
                            }
                            switch(ch3){
                                case 1:
                                    String symbol;
                                    int quantity;
                                    while (true) {
                                        try {
                                            System.out.print("\nEnter Symbol: ");
                                            symbol=sc.next();
                                            System.out.print("Enter Quantity: ");
                                            quantity=sc.nextInt();
                                            break;
                                        } catch (Exception e) {
                                            System.out.println("Enter Valid Input!!!");
                                            sc.nextLine();
                                        }
                                    }
                                    rs=st.executeQuery("Select price from stocks where symbol= '"+symbol+"'");
                                    if (rs.next()) {
                                        double price=rs.getDouble("price");
                                        LocalDate date=LocalDate.now();
                                        double value=price*quantity;
                                        ResultSet rs2=st.executeQuery("Select balance from accounts where name='"+ls.name+"'");
                                        if(rs2.next()){
                                            double balance=rs2.getDouble("balance");
                                            if(balance>value){
                                                rs2=st.executeQuery("Select * from "+ls.name+"Portfolio");
                                                int flag5=0;
                                                while(rs2.next()){
                                                    if(rs2.getString("Symbol").equals(symbol)){
                                                        double temp=rs2.getDouble("price")*rs2.getInt("quantity");
                                                        double temp1=temp+value;
                                                        price=temp1/(quantity+rs2.getInt("quantity"));;
                                                        st.executeUpdate("Update "+ls.name+"Portfolio set quantity="+(quantity+rs2.getInt("quantity"))+", price="+price+" where symbol='"+symbol+"'");

                                                        bfw.write(symbol+" of quantity "+quantity+" at price "+price+" of total valuation of "+value+" on date "+date+" is bought!");
                                                        balance=balance-value;
                                                
                                                        st.executeUpdate("Update accounts set balance="+balance+" where name='"+ls.name+"'");
                                                        System.out.println("your total funds in your Wallet: "+balance);
                                                        flag5=1;
                                                        break;
                                                    }
                                                }
                                                if(flag5==0){
                                                    st.executeUpdate("Insert into "+ls.name+"Portfolio (symbol, quantity, price, date) values('"+symbol+"',"+quantity+","+price+",'"+LocalDate.now()+"')");
                                                    bfw.write(symbol+" of quantity "+quantity+" at price "+price+" of total valuation of "+value+" on date "+date+" is bought!");
                                                    balance=balance-value;
                                                    
                                                    st.executeUpdate("Update accounts set balance="+balance+" where name='"+ls.name+"'");
                                                    System.out.println("your total funds in your Wallet: "+balance);
                                                }
                                            }
                                            else{
                                                System.out.println("Not enough funds in your wallet!");
                                            }
                                            
                                        }
                                    }
                                    bfw.close();
                                break;
                                case 2:
                                    System.out.print("Enter Symbol: ");
                                    String symbol1=sc.next();
                                    rs=st.executeQuery("Select price from stocks where symbol='"+symbol1+"'");
                                    if(rs.next()){
                                        double price1=rs.getDouble("price");
                                        int quantity1;
                                        while(true) {
                                            try {
                                                System.out.print("Enter Quantity: ");
                                                quantity1=sc.nextInt();
                                                break;
                                            } catch (Exception e) {
                                                System.out.println("Enter Valid input!!!");
                                                sc.nextLine();
                                            }
                                        }
                                        int quantity3=quantity1;
                                        LocalDate date2=LocalDate.now();
                                        ResultSet rs1;
                                        rs1=st.executeQuery("Select * from "+ls.name+"Portfolio where symbol='"+symbol1+"'");
                                        if(rs1.next()){
                                            int quantity2=rs1.getInt("quantity");
                                            if(quantity1<=quantity2){
                                                quantity2=quantity2-quantity1;
                                                if(quantity2==0){
                                                    st.executeUpdate("delete from "+ls.name+"Portfolio where sr="+rs1.getInt("sr"));
                                                }
                                                else{
                                                    st.executeUpdate("Update "+ls.name+"Portfolio set quantity="+quantity2+" where sr="+rs1.getInt("sr"));
                                                }
                                                double profit=quantity3*price1;
                                                double tax=profit*0.1;
                                                System.out.println("Profit: "+profit);
                                                System.out.println("Total Tax: "+tax);
                                                double totalValue=profit-tax;
                                                System.out.println("Net profit: "+totalValue);
                                                ResultSet rs2=st.executeQuery("Select balance from accounts where name='"+ls.name+"'");
                                                if(rs2.next()){
                                                    double balance=rs2.getDouble("balance");
                                                    balance=balance+totalValue;
                                                    System.out.println("your total funds in your Wallet: "+balance);
                                                    st.executeUpdate("Update accounts set balance="+balance+" where name='"+ls.name+"'");
                                                }
                                                bfw.write(symbol1+" of quantity "+quantity1+" at price "+price1+" on date "+date2+" is sold!");
                                                }
                                                else{
                                                    System.out.println("You don't have this much shares!");
                                                }
                                            }
                                        }
                                    bfw.close();
                                break;
                               
                            }
                        break;
                        case 5:
                            boolean flag3=true;
                            while(flag3){
                                System.out.println("\n\u001B[34m╔══════════════════════════════════════════════╗");
                                System.out.println("║\u001B[38;5;208m                Account Options               \u001B[34m║");
                                System.out.println("╠══════════════════════════════════════════════╣");
                                System.out.println("║\u001B[38;5;208m [1] Add Funds                                \u001B[34m║");
                                System.out.println("║\u001B[38;5;208m [2] Withdraw Funds                           \u001B[34m║");
                                System.out.println("║\u001B[38;5;208m [3] Change Password                          \u001B[34m║");
                                System.out.println("║\u001B[38;5;208m [4] Your Funds                               \u001B[34m║");
                                System.out.println("║\u001B[38;5;208m [5] Back                                     \u001B[34m║");
                                System.out.println("╚══════════════════════════════════════════════╝\u001B[0m");
                                int cho;
                                while (true) {
                                    try {
                                        System.out.print("Enter Choice : ");
                                        cho=sc.nextInt();
                                        break;
                                    } catch (Exception e) {
                                        System.out.println("Enter Valid Input!!!");
                                        sc.nextLine();
                                    }
                                }
                                switch(cho){
                                    case 1:
                                        double amt;
                                        while(true) {
                                            try {
                                                System.out.print("Enter amount: ");
                                                amt=sc.nextDouble();
                                                break;
                                            } catch (Exception e) {
                                                System.out.println("Enter Valid Input!!!");
                                                sc.nextLine();
                                            }
                                        }
                                        PaymentSystem ps=new PaymentSystem(amt);
                                        System.out.println("\n\u001B[34m╔══════════════════════════════════════╗");
                                        System.out.println("║\u001B[38;5;208m           Payment Choices            \u001B[34m║");
                                        System.out.println("╠══════════════════════════════════════╣");
                                        System.out.println("║\u001B[38;5;208m [1] UPI                              \u001B[34m║");
                                        System.out.println("║\u001B[38;5;208m [2] Debit                            \u001B[34m║");
                                        System.out.println("║\u001B[38;5;208m [3] Credit                           \u001B[34m║");
                                        System.out.println("╚══════════════════════════════════════╝\u001B[0m");
                                        int cho1;
                                        while(true) {
                                            try {
                                                System.out.print("Enter CHoice : ");
                                                cho1=sc.nextInt();
                                                break;
                                            } catch (Exception e) {
                                                System.out.println("Enter Valid Input!!!");
                                                sc.nextLine();
                                            }
                                        }
                                        switch(cho1)
                                        {
                                            case 1:
                                                ps.UPI();
                                            break;
                                            case 2:
                                                ps.Debit();
                                            break;
                                            case 3:
                                                ps.Credit();
                                            break;
                                            default:
                                                System.out.println("Enter valid choice..!!");
                                            break;
                                        }
                                        rs=st.executeQuery("Select balance from accounts where name='"+ls.name+"'");
                                        double bal=0;
                                        while(rs.next()){
                                            bal=rs.getDouble("balance");
                                        }
                                        bal=bal+amt;
                                        st.executeUpdate("Update accounts set balance="+bal+" where name='"+ls.name+"'");
                                        sc.nextLine();
                                        
                                    break;
                                    case 2:
                                        double am;
                                        while (true) {
                                            try {
                                                System.out.print("\nEnter amount to withdraw: ");
                                                am=sc.nextDouble();
                                                break;
                                            } catch (Exception e) {
                                                System.out.println("Enter Valid Input!!!");
                                                sc.nextLine();
                                            }
                                        }
                                        rs=st.executeQuery("Select balance from accounts where name='"+ls.name+"'");
                                        while(rs.next()){
                                            double bala=rs.getDouble("balance");
                                            if(am<bala){
                                                while(true) {
                                                    try {
                                                        System.out.print("Enter bank acc number: ");
                                                        sc.nextInt();
                                                        break;
                                                    } catch (Exception e) {
                                                        System.out.println("Enter Valid Input!!!");
                                                        sc.nextLine();
                                                    }
                                                }
                                                System.out.println(am+" added to your bank account!");
                                                bala=bala-am;
                                                System.out.println("your total funds left are: "+bala);
                                                st.executeUpdate("Update accounts set balance="+bala+" where name='"+ls.name+"'");
                                            }
                                        }
                                    break;
                                    case 3: 
                                        System.out.print("\nEnter new password: ");
                                        String pass=sc.next();
                                        st.executeUpdate("Update accounts set password='"+pass+"' where name='"+ls.name+"'");
                                    break;
                                    case 4:
                                        rs=st.executeQuery("Select name,balance from accounts where name='"+ls.name+"'");
                                        DBTablePrinter.printResultSet(rs, 20);
                                    break;
                                    case 5:
                                        flag3=false;
                                    break;
                                    default:
                                        System.out.println("Enter valid choice!");
                                    break;
                                    }
                                }
                                break;
                                case 6:
                                    flag1=false;
                                    System.exit(0);
                                break;
                                default:
                                    System.out.println("Enter valid choice!");
                                break;
                            }
                            
            }
            break;
            default:
                System.out.println("Enter valid choice!");
            break;
        }
        sc.close();
    }

    public static void updateStockPrice() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/stockList";
        String username = "root";
        String password = "";
        
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Statement st=connection.createStatement();
            ResultSet rs1=st.executeQuery("Select count(*) from stocks");
            int rows=0;
            while(rs1.next()){
                rows=rs1.getInt(1);
            }
            for(int i=0;i<rows;i++){
                String updateQuery = "UPDATE stocks SET price = ? where stock_id="+(i+1);
                String update52High = "UPDATE stocks SET week_52_high = ? where stock_id="+(i+1);
                String update52Low = "UPDATE stocks SET week_52_low = ? where stock_id="+(i+1);
                rs1=st.executeQuery("select * from stocks where stock_id="+(i+1));
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    while(rs1.next()){
                        double value=rs1.getDouble("price")+(-2+Math.random()*2);
                        preparedStatement.setDouble(1,value);
                        preparedStatement.executeUpdate();
                        if(value>rs1.getDouble("week_52_high")){
                            PreparedStatement preparedStatement2=connection.prepareStatement(update52High);
                            preparedStatement2.setDouble(1, value);
                            preparedStatement2.executeUpdate();
                        }
                        else if(value<rs1.getDouble("week_52_low")){
                            PreparedStatement preparedStatement2=connection.prepareStatement(update52Low);
                            preparedStatement2.setDouble(1, value);
                            preparedStatement2.executeUpdate();
                        } 
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class MyThread extends Thread{

    public void run(){
        while(true){
            try {
                StockMarketManagementSystem.updateStockPrice();
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }
}

