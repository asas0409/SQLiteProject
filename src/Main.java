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
				System.out.print("������ �޴� �Է�(C:1, R:2, U:3, D:4, ����: 0) : ");
				menu = s.nextInt();
				if (menu == 2) {
					// ��ȸ
					System.out.println("\n*** ������ ��ȸ ***");
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
					// �߰�
					System.out.println("\n*** �� ������ �߰� ***");
					Statement stat2 = con.createStatement();
					String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate) values ('��ȿ��', '����', '1990���, 2000���, 2010���', '1999��', datetime('now', 'localtime'));";
					int cnt = stat2.executeUpdate(sql2);
					if (cnt > 0)
						System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!");
					else
						System.out.println("[Error] ������ �߰� ����!");
					stat2.close();
				} else if (menu == 3) {
					// ����
					System.out.println("\n*** ������ ���� ***");
					Statement stat3 = con.createStatement();
					String sql3 = "update g_artists set debut = '1999�� / ���� �� ���� ��' where name = '��ȿ��';";
					int cnt3 = stat3.executeUpdate(sql3);
					if (cnt3 > 0)
						System.out.println("�����Ͱ� �����Ǿ����ϴ�.");
					else
						System.out.println("[Error] ������ ���� ����!");
					stat3.close();
				} else if (menu == 4) {
					// ����
					System.out.println("\n*** ������ ���� ***");
					Statement stat4 = con.createStatement();
					String sql4 = "delete from g_artists where id = 2;";
					int cnt4 = stat4.executeUpdate(sql4);
					if (cnt4 > 0)
						System.out.println("�����Ͱ� �����Ǿ����ϴ�.");
					else
						System.out.println("[Error] ������ ���� ����!");
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
