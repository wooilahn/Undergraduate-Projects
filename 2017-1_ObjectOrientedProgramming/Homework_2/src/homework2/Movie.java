package homework2;

public class Movie { //SubClass : ReservedMovie

	private String title; // 제목
	private String director; // 감독
	private char[][] seat; // 좌석표
	private int id; // id
	private int time; // 상영시간
	private int age; // 제한연령
	private int price; // 표 값
	private int row; // 좌석 행
	private int col; // 좌석 열

	public Movie() { //생성자

	}

	public Movie(Movie movie) { //생성자
		this.setTitle(movie.getTitle());
		this.setDirector(movie.getDirector());
		this.setId(movie.getId());
		this.setTime(movie.getTime());
		this.setAge(movie.getAge());
		this.setPrice(movie.getPrice());
		this.setRow(movie.getRow());
		this.setCol(movie.getRow());
		this.setSeat(movie.getSeat());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public char[][] getSeat() {
		return seat;
	}

	public void setSeat(char[][] seat) {
		this.seat = seat;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
