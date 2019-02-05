package lightsout;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LightsOutModelTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidRowArgument ()
	{
		LightsOutModel c = new LightsOutModel(3,5);
		assertEquals(1, c.getOccupant(4, 3));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testForInvalidColArgument ()
	{
		LightsOutModel c = new LightsOutModel(5,2);
		assertEquals(1, c.getOccupant(4, 3));
	}

}
