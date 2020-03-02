package homework2;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	private static Scanner scanner = new Scanner(System.in);
	private static Theater theater = new Theater();
	private static ArrayList<User> userList = new ArrayList<User>();

	public static void main(String[] args) {

		int opt = 0;

		while (true) {
			Printing.PrintMenu();

			while (true) {
				try {
					while (true) {
						opt = scanner.nextInt();
						scanner.nextLine();

						if (opt > 0 && opt < 4)
							break;

						System.out.println("1~3 사이의 정수를 입력해주세요.");
					}
					break;
				} catch (Exception e) {
					System.out.println("1~3 사이의 정수를 입력해주세요.");
					scanner.nextLine();
				}
			}

			if (opt == 1) { /* 1.계정 생성 */
				User temp = new User();
				Printing.AppendUser(temp, userList);
			}

			else if (opt == 2) { /* 2.로그인 */
				String id;
				String password;

				System.out.println("***로그인 ***");
				System.out.println("아이디: ");
				id = scanner.nextLine();
				System.out.println("비밀번호: ");
				password = scanner.nextLine();

				int key = -1;

				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getId().equals(id) && userList.get(i).getPassword().equals(password))
						key = i;
				}

				if (key > -1) {
					System.out.println("로그인 성공");

					if (userList.get(key).getAdmin() == 1) { // 관리자(Admin)
						Admin admin=(Admin) userList.get(key);
						
						while (true) {
							Printing.PrintAdminMenu();
							int admin_opt = 0;
							boolean admin_quit = false;
							
							while (true) {
								try { 
									admin_opt = scanner.nextInt(); /*  admin_opt는 1~4사이의 int값만 입력가능      */
									scanner.nextLine();
									if (admin_opt < 1 || admin_opt > 4) {
										System.out.println("1~4까지의 숫자만 입력해주세요.");
										continue;
									} 
									
									else {
										break;
									}
								} catch (Exception e) {
									scanner.nextLine();
									System.out.println("1~4까지의 숫자만 입력해주세요.");
								}
							}

							switch (admin_opt) {
							case 1: // 1.영화 추가
								admin.AppendMovie(theater);
								break;
							case 2: // 2.영화 삭제
								admin.DeleteMovie(theater, userList);
								break;
							case 3: // 3.영화 리스트 보기
								admin.BrowseMovie(theater);
								break;
							case 4: // 4.로그아웃
								admin_quit = true;
								break;
							}

							if (admin_quit == true) {
								System.out.println("*** 로그아웃 ***");
								break;
							}
						}
					} else if (userList.get(key).getAdmin() == 0) { // 일반사용자(Audience)
						Audience audience = (Audience) userList.get(key);

						while (true) {
							Printing.PrintAudienceMenu();
							int audie_opt = 0;
							boolean audie_quit = false;
							
							
							while (true) {
								try {
									audie_opt = scanner.nextInt(); /*  audie_opt는 1~4사이의 int값만 입력가능      */
									scanner.nextLine();

									if (audie_opt < 1 || audie_opt > 5) {
										System.out.println("1~5까지의 숫자만 입력해주세요.");
										continue;
									} else {
										break;
									}
								} catch (Exception e) {
									scanner.nextLine();
									System.out.println("1~5까지의 숫자만 입력해주세요.");
								}
							}

							switch (audie_opt) {
							case 1: // 1.영화 예매
								audience.ReserveMovie(theater);
								break;
							case 2: // 2.예매 내역 확인
								audience.ViewReservedMovie(theater);
								break;
							case 3: // 3.영화 정보 상세보기
								audience.BrowseMovie(theater);
								break;
							case 4: // 4.잔액 확인
								audience.CheckMoney();
								break;
							case 5: // 5.로그아웃
								audie_quit = true;
								break;
							}

							if (audie_quit == true) {
								System.out.println("*** 로그아웃 ***");
								break;
							}
						}
					}
				}

				else if (key == -1) {
					System.out.println("로그인 실패");
				}
			}

			else if (opt == 3) { /* 3.종료 */
				System.out.println("Program Exit");
				break;
			}
		}
	}
}
