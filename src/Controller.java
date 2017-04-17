import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

public class Controller {

	private EquipmentEditor editor;
	private GraphView graphView;
	private HashMap<String, Faction> factions = new HashMap<>();
	private String currentFactionKey;
	private Unit selectedUnit;
	private boolean listeningMode = false;
	private HashSet<AddableType> thingsToListenFor = new HashSet<>();

	private ArrayList<String> browseFolder(String path, boolean isDir) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		ArrayList<String> result = new ArrayList<String>();
		for (String name : file.list()) {
			if (new File(path + name).isDirectory() == isDir) {
				result.add(name);
			}
		}
		return result;
	}

	public void readFactions() {
		browseFolder(EquipmentWriter.EQUIPMENT_DIRECTORY, true).forEach(x -> {
			if (!factions.containsKey(x)) {
				System.out.println("Adding faction " + x);
				Faction newFaction = new Faction(x);
				factions.put(x, newFaction);
				if (getCurrentFaction() == null) {
					setCurrentFaction(x);
					System.out.println("Current faction set to " + x);
				} else {
					System.out.println("Current faction: " + getCurrentFaction().getName());
				}
				String filePath = EquipmentWriter.EQUIPMENT_DIRECTORY + newFaction.getName() + "\\";
				for (String unitFileName : browseFolder(filePath, false)) {
					Unit newUnit = createUnit(removePathAndExtension(unitFileName));
					newFaction.addUnit(newUnit);
					try {
						newUnit.addItems(StringParser.readFile(filePath + newUnit.getName() + ".sqf"));
					} catch (Exception e) {
						errorMessage("Path not found: " + filePath);
						e.printStackTrace();
					}
				}
				System.out.println("Editor " + editor);
				System.out.println("Editor fcb " + editor.factionsComboBox);
				editor.factionsComboBox.addItem(x);
			}
		});
	}

	private String removePathAndExtension(String unitFileName) {
		String[] splitName = unitFileName.split("\\\\");
		String lastPart = splitName[splitName.length - 1];
		if (lastPart.toLowerCase().endsWith(".sqf")) {
			return lastPart.substring(0, lastPart.length() - 4);
		} else {
			return lastPart;
		}
	}

	void setGotChanges(boolean changes) {
		if (changes) {
			editor.getBtnSave().setEnabled(true);
		} else {
			editor.getBtnSave().setEnabled(false);
		}
	}

	public void saveUnit(Unit unit) {
		if (getCurrentFaction() == null || getCurrentFaction().getUnits().size() < 1) {
			errorMessage("Nothing to save");
			return;
		}
		try {
			updateAllChildren(unit.getFaction());
			EquipmentWriter writer = new EquipmentWriter(unit, false);
			writer.write();
			setGotChanges(false);
			// TODO: update model
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAllChildren(Faction faction) {
		for(String unitName: faction.getUnits().keySet()) {
			faction.getUnit(unitName).updateChildren();
		}
	}
	
	public void saveAll(Faction faction) {
		updateAllChildren(faction);
		for(String unitName: faction.getUnits().keySet()) {
			saveUnit(faction.getUnit(unitName));
		}
	}

	public void pasteFromClipboard() {
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
				if (parsedLine != null && (!listeningMode || thingsToListenFor.contains(parsedLine.addableType))) {
					getSelectedUnit().addItem(parser.parse(line));
				}
			}
			setGotChanges(true);
			setSelectedUnit(getSelectedUnit().getName());
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveAllUnits(boolean precompile) {
		updateAllChildren(getCurrentFaction());
		// TODO Auto-generated method stub
		if (getCurrentFaction() == null || getCurrentFaction().getUnits().size() < 1) {
			errorMessage("Nothing to save");
			return;
		}
		for (String unitName : getCurrentFaction().getUnits().keySet()) {
			saveUnit(getCurrentFaction().getUnits().get(unitName));
		}
		try {
			EquipmentWriter.finalize(getCurrentFaction().getName());
			setGotChanges(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new unit in the current faction with the given name and sets it
	 * as selected.
	 * 
	 * @param unitName
	 * @return
	 */
	public Unit createUnit(String unitName) {
		Unit newUnit = new Unit();
		newUnit.setName(unitName);
		if (unitName != null && unitName.length() > 0) {
			String extension = "";
			if (!unitName.toLowerCase().endsWith(".sqf"))
				extension = ".sqf";
			/*
			 * File f = new File( EQUIPMENT_DIRECTORY +
			 * getCurrentFaction().getName() + "\\" + unitName + extension); try
			 * { f.createNewFile(); //
			 * editor.unitsComboBox.setSelectedItem(unitName); } catch
			 * (IOException e) { // TODO unable to create file
			 * e.printStackTrace(); }
			 */
		}
		getCurrentFaction().addUnit(newUnit);
		setSelectedUnit(newUnit.getName());
		graphView.addUnit(newUnit.getName());
		setGotChanges(true);
		return newUnit;
	}

	public Faction createNewFaction() {
		String name = JOptionPane.showInputDialog("Введите название новой фракции:");
		if (name != null && name.length() > 0) {
			File f = new File(EquipmentWriter.EQUIPMENT_DIRECTORY + name);
			if (!f.exists()) {
				f.mkdir();
				editor.factionsComboBox.setSelectedItem(name);
				Faction faction = new Faction(name);
				factions.put(name, faction);
				return faction;
			} else {
				errorMessage("Фракция с таким названием уже существует!");
			}
		}
		return null;
	}

	private void errorMessage(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}

	public HashMap<String, Faction> getFactions() {
		return factions;
	}

	public void addFaction(Faction faction) {
		this.factions.put(faction.getName(), faction);
	}

	public Faction getCurrentFaction() {
		return factions.get(currentFactionKey);
	}

	public void setCurrentFaction(String key) {
		if (factions.containsKey(key)) {
			this.currentFactionKey = key;
			editor.updateUnitList(factions.get(key));
		} else {
			throw (new IllegalArgumentException("Faction list does not contain key '" + key + "'"));
		}
	}

	public Unit getSelectedUnit() {
		return selectedUnit;
	}

	public void setSelectedUnit(String unitName) {
		if (getCurrentFaction().getUnits().containsKey(unitName)) {
			selectedUnit = getCurrentFaction().getUnits().get(unitName);
			if (editor.getModel() == null || editor.getModel().getCurrentUnit() != selectedUnit) {
				editor.setModel(new UnitModel(selectedUnit, this));
			}
			editor.getModel().setCurrentUnit(selectedUnit);
			for (int i = 0; i < editor.factionsComboBox.getItemCount(); i++) {
				if (editor.factionsComboBox.getItemAt(i).equals(getCurrentFaction().getName())) {
					editor.factionsComboBox.setSelectedIndex(i);
				}
			}

			for (int i = 0; i < editor.unitsComboBox.getItemCount(); i++) {
				if (editor.unitsComboBox.getItemAt(i).equals(unitName)) {
					editor.unitsComboBox.setSelectedIndex(i);
				}
			}

			selectedUnit.updateChildren();
			// editor.updateUnitList(getCurrentFaction());
		} else {
			throw (new IllegalArgumentException(
					"Faction '" + getCurrentFaction().getName() + "' does not contain unit '" + unitName + "'"));
		}
	}

	public void showGraphView() {
		graphView.setVisible(true);
		graphView.refreshGraph(getCurrentFaction());
	}

	// TODO: update children

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.editor = new EquipmentEditor(controller);
		UnitModel unitModel = new UnitModel(controller.getSelectedUnit(), controller);
		controller.graphView = new GraphView(controller);
		controller.readFactions();

		// TODO: move inside?
		unitModel.setParent(controller.editor);
		controller.editor.getTable().setModel(unitModel);
		controller.editor.updateColumnEditors();
		controller.editor.setVisible(true);
	}

	public boolean getListeningMode() {
		return listeningMode;
	}

	public void toggleListeningMode() {
		listeningMode = !listeningMode;
	}

	public void addSomethingToListenFor(AddableType addableType) {
		thingsToListenFor.add(addableType);
	}

	public void removeSomethingToListenFor(AddableType addableType) {
		thingsToListenFor.remove(addableType);
	}
}
