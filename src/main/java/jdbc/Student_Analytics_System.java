package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class Student_Analytics_System {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Demo","postgres","test");
		boolean flag = true;
		while(flag) {
			System.out.println("Choose the option:\n1.Add\n2.Update\n3.Delete\n4.View All\n5.view reports\n6.Exit");
			int option  = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				try {
					System.out.print("Enter the Name of the student: ");
					String name = sc.next();
					System.out.print("\nEnter the rollNo of the student: ");
					int rollNo = sc.nextInt();
					String existedname = null;
					String esql = "select name from students where id = ?";
					PreparedStatement pst = con.prepareStatement(esql);
					pst.setInt(1,rollNo);
					ResultSet rname = pst.executeQuery();
					while(rname.next()) {
						existedname = rname.getString("name");
					}
					if(name!=null) {
						System.out.println("the id is already existed so please enter new one");
						rollNo = sc.nextInt();
					}
					System.out.print("\nEnter the Age of the student: ");
					int age = sc.nextInt();
					sc.nextLine();
					System.out.print("\nEnter the branch: ");
					String branch = sc.next();
					String sql1 = "insert into students values(?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(sql1);
					ps.setString(1,name);
					ps.setInt(2, rollNo);
					ps.setInt(3, age);
					ps.setString(4, branch);
					ps.executeUpdate();
					System.out.println("New Record added succesfully");
					
				}catch(Exception e) {
					System.out.println("Enter valid input bro");
				}
				break;
			case 2:
				try {
					System.out.println("Enter the roll no of the student");
					int rollno = sc.nextInt();
					System.out.println("Choose which one you want to update:\n1.Name\n2.Roll No\n3.Age\n4.Branch");
					int op = sc.nextInt();
					sc.nextLine();
					if(op==1) {
						System.out.print("Enter name to update: ");
						String name = sc.nextLine();
						PreparedStatement ps1 = con.prepareStatement("update students set name = ? where id = ?");
						ps1.setString(1, name);
						ps1.setInt(2, rollno);
						ps1.executeUpdate();
						System.out.println("Name updated succesfully");
					}
					else if(op==2) {
						System.out.print("enter new rollno: ");
						int roll = sc.nextInt();
						PreparedStatement ps1 = con.prepareStatement("update students set rollNo = ? where rollNo = ?");
						ps1.setInt(1, roll);
						ps1.setInt(2, rollno);
						ps1.executeUpdate();
						System.out.println("Roll No updated succesfully");
					}
					else if(op==3) {
						System.out.print("Enter new age: ");
						int age = sc.nextInt();
						PreparedStatement ps1 = con.prepareStatement("update students set age = ? where rollNo = ?");
						ps1.setInt(1, age);
						ps1.setInt(2, rollno);
						ps1.executeUpdate();
						System.out.println("age updated succesfully");
					}
					else if(op==4) {
						System.out.print("Enter new branch :");
						String course = sc.next();
						PreparedStatement ps1 = con.prepareStatement("update students set branch = ? where rollNo = ?");
						ps1.setString(1, course);
						ps1.setInt(2, rollno);
						ps1.executeUpdate();
						System.out.println("Branch updated succesfully");
					}
					else System.out.println("Enter valid input");
				}catch(Exception e) {
					System.out.println("Enter valid input".toUpperCase());
				}
				break;
			case 3:
				try {
					System.out.print("Enter the roll no of the student you want to delete: ");
					int no = sc.nextInt();
					sc.nextLine();
					System.out.println("you sure wanna delete this record??y/n");
					char ans = sc.next().charAt(0);
					if(ans=='n') System.out.println("can't able to delete the record");
					else {
						PreparedStatement ps2 = con.prepareStatement("delete from students where rollNo = ?");
						ps2.setInt(1, no);
						ps2.executeUpdate();
						System.out.println("Succesfully deleted the record");
					}
				}catch(Exception e) {
					System.out.println("enter valid input brohh");
				}
				break;
			case 4:
				PreparedStatement ps3 = con.prepareStatement("select * from students");
				ResultSet r= ps3.executeQuery();
				System.out.printf("%-8s|%-8s|%-4s|%-8s|\n", "Name","RollNo","Age","Branch");
				while(r.next()) {
					System.out.printf("%-8s|%-8s|%-4s|%-8s|\n",r.getString("name"),r.getInt(2),r.getInt(3),r.getString("branch"));
				}
				break;
			case 5:
				try {
					System.out.println("choose one option :\n1.Average Age\n2.students per branch\n3.oldest student\n4.youngest student");
					int report = sc.nextInt();
					if(report==1) {
						PreparedStatement ps4 = con.prepareStatement("select avg(age) from students");
						ResultSet avg = ps4.executeQuery();
						if(avg.next()) {
							System.out.println("Average Age of Students: "+(int)avg.getInt(1));
						}
					}
					else if(report==2) {
						PreparedStatement ps4 = con.prepareStatement("select branch,count(rollNo) from students group by branch");
						ResultSet rs = ps4.executeQuery();
						while(rs.next()) {
							System.out.println("Branch: "+rs.getString("branch")+" No of students: "+rs.getInt(2));
						}
						
					}
					else if(report == 3) {
						PreparedStatement ps4 = con.prepareStatement("select max(age) from students");
						ResultSet rs = ps4.executeQuery();
						if(rs.next()) {
							System.out.println("oldest age is: "+rs.getInt(1));
						}
					}
					else if(report==4) {
						PreparedStatement ps4 = con.prepareStatement("select min(age) as Age from students");
						ResultSet rs = ps4.executeQuery();
						if(rs.next()) {
							System.out.println("yougest age is: "+rs.getInt("age"));
						}
					}else System.out.println("choose valid option bro");
				}catch(Exception e) {
					System.out.println("enter valid input");
				}
				
			case 6:
				flag = false;
				break;
			default:
				System.out.println("choose valid option")	;		
				}
			
			
		}
		
		
			
		
	}

}
