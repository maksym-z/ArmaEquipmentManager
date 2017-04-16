import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class PersonalEquipmentModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6296481131578398313L;
	private String[] columnNames = new String[] { "№", "Тип", "Класснейм", "Кол-во", "Куда" };
	private boolean gotChanges = false;

	private ArrayList<ArmaEquip> items = new ArrayList<>();
	private UnitEquipmentEditor host;
	private HashSet<PersonalEquipmentModel> children = new HashSet<>();
	private Faction currentFaction; 
	
	public PersonalEquipmentModel() {
		super();
	}
	
	public PersonalEquipmentModel(String filename) {
		super();
		reloadFromFile(filename);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.items.size();
	}

	public ArrayList<ArmaEquip> getAllItems() {
		return items;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case 0:
			return arg0 + 1;
		case 1:
			if (this.items.get(arg0).addableType != null)
				return this.items.get(arg0).addableType.toString();
			else
				return "-";
		case 2:
			return this.items.get(arg0).classname;
		case 3:
			return this.items.get(arg0).qty;
		case 4:
			if (this.items.get(arg0).destination != null && this.items.get(arg0).destination != Destination.ANY)
				return this.items.get(arg0).destination.toString();
			else
				return "-";
		default:
			return null;
		}

	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			AddableType newAddable = ArmaEquip.addableMapInversed.get(value.toString().toLowerCase());
			if (!newAddable.toString().equalsIgnoreCase(this.getValueAt(rowIndex, columnIndex).toString())) {
				this.items.get(rowIndex).addableType = newAddable;
				this.setGotChangesAndUpdate(true);
			}
			break;
		case 2:
			if (!value.toString().equalsIgnoreCase(this.getValueAt(rowIndex, columnIndex).toString())) {
				this.items.get(rowIndex).classname = value.toString();
				this.setGotChangesAndUpdate(true);
			}
			break;
		case 3:
			try {
				if (Integer.parseInt(value.toString()) != this.items.get(rowIndex).qty) {
					this.items.get(rowIndex).qty = Integer.parseInt(value.toString());
					this.setGotChangesAndUpdate(true);
				}
			} catch (NumberFormatException e) {

			}
			break;
		case 4:
			Destination newDestination = ArmaEquip.destinationMapInversed.get(value.toString().toLowerCase());
			if (!newDestination.toString().equalsIgnoreCase(this.getValueAt(rowIndex, columnIndex).toString())) {
				this.items.get(rowIndex).destination = newDestination;
				this.setGotChangesAndUpdate(true);
			}
			break;
		default:
		}

	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	public void addItem(ArmaEquip item) {
		item.faction=this.getCurrentFaction();
		this.items.add(item);
	}

	public void addItems(ArrayList<ArmaEquip> items) {
		this.addAll(items);
	}

	private void addAll(ArrayList<ArmaEquip> items2) {
		// TODO Auto-generated method stub
		for (ArmaEquip item: items2) {
			this.addItem(item);
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		switch (column) {
		case 0:
			return false;
		case 4:
			System.out.println("case 4 " + this.getValueAt(row, 1));
			if (this.getValueAt(row, 1).toString().equalsIgnoreCase(AddableType.ITEM.toString()))
				return true;
			else
				return false;
		default:
			return true;
		}
	}

	public void reloadFromFile(String path) {
		if (this.gotChanges) {
			int result = JOptionPane.showConfirmDialog(null, "Сохранить изменения?", "alert",
					JOptionPane.YES_NO_OPTION);
			switch (result) {
			case 0:
				this.host.saveUnit();
			case 1:
				this.setGotChangesAndUpdate(false);
				break;
			}
		}
		// TODO Are you sure?
		this.items.clear();
		try {
			this.items.addAll(StringParser.readFile(path));
			this.setGotChanges(false);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setGotChanges(boolean changed) {
		this.gotChanges = changed;
	}

	public void setGotChangesAndUpdate(boolean changed) {
		this.gotChanges = changed;
		this.children.clear();
		for (ArmaEquip item: items) {
			System.out.println("SGTCHU " + item.addableType.toString());
			if (item.addableType == AddableType.KIT || item.addableType == AddableType.FUNCTION) {
				System.out.println("CHILDREN");
				System.out.println(item.classname);
				System.out.println(item.faction.getUnits().get(item.classname+".sqf").toString());
				children.add(item.faction.getUnits().get(item.classname+".sqf")); // item.faction
			}
		}
		if (this.host != null) {
			this.host.getBtnSave().setEnabled(changed);
		}
	}

	public void setParent(UnitEquipmentEditor parent) {
		this.host = parent;
	}

	public void reloadFromClipboard() {
		// TODO Auto-generated method stub
		StringParser parser = new StringParser();
		/*
		 * if (this.gotChanges) { int result =
		 * JOptionPane.showConfirmDialog(null, "Сохранить изменения?","alert",
		 * JOptionPane.YES_NO_OPTION); switch (result) { case 0:
		 * this.parent.saveUnit(); case 1: this.setGotChanges(false); break; } }
		 */

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String textFromClipboard;
		try {
			textFromClipboard = (String) clipboard.getData(DataFlavor.stringFlavor);
			for (String line : textFromClipboard.split("\n")) {
				System.out.println("Clipboard line:" + line);
				ArmaEquip parsedLine = parser.parse(line);
				if (parsedLine != null) {
					this.addItem(parser.parse(line));
				}
			}
			this.setGotChangesAndUpdate(true);
			this.fireTableDataChanged();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}


	public void deleteItem(int selectedRow) {
		// TODO Auto-generated method stub
		System.out.println("Item to delete:" + selectedRow + " " + ((int)this.getValueAt(selectedRow, 0)-1) + " " + this.items.get((int) this.getValueAt(selectedRow, 0)-1).classname);
		this.items.remove((int) this.getValueAt(selectedRow, 0)-1);
		this.fireTableDataChanged();
		this.setGotChangesAndUpdate(true);
	}

	public Faction getCurrentFaction() {
		return currentFaction;
	}

	public void setCurrentFaction(Faction currentFaction) {
		this.currentFaction = currentFaction;
		for (ArmaEquip item: this.items) {
			item.faction = currentFaction;
		}
	}

	public HashSet<PersonalEquipmentModel> getChildren() {
		return children;
	}
}
