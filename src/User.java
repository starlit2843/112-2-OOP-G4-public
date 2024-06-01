import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class User {

	String server = "jdbc:mysql://140.119.19.73:3315/";
	String database = "108306081";
	String url = server + database + "?useSSL=false";
	String DBUsername = "108306081";
	String DBPassword = "gkv1y";

	public String username;

	Statement stat;
	String query;

	private int q0 = 0;
	private int q1, q2, q3, q4, q5, q6 = 0;
	private String place;

	// singleton pattern.
	private static User instance;

	private User() {
	};

	public static synchronized User getInstance() {
		if (instance == null) {
			instance = new User();
		}
		return instance;
	}

	public void signUp(String username, String password) throws ShortPasswordError, AlreadyExistError {

		try (Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
			;
			if (password.length() < 4) {
				throw new ShortPasswordError("密碼請大於四個字元");
			}

			stat = conn.createStatement();
			query = "SELECT * FROM users WHERE Username = '" + username + "'";
			ResultSet rs = stat.executeQuery(query);
			if (rs.next()) {
				throw new AlreadyExistError("帳號已經存在");
			}

			query = "INSERT INTO `users` (`ID`, `Username`, `Password`) VALUES (NULL, '" + username + "', '" + password
					+ "')";
			stat.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void Login(String username, String password) throws WrongDataError {
		try (Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
			;
			stat = conn.createStatement();

			query = "SELECT * FROM users WHERE Username = '" + username + "' AND Password = '" + password + "'";
			ResultSet rs = stat.executeQuery(query);
			if (!rs.next()) {
				throw new WrongDataError("帳號或密碼錯誤");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void submit(int q1, int q2, int q3, int q4, int q5, int q6, int totalPayment) {
		try (Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
			stat = conn.createStatement();

			String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
			String code = ranCodeGen(8);

			query = "INSERT INTO `records` (`ID`, `Username`, `Code`,`Place`, `Total`, `Q1`, `Q2`, `Q3`, `Q4`, `Q5`, `Q6`, `Time`) "
					+ "VALUES (NULL, '" + username + "', '" + code + "','" + place + "', '" + totalPayment + "', '" + q1
					+ "', '" + q2 + "', '" + q3 + "', '" + q4 + "', '" + q5 + "', '" + q6 + "', '" + time + "')";

			stat.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String info() {
		String info = "";
		try (Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
			stat = conn.createStatement();

			query = "SELECT Code, Place, Total, Q1, Q2, Q3, Q4, Q5, Q6, Time FROM records WHERE Username = '" + username
					+ "'";
			ResultSet rs = stat.executeQuery(query);

			while (rs.next()) {

				// String username_info = rs.getString("Username");
				String code_info = rs.getString("Code");
				String place_info = rs.getString("Place");
				int total_info = rs.getInt("Total");
				int q1_info = rs.getInt("q1");
				int q2_info = rs.getInt("q2");
				int q3_info = rs.getInt("q3");
				int q4_info = rs.getInt("q4");
				int q5_info = rs.getInt("q5");
				int q6_info = rs.getInt("q6");
				String time_info = rs.getString("Time");

				info += String.format("   %-13s%-10s%-7d%-5d%-6d%-7d%-12d%-11d%-11d%-25s%n", code_info, place_info,
						total_info, q1_info, q2_info, q3_info, q4_info, q5_info, q6_info, time_info);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(info);
		return info;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getName() {
		return username;
	}

	// changable code formats
	private String ranCodeGen(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random rd = new Random();
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rd.nextInt(chars.length())));
		}

		return sb.toString();
	}

	public void setq0(int q) {
		q0 = q;
	}

	public void setq1(int q) {
		q1 += q;
	}

	public void setq2(int q) {
		q2 += q;
	}

	public void setq3(int q) {
		q3 += q;
	}

	public void setq4(int q) {
		q4 += q;
	}

	public void setq5(int q) {
		q5 += q;
	}

	public void setq6(int q) {
		q6 += q;
	}

	public void setPlace(String p) {
		place = p;
	}

	public int getq0() {
		return q0;
	}

	public int getq1() {
		return q1;
	}

	public int getq2() {
		return q2;
	}

	public int getq3() {
		return q3;
	}

	public int getq4() {
		return q4;
	}

	public int getq5() {
		return q5;
	}

	public int getq6() {
		return q6;
	}

	public String gerPlace() {
		return place;
	}

	public void resetAll() {
		q1 = 0;
		q2 = 0;
		q3 = 0;
		q4 = 0;
		q5 = 0;
		q6 = 0;
		place = null;
	}

}

class ShortPasswordError extends Exception {
	public ShortPasswordError(String Error) {
		super(Error);
	}
}

class AlreadyExistError extends Exception {
	public AlreadyExistError(String Error) {
		super(Error);
	}
}

class WrongDataError extends Exception {
	public WrongDataError(String Error) {
		super(Error);
	}
}
