package testModel;

import static org.junit.jupiter.api.Assertions.*;
import model.Fligth;
import org.junit.jupiter.api.Test;

class TestFligth {

	private Fligth fligth;

	public void setupSceneary1(){
		fligth = null;
	}

	public void setupSceneary2(){
		fligth = new Fligth("2019-07-23","04:34 AM","AVIANCA","AV 3213","Madrid",6,"Exit");
	}

	@Test
	public void testFligth1(){
		setupSceneary1();

		assertNull(fligth, "The reference of a fligth is different of null.");
		//assertNull(fligth.getDate(),"The date of de fligth exist");
		//assertNull(fligth.getTime());

	}

	@Test
	public void testFligth2(){
		setupSceneary2();

		String date = "2019-07-23";
		String time="04:34 AM";
		String airline="AVIANCA";
		String cfligth="AV 3213";
		String city="Madrid";
		int gate=6;
		String state="Exit";

		assertNotNull(fligth,"The fligth not exist");

		assertEquals(date,fligth.getDate(),"The date not is correct");
		assertEquals(time,fligth.getTime(),"The time not is correct");
		assertEquals(airline,fligth.getAirline(),"The airline not is correct");
		assertEquals(cfligth,fligth.getFligth(),"The code fligth not is correct");
		assertEquals(city,fligth.getCity(),"The city not is correct");
		assertEquals(gate,fligth.getGate(),"The gate not is correct");
		assertEquals(state,fligth.getState(),"The state not is correct");

	}





}
