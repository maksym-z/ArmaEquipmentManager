import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class StringParser {
	
	private boolean makeParserReports = true;
	
	private void parserReport(String report) {
		if (makeParserReports) {
			System.out.println("Parser: " + report);
		}
	}

	public String[] tokenize(String s) {
		String result = s.replaceAll("([\\[\\],\\(\\);\\{\\}])", " $1 ");
		result = result.trim();
		while (result.contains("  ")) {
			result = result.replaceAll("  ", " ");
		}
		// System.out.println(result);
		return result.split(" ");
	}

	public String unquote(String s) {
		return s.replaceAll("[\"']", "");
	}

	public ArmaEquip parse(String[] ts) {
//		System.out.println("Trying to parse " + Arrays.deepToString(ts));
		ArmaEquip result = new ArmaEquip();
		try {
			if (who(ts[0]) && (addCommand(ts[1]) != null)) {
				parserReport("who");
				System.out.println(ts[2]);
				System.out.println(ts[3] + " " + classnameOrRandomListMarker(ts[2]));
				if ((openSquareBracket(ts[3])) && (classnameOrRandomListMarker(ts[2]))) {
						result.classnames.setClassnames(parseClassnamesOrFunctionNames(ts,2,true));
						int nextStop = 4 + result.classnames.size(); 
						if ((comma(ts[nextStop])) && (number(ts[nextStop+1])) && (closeParen(ts[nextStop+2]))) {
						result.qty = Integer.parseInt(ts[nextStop+1]);
						
						result.destination = addCommand(ts[1]).destination;
						result.addableType = addCommand(ts[1]).addableType;
					}
				} else if (classnameOrRandomListMarker(ts[2])) {
					result.classnames.setClassnames(ts[2].replaceAll("[\"\']", ""));
					result.qty = 1;
					result.destination = addCommand(ts[1]).destination;
					result.addableType = addCommand(ts[1]).addableType;
					// System.out.println("class: " + result.classname);
				}
			} else if (cycle(ts) != null) {
				return cycle(ts);
			} else if (script(ts) != null) {
				return script(ts);
				// [_unit] call compile preprocessFileLineNumbers ("\kits\"
				// _kit_name + ".sqf");
				
			} else {
//				System.out.println("Cannot parse '" + ts[2] + "'");
				throw new ParserException();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Cannot parse line");
			return null;
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot parse line");
			return null;
		}
		return result;
	}
	
	private boolean isClassname(String s, boolean requireQuotes) {
		if (requireQuotes)  {
			return s.matches("\"[a-zA-Z0-9_]*\"") || s.matches("'[a-zA-Z0-9_]*'");
		} else {
			return s.matches("[a-zA-Z0-9_]*");
		}
	}

	private String parseClassnamesOrFunctionNames(String[] ts, int i, boolean requireQuotes) throws ParserException {
		parserReport("parseClassnames, " + ts[i]);
		if (ts[i].equalsIgnoreCase("selectRandom")) {
			parserReport("selectRandom");
			String output = "";
			i+=2; // [
			boolean finished = false;
			parserReport("class: " + ts[i]);
			while (isClassname(ts[i],  requireQuotes) && !finished) {
				parserReport("Next class name: " + ts[i] + " " + ts[i+1]);
				if (closeSquareBracket(ts[i+1])) {
					output += unquote(ts[i]);
					finished = true;
				} else if (ts[i+1].equals(",")) {
					output += unquote(ts[i]) + ", ";
				} else {
					throw new ParserException();
				}
				i+=2;
			}
			return output;
		} else if (isClassname(ts[i], requireQuotes)) {
			return ts[i].replaceAll("[\"\']", "");
		} else {
			throw new ParserException();
		}

	}

	private ArmaEquip script(String[] ts) throws ParserException {
		// TODO Auto-generated method stub
		parserReport("Script");
		ArmaEquip result = new ArmaEquip();
		if (openSquareBracket(ts[0]) && who(ts[1]) && closeSquareBracket(ts[2])) {
			if (ts[3].equalsIgnoreCase("call")) {
				if (ts[4].equalsIgnoreCase("compile")) {
					ts[6] = unquote(ts[6]);
					System.out.println("script: "+ts[6]);
					String[] paths = ts[6].split("\\\\");
					if (paths[paths.length - 3].equalsIgnoreCase("equip_patterns")) {
						result.classnames.setClassnames(paths[paths.length - 1].substring(0, paths[paths.length - 1].length()-4));
						result.addableType = AddableType.KIT;
					} else {
						throw (new ParserException());
					}
					return result;
				} else if (ts[4].equalsIgnoreCase("selectRandom")) {
					result.classnames.setClassnames(parseClassnamesOrFunctionNames(ts,4,false));
					result.addableType = AddableType.FUNCTION;
					return result;
				} else if (ts[4].matches("[A-Za-z0-9_]+")) {
/*					String[] between_underscores = ts[4].split("_");
					int i;
					for (i=between_underscores.length-1;i>=0;i--) {*/
					result.classnames.setClassnames(ts[4]);
					result.addableType = AddableType.FUNCTION;
//					}
					return result;
				}
			}

		}
		return null;
	}

	private boolean quote(String string) {
		return (string.equals("\"") || string.equals("'"));
	}

	private boolean openSquareBracket(String string) {
		return (string.equals("["));
	}

	private boolean closeSquareBracket(String string) {
		return (string.equals("]"));
	}

	private boolean closeParen(String string) {
		// TODO Auto-generated method stub
		if (string.equals("]"))
			return true;
		else
			return false;
	}

	private boolean number(String string) {
		// TODO number parsing regex
		if (string.matches("[0-9\\.]*")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comma(String string) {
		// TODO Auto-generated method stub
		if (string.equals(","))
			return true;
		else
			return false;
	}

	private boolean classnameOrRandomListMarker(String string) {
		// TODO Auto-generated method stub
		// System.out.println(string);
		if (string.matches("\"[a-zA-Z0-9_]*\"") || string.matches("'[a-zA-Z0-9_]*'")) {
//			System.out.println("is classname: " + string);
			return true;
		} else if (string.equalsIgnoreCase("selectRandom")) {
			return true;
		} else {
//			System.out.println("NOT a classname: " + string);
			return false;
		}
	}

	private boolean openParen(String string) {
		// TODO Auto-generated method stub
		// System.out.println(string);
		if (string.equals("["))
			return true;
		else
			return false;
	}

	private Stuff stuffToAdd(String string) {
		// TODO Auto-generated method stub

		return null;
	}

	private Adder addCommand(String string) throws ParserException {
		// TODO Auto-generated method stub
		if (string.matches("[a-zA-Z]*")) {
			switch (string.toLowerCase()) {
			case "addmagazine":
				return new Adder(Destination.ANY, AddableType.MAGAZINE);
			case "additem":
				return new Adder(Destination.ANY, AddableType.ITEM);
			case "additemtovest":
				return new Adder(Destination.VEST, AddableType.ITEM);
			case "additemtobackpack":
				return new Adder(Destination.BACKPACK, AddableType.ITEM);
			case "addweapon":
				return new Adder(Destination.ANY, AddableType.WEAPON);
			case "addprimaryweaponitem":
				return new Adder(Destination.ANY, AddableType.PRIMARYWEAPONITEM);
			case "addsecondaryweaponitem":
				return new Adder(Destination.ANY, AddableType.SECONDARYWEAPONITEM);
			case "addHandgunItem":
				return new Adder(Destination.ANY, AddableType.HANDGUNITEM);
			case "addbackpack":
				return new Adder(Destination.ANY, AddableType.BACKPACK);
			case "addvest":
				return new Adder(Destination.ANY, AddableType.VEST);
			case "addheadgear":
				return new Adder(Destination.ANY, AddableType.HEADGEAR);
			case "adduniform":
			case "forceadduniform":
				return new Adder(Destination.ANY, AddableType.UNIFORM);
			case "additemtouniform":
				return new Adder(Destination.UNIFORM, AddableType.ITEM);
			case "linkitem":
				return new Adder(Destination.LINK, AddableType.ITEM);
			default:
				throw (new ParserException());
			}
		} else {
			return null;
		}
	}

	public ArmaEquip parse(String s) {
		return parse(tokenize(s));
	}

	private ArmaEquip cycle(String[] ts) {
		ArmaEquip result = new ArmaEquip();
		int qty;
		int i = 0;
		// for "_i" from 1 to 2 do {this addItemToBackpack
		// "rhsusf_100Rnd_762x51" ; } ;
		if (ts[0].equalsIgnoreCase("for")) {
			do {
				i++;
				// System.out.println(ts[i]);
				if (i >= ts.length)
					return null;
			} while (!ts[i].equalsIgnoreCase("to"));
			i++;
			qty = Integer.parseInt(ts[i]);
//			System.out.println("qty: " + qty);
			do {
				i++;
				if (i >= ts.length)
					return null;
//				System.out.println(ts[i]);
			} while (!ts[i].equalsIgnoreCase("{"));
			result = parse(Arrays.copyOfRange(ts, i + 1, ts.length - 1));
			if (result != null) {
				result.qty = qty;
			}
			return result;
		}
		return null;
	}

	private boolean who(String string) {
		if (string.matches("[a-zA-Z_0-9]*")) {
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<ArmaEquip> readFile(String path)
			throws UnsupportedEncodingException, FileNotFoundException {
		ArrayList<ArmaEquip> result = new ArrayList<>();
		ArmaEquip r = new ArmaEquip();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
		StringParser parser = new StringParser();
		do {
			try {
				String line = reader.readLine();
				if (line == null) {
					System.out.println("File read complete.");
					break;
				} else {
					r = parser.parse(line.trim());
					if (r != null) {
						result.add(parser.parse(line.trim()));
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				break;
			}
		} while (true);
		return result;
	}

}
