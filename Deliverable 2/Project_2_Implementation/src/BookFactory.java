
public class BookFactory extends LibraryItemFactory {

	@Override
	public Item createItem(String itemType) {
		if (itemType.equalsIgnoreCase("Book")) {
            return new Book(0, itemType, itemType, false);
        } else {
        	return null;
        }
	}

}
