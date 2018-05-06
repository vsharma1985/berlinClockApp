package com.ubs.opsit.interviews;

public interface TimeConverter {

    public String convertTime(String time);
    public String getLightLamps(int timeUnitValue);
    public String getLightLamps(int timeUnitValue, int numericValue,String strLamp);
    public String getLightLamps(int timeUnitValue, int numericValue,String strRedLamp, String strYellowLamp);

}
