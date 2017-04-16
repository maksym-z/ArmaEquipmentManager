
public enum Destination {
	ANY, UNIFORM, VEST, BACKPACK, LINK;
	
	// PRIMARY, LINK, HANDGUN, SECONDARY
	
	public static String[] stringValues() {
		String[] result = new String[values().length];
		int i = 0;
		for (Destination item: values()) {
			result[i] = item.toString();
			i++;
		}
		return result;
	}
}
