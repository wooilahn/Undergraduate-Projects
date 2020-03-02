package homework2;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User { //SuperClass : User

	private Scanner scanner = new Scanner(System.in);

	public Admin() { //생성자

	}

	public Admin(User user) { //생성자
		super(user.getId(), user.getPassword(), user.getAdmin());
	}

	/* 영화 추가 Method */
	/*[ID && 상영시간(1이상) && 제한연령(0이상) && 표 값(0이상) && 좌석 행(1~26),열(1이상)]은  정수(int) */
	public void AppendMovie(Theater theater) {

		Movie addMovie = new Movie();

		while (true) {
			try {
				System.out.println("*** 영화 추가 ***");
				
				while(true){
				boolean id_flag = false;	
					
				System.out.println("ID:"); /*  영화의 ID는 int값만 입력가능      */
				addMovie.setId(scanner.nextInt());
				scanner.nextLine();
				
					for (int i = 0; i < theater.getMovieList().size(); i++) {
						if(theater.getMovieList().get(i).getId()==addMovie.getId()){
							System.out.println("중복된 ID가 있습니다. 다시 입력해주세요.");
							id_flag=true;
							break;
						}
					}
					
					if(id_flag == false)
						break;
				}
				
				System.out.println("제목: ");
				addMovie.setTitle(scanner.nextLine());
				System.out.println("감독: ");
				addMovie.setDirector(scanner.nextLine());
				
				while (true) {
					System.out.println("상영시간(분): "); /*  영화의 상영시간는  1이상 int값만 입력가능      */
					addMovie.setTime(scanner.nextInt());

					if (addMovie.getTime() < 1) {
						System.out.println("상영시간은 1이상의 정수값을 가집니다. 다시입력해주세요. ");
					} else
						break;
				}
				
				while(true){
				System.out.println("제한연령: "); /*  영화의 제한연령는 0이상 int값만 입력가능      */
				addMovie.setAge(scanner.nextInt());
				
				if (addMovie.getAge() < 0) {
					System.out.println("제한연령은 0이상의 정수값을 가집니다. 다시입력해주세요. ");
				} else
					break;
				}
				while(true){
				System.out.println("표 값: ");  /*  영화의 표값은  0이상 int값만 입력가능      */
				addMovie.setPrice(scanner.nextInt());
				
				if (addMovie.getPrice() < 0) {
					System.out.println("표 값은 0이상의 정수값을 가집니다. 다시입력해주세요. ");
				} else
					break;
				}
				while(true){
				System.out.println("좌석 행(row): "); /*  영화의 행은 1~26사이의 int값만 입력가능      */
				addMovie.setRow(scanner.nextInt());
				
				if(addMovie.getRow() < 1 || addMovie.getRow() > 26)
					System.out.println("행(row)은 1~26의 정수만 입력가능합니다.");
				else
					break;
				}

				while (true) {
					System.out.println("좌석 열(col): "); /*  영화의 열은 1이상 int값만 입력가능      */
					addMovie.setCol(scanner.nextInt());
					addMovie.setCol(addMovie.getCol() + 1);

					if (addMovie.getCol() < 2)
						System.out.println("열(col)은 1이상의 정수만 입력가능합니다.");
					else
						break;
				}

				break;
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("입력값이 잘못되었습니다. 처음부터 다시입력해주세요. "); //다시 ID부터 입력하도록 함.
				System.out.println("ID/상영시간/제한연령/표 값/좌석 행&열은 정수(int)의 값을 가집니다. ");
			}
		} //END ALL While(true) loop

		char[][] temp = new char[addMovie.getRow()][addMovie.getCol()];
		
		for (int i = 0; i < addMovie.getRow(); i++) {

			temp[i][0] = (char) ('A' + i);

			for (int j = 1; j < addMovie.getCol(); j++) {
				temp[i][j] = 'O';
			}
		}

		addMovie.setSeat(temp);

		theater.getMovieList().add(addMovie);
	}

	/* 영화 삭제 Method */
	public void DeleteMovie(Theater theater, ArrayList<User> userList) {

		int Delete_id = 0;
		int Delete_key = -1;

		ArrayList<Audience> AudienceList = new ArrayList<Audience>();
		
		for(int i=0;i<userList.size();i++){
			if(userList.get(i) instanceof Audience)
			AudienceList.add((Audience)userList.get(i));
		}
		
		while (true) {
			try {
				/*  영화의 ID는 int값만 입력가능      */
				System.out.println("*** 영화 삭제 ***");
				System.out.println("삭제할 영화 ID: ");
				Delete_id = scanner.nextInt();
				break;
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("ID는 정수(int)의 값만 입력이 가능합니다.");
			}
		}
		
		for (int i = 0; i < theater.getMovieList().size(); i++) {
			if (Delete_id == theater.getMovieList().get(i).getId()) {
				Delete_key = i;
				break;
			}
		}

		if (Delete_key == -1) {
			System.out.println("입력한 ID를 가지는 영화가 존재하지 않습니다.");
		} else if (Delete_key > -1) {
			
			/* 손님(꽌객)들의 예약영화목록에서 삭제하는 옇화가 예매되어있다면 삭제하지 못하도록 함.
			 * 영화를 예약한 관객이 없을때만 삭제 (Delete_flag==true일때만 삭제)*/
			boolean Delete_flag = true;
			
			for (int i = 0; i < AudienceList.size(); i++) {
				for (int j = 0; j < AudienceList.get(i).getReserveMovieList().size(); j++) {
					if (Delete_id == AudienceList.get(i).getReserveMovieList().get(j).getId()) {
						Delete_flag = false;
						break;
						}
					}
				if(Delete_flag == false)
					break;
			}
			
			if(Delete_flag == true)
			theater.getMovieList().remove(Delete_key);
			else
				System.out.println("예약한 관객이 있어 삭제가 불가능합니다");
		}
	}
}
