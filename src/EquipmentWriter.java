import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class EquipmentWriter {

	public static final String BN_FILE_PREFIX = "equip_patterns";
	public static final String EQUIPMENT_DIRECTORY = "equip_patterns\\";
	public static final String BN_FUNCTION_PREFIX = "bn_equip";
	private String PREFIX = "if (!isServer) exitWith {};\n_unit = _this select 0;\n";
	private String CLEAR_PREFIX = "removeAllWeapons _unit;\nremoveAllItems _unit;\nremoveAllAssignedItems _unit;\nremoveUniform _unit;\nremoveVest _unit;\nremoveBackpack _unit;\nremoveHeadgear _unit;\nremoveGoggles _unit;";
	private String WHO = "_unit";

	private ArrayList<ArmaEquip> equipment;
	private String path;
	private static String precompileList;
	private boolean precompile;
	private Unit unit;

	private String enquote(String string) {
		return "'" + string + "'";
	}

	public void write() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		this.writePrecompile(this.precompileList, this.precompile);
	}

	public static void finalize(String currentFaction) throws IOException {
		precompileList += "//Insert the following (uncommented) lines into description.sqf:" + System.lineSeparator();
		precompileList += "/*class CfgFunctions" + System.lineSeparator() + "{" + System.lineSeparator()
				+ "	class bn" + System.lineSeparator() + "	{" + System.lineSeparator() + "		class myCategory"
				+ System.lineSeparator() + "		{" + System.lineSeparator() + "			class campaign_"
				+ currentFaction + "_preInit {" + System.lineSeparator() + "				file = \"" + BN_FILE_PREFIX
				+ "\\" + currentFaction + "\\precompile_equipment.sqf\";" + System.lineSeparator()
				+ "				preInit = 1;" + System.lineSeparator() + "			};" + System.lineSeparator()
				+ "		};" + System.lineSeparator() + "	};" + System.lineSeparator() + "};*/";
		File file = new File(BN_FILE_PREFIX + "\\" + currentFaction + "\\precompile_equipment.sqf");
		System.out.println(BN_FILE_PREFIX + "\\" + currentFaction + "\\precompile_equipment.sqf");
		file.createNewFile();
		try (BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(BN_FILE_PREFIX + "\\" + currentFaction + "\\precompile_equipment.sqf"),
				"utf-8"))) {
			writer1.flush();
			writer1.write("if (!isServer) exitWith {};"+System.lineSeparator());
			writer1.write(precompileList);
			precompileList = "";
			writer1.close();
		}
	}

	public String writePrecompile(String precompileList, boolean precompile)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		try (BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(this.path), "utf-8"))) {
			writer.flush();
			writer.write(PREFIX + System.lineSeparator());
			unit.updateParentInfo();
			if (unit.getParents().size() == 0) {
				writer.write(CLEAR_PREFIX + System.lineSeparator());
			}
			for (ArmaEquip item : equipment) {
				String addCommand = "";

				switch (item.addableType) {
				case ITEM:
					addCommand = ArmaEquip.destinationMap.get(item.destination);
					break;
				default:
					addCommand = ArmaEquip.addableMap.get(item.addableType);
				}
				if (item.addableType == AddableType.KIT) {
					writer.write("[" + WHO + "] call " + item.classnames.formatAsFunctions(unit.getFaction()) + ";");
					writer.write("// kit");
					/*
					if (precompile) {
						writer.write("[" + WHO + "] call " + item.classnames.formatAsFunctions(unit) + ";");
						//this.addToPrecompileList(getFunctionName() + " = compile preprocessFileLineNumbers "	+ enquote(EQUIPMENT_DIRECTORY + unit.getFaction().getName() + "\\" + item.classnames + ".sqf") + ";");
					} else {
						
					}*/
				} else if (item.addableType == AddableType.FUNCTION) {
					writer.write("[" + WHO + "] call " + item.classnames + ";");
					writer.write("// function");
				} else if (item.qty == 1) {
					writer.write(WHO + " " + addCommand + " " + (item.classnames.getSQF()) + ";");
				} else {
					if (item.addableType != AddableType.MAGAZINE) {
						writer.write("for '_i' from 1 to " + item.qty + " do {" + WHO + " " + addCommand + " "
								+ item.classnames.getSQF() + ";};");
					} else {
						writer.write(WHO + " " + addCommand + " [" + (item.classnames.getSQF()) + ", " + item.qty + "];");
					}
				}
				this.addToPrecompileList(pathToFunctionName(path) + " = compile preprocessFileLineNumbers " + enquote(path) + ";");
				writer.write(System.lineSeparator());
			}
			writer.close();
			return this.precompileList;
		}
	}

	private String pathToFunctionName(String path2) {
		return path2.replaceAll(BN_FILE_PREFIX, BN_FUNCTION_PREFIX).replaceAll("\\\\", "_")
				.replaceAll(".sqf", "");
	}

	private void addToPrecompileList(String string) {
		// TODO Auto-generated method stub
		if (this.precompileList == null) {
			this.precompileList = "";
		}
		if (!this.precompileList.contains(string)) {
			this.precompileList += string + System.lineSeparator();
		}
	}
	
	private String getFunctionName() {
		return unit.getFaction().getName() + "_" + unit.getName();
	}

	public EquipmentWriter(Unit unit, boolean precompile)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		this.equipment = unit.getAllItems();
		this.unit = unit;
		this.path = EQUIPMENT_DIRECTORY + unit.getFaction().getName() + "\\"
				+ unit.getName() + ".sqf";
		this.precompile = precompile;
	}
}
