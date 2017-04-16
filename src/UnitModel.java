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

public class UnitModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6296481131578398313L;
	private String[] columnNames = new String[] { "№", "Тип", "Класснейм", "Кол-во", "Куда" };
	private boolean gotChanges = false;
	
	private Unit currentUnit; 

	private EquipmentEditor host;
	private Controller controller;
	
//	
	
	
	public UnitModel(Unit unit, Controller controller) {
		super();
		this.currentUnit = unit;
		this.controller = controller;
	}

	//TODO: move
/*	public String functionNameToFileName(String classname) {
		String prefix = ScriptPath.BN_PREFIX+"_"+this.faction.getName()+"_"; 
		String filename = classname.substring(prefix.length()) + ".sqf"; 
		return filename;
	}*/

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (currentUnit != null) {
			return currentUnit.getAllItems().size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case 0:
			return arg0 + 1;
		case 1:
			if (currentUnit.getItem(arg0).addableType != null)
				return currentUnit.getItem(arg0).addableType.toString();
			else
				return "-";
		case 2:
			System.out.println("Classname: " + currentUnit.getItem(arg0).classnames);
			return currentUnit.getItem(arg0).classnames;
		case 3:
			return currentUnit.getItem(arg0).qty;
		case 4:
			if (currentUnit.getItem(arg0).destination != null && currentUnit.getItem(arg0).destination != Destination.ANY)
				return currentUnit.getItem(arg0).destination.toString();
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
				currentUnit.getItem(rowIndex).addableType = newAddable;
				this.setGotChanges(true);
			}
			break;
		case 2:
			if (!value.toString().equalsIgnoreCase(this.getValueAt(rowIndex, columnIndex).toString())) {
				currentUnit.getItem(rowIndex).classnames.setClassnames(value.toString());
				this.setGotChanges(true);
			}
			break;
		case 3:
			try {
				if (Integer.parseInt(value.toString()) != currentUnit.getItem(rowIndex).qty) {
					currentUnit.getItem(rowIndex).qty = Integer.parseInt(value.toString());
					this.setGotChanges(true);
				}
			} catch (NumberFormatException e) {

			}
			break;
		case 4:
			Destination newDestination = ArmaEquip.destinationMapInversed.get(value.toString().toLowerCase());
			if (!newDestination.toString().equalsIgnoreCase(this.getValueAt(rowIndex, columnIndex).toString())) {
				currentUnit.getItem(rowIndex).destination = newDestination;
				this.setGotChanges(true);
			}
			break;
		default:
		}

	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
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

	/*
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
	*/
	public void setGotChanges(boolean changed) {
		this.gotChanges = changed;
		controller.setGotChanges(changed);
	}


	public void setParent(EquipmentEditor parent) {
		this.host = parent;
	}



	public void deleteItem(int selectedRow) {
		// TODO Auto-generated method stub
		System.out.println("Item to delete:" + selectedRow + " " + ((int)this.getValueAt(selectedRow, 0)-1) + " " + currentUnit.getItem((int) this.getValueAt(selectedRow, 0)-1).classnames);
		currentUnit.removeItem((int) this.getValueAt(selectedRow, 0)-1);
		fireTableDataChanged();
	}



	public Unit getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(Unit currentUnit) {
		this.currentUnit = currentUnit;
		System.out.println("Current unit set to " + currentUnit.getName() + "; " + currentUnit.getAllItems().size());
		fireTableDataChanged();
	}
}
