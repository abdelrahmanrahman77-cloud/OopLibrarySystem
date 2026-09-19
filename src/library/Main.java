package library;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        boolean running = true;

        do {

            System.out.println("\n LIBRARY SYSTEM ");
            System.out.println("1. Add Item");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");
            System.out.println("5. List Catalog");
            System.out.println("6. Report");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\n ADD ITEM ");
                    System.out.println("1. Book");
                    System.out.println("2. Magazine");
                    System.out.println("3. DVD");

                    System.out.print("Choose item type: ");
                    int itemType = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    if (itemType == 1) {

                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();

                        System.out.print("Enter pages: ");
                        int pages = scanner.nextInt();
                        scanner.nextLine();

                        Book book = new Book(
                                title,
                                author,
                                pages
                        );

                        library.addItem(book);

                        System.out.println(
                                "Book added successfully."
                        );

                    } else if (itemType == 2) {

                        System.out.print("Enter issue number: ");
                        int issueNumber = scanner.nextInt();
                        scanner.nextLine();

                        Magazine magazine =
                                new Magazine(
                                        title,
                                        issueNumber
                                );

                        library.addItem(magazine);

                        System.out.println(
                                "Magazine added successfully."
                        );

                    } else if (itemType == 3) {

                        System.out.print("Enter runtime in minutes: ");

                        int runtimeMinutes = scanner.nextInt();

                        scanner.nextLine();

                        DVD dvd = new DVD(title, runtimeMinutes);

                        library.addItem(dvd);

                        System.out.println("DVD added successfully.");

                    } else {

                        System.out.println("Invalid item type.");
                    }

                    break;



                case 2:

                    System.out.println("\n ADD MEMBER ");

                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();

                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter maximum allowed items: ");

                    int maxAllowed = scanner.nextInt();
                    scanner.nextLine();

                    Member member = new Member(memberId, name, maxAllowed);

                    library.addMember(member);

                    System.out.println("Member added successfully.");

                    break;

                case 3:

                    System.out.println("\n BORROW ITEM ");

                    System.out.print("Enter member ID: ");
                    String borrowMemberId =
                            scanner.nextLine();

                    System.out.print("Enter item ID: ");
                    String borrowItemId =
                            scanner.nextLine();

                    try {

                        library.borrowItem(
                                borrowMemberId,
                                borrowItemId
                        );

                    } catch (LibraryException e) {

                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 4:

                    System.out.println("\n RETURN ITEM ");

                    System.out.println("Return Item selected.");

                    break;
                case 5:

                    System.out.println("\n LIBRARY CATALOG ");

                    library.listCatalog();

                    break;

                case 6:

                    library.printReport();

                    break;

                case 7:

                    running = false;

                    System.out.println("Goodbye!");

                    break;

                default:

                    System.out.println("Invalid option.");
            }

        } while (running);

        scanner.close();
    }
}