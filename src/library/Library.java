package library;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {

    private Map<String, LibraryItem> catalog;
    private Map<String, Member> members;
    private Set<String> borrowedIds;

    public Library() {

        catalog = new HashMap<>();
        members = new HashMap<>();
        borrowedIds = new HashSet<>();
    }

    public void addItem(LibraryItem item) {
        catalog.put(item.getId(), item);
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public void borrowItem(String memberId, String itemId)
            throws LibraryException {

        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (member == null) {
            throw new LibraryException(
                    "Member " + memberId + " does not exist."
            );
        }

        if (item == null) {
            throw new LibraryException(
                    "Item " + itemId + " does not exist."
            );
        }

        if (item.isBorrowed()) {
            throw new LibraryException(
                    "Item " + itemId + " is already out."
            );
        }

        if (!member.canBorrowMore()) {
            throw new LibraryException(
                    "Member has reached the borrowing limit."
            );
        }

        item.markBorrowed();
        member.addBorrowedItem(item);
        borrowedIds.add(itemId);

        System.out.println(
                "Borrowed " + itemId + " to " + memberId + "."
        );
    }

    public void returnItem(String memberId, String itemId)
            throws LibraryException {

        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        if (member == null) {
            throw new LibraryException(
                    "Member " + memberId + " does not exist."
            );
        }

        if (item == null) {
            throw new LibraryException(
                    "Item " + itemId + " does not exist."
            );
        }

        if (!member.getBorrowedItems().contains(item)) {
            throw new LibraryException(
                    "Member did not borrow item " + itemId + "."
            );
        }

        item.markReturned();
        member.removeBorrowedItem(item);
        borrowedIds.remove(itemId);

        System.out.println(
                "Returned " + itemId + " from " + memberId + "."
        );
    }

    public void listCatalog() {

        for (LibraryItem item : catalog.values()) {
            item.displayInfo();
        }
    }

    public void printReport() {

        Map<String, Integer> itemsByType = new HashMap<>();

        for (LibraryItem item : catalog.values()) {

            String type = item.getType();

            itemsByType.put(
                    type,
                    itemsByType.getOrDefault(type, 0) + 1
            );
        }

        System.out.println(" REPORT ");

        System.out.println(
                "Total items : " + catalog.size()
        );

        System.out.println(
                "Currently out : " + borrowedIds.size()
        );

        System.out.println(
                "Borrowed ids : " + borrowedIds
        );

        System.out.println(
                "Items by type : " + itemsByType
        );

        System.out.println(
                "Total created : " +
                        LibraryItem.getTotalItemsCreated()
        );

        System.out.println("----------------------------");
    }
}