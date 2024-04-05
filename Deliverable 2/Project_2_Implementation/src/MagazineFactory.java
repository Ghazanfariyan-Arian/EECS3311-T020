
public class MagazineFactory extends LibraryItemFactory {
	@Override
	public Item createItem(String itemType) {
		if (itemType.equalsIgnoreCase("Magazine")) {
            return new Magazine(0, itemType, itemType, false);
        } else {
        	return null;
        }
	}

}
