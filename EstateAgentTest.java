import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Nalawade, K. (2023) How to write unit tests in Java, freeCodeCamp.org. Available at: https://www.freecodecamp.org/news/java-unit-testing/ (Accessed: 22 November 2023). 
//Java unit testing with junit - tutorial - how to create and use unit tests (2022) YouTube. Available at: https://www.youtube.com/watch?v=vZm0lHciFsQ (Accessed: 22 November 2023). 
//Grant, J. (2023) How to do unit testing in Java, Code Intelligence. Available at: https://www.code-intelligence.com/blog/how-to-do-unit-testing-in-java (Accessed: 22 November 2023). 

public class EstateAgentTest {

    @Test
    public void testCalculateCommission() {
        EstateAgent estateAgent = new EstateAgent();

       
        double commission = estateAgent.calculateCommission("100000", "5");
        assertEquals(5000.0, commission, 0.01, "Commission calculation is incorrect");

       
        commission = estateAgent.calculateCommission("150000", "7.5");
        assertEquals(11250.0, commission, 0.01, "Commission calculation is incorrect");

       
        commission = estateAgent.calculateCommission("200000", "0");
        assertEquals(0.0, commission, 0.01, "Commission calculation is incorrect");

      
        commission = estateAgent.calculateCommission("1000000000", "10");
        assertEquals(100000000.0, commission, 0.01, "Commission calculation is incorrect");
    }

    @Test
    public void testValidateData() {
        EstateAgent estateAgent = new EstateAgent();

      
        EstateAgentData validData = new EstateAgentData("Cape Town", "John Doe", 200000, 5);
        assertTrue(estateAgent.validateData(validData), "Data validation failed for valid data");

       
        EstateAgentData invalidData1 = new EstateAgentData("", "John Doe", 200000, 5);
        assertFalse(estateAgent.validateData(invalidData1), "Data validation passed for empty location");

     
        EstateAgentData invalidData2 = new EstateAgentData("Cape Town", "", 200000, 5);
        assertFalse(estateAgent.validateData(invalidData2), "Data validation passed for empty name");

       
        EstateAgentData invalidData3 = new EstateAgentData("Cape Town", "John Doe", -200000, 5);
        assertFalse(estateAgent.validateData(invalidData3), "Data validation passed for negative property price");

       
        EstateAgentData invalidData4 = new EstateAgentData("Cape Town", "John Doe", 200000, -5);
        assertFalse(estateAgent.validateData(invalidData4), "Data validation passed for negative commission percentage");
    }
}
