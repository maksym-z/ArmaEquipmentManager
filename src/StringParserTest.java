import static org.junit.Assert.*;

import org.junit.Test;

public class StringParserTest {

	@Test
	public void testReadFile() {
		String testString = "_unit addWeapon 'a';";
		String testString1 = "_unit addWeapon selectRandom ['a','b','c'];";
		String testString2 = "[_unit] call selectRandom [fnc_1, fnc_2, fnc_3];";
		StringParser p = new StringParser();
		ArmaEquip r = p.parse(testString);
		System.out.println(r.classnames);
		System.out.println(r.classnames.getSQF(true));
		r = p.parse(testString1);
		System.out.println(r.classnames);
		System.out.println(r.classnames.getSQF(true));
		r = p.parse(testString2);
		System.out.println(r.classnames);
		System.out.println(r.classnames.getSQF(false));
	}

}
