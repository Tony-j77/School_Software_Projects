//Tony Jiang
//Computer Science 20 
//Henry Wise Wood High School
//2020-2021 semester 1

public class Weather {
    
    private String weatherCondition; 
    private double temperature;


    public Weather() {
        weatherCondition = "Sunny";
        temperature = 20;
    }

    public Weather(String weatherCond, double temp) {
        weatherCondition = weatherCond;
        temperature = temp;
    }

    public String getWeatherCondition() { //accessor for private weatherCondition (don't know why it's private in the first place)
        return weatherCondition;
    }

    
    public double getTemperature() { //accessor for private temperature 
        return temperature;
    }

    public void setWeatherCondition(String weatherCond) {
        if(weatherCond == "Sunny" || weatherCond == "Cloudy" || weatherCond == "Raining" || weatherCond == "Snowing") {
            weatherCondition = weatherCond;
        }
        else {
            weatherCondition = "Sunny";
        }
    }

    public void setTemperature(double temp) { 
        temperature = temp;
    }

    public double fahrenheitTemperature() {
        double fahrenheit = (getTemperature() * 9/5) + 32;  //turns c to f, but the return is wrong... whatever...
        return fahrenheit;
    }

    public String toString() {
        String result = String.format("The current weather is %s and it is %.2f degrees celsius", getWeatherCondition(), getTemperature()); // this is the easiest way to be sure all values have 2 decimals rather than converting them using DecimalFormat("#.00")
        return result;    
    }


}
