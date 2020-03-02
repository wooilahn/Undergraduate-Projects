/*
 *  Class    : Library.java 
 *  function : 도서관의 책리스트에 대한 정보를 담고 있음.
 *             도서관의 기능에 해당하는 책 [ 추가, 삭제, 변경,] 에 대한 기능을 수행할 수 있음.
 *                            [ 대여, 반납, 열람   ]
 *  ID       : 201420907
 *  Name     : 안우일
 */

package homework1;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {

	private ArrayList<Book> booklist = new ArrayList<Book>();
	Scanner scanner = new Scanner(System.in);

	public Library(ArrayList<Book> booklist) {
		this.booklist = booklist;
	}

	public int getKey(Book book) {

		int key = -1;

		for (int i = 0; i < booklist.size(); i++) {
			if (booklist.get(i).getTitle().equals(book.getTitle())
					&& booklist.get(i).getAuthor().equals(book.getAuthor())) {
				key = i;
				break;
			}
		}
		return key;
	}

	public int getUserKey(Book book, User user) {

		int Userkey = -1;

		for (int i = 0; i < user.getBorrowBookList().size(); i++) {
			if (book.getTitle().equals(user.getBorrowBookList().get(i).getTitle())
					&& book.getAuthor().equals(user.getBorrowBookList().get(i).getAuthor())) {
				Userkey = i;
				break;
			}
		}

		return Userkey;
	}

	public void appendBook(Book book) {

		int key = -1;

		System.out.println("<Append book>");
		System.out.print("Book title: ");
		book.setTitle(scanner.nextLine());
		System.out.print("Author: ");
		book.setAuthor(scanner.nextLine());

		key = getKey(book);

		if (key == -1) {
			System.out.println("Append book complete");
			booklist.add(book);
		} else {
			System.out.println("Already the Book exists!");
		}
	}

	public void deleteBook(Book book) {

		int key = -1;

		System.out.println("<Delete book>");
		System.out.print("Book title: ");
		book.setTitle(scanner.nextLine());
		System.out.print("Author: ");
		book.setAuthor(scanner.nextLine());

		key = getKey(book);

		if (key == -1)
			System.out.println("[No such book!]");
		else if (key > -1) {
			if (booklist.get(key).getStatus() == 0)
				System.out.println("[Cannot delete the book!]");
			else if (booklist.get(key).getStatus() == 1) {
				System.out.println("Delete book complete");
				booklist.get(key).setStatus(0);
				booklist.remove(key);

				for (int i = 0; i < booklist.size(); i++)
					booklist.get(i).setId(i + 1);
			}
		}
		Book.setNumID(booklist.size());
	}

	public void modifyBook(Book book) {

		int key = -1;

		System.out.println("<Modify book>");
		System.out.print("Book title: ");
		book.setTitle(scanner.nextLine());
		System.out.print("Author: ");
		book.setAuthor(scanner.nextLine());

		key = getKey(book);

		if (key == -1) {
			System.out.println("[Wrong book info!]");
		}

		else if (key > -1) {

			if (booklist.get(key).getStatus() == 0) {
				System.out.println("[Cannot modify book info!]");

			} else if (booklist.get(key).getStatus() == 1) {

				int modKey = -1;

				System.out.print("New book title: ");
				String NewTitle = scanner.nextLine();
				System.out.print("New author: ");
				String NewAuthor = scanner.nextLine();

				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getTitle().equals(NewTitle) && booklist.get(i).getAuthor().equals(NewAuthor)) {
						modKey = i;
						break;
					}
				}

				if(modKey==-1){
				System.out.println("Modify book complete");

				booklist.get(key).setTitle(NewTitle);
				booklist.get(key).setAuthor(NewAuthor);
				}else if(modKey>-1){
					System.out.println("[The Book already Exists!]");
				}
			}
		}
		Book.setNumID(booklist.size());
	}

	public void borrowBook(Book book, User user) {

		int key = -1;

		System.out.println("<Borrow book>");
		System.out.print("Book title: ");
		book.setTitle(scanner.nextLine());
		System.out.print("Author: ");
		book.setAuthor(scanner.nextLine());

		key = getKey(book);

		if (key == -1) {
			System.out.println("[No such book!]");
		} else if (key > -1) {

			if (booklist.get(key).getStatus() == 0) {
				System.out.println("[Cannot borrow the book!]");
			} else if (booklist.get(key).getStatus() == 1) {
				System.out.println("Borrow book complete");
				booklist.get(key).setStatus(0);
				user.setBorrowBookList(booklist.get(key));
			}
		}
		Book.setNumID(booklist.size());
	}

	public void returnBook(Book book, User user) {

		int key = -1;

		System.out.println("<Return book>");
		System.out.print("Book title: ");
		book.setTitle(scanner.nextLine());
		System.out.print("Author: ");
		book.setAuthor(scanner.nextLine());

		key = getKey(book);

		if (key == -1) {
			System.out.println("[Wrong book info!]");
		} else if (key > -1) {

			int UserKey = getUserKey(book, user);

			if (booklist.get(key).getStatus() == 1 && UserKey == -1) {
				System.out.println("[Fail to return book!(you don't borrow this book)]");
			} else if (booklist.get(key).getStatus() == 0 && UserKey == -1) {
				System.out.println("[Fail to return book!(Someone borrow this book)]");
			} else if (booklist.get(key).getStatus() == 0 && UserKey > -1) {
				System.out.println("Return book complete");
				booklist.get(key).setStatus(1);
				user.delBorrowBookList(booklist.get(key));
			}
		}
		Book.setNumID(booklist.size());
	}

	public ArrayList<Book> browseBook() {

		System.out.println("<Show all books>");
		if (booklist.size() == 0)
			System.out.println("There is no Book!");
		else {
			for (int i = 0; i < booklist.size(); i++) {
				if (booklist.get(i).getStatus() == 0) {
					System.out.println(booklist.get(i).getId() + " 'Borrow unavailable' Title: " + "\""
							+ booklist.get(i).getTitle() + "\" " + "Author: " + "\"" + booklist.get(i).getAuthor()
							+ "\" ");
				} else if (booklist.get(i).getStatus() == 1) {
					System.out.println(
							booklist.get(i).getId() + " 'Borrow available' Title: " + "\"" + booklist.get(i).getTitle()
									+ "\" " + "Author: " + "\"" + booklist.get(i).getAuthor() + "\" ");
				}
			}
		}
		return booklist;
	}
}