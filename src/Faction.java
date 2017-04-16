import java.util.HashMap;

public class Faction {
	private String name;
	
	private HashMap<String, Unit> units;
	
	public Faction(String name) {
		this.name = name;
		this.units = new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, Unit> getUnits() {
		return units;
	}
	public void setUnits(HashMap<String, Unit> units) {
		this.units = units;
	}
	
	public void updateUnits() {
		for (String unitName: units.keySet()) {
			units.get(unitName).setFaction(this);
		}
	}
	
	public void addUnit(Unit unit) {
		unit.setFaction(this);
		unit.updateChildren();
		units.put(unit.getName(), unit);
	}
	
	public Unit getUnit(String name) {
		if (!units.containsKey(name) || units.get(name) == null) {
			throw (new IllegalArgumentException("Faction does not contain unit '" + name + "'"));
		}
		return units.get(name);
	}
	
	public Unit getUnitByFunctionName(String name) {
		System.out.println("getUnitByFunctionName");
		System.out.println(EquipmentWriter.BN_FUNCTION_PREFIX.length()+","+this.name.length());
		System.out.println(name.substring(EquipmentWriter.BN_FUNCTION_PREFIX.length()+this.name.length()+2));
		return getUnit(name.substring(EquipmentWriter.BN_FUNCTION_PREFIX.length()+this.name.length()+2));
	}
}
