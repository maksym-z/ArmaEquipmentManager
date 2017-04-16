import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Verifier {
	private StringParser parser;
	private boolean hasUniform; 
	private boolean hasHeadgear;
	private boolean hasVest;
	private boolean hasPrimaryWeapon;
	private boolean hasPrimaryAmmo;
	private String primaryWeapon;
	private ArrayList<ArmaEquip> equipment = new ArrayList<>();
	
	public boolean hasUniform() {
		return hasUniform;
	}
	public boolean hasHeadgear() {
		return hasHeadgear;
	}
	public boolean hasVest() {
		return hasVest;
	}
	public boolean hasPrimaryWeapon() {
		return hasPrimaryWeapon;
	}
	public boolean hasPrimaryAmmo() {
		return hasPrimaryAmmo;
	}
	
	public Verifier(String path) {
/*		
		try {
			this.equipment.addAll(StringParser.readFile(path));
			for(ArmaEquip thing: this.equipment.stream().filter(x -> (x.addableType==AddableType.KIT || x.addableType==AddableType.SCRIPT)).collect(Collectors.toList())) {
//				this.equipment.addAll(StringParser.readFile(thing.classnames));
			}
			
			for (ArmaEquip thing: this.equipment) {
				switch (thing.addableType) {
				case VEST:
					this.hasVest = true;
					break;
				case HEADGEAR:
					this.hasHeadgear = true;
					break;
				case UNIFORM:
					this.hasUniform = true;
					break;
				case WEAPON:
//					this.primaryWeapon = thing.classnames;
					this.hasPrimaryWeapon = true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
