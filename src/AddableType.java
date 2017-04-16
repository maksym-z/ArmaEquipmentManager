
public enum AddableType {
	ITEM, BACKPACK, MAGAZINE, HEADGEAR, GOGGLES, VEST, UNIFORM, WEAPON, PRIMARYWEAPONITEM, SECONDARYWEAPONITEM, HANDGUNITEM, KIT, FUNCTION;
	
	public static String[] stringValues() {
		String[] result = new String[values().length];
		int i = 0;
		for (AddableType item: values()) {
			result[i] = item.toString();
			i++;
		}
		return result;
	}
}
