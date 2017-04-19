import java.util.ArrayList;
import java.util.HashSet;

public class Unit {
	private ArrayList<ArmaEquip> items = new ArrayList<>();
	private HashSet<Unit> children = new HashSet<>();
	private HashSet<Unit> parents = new HashSet<>();
	private String name;
	private Faction faction;
	private int x;
	private int y;
	
	public ArrayList<ArmaEquip> getAllItems() {
		return items;
	}

	public ArmaEquip getItem(int i) {
		return items.get(i);
	}
	
	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction currentFaction) {
		this.faction = currentFaction;
	}
	
	public HashSet<Unit> getChildren() {
		return children;
	}
	
	public void addParent(Unit parent) {
		parents.add(parent);
	}
	
	public HashSet<Unit> getParents() {
		return parents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void updateChildren() {
		System.out.println("Updating children");
		System.out.println(getFaction().getName() + "\\" + getName());
		children.clear();
		for (ArmaEquip thing: items) {
			if (thing.addableType==AddableType.FUNCTION) {
				System.out.println(thing.classnames);
				for (String s: thing.classnames.getAsList()) {
					System.out.println("Child found, name: " + s);
					System.out.println("Child found: " + getFaction().getUnitByFunctionName(s));
					children.add(getFaction().getUnitByFunctionName(s));
				}
			} else if (thing.addableType==AddableType.KIT) {
				System.out.println(thing.classnames);
				for (String s: thing.classnames.formatAsFunctionList(this.getFaction())) {
					System.out.println("Child found, name: " + s);
					System.out.println("Child found: " + getFaction().getUnitByFunctionName(s));
					children.add(getFaction().getUnitByFunctionName(s));
				}
			}
		}
		System.out.println("Child count: " + children.size());
		updateParentInfo();
	}
	
	public void updateParentInfo() {
		for (Unit child: this.children) {
			child.addParent(this);
		}
	}

	public void removeItem(int i) {
		items.remove(i);
	}
	
	public void addItem(ArmaEquip item) {
		// TODO: combine similar items
		AddableType[] combinableTypes = new AddableType[] {AddableType.HEADGEAR,AddableType.UNIFORM, AddableType.VEST};
		//if (combinableTypes.item.addableType)
		this.items.add(item);
	}

	public void addItems(ArrayList<ArmaEquip> items) {
		this.addAll(items);
	}

	private void addAll(ArrayList<ArmaEquip> items) {
		for (ArmaEquip item: items) {
			this.addItem(item);
		}
	}

	public void setGraphPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
