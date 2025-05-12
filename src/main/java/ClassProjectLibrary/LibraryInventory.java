package ClassProjectLibrary;

public class LibraryInventory implements InventoryActions {
    LibraryItem[] libraryItems = new LibraryItem[100];
    int counter = 0;


    @Override
    public void addItems(LibraryItem libraryItem) {
        libraryItems[counter] = libraryItem;
        counter++;
    }

    @Override
    public void listItems() {
        for (int i = 0; i < counter; i++) {
            System.out.println(libraryItems[i].toString());
        }
    }

    @Override
    public LibraryItem findByTitle(String title) {
        LibraryItem foundItem= null;
        for (int i = 0; i < counter; i++) {
            LibraryItem libraryItem = libraryItems[i];
            if (libraryItem.getTitle().equals(title));
            foundItem= libraryItem;
            break;
        }
        return foundItem;
    }

    @Override
    public LibraryItem findByAuthor(String title) {
        for (int i = 0; i < counter; i++) {
            LibraryItem libraryItem = libraryItems[i];
        }
        return null;
    }
}
