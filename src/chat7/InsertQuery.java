package chat7;

import java.util.Scanner;

public class InsertQuery extends IConnectImpl{

	public InsertQuery() {
		super("kosmo", "1234");
	}

	@Override
	public void execute() {
		try {
			String query = "INSERT into NetworkChat values (seq_NetworkChat.nextval, ?, ?, ?)";

			psmt = con.prepareStatement(query);

			Scanner scan = new Scanner(System.in);
			System.out.print("대화명 : ");
			String pb_name = scan.nextLine();
			System.out.print("대화내용 : ");
			String pb_phoneNumber = scan.nextLine();
			System.out.print("현재시간 : ");
			int pb_birthday = scan.nextInt();

			psmt.setString(1, pb_name);
			psmt.setString(2, pb_phoneNumber);
			psmt.setInt(3, pb_birthday);

			int affected = psmt.executeUpdate();
			System.out.println(affected + "개의 계좌입력이 완료되었습니다.");

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

}
