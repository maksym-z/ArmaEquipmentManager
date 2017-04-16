public class ScriptPath {
	
	private String path = "";
	private String filename = "";
	private String extension = "";
	private String factionName = "";
	
	public ScriptPath(ArmaEquip item, Faction faction) {
		/*		if (item.addableType!=AddableType.KIT && item.addableType!=AddableType.SCRIPT) {
			throw (new IllegalArgumentException());
		}
		String[] slashSplitString = item.classnames.split("[\\\\\\/]");
		factionName = faction.getName();
		filename = slashSplitString[slashSplitString.length-1];
		for (int i=0; i<slashSplitString.length-2;i++) {
			if (slashSplitString[i].equalsIgnoreCase(EQUIPMENT_PATH) && !slashSplitString[i+1].equalsIgnoreCase(EQUIPMENT_PATH)) {
				path = EquipmentWriter.EQUIPMENT_DIRECTORY + slashSplitString[i+1];
				break;
			}
		}
		if (path.isEmpty()) {
			path = EquipmentWriter.EQUIPMENT_DIRECTORY + item.faction;
		}
		String[] dotSplitString = filename.split("\\.");
		extension="sqf";
		if (dotSplitString.length>0 && dotSplitString[dotSplitString.length-1].equalsIgnoreCase(extension.replaceAll("\\.", ""))) {
			filename = filename.substring(0, filename.length()-extension.length()-1);
		}*/
	}

	public String getPath() {
		return path;
	}

	public String getFilename() {
		return filename;
	}

	public String getExtension() {
		return extension;
	}
	
	/*	public String getFunctionName() {
		System.out.println(BN_PREFIX+"_\n"+this.getFaction()+"_\n"+this.filename);
		return BN_PREFIX+"_"+this.getFaction()+"_"+this.filename;
	}*/
	
	private String getFaction() {
		// TODO Auto-generated method stub
		return factionName;
	}

	public String getFullPath() {
		return path + "\\" + filename + "." + extension;
	}
}
