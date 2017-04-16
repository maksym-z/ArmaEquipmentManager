import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClassnameSetTest {
	
	ClassnameSet set = new ClassnameSet();

	@Before
	public void setUp() throws Exception {
		set.setClassnames("Alpha, Bravo, Charlie");
	}	

	@Test
	public void testToString() {
		System.out.println(set.toString());
	}

	@Test
	public void testGetSQF() {
		System.out.println(set.getSQF(true));
	}

	@Test
	public void testFormatAsFunctions() {
		Unit unit = new Unit();
		unit.setFaction(new Faction("testfaction"));
		unit.setName("testunit");
		System.out.println(set.formatAsFunctions(unit));
	}

}
