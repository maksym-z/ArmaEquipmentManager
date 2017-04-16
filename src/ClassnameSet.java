import java.util.ArrayList;
import java.util.List;

public class ClassnameSet {
	private ArrayList<String> classnames = new ArrayList<>();
	

	public void setClassnames(String input) {
		System.out.println("Setting classnames from " + input);
		String[] names = input.split(",");
		classnames.clear();
		for (String name: names) {
			classnames.add(name.trim());
		}
	}
	
	private String generateList(String itemPrefix, String itemPostfix, String listPrefix, String listPostfix) {
		String output;
		if (classnames.size() < 2) {
			output = "";
		} else {
			output = listPrefix;
		}
		int i;
		for (i=0; i<classnames.size()-1; i++) {
			output += itemPrefix + classnames.get(i) + itemPostfix + ",";
		}
		if (i<classnames.size()) {
			output += itemPrefix + classnames.get(i) + itemPostfix;
		}
		if (classnames.size() > 1) {
			output += listPostfix;
		}
		return output;
	}
	
	public String toString() {
		return generateList("","","","");
	}
	
	public String getSQF() {
		return generateList("'","'","selectRandom [","]");
	}
	
	public String formatAsFunctions(Unit unit) {
		return generateList(unit.getFaction().getName() + "_" + unit.getName() + "_","","selectRandom [","]");
	}

	public List<String> formatAsFunctionList(Unit unit) {
		ArrayList<String> newList = new ArrayList<>();
		for (String classname: classnames) {
			newList.add(EquipmentWriter.BN_FUNCTION_PREFIX + "_" + unit.getFaction().getName() + "_" + unit.getName() + "_" + classname);
		}
		return newList;
	}
	
	public List<String> formatAsFunctionList(Faction faction) {
		ArrayList<String> newList = new ArrayList<>();
		for (String classname: classnames) {
			newList.add(EquipmentWriter.BN_FUNCTION_PREFIX + "_" + faction.getName() + "_" + classname);
		}
		return newList;
	}

	public List<String> getAsList() {
		return classnames;
	}

	public String formatAsFunctions(Faction faction) {
		return generateList(EquipmentWriter.BN_FUNCTION_PREFIX + "_" +faction.getName() + "_","","selectRandom [","]");
	}

	public int size() {
		return classnames.size();
	}
}
