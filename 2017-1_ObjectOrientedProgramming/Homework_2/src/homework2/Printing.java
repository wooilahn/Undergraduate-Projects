package homework2;

import java.util.ArrayList;
import java.util.Scanner;

public class Printing {

	private static Scanner scanner = new Scanner(System.in);
	
	/* 초기 Menu 출력   Method */
	static void PrintMenu() {
		System.out.println("### Welcome to Ajou Movie Theater ###");
		System.out.println("1. 계정 생성");
		System.out.println("2. 로그인");
		System.out.println("3. 종료");
		System.out.println("원하는 메뉴를 선택하세요.");
	}

	/* AdminMenu 출력   Method */
	static void PrintAdminMenu() {
		System.out.println("### 관리자 메뉴 ###");
		System.out.println("1. 영화 추가");
		System.out.println("2. 영화 삭제");
		System.out.println("3. 영화 리스트 보기");
		System.out.println("4. 로그아웃");
		System.out.println("원하는 메뉴를 선택하세요.");
	}

	/* AudienceMenu 출력   Method */
	static void PrintAudienceMenu() {
		System.out.println("### 일반 사용자 메뉴 ###");
		System.out.println("1. 영화 예매");
		System.out.println("2. 예매 내역 확인");
		System.out.println("3. 영화 정보 상세보기");
		System.out.println("4. 잔액 확인");
		System.out.println("5. 로그아웃");
		System.out.println("원하는 메뉴를 선택하세요.");
	}
	
	/* User 추가 Method */
	static void AppendUser(User user, ArrayList<User> userList) {

		while (true) {
			System.out.println("*** 계정을 생성하세요. ***");
			System.out.println("아이디: ");
			user.setId(scanner.nextLine());
			System.out.println("비밀번호: ");
			user.setPassword(scanner.nextLine());
			
			while (true) {
				try {
					System.out.println("계정등급을 선택하세요. (0=일반, 1=관리자)");
					user.setAdmin(scanner.nextInt()); /*  admin은 0또는 1의 int값만 입력가능      */
					scanner.nextLine();

					if (user.getAdmin() != 0 && user.getAdmin() != 1) {
						System.out.println("계정 등급은 0또는 1의 값만 입력이 가능합니다.");
						continue;
					}
					break;
				} catch (Exception e) {
					scanner.nextLine();
					System.out.println("계정 등급은 0또는 1의 값만 입력이 가능합니다.");
				}
			}

			int id_check = -1;

			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getId().equals(user.getId())) {
					id_check = i;
					break;
				}
			}

			if (id_check > -1) {
				System.out.println("중복된 id가 존재합니다. 다시 입력해주세요.");
				continue;
			}

			if (user.getAdmin() == 1) { /* 관리자전용 계정생성 */

				boolean admin_flag = false;

				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getAdmin() == 1) {
						admin_flag = true;
						break;
					}
				}

				if (admin_flag == true) {
					System.out.println("이미 생성한 관리자 계정이 존재합니다. 관리자 계정은 1개만 존재 가능합니다.");
				} else if (admin_flag == false) {
					Admin admin = new Admin(user);
					userList.add(admin);
					break;
				}
			}

			else if (user.getAdmin() == 0) { /* 일반사용자전용 계정생성 */

				int Audience_age = 0;
				
				while (true) {
					try {
						System.out.println("나이를 입력하세요.");
						Audience_age = scanner.nextInt(); /*  Audience_age는 1이상의 int값만 입력가능      */
						scanner.nextLine();
						
						if(Audience_age < 1)
							throw new IllegalArgumentException();
						
						break;
						
					} catch (Exception e) {
						System.out.println("나이는 1이상의 양수입니다. 다시 입력해주세요.");
						
						if(e instanceof IllegalArgumentException)
							continue;
						else
							scanner.nextLine();
						
					}
				}

				user.setAge(Audience_age);

				Audience audience = new Audience(user);
				userList.add(audience);
				break;
			}

			else {
				System.out.println("\nadmin을 잘못입력하였습니다. 다시 입력해주세요.");
			}
		}
	}
}
