/*
 *  Class    : Book.java 
 *  function : 책의 id, 제목, 작가, 상태에 대한 정보를 가지고 있음.
 *             get, set 메소드를 통해  인스턴스 변수들의 정보에 접근가능.
 *  ID       : 201420907
 *  Name     : 안우일
 */

package homework1;

public class Book {

	private static int numID = 0; //numID의 숫자로 ID를 생성
	private int id = 0;
	private int status;
	private String title;
	private String author;

	public Book() {
		
		numID = numID + 1;

		this.status = 1;
		this.id = numID;
	}

	public Book(String title, String author) {

		numID = numID + 1;

		this.id = numID;
		this.title = title;
		this.author = author;
		this.status = 1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public static int getNumID() {
		return numID;
	}

	public static void setNumID(int numID) {
		Book.numID = numID;
	}

}
