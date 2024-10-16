// Frame1 = Main Frame
// Frame2 = Login Frame
// Frame3 = Registration Frame
// Frame4 = Home Page Frame
// Frame5 = Book Issue Frame
// Frame6 = Return Book Frame
// Frame7 = Available Books Frame

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

class Frame1 extends JFrame{
    JButton b1, b2;
    ImageIcon background;

    public Frame1(){
        super("Library Management System");
        setSize(600, 400);
        setLocation(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    

        background = new ImageIcon("library.jpg"); 

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        b1 = new JButton("Login");
        b1.setBounds(450, 80, 100, 40);
        backgroundPanel.add(b1);  // Add to backgroundPanel

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame2();
            }
        });

        b2 = new JButton("Register");
        b2.setBounds(450, 250, 100, 40);
        backgroundPanel.add(b2);  // Add to backgroundPanel

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame3();
            }     
        });

        setVisible(true);

    }

}

class Frame2 extends JFrame{
    JButton b1, b2, b3;
    JLabel l,l1,l2;
    JTextField t1;
    JPasswordField p1;

    String url = "jdbc:mysql://localhost:3306/project"; // Database name is 'project'
    String username = "root"; // MySQL username
    String password = "password";  // MySQL password
    Connection connection = null;

    public Frame2(){
        super("Login");
        setSize(500, 400);
        setLocation(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(null);  
        Color lightBlue = new Color(173, 216, 230);
        getContentPane().setBackground(lightBlue);

        l = new JLabel("Login");
        l.setBounds(200,20,150,80);
        l.setFont(new Font("Arial", Font.PLAIN, 30));
        add(l);  

        l1 = new JLabel("PRN :");
        l1.setBounds(90, 120, 80, 40);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l1);  

        l2 = new JLabel("Password :");
        l2.setBounds(90, 180, 100, 40);
        l2.setForeground(Color.RED);
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l2);  

        t1 = new JTextField("");
        t1.setBounds(210, 120, 150, 40);
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t1);  

        p1 = new JPasswordField("");
        p1.setBounds(210, 180, 150, 40);
        p1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(p1);  

        b1 = new JButton("Login");
        b1.setBounds(120, 260, 100, 40);
        add(b1);  

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            char pw[] = p1.getPassword();
            String pword = new String(pw);

            if(t1.getText().equals("")){
                JOptionPane.showMessageDialog(Frame2.this, "Please fill all the fields");
            }
            else{     
                int prn = Integer.parseInt(t1.getText());
            try {
                // Load the MySQL JDBC driver
             Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish the connection
             connection = DriverManager.getConnection(url, username, password);

             if (connection != null) {

                PreparedStatement stmt = connection.prepareStatement("select * from register where prn=? and password=?");
                stmt.setInt(1, prn); 
                stmt.setString(2, pword); 
                ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(Frame2.this,"Login Successful!!","Success",JOptionPane.PLAIN_MESSAGE);

                try {
                    if (stmt != null) stmt.close();
                    if (connection != null) connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
                new Frame4();

            } else {
                JOptionPane.showMessageDialog(Frame2.this, "Login Failed.");
            }
                try {
                    if (stmt != null) stmt.close();
                    if (connection != null) connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

             }
             } catch (SQLException ex) {
                  System.out.println("An error occurred while connecting to the database.");
                 ex.printStackTrace();
              } catch (ClassNotFoundException ex) {
                 System.out.println("MySQL JDBC Driver not found.");
                 ex.printStackTrace();
             }

            }
        }
        });

        b2 = new JButton("Clear All");
        b2.setBounds(240, 260, 100, 40);
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        add(b2); 

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                p1.setText("");
            }     
        });

        b3 = new JButton("Back");
        b3.setBounds(400, 20, 80, 30);
        add(b3); 

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame1();
            }     
        });

        setVisible(true);
    }
}

class Frame3 extends JFrame{
    JButton b1, b2,b3;
    JLabel l,l1,l2,l3;
    JTextField t1,t2,t3;

    String url = "jdbc:mysql://localhost:3306/project"; 
    String username = "root"; 
    String password = "password";  
    Connection connection = null;

    public Frame3(){
        super("Resiter");
        setSize(500, 500);
        setLocation(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        setLayout(null); 
        Color lightBlue = new Color(173, 216, 230);
        getContentPane().setBackground(lightBlue);

        l = new JLabel("Register");
        l.setBounds(190,20,150,80);
        l.setFont(new Font("Arial", Font.PLAIN, 30));
        add(l);  

        l1 = new JLabel("PRN :");
        l1.setBounds(90, 120, 80, 40);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l1);  

        l2 = new JLabel("UserName :");
        l2.setBounds(90, 180, 100, 40);
        l2.setForeground(Color.RED);
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l2); 

        l3 = new JLabel("Password :");
        l3.setBounds(90, 240, 100, 40);
        l3.setForeground(Color.RED);
        l3.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l3); 

        t1 = new JTextField("");
        t1.setBounds(240, 120, 150, 40);
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t1);  

        t2 = new JTextField("");
        t2.setBounds(240, 180, 150, 40);
        t2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t2);  

        t3 = new JTextField("");
        t3.setBounds(240, 240, 150, 40);
        t3.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t3);  

        b1 = new JButton("Register");
        b1.setBounds(120, 320, 100, 40);
        add(b1);  

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
                String regex1 = "^[0-9]+$";
                String regex2 = "^[A-Za-z\\s]+$";
                String regex3 = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

            if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")){
                JOptionPane.showMessageDialog(Frame3.this, "Please fill all the fields");
            }
            else if(!t1.getText().matches(regex1)){
                JOptionPane.showMessageDialog(Frame3.this, "Only numbers in PRN field");
            }
            else if(!t2.getText().matches(regex2)){
                JOptionPane.showMessageDialog(Frame3.this, "Only alpdhabets in Name field");
            }
            else if(!t3.getText().matches(regex3)){
                JOptionPane.showMessageDialog(Frame3.this, "Password should contain atleast 8 characters with 1 Capital letter, 1 Small letter, 1 Digit and 1 Special character");
            }
            else{
                int prn = Integer.parseInt(t1.getText());
                String name = t2.getText();
                String pword = t3.getText();
                try {
                   // Load the MySQL JDBC driver 
                 Class.forName("com.mysql.cj.jdbc.Driver");

                   // Establish the connection
                 connection = DriverManager.getConnection(url, username, password);

                 if (connection != null) {

                  PreparedStatement stmt = connection.prepareStatement("select * from register where prn=?");
                   stmt.setInt(1, prn); 
                   ResultSet rs = stmt.executeQuery();

                   if (rs.next()) {
                    JOptionPane.showMessageDialog(Frame3.this, "User PRN is already registered.","Registered",JOptionPane.PLAIN_MESSAGE);

                    try {
                        if (stmt != null) stmt.close();
                        if (connection != null) connection.close();
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    }
                    else{
                  PreparedStatement pstmt = connection.prepareStatement("INSERT INTO register VALUES (?,?,?)");
                    pstmt.setInt(1, prn);       
                    pstmt.setString(2, name); 
                    pstmt.setString(3, pword);
                    int rowsAffected1 = pstmt.executeUpdate();
                    
                    // Check if the insertion was successful
                if (rowsAffected1 > 0) {
                    JOptionPane.showMessageDialog(Frame3.this, "Registration successful!","Success",JOptionPane.PLAIN_MESSAGE);

                    try {
                        if (stmt != null) stmt.close();
                        if (pstmt != null) pstmt.close();
                        if (connection != null) connection.close();
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    dispose();
                    new Frame2();
                } else {
                    JOptionPane.showMessageDialog(Frame3.this, "Registration Failed");
                }
                    try {
                        if (pstmt != null) pstmt.close();
                        if (stmt != null) stmt.close();
                        if (connection != null) connection.close();
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
                } catch (SQLException ex) {
                    System.out.println("An error occurred while connecting to the database.");
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    System.out.println("MySQL JDBC Driver not found.");
                    ex.printStackTrace();
                }

            }
        }
        });

        b2 = new JButton("Clear All");
        b2.setBounds(240, 320, 100, 40);
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        add(b2); 

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
            }     
        });

        b3 = new JButton("Back");
        b3.setBounds(400, 20, 80, 30);
        add(b3); 

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame1();
            }     
        });

        setVisible(true);
    }
}

class Frame4 extends JFrame{
    JLabel l;
    JButton b1,b2,b3,b;
    ImageIcon background;

    public Frame4(){
        super("Home Page");
        setSize(500, 400);
        setLocation(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        setLayout(null);

        background = new ImageIcon("books.jpg"); 

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        l = new JLabel("Library Services");
        l.setBounds(110, 5, 300,65);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Arial", Font.PLAIN, 36));
        backgroundPanel.add(l);  // Add to backgroundPanel

        b1 = new JButton("Issue a Book");
        b1.setBounds(30, 100, 150, 40);
        b1.setFont(new Font("Arial", Font.PLAIN, 18));
        backgroundPanel.add(b1);  // Add to backgroundPanel

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame5();
            }
        });

        b2 = new JButton("Return a Book");
        b2.setBounds(30, 170, 150, 40);
        b2.setFont(new Font("Arial", Font.PLAIN, 18));
        backgroundPanel.add(b2);  // Add to backgroundPanel

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame6();
            }     
        });

        b3 = new JButton("Available Books");
        b3.setBounds(25, 250, 170, 40);
        b3.setFont(new Font("Arial", Font.PLAIN, 18));
        backgroundPanel.add(b3);  // Add to backgroundPanel

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame7();
            }     
        });

        b = new JButton("Log Out");
        b.setBounds(400, 20, 80, 30);
        backgroundPanel.add(b);  // Add to backgroundPanel

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame1();
            }     
        });

        setVisible(true);

    }
}

class Frame5 extends JFrame{
    JButton b1, b2,b3;
    JLabel l,l1,l2,l3;
    JTextField t1,t2,t3;

    String url = "jdbc:mysql://localhost:3306/project"; 
    String username = "root"; 
    String password = "password";  
    Connection connection = null;

    public Frame5(){
        super("Book Issue");
        setSize(500, 500);
        setLocation(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        setLayout(null); 
        Color lightBlue = new Color(173, 216, 230);
        getContentPane().setBackground(lightBlue);

        l = new JLabel("Issue a Book");
        l.setBounds(150,20,200,80);
        l.setFont(new Font("Arial", Font.PLAIN, 30));
        add(l);  

        l1 = new JLabel("PRN :");
        l1.setBounds(90, 120, 80, 40);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l1);  

        l2 = new JLabel("Book ID :");
        l2.setBounds(90, 180, 100, 40);
        l2.setForeground(Color.RED);
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l2); 

        l3 = new JLabel("Book Name :");
        l3.setBounds(90, 240, 130, 40);
        l3.setForeground(Color.RED);
        l3.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l3); 

        t1 = new JTextField("");
        t1.setBounds(240, 120, 170, 40);
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t1);  

        t2 = new JTextField("");
        t2.setBounds(240, 180, 170, 40);
        t2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t2);  

        t3 = new JTextField("");
        t3.setBounds(240, 240, 170, 40);
        t3.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t3);  

        b1 = new JButton("Issue");
        b1.setBounds(120, 320, 100, 40);
        add(b1);  

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")){
                JOptionPane.showMessageDialog(Frame5.this, "Please fill all the fields");
            }
            else{
                int prn = Integer.parseInt(t1.getText());
                String b_id = t2.getText();
                String b_name = t3.getText();
                try {
                   // Load the MySQL JDBC driver 
                 Class.forName("com.mysql.cj.jdbc.Driver");

                   // Establish the connection
                 connection = DriverManager.getConnection(url, username, password);

                 if (connection != null) {

                  PreparedStatement stmt = connection.prepareStatement("select * from book_db where id=?");
                   stmt.setString(1, b_id); 
                   ResultSet rs = stmt.executeQuery();

                   PreparedStatement tmt = connection.prepareStatement("select * from register where prn=?");
                   tmt.setInt(1, prn); 
                   ResultSet r = tmt.executeQuery();

                   if (!rs.next()) {
                    JOptionPane.showMessageDialog(Frame5.this, "Invalid Book ID","Invalid",JOptionPane.PLAIN_MESSAGE);

                    try {
                        if (stmt != null) stmt.close();
                        if (tmt != null) tmt.close();
                        if (connection != null) connection.close();
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    }
                    else if (!r.next()) {
                        JOptionPane.showMessageDialog(Frame5.this, "PRN not registered","Invalid",JOptionPane.PLAIN_MESSAGE);
    
                        try {
                            if (stmt != null) stmt.close();
                            if (connection != null) connection.close();
                        } 
                        catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else{
                    PreparedStatement pstmt = connection.prepareStatement("select avl_copies from book_db where id=?");
                    pstmt.setString(1, b_id);       
                    ResultSet rs1 = pstmt.executeQuery();
                    rs1.next();
                    int availableCopies = rs1.getInt("avl_copies");

                    if (availableCopies == 0) {
                        JOptionPane.showMessageDialog(Frame5.this, "Sorry. The book is unavailable. Please try again in 2 to 3 days.","Unavailable",JOptionPane.PLAIN_MESSAGE);

                    try {
                        if (stmt != null) stmt.close();
                        if (tmt != null) tmt.close();
                        if (pstmt != null) pstmt.close();
                        if (connection != null) connection.close();
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    } 
                    else {
                        availableCopies--;
                        PreparedStatement rstmt = connection.prepareStatement("update book_db set avl_copies=? where id=?");
                        rstmt.setInt(1, availableCopies);  
                        rstmt.setString(2, b_id);  

                        int rowsAffected1 = rstmt.executeUpdate();

                        LocalDate issueDate = LocalDate.now();
                        LocalDate returnDate = issueDate.plusDays(7);
                        PreparedStatement tstmt = connection.prepareStatement("insert into issued_book values(?,?,?,CURDATE(),DATE_ADD(CURDATE(), INTERVAL 7 DAY)) ");
                        tstmt.setInt(1, prn);  
                        tstmt.setString(2, b_id);  
                        tstmt.setString(3, b_name);  

                        int rowsAffected2 = tstmt.executeUpdate();

                        if (rowsAffected1 > 0 && rowsAffected2 > 0){
                            JOptionPane.showMessageDialog(Frame5.this, "Book has been issued on date "+issueDate+" and date of return is "+returnDate,"Success",JOptionPane.PLAIN_MESSAGE);
        
                            try {
                                if (stmt != null) stmt.close();
                                if (tmt != null) tmt.close();
                                if (pstmt != null) pstmt.close();
                                if (tstmt != null) tstmt.close();
                                if (rstmt != null) rstmt.close();
                                if (connection != null) connection.close();
                            } 
                            catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            dispose();
                            new Frame4();
                        } else {
                            JOptionPane.showMessageDialog(Frame5.this, "Book issue failed.");
                        }

                    }
                     try {
                        if (stmt != null) stmt.close();
                        if (tmt != null) tmt.close();
                        if (pstmt != null) pstmt.close();
                        if (connection != null) connection.close();
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
                } catch (SQLException ex) {
                    System.out.println("An error occurred while connecting to the database.");
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    System.out.println("MySQL JDBC Driver not found.");
                    ex.printStackTrace();
                }

            }
        }
        });

        b2 = new JButton("Clear All");
        b2.setBounds(240, 320, 100, 40);
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        add(b2); 

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
            }     
        });

        b3 = new JButton("Back");
        b3.setBounds(400, 20, 80, 30);
        add(b3); 

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame4();
            }     
        });

        setVisible(true);
    }
}

class Frame6 extends JFrame{
    JButton b1, b2,b3;
    JLabel l,l1,l2,l3;
    JTextField t1,t2,t3;

    String url = "jdbc:mysql://localhost:3306/project"; 
    String username = "root"; 
    String password = "password";  
    Connection connection = null;

    public Frame6(){
        super("Book Return");
        setSize(500, 500);
        setLocation(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        setLayout(null); 
        Color lightBlue = new Color(173, 216, 230);
        getContentPane().setBackground(lightBlue);

        l = new JLabel("Return a Book");
        l.setBounds(150,20,200,80);
        l.setFont(new Font("Arial", Font.PLAIN, 30));
        add(l);  

        l1 = new JLabel("PRN :");
        l1.setBounds(90, 120, 80, 40);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l1);  

        l2 = new JLabel("Book ID :");
        l2.setBounds(90, 180, 100, 40);
        l2.setForeground(Color.RED);
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l2); 

        l3 = new JLabel("Book Name :");
        l3.setBounds(90, 240, 130, 40);
        l3.setForeground(Color.RED);
        l3.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l3); 

        t1 = new JTextField("");
        t1.setBounds(240, 120, 170, 40);
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t1);  

        t2 = new JTextField("");
        t2.setBounds(240, 180, 170, 40);
        t2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t2);  

        t3 = new JTextField("");
        t3.setBounds(240, 240, 170, 40);
        t3.setFont(new Font("Arial", Font.PLAIN, 18));
        add(t3);  

        b1 = new JButton("Return");
        b1.setBounds(120, 320, 100, 40);
        add(b1);  

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
            if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")){
                JOptionPane.showMessageDialog(Frame6.this, "Please fill all the fields");
            }
            else{
                int prn = Integer.parseInt(t1.getText());
                String b_id = t2.getText();

                try {
                   // Load the MySQL JDBC driver 
                 Class.forName("com.mysql.cj.jdbc.Driver");

                   // Establish the connection
                 connection = DriverManager.getConnection(url, username, password);

                 if (connection != null) {

                  PreparedStatement stmt = connection.prepareStatement("select * from issued_book where b_id=? and prn=?");
                   stmt.setString(1, b_id); 
                   stmt.setInt(2, prn);  
                   ResultSet rs = stmt.executeQuery();

                   if (!rs.next()) {
                    JOptionPane.showMessageDialog(Frame6.this, "Invalid Book ID or PRN","Invalid",JOptionPane.PLAIN_MESSAGE);

                    try {
                        if (stmt != null) stmt.close();
                        if (connection != null) connection.close();
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    }
                    else{
                    PreparedStatement pstmt = connection.prepareStatement("select avl_copies from book_db where id=?");
                    pstmt.setString(1, b_id);       
                    ResultSet rs1 = pstmt.executeQuery();
                    rs1.next();
                    int availableCopies = rs1.getInt("avl_copies");

                        availableCopies++;
                        PreparedStatement rstmt = connection.prepareStatement("update book_db set avl_copies=? where id=?");
                        rstmt.setInt(1, availableCopies);  
                        rstmt.setString(2, b_id);  

                        int rowsAffected1 = rstmt.executeUpdate();

                        PreparedStatement tstmt = connection.prepareStatement("delete from issued_book where prn=? and b_id=?");
                        tstmt.setInt(1, prn);  
                        tstmt.setString(2, b_id);    

                        int rowsAffected2 = tstmt.executeUpdate();

                        if (rowsAffected1 > 0 && rowsAffected2 > 0){
                            JOptionPane.showMessageDialog(Frame6.this, "Book returned successfully","Success",JOptionPane.PLAIN_MESSAGE);
        
                            try {
                                if (pstmt != null) pstmt.close();
                                if (stmt != null) stmt.close();
                                if (rstmt != null) rstmt.close();
                                if (tstmt != null) tstmt.close();
                                if (connection != null) connection.close();
                            } 
                            catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            dispose();
                            new Frame4();
                        } else {
                            JOptionPane.showMessageDialog(Frame6.this, "Book issue failed.");
                        }
                    
                     try {
                        if (pstmt != null) pstmt.close();
                        if (stmt != null) stmt.close();
                        if (rstmt != null) rstmt.close();
                        if (tstmt != null) tstmt.close();
                        if (connection != null) connection.close();
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                  }
                }
                }
                 catch (SQLException ex) {
                    System.out.println("An error occurred while connecting to the database.");
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    System.out.println("MySQL JDBC Driver not found.");
                    ex.printStackTrace();
                }

            }
        }
        });

        b2 = new JButton("Clear All");
        b2.setBounds(240, 320, 100, 40);
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        add(b2); 

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
            }     
        });

        b3 = new JButton("Back");
        b3.setBounds(400, 20, 80, 30);
        add(b3); 

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame4();
            }     
        });

        setVisible(true);
    }
}

class Frame7 extends JFrame{

    JLabel l1;
    JButton b;
    String url = "jdbc:mysql://localhost:3306/project"; 
    String username = "root"; 
    String password = "password";  
    Connection connection = null;
    JTable table;

    public Frame7(){
        super("Available Books");
        setSize(500, 500);
        setLocation(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        setLayout(null); 
        Color lightBlue = new Color(173, 216, 230);
        getContentPane().setBackground(lightBlue);

        l1 = new JLabel("Available Books");
        l1.setBounds(130,20,250,80);
        l1.setFont(new Font("Arial", Font.PLAIN, 30));
        add(l1);  

        b = new JButton("Back");
        b.setBounds(400, 20, 80, 30);
        add(b); 

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame4();
            }     
        });

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

          connection = DriverManager.getConnection(url, username, password);

          if (connection != null) {

            String query = "SELECT * FROM book_db";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get metadata to create table columns
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            String[] columnNames = {"ID", "Name", "Total", "Available"};

            // Add rows to table model
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }

            // Create a JTable with the model and add it to the frame
            table = new JTable(model);
            table.setFont(new Font("Arial", Font.PLAIN, 18));
            table.setRowHeight(25);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 120, 400, 300);  
            add(scrollPane);

            rs.close();
            stmt.close();
            connection.close();
        }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        setVisible(true);
    }

}

public class LibraryManagement {
    public static void main(String[] args) {
        new Frame1();
    }
    
}
