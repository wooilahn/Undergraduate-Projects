package homework2;

public class User { //SubClass : Admin, Audience

	private String id; // id
	private String password; // 패스워드
	private int admin; // admin = 0 or 1
	private int age; // 일반사용자의 나이

	public User() { //생성자

	}

	public User(String id, String password, int admin) { // admin용 생성자
		this.setId(id);
		this.setPassword(password);
		
		this.setAdmin(admin);
	}

	public User(String id, String password, int admin, int age) { // Audience용 생성자
		this.setId(id);
		this.setPassword(password);
		this.setAdmin(admin);
		this.setAge(age);
	}
	
	/* 영화 리스트보기(AdminMenu) && 영화 정보 상세보기(AudienceMenu) Method */
	public void BrowseMovie(Theater theater) {
		System.out.println("*** 영화 목록 ***");
		for (int i = 0; i < theater.getMovieList().size(); i++) {
			System.out.println("ID: " + theater.getMovieList().get(i).getId());
			System.out.println("제목: " + theater.getMovieList().get(i).getTitle());
			System.out.println("감독: " + theater.getMovieList().get(i).getDirector());
			System.out.println("상영시간: " + theater.getMovieList().get(i).getTime());
			System.out.println("제한연령: " + theater.getMovieList().get(i).getAge());
			System.out.println("표 값: " + theater.getMovieList().get(i).getPrice());
			System.out.println("-----------------------------------------");
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
