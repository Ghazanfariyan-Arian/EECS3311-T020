
public class CDFactory extends LibraryItemFactory {
	@Override
	public Item createItem(String itemType) {
		if (itemType.equalsIgnoreCase("CD")) {
            return new CD(0, itemType, itemType, false);
        } else {
        	return null;
        }
	}
}
