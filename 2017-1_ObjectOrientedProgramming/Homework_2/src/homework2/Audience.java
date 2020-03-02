package homework2;

import java.util.ArrayList;
import java.util.Scanner;

public class Audience extends User { // SuperClass : User

	private int money = 0;
	private ArrayList<ReservedMovie> ReserveMovieList = new ArrayList<ReservedMovie>();
	private Scanner scanner = new Scanner(System.in);

	public Audience() { //생성자
		money = 100000;
	}

	public Audience(User user) { //생성자
		super(user.getId(), user.getPassword(), user.getAdmin(), user.getAge());
		money = 100000;

	}

	/* 1.영화 예매 */
	public void ReserveMovie(Theater theater) {

		int Reserve_id = 0;
		int Reserve_key = -1;
		int count = 0;

		System.out.println("*** 영화 예매 ***");
		System.out.println("예약 가능 영화 목록");
		System.out.println("-----------------------------------------");
		for (int i = 0; i < theater.getMovieList().size(); i++) {
			System.out.println(
					theater.getMovieList().get(i).getTitle() + " (ID:" + theater.getMovieList().get(i).getId() + ")");
		}
		System.out.println("-----------------------------------------");

		while (true) {
			try { 
				System.out.println("예매 영화 ID: "); /*  영화의 ID는 int값만 입력가능      */
				Reserve_id = scanner.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("ID는 정수만 입력가능합니다. 다시 입력해주세요.");
				scanner.nextLine();
			}
		}

		for (int i = 0; i < theater.getMovieList().size(); i++) {
			if (Reserve_id == theater.getMovieList().get(i).getId()) {
				Reserve_key = i;
				break;
			}
		}

		if (Reserve_key == -1) {
			System.out.println("입력한 ID를 가지는 영화가 존재하지 않습니다. 사용자 메뉴로 되돌아갑니다.");
			return;
		} else if (Reserve_key > -1) {
			if (theater.getMovieList().get(Reserve_key).getAge() > this.getAge()) {
				System.out.println("연령 제한으로 인해 보실 수 없습니다.");
				return;
			} 
			
			/* 좌석 출력 시작 (좌석표의 Col이 두자리수 이상일때 띄어쓰기를 통해 출력을 구현함)*/
			else {
				System.out.println("-----------------------------------------");

				if (theater.getMovieList().get(Reserve_key).getCol() > 9) {
					if (theater.getMovieList().get(Reserve_key).getCol() < 101) {
						System.out.print("  ");
						for (int i = 0; i < theater.getMovieList().get(Reserve_key).getCol() - 1; i++)
							System.out.print((i + 1) + " ");

						System.out.println();

						for (int row = 0; row < theater.getMovieList().get(Reserve_key).getRow(); row++) {
							for (int col = 0; col < theater.getMovieList().get(Reserve_key).getCol(); col++) {
								if (col < 10)
									System.out.print(theater.getMovieList().get(Reserve_key).getSeat()[row][col] + " ");
								else
									System.out.print(theater.getMovieList().get(Reserve_key).getSeat()[row][col] + "  ");
							}
							System.out.println();
						}
					} else {
						System.out.print("  ");
						for (int i = 0; i < theater.getMovieList().get(Reserve_key).getCol() - 1; i++)
							System.out.print((i + 1) + " ");

						System.out.println();

						for (int row = 0; row < theater.getMovieList().get(Reserve_key).getRow(); row++) {
							for (int col = 0; col < theater.getMovieList().get(Reserve_key).getCol(); col++) {
								if (col < 10)
									System.out.print(theater.getMovieList().get(Reserve_key).getSeat()[row][col] + " ");
								else {
									System.out.print(theater.getMovieList().get(Reserve_key).getSeat()[row][col]);
									for (int i = col; i > 0; i /= 10)
										System.out.print(" ");
								}
							}
							System.out.println();
						}
					}
				} else {
					System.out.print(" ");
					for (int i = 0; i < theater.getMovieList().get(Reserve_key).getCol() - 1; i++)
						System.out.print(i + 1);

					System.out.println();

					for (int row = 0; row < theater.getMovieList().get(Reserve_key).getRow(); row++) {
						for (int col = 0; col < theater.getMovieList().get(Reserve_key).getCol(); col++) {
							System.out.print(theater.getMovieList().get(Reserve_key).getSeat()[row][col]);
						}
						System.out.println();
					}
				}
				System.out.println("-----------------------------------------");
				/* 좌석 출력 끝  */
				
				String SeatNumber = null;

				scanner.nextLine();
				System.out.println("예매 좌석 선택 (종료: -1입력)");

				while (true) {
					SeatNumber = scanner.nextLine();

					if (SeatNumber.equals("-1")) {
						System.out.println("예매 완료");
						break;
					}

					else {

						try {
							int reserve_row = (SeatNumber.charAt(0) - 65);

							if (reserve_row < 0 || reserve_row > theater.getMovieList().get(Reserve_key).getRow() - 1)
								throw new IllegalArgumentException();

							int reserve_col = (Integer.parseInt(SeatNumber.substring(1)));

							if (this.getMoney() - theater.getMovieList().get(Reserve_key).getPrice() < 0) {
								System.out.println("잔액이 부족합니다.");
							}

							else {
								if (theater.getMovieList().get(Reserve_key).getSeat()[reserve_row][reserve_col] == 'O') {
									this.setMoney(this.getMoney() - theater.getMovieList().get(Reserve_key).getPrice());
									theater.getMovieList().get(Reserve_key).getSeat()[reserve_row][reserve_col] = 'X';
									count++;
								}

								else {
									System.out.println("이미 예메된 좌석입니다.");
								}
							}
						} catch (Exception e) {
							System.out.println("유효하지 않은 좌석입니다.('A5'와 같이 [알파벳 대문자+양수]의 형태로 입력해주세요.)");
							System.out.println("row: A~" + (char) (theater.getMovieList().get(Reserve_key).getRow() + 'A' - 1) + " Col: 1~" + (theater.getMovieList().get(Reserve_key).getCol()-1));
							System.out.println("예매 좌석 선택 (종료: -1입력)");
						}
					}
				}
			}

			if (count > 0) {
				ReservedMovie ReserveMovie = new ReservedMovie(theater.getMovieList().get(Reserve_key));
				ReserveMovie.setCount(count);
				
				int count_key = -1;

				if (this.getReserveMovieList().size() == 0)
					this.getReserveMovieList().add(ReserveMovie);

				else if (this.getReserveMovieList().size() > 0) {
					for (int i = 0; i < this.getReserveMovieList().size(); i++) {
						if (theater.getMovieList().get(Reserve_key).getTitle().equals(this.getReserveMovieList().get(i).getTitle())) {
							this.getReserveMovieList().get(i).setCount(this.getReserveMovieList().get(i).getCount() + count);
							count_key = i;
							break;
						}
					}

					if (count_key == -1) {
						this.getReserveMovieList().add(ReserveMovie);
					}
				}
			}
		}
	}

	/* 2.예매 내역 확인 */
	public void ViewReservedMovie(Theater theater) {

		// 예매 내역 출력 
		System.out.println("*** 예매 내역 확인 ***");
		for (int i = 0; i < this.getReserveMovieList().size(); i++) {
			System.out.println(this.getReserveMovieList().get(i).getTitle() + ", " + this.getReserveMovieList().get(i).getCount() + "장");
		}
	}

	/* 4.잔액 확인 */
	public void CheckMoney() {
		System.out.println("*** 잔액 확인 ***");
		System.out.println(this.getMoney());
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<ReservedMovie> getReserveMovieList() {
		return ReserveMovieList;
	}

	public void setReserveMovieList(ArrayList<ReservedMovie> reserveMovieList) {
		ReserveMovieList = reserveMovieList;
	}

}
