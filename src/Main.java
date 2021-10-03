import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");

			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			int menu;
			Scanner s = new Scanner(System.in);
			while (true) {
				System.out.print("실행할 메뉴 입력(C:1, R:2, U:3, D:4, 종료: 0) : ");
				menu = s.nextInt();
				if (menu == 2) {
					// 조회
					System.out.println("\n*** 데이터 조회 ***");
					Statement stat1 = con.createStatement();
					String sql1 = "select * from g_artists";
					ResultSet rs1 = stat1.executeQuery(sql1);
					while (rs1.next()) {
						String id = rs1.getString("id");
						String name = rs1.getString("name");
						String a_type = rs1.getString("a_type");
						String a_year = rs1.getString("a_year");
						String debut = rs1.getString("debut");
						String regdate = rs1.getString("regdate");
						System.out.println(id + " " + name + " " + a_type + " " + a_year + " " + debut + " " + regdate);
					}
					stat1.close();
				} else if (menu == 1) {
					// 추가
					System.out.println("\n*** 새 데이터 추가 ***");
					Statement stat2 = con.createStatement();
					String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate) values ('박효신', '남성', '1990년대, 2000년대, 2010년대', '1999년', datetime('now', 'localtime'));";
					int cnt = stat2.executeUpdate(sql2);
					if (cnt > 0)
						System.out.println("새로운 데이터가 추가되었습니다!");
					else
						System.out.println("[Error] 데이터 추가 오류!");
					stat2.close();
				} else if (menu == 3) {
					// 수정
					System.out.println("\n*** 데이터 수정 ***");
					Statement stat3 = con.createStatement();
					String sql3 = "update g_artists set debut = '1999년 / 해줄 수 없는 일' where name = '박효신';";
					int cnt3 = stat3.executeUpdate(sql3);
					if (cnt3 > 0)
						System.out.println("데이터가 수정되었습니다.");
					else
						System.out.println("[Error] 데이터 수정 오류!");
					stat3.close();
				} else if (menu == 4) {
					// 삭제
					System.out.println("\n*** 데이터 삭제 ***");
					Statement stat4 = con.createStatement();
					String sql4 = "delete from g_artists where id = 2;";
					int cnt4 = stat4.executeUpdate(sql4);
					if (cnt4 > 0)
						System.out.println("데이터가 삭제되었습니다.");
					else
						System.out.println("[Error] 데이터 삭제 오류!");
					stat4.close();
				} else if (menu == 0) {
					return;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}
			}
		}

	}

}
