/*
 *  Class    : Menu.java 
 *  function : 사용자에게 interface를 제공함.
 *             1.login을 통해 Admin Menu와 User Menu로 접근가능.
 *             2.Add User를 통해 새롭게 계정 생성 가능
 *             3.Exit를 통해 프로그램 종료 가능.
 *  ID       : 201420907
 *  Name     : 안우일
 */

package homework1;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	static ArrayList<User> userList = new ArrayList<User>();
	static ArrayList<Book> bookList = new ArrayList<Book>();
	static Library library = new Library(bookList);
	static Scanner scanner = new Scanner(System.in);

	public static void appendUser(User user) {
		System.out.println();
		System.out.println("<Create a new user>");

		while (true) {

			System.out.print("user id: ");
			user.setId(scanner.nextInt());
			scanner.nextLine();

			int id_check = 0;

			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getId() == user.getId()) {
					id_check++;
					break;
				}
			}

			if (id_check == 0) {
				break;
			} else if (id_check > 0) {
				System.out.println("Already the ID exists. Please ReEnter ID");
			}
		}

		System.out.print("password: ");
		user.setPassword(scanner.nextLine());

		System.out.print("Is the user admin? (yes: 1, no: 0): ");
		user.setAdmin(scanner.nextInt());
		scanner.nextLine();

	}

	public static void printAdminMenu() {
		System.out.println();
		System.out.println("<Admin Menu>");
		System.out.println("1.Append book");
		System.out.println("2.Delete book");
		System.out.println("3.Modify book info");
		System.out.println("4.Show all books");
		System.out.println("5.Log out");
		System.out.print(">> ");
	}

	public static void printUserMenu() {
		System.out.println();
		System.out.println("<User Menu>");
		System.out.println("1.Borrow book");
		System.out.println("2.Return book");
		System.out.println("3.Show all books");
		System.out.println("4.Log out");
		System.out.print(">> ");
	}

	public static void main(String[] args) {

		int Menu_opt = 0;
		int Admin_opt = 0;
		int User_opt = 0;

		while (true) {
			System.out.println();
			System.out.println("-- My Library --");
			System.out.println("1. Log in");
			System.out.println("2. Add user");
			System.out.println("3. Exit");
			System.out.print(">> ");

			Menu_opt = scanner.nextInt();
			scanner.nextLine();

			if (Menu_opt == 1) { /*// 1.login //*/
				int id;
				String password;
				int key = -1;

				System.out.println();
				System.out.println("<Log in>");
				System.out.print("user id: ");
				id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("password: ");
				password = scanner.nextLine();

				// search index
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getId() == id && userList.get(i).getPassword().equals(password)) {
						key = i;
						break;
					}
				}

				if (key == -1)
					System.out.println("[Wrong info is entered!]");

				if (key > -1) {
					if (userList.get(key).getAdmin() == 1) { /* admin 으로 접속 */

						while (true) {
							printAdminMenu();
							Admin_opt = scanner.nextInt();
							scanner.nextLine();

							if (Admin_opt == 5) { // 5.Log out
								System.out.println("Logout");
								break;
							}
							switch (Admin_opt) {
							case 1: // 1.Append Book

								Book AddBook = new Book("AddBook", "AddAuthor");
								library.appendBook(AddBook);
								break;

							case 2: // 2.Delete Book

								Book DelBook = new Book("DelBook", "DelAuthor");
								library.deleteBook(DelBook);
								break;

							case 3: // 3.Modify Book info

								Book ModBook = new Book("ModBook", "ModAuthor");
								library.modifyBook(ModBook);
								break;

							case 4: // 4.Show All Books

								library.browseBook();
								break;

							default: // Not 1~5
								System.out.println("[Wrong number is entered!]");
								break;
							}
						}
					} else if (userList.get(key)
							.getAdmin() == 0) { /* User 으로 접속 */

						while (true) {
							printUserMenu();
							User_opt = scanner.nextInt();

							if (User_opt == 4) { // 4.Logout
								System.out.println("Logout");
								break;
							}

							User user = new User(0, "password", 0);
							user = userList.get(key);

							switch (User_opt) {

							case 1: // 1.Borrow Book

								Book BorBook = new Book("BorBook", "BorAuthor");
								library.borrowBook(BorBook, user);
								break;

							case 2: // 2.Return Book

								Book RtnBook = new Book("RtnBook", "RtnAuthor");
								library.returnBook(RtnBook, user);
								break;

							case 3: // 3.Show all Book
								library.browseBook();
								break;

							default: // Not 1~5
								System.out.println("[Wrong number is entered!]");
								break;
							}
						}
					}
				}
			}

			else if (Menu_opt == 2) { /*// 2.Add  User //*/
				User temp = new User(0, "password", 0);
				appendUser(temp);
				userList.add(temp);
			}

			else if (Menu_opt == 3) { /*// 3.Exit //*/
				System.out.println("[Program Exit]");
				return;
			} else
				System.out.println("[Wrong number is entered!]");

		}
	}
}