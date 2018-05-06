package com.ubs.opsit.interviews;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.junit.Test;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  You should not need to
 * edit this class to complete the exercise, this is your definition of done.
 */
public class BerlinClockFixture {

    private TimeConverter timeConverter=new TimeConverterImpl();
    private String theTime;

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock.story")
                .run();
    }

    /**
     *  Seconds lamp should blink on/off every two seconds
     *  Returns Yellow lamp for every two seconds.
     */
    @Test
    public void testSecondsLamps() {
        Assert.assertEquals("Y", timeConverter.getLightLamps(0));
        Assert.assertEquals("O", timeConverter.getLightLamps(3));
        Assert.assertEquals("O", timeConverter.getLightLamps(47));
        Assert.assertEquals("Y", timeConverter.getLightLamps(40));
    }
 
    /**
     *  Second Row of hour Lamp should have 4 lamps.
     */
    @Test
    public void testSecondRowOfHourLampsLength() {
        Assert.assertEquals(4, timeConverter.getLightLamps(10,10/5,TimeConverterConstants.RED_LAMP).length());
    }
 
    /**
     *  Third Row of hour Lamp should have 4 lamps.
     */
    @Test
    public void testThirdRowOfHourLampsLength() {
        Assert.assertEquals(4, timeConverter.getLightLamps(15,15/5,TimeConverterConstants.RED_LAMP).length());
    }
    
    /**
     * Second Row of hour Lamp should contain Red Lamp for every Five Hours.
     * 
     */
    @Test
    public void testSecondRowOfHourLamps() {
        Assert.assertEquals("OOOO", timeConverter.getLightLamps(0,0/5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("RROO", timeConverter.getLightLamps(13,13/5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("RRRO", timeConverter.getLightLamps(18,18/5,TimeConverterConstants.RED_LAMP));
        Assert.assertEquals("RRRR", timeConverter.getLightLamps(23,23/5,TimeConverterConstants.RED_LAMP));
    
        
    }
    
    /**
     * Third Row of hour Lamp should contain Red Lamp for every One Hour.
     * 
     */
    @Test
    public void testThirdRowOfHourLamps() {
    	 Assert.assertEquals("OOOO", timeConverter.getLightLamps(0,0%5,TimeConverterConstants.RED_LAMP));
         Assert.assertEquals("RRRO", timeConverter.getLightLamps(13,13%5,TimeConverterConstants.RED_LAMP));
         Assert.assertEquals("OOOO", timeConverter.getLightLamps(20,20%5,TimeConverterConstants.RED_LAMP));
         Assert.assertEquals("RRRO", timeConverter.getLightLamps(23,23%5,TimeConverterConstants.RED_LAMP));
        
    }

    /**
     * Fourth Row of Minute Lamp should have 11 lamps.
     * 
     */
    @Test
    public void testFourthRowOfMinuteLampsLength() {
        Assert.assertEquals(11, timeConverter.getLightLamps(59,59/5,TimeConverterConstants.RED_LAMP,TimeConverterConstants.YELLOW_LAMP).length());
    }
    
    /**
     * Fifth Row of Minute Lamp should have 11 lamps.
     * 
     */
    @Test
    public void testFifthhRowOfMinuteLampsLength() {
        Assert.assertEquals(4, timeConverter.getLightLamps(59,59%5,TimeConverterConstants.YELLOW_LAMP).length());
    }
    
    /**
     * Fourth Row of Minute Lamp should have Yellow lamps for every 5 minutes and Red Lamps in 3,6,9th position indicating first,half and last quarter of an hour.
     * 
     */
    @Test
    public void testFourthRowOfMinuteLamps() {
    	 Assert.assertEquals("OOOOOOOOOOO", timeConverter.getLightLamps(0,0/55,TimeConverterConstants.RED_LAMP,TimeConverterConstants.YELLOW_LAMP));
         Assert.assertEquals("YYROOOOOOOO", timeConverter.getLightLamps(17,17/5,TimeConverterConstants.RED_LAMP,TimeConverterConstants.YELLOW_LAMP));
         Assert.assertEquals("YYRYYRYYRYY", timeConverter.getLightLamps(59,59/5,TimeConverterConstants.RED_LAMP,TimeConverterConstants.YELLOW_LAMP));        

    }
    
    /**
     * Fifth Row of Minute Lamp should have Yellow lamps .
     * 
     */
    @Test
    public void testFifthRowOfMinuteLamps() {
    	Assert.assertEquals("OOOO", timeConverter.getLightLamps(0,0%5,TimeConverterConstants.YELLOW_LAMP));
        Assert.assertEquals("YYOO", timeConverter.getLightLamps(17,17%5,TimeConverterConstants.YELLOW_LAMP));
        Assert.assertEquals("YYYY", timeConverter.getLightLamps(59,59%5,TimeConverterConstants.YELLOW_LAMP));
    }     

    /**
     * test time containing value beyond the range of HH or MM or SS.
     * 
     */
    @Test
    public void testInvalidTimeFomat1() {
    	Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("25:00:00"));
   
    } 
    
    /**
     * test time in hh:mm format
     * 
     */
    @Test
    public void testInvalidTimeFomat2() {
    	Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("23:00"));
   
    } 
    
    /**
     * test time in :: format
     */
    @Test
    public void testInvalidTimeFomat3() {
    	Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("::"));
   
    } 
    
    /**
     * test time containing alphabets.
     * 
     */
    @Test
    public void testInvalidTimeFomat4() {
    	Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("qwerty"));
   
    } 
    
    /**
     * test time containing alphabets in hh:mm:ss format.
     * 
     */
    @Test
    public void testInvalidTimeFomat5() {
    	Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("aa:bb:cc"));
   
    } 
    
    /**
     * test time in this format h:m:s
     */
    @Test
    public void testInvalidTimeFomat6() {
    	Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime("1:2:3"));
   
    } 
    /**
     * test empty time value
     * 
     */
    @Test
    public void testInvalidTimeFomat7() {
    	Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime(""));
   
    } 
    
    /**
     * test for time value as null
     */
    @Test
    public void testInvalidTimeFomat8() {
    	Assert.assertEquals(TimeConverterConstants.INVALID_TIME, timeConverter.convertTime(null));
   
    } 
    
    @When("the time is $time")
    public void whenTheTimeIs(String time) {
        theTime = time;
    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
       
        assertThat(timeConverter.convertTime(theTime)).isEqualTo(theExpectedBerlinClockOutput);
    	
    }
}
