import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArmaEquip {
	public AddableType addableType = AddableType.ITEM;
	public Destination destination = Destination.ANY;
	Faction faction;
	ClassnameSet classnames = new ClassnameSet();
	int qty = 1;
	
	public static EnumMap<Destination, String> destinationMap = new EnumMap<Destination, String>(Destination.class) {
		{
			put(Destination.ANY,"addItem");
			put(Destination.BACKPACK,"addItemToBackpack");
			put(Destination.UNIFORM,"addItemToUniform");
			put(Destination.VEST,"addItemToVest");
			put(Destination.LINK,"linkItem");
		};
	};
	public static EnumMap<AddableType, String> addableMap = new EnumMap<AddableType, String>(AddableType.class) {
		{
			put(AddableType.ITEM,"addItem");
			put(AddableType.BACKPACK,"addBackpack");
			put(AddableType.MAGAZINE,"addMagazine");
			put(AddableType.HEADGEAR,"addHeadgear");
			put(AddableType.GOGGLES,"addGoggles");
			put(AddableType.VEST,"addVest");
			put(AddableType.UNIFORM,"forceAddUniform");
			put(AddableType.WEAPON,"addWeapon");
			put(AddableType.PRIMARYWEAPONITEM,"addPrimaryWeaponItem");
			put(AddableType.SECONDARYWEAPONITEM,"addSecondaryWeaponItem");
			put(AddableType.HANDGUNITEM,"addHandgunItem");
			put(AddableType.KIT,"kit");
//			put(AddableType.SCRIPT,"script");
			put(AddableType.FUNCTION,"function");
		};
	};
	
	public static HashMap<String, AddableType> addableMapInversed = new HashMap<String, AddableType>() {
		{
			for (AddableType key: addableMap.keySet()) {
				put(key.toString().toLowerCase(),key);
				System.out.println(" map fill " + addableMap.get(key).toLowerCase() + " " +key);
			};
		};
	};
	
	public static HashMap<String, Destination> destinationMapInversed = new HashMap<String, Destination>() {
		{
			for (Destination key: destinationMap.keySet()) {
				put(key.toString().toLowerCase(),key);
			};
		};
	};
	
	public String encode() {
		String result = "_unit ";
		if (this.addableType == AddableType.ITEM) {
			result += "addItem" + destinationMap.get(this.destination) + " ";
		} else {
			result += addableMap.get(this.addableType) + destinationMap.get(this.destination);
		}
		return result;
	}
}
