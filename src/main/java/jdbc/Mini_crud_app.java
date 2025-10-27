package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class Mini_crud_app {

	public static void main(String[] args) throws Exception{
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Demo","postgres","test");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose one option:\n 1.Add new student\n2.update student detalis\n3.delete a student\n4.Show all students");
		int option = sc.nextInt();
		switch(option) {
		case 1:
			try {
				System.out.print("Enter StudentName: ");
				String sname = sc.next();
				System.out.println("\nEnter StudentRollno: ");
				int rollno = sc.nextInt();
				System.out.println("\nEnter StudentAge: ");
				int age = sc.nextInt();
				System.out.println("\nEnter StudentBranch: ");
				String branch = sc.next();
				String sql = "insert into students values(?,?,?,?)";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, sname);
				st.setInt(2, rollno);
				st.setInt(3, age);
				st.setString(4, branch);
				st.executeUpdate();
				System.out.println("Succesfully added new Student");
			}
			catch(Exception e) {
				System.out.println("Enter valid input brother");
			}
			break;
		case 2:
			try {
				System.out.print("Enter the roll no of student: ");
				int rollNo = sc.nextInt();
				System.out.println("Enter the details you want to change: \n1.Name\n2.RollNo\n3.Age\n4.Branch");
				int op = sc.nextInt();
				if(op==1) {
					System.out.print("Enter new name: ");
					String name = sc.nextLine();
					String sql1 = "update students set name = ? where rollNo = ?";
					PreparedStatement st1 = con.prepareStatement(sql1);
					st1.setString(1, name);
					st1.setInt(2,rollNo);	
					st1.executeUpdate();
					System.out.println("Succesfully updated name of the student");
				}
				else if(op==2) {
					System.out.print("Enter new rollNo: ");
					int num = sc.nextInt();
					String sql1 = "update students set rollNo = ? where rollNo = ?";
					PreparedStatement st1 = con.prepareStatement(sql1);
					st1.setInt(1, num);
					st1.setInt(2,rollNo);
					st1.executeUpdate();
					System.out.println("Succesfully updated roll no of the student");
				}
				else if(op==3) {
					System.out.print("Enter new Age: ");
					int agee = sc.nextInt();
					String sql1 = "update students set age = ? where rollNo = ?";
					PreparedStatement st1 = con.prepareStatement(sql1);
					
					st1.setInt(1, agee);
					st1.setInt(2,rollNo);
					st1.executeUpdate();
					System.out.println("Succesfully updated age of the student");
				}
				else if(op==4) {
					System.out.print("Enter new branch: ");
					String course = sc.next();
					String sql1 = "update students set branch = ? where rollNo = ?";
					PreparedStatement st1 = con.prepareStatement(sql1);
					st1.setString(1, course);
					st1.setInt(2,rollNo);
					System.out.println("Succesfully updated branch of the student");
					st1.executeUpdate();
				}
				else System.out.println("Enter valid option");
			}
			catch(Exception e) {
				System.out.println("Enter valid input brother");
			}
			break;
		case 3:
			try {
				System.out.println("Enter the rollNo of the student you want to delete");
				int roll = sc.nextInt();
				String sql1 = "delete from students where rollno = ?";
				PreparedStatement st2 = con.prepareStatement(sql1);
				st2.setInt(1, roll);
				st2.executeUpdate();
				System.out.println("Succesfully deleted record of the student");
			}
			catch(Exception e) {
				System.out.println("Enter valid input brother");
			}
			break;
		case 4:
			try {
				String sql2 = "select * from students";
				PreparedStatement st3 = con.prepareStatement(sql2);
				ResultSet rs = st3.executeQuery();
				System.out.println("Name "+"RollNo "+"Age "+"Branch ");
				while(rs.next()) {
					System.out.println(rs.getString("name")+" "+rs.getInt("rollNo")+" "+rs.getInt("age")+" "+rs.getString("branch"));
				}
			}
			catch(Exception e) {
				System.out.println("Enter valid input Brother");
			}
			break;
			
			
		}
		con.close();

	}

}
