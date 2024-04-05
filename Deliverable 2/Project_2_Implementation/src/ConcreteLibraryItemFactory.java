
public class ConcreteLibraryItemFactory extends LibraryItemFactory {
    @Override
    public Item createItem(String itemType) {
        if (itemType.equalsIgnoreCase("Book")) {
            return new Book(0, itemType, itemType, false);
        } else if (itemType.equalsIgnoreCase("CD")) {
            return new CD(0, itemType, itemType, false);
        } else if (itemType.equalsIgnoreCase("Magazine")) {
            return new Magazine(0, itemType, itemType, false);
        } else {
            return null;
        }
    }
}
