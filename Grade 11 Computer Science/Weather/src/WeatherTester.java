import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WeatherTester {
    @Test
    public void testWeather() {
        Weather testWeather0 = new Weather(); // creating Weather object with default constructors
        Weather testWeather1 = new Weather("Snowing", -10.00); // creating Weather object with given constructors
        //testing testWeather0
        assertEquals("Sunny", testWeather0.getWeatherCondition());
        assertEquals("The current weather is Sunny and it is 20.00 degrees celsius", testWeather0.toString());
        assertTrue((testWeather0.getTemperature() == 20.00)); // testing all accessors and the variable returned
        assertTrue((testWeather0.fahrenheitTemperature() == 68.0));

        testWeather0.setWeatherCondition("Cloudy"); // testing setters
        testWeather0.setTemperature(40.00);
        assertTrue((testWeather0.getTemperature() == 40.00)); // testing new variables after altering some
        assertTrue((testWeather0.fahrenheitTemperature() == 104.00));
        assertEquals("Cloudy", testWeather0.getWeatherCondition());
        assertEquals("The current weather is Cloudy and it is 40.00 degrees celsius", testWeather0.toString());
        // testing testWeather1
        assertTrue((testWeather1.getTemperature() == -10.00)); // testing all accessors and the variable returned
        assertTrue((testWeather1.fahrenheitTemperature() == 14.00));
        assertEquals(testWeather1.getWeatherCondition(), "Snowing");
        assertEquals( "The current weather is Snowing and it is -10.00 degrees celsius", testWeather1.toString());
        testWeather1.setWeatherCondition("Raining"); // testing setters
        testWeather1.setTemperature(-5.00);
        assertTrue((testWeather1.getTemperature() == -5.00)); // testing new variables after altering some
        assertTrue((testWeather1.fahrenheitTemperature() == 23.00));
        assertEquals("Raining", testWeather1.getWeatherCondition());
        assertEquals("The current weather is Raining and it is -5.00 degrees celsius", testWeather1.toString());
        testWeather1.setWeatherCondition("*hits keyboard* se4bv8e7oys");
        assertEquals("Sunny", testWeather1.getWeatherCondition()); // testing validity checker and default setter
    }
}
