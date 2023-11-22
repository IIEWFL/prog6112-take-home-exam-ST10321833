import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Nalawade, K. (2023) How to write unit tests in Java, freeCodeCamp.org. Available at: https://www.freecodecamp.org/news/java-unit-testing/ (Accessed: 22 November 2023). 
//Java unit testing with junit - tutorial - how to create and use unit tests (2022) YouTube. Available at: https://www.youtube.com/watch?v=vZm0lHciFsQ (Accessed: 22 November 2023).
//Grant, J. (2023) How to do unit testing in Java, Code Intelligence. Available at: https://www.code-intelligence.com/blog/how-to-do-unit-testing-in-java (Accessed: 22 November 2023). 

public class EstateAgentDataTest {

    @Test
    public void testConstructorAndGetters() {
      
        EstateAgentData estateAgentData = new EstateAgentData("Cape Town", "John Doe", 200000, 5);

     
        assertEquals("Cape Town", estateAgentData.agentLocation, "Agent location is incorrect");
        assertEquals("John Doe", estateAgentData.agentName, "Agent name is incorrect");
        assertEquals(200000, estateAgentData.propertyPrice, 0.01, "Property price is incorrect");
        assertEquals(5, estateAgentData.commissionPercentage, 0.01, "Commission percentage is incorrect");

        
        estateAgentData = new EstateAgentData("Durban", "Jane Smith", 150000, 7.5);

        
        assertEquals("Durban", estateAgentData.agentLocation, "Agent location is incorrect");
        assertEquals("Jane Smith", estateAgentData.agentName, "Agent name is incorrect");
        assertEquals(150000, estateAgentData.propertyPrice, 0.01, "Property price is incorrect");
        assertEquals(7.5, estateAgentData.commissionPercentage, 0.01, "Commission percentage is incorrect");
    }
}
