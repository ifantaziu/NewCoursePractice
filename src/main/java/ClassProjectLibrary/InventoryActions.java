package ClassProjectLibrary;

public interface InventoryActions {
    public void addItems(LibraryItem libraryItem);
    public void listItems();
    public LibraryItem findByTitle(String title);
    public LibraryItem findByAuthor(String title);
}
