public class Weather
{
    
    private String weatherCondition;
    private double temperature;

    /**
     * Constructor for objects of class Weather
     */
    public Weather()
    {
        weatherCondition = "Sunny";
        temperature = 20;
    }

    // Constructor
    public Weather(String weatherCond, double temp)
    {
        weatherCondition = weatherCond;
        temperature = temp;
    }

    // Accessor for weatherCondition
    public String getWeatherCondition()
    {
        return weatherCondition;
    }

    // Accessor for temperature
    public double getTemperature()
    {
        return temperature;
    }

    // Mutator for weatherCondition
    public void setWeatherCondition(String weatherCond)
    {
        if(weatherCond == "Sunny" || weatherCond == "Raining" || weatherCond == "Cloudy" || weatherCond == "Snowing")
        {
            weatherCondition = weatherCond;
        }
        else
        {
            weatherCondition = "Sunny";
        }
    }

    // Mutator for temperature
    public void setTemperature(double temp)
    {
        temperature = temp;
    }

    // Convert Celsius to fahrenheits
    public double fahrenheitTemperature()
    {
        double fahrenheit = (getTemperature() * 9/5) + 32;

        return fahrenheit;
    }

    // toString function
    public String toString()
    {
        String result = String.format("The current weather is %s and it is %.2f degrees celsius", getWeatherCondition(), getTemperature());

        return result;
    }

    


}
