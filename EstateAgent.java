
import com.mycompany.iestateagent.IEstateAgent;

//Arrays in Java (2023) GeeksforGeeks. Available at: https://www.geeksforgeeks.org/arrays-in-java/ (Accessed: 22 November 2023). 
//Abba, I.V. (2022) 2D array in java – two-dimensional and nested arrays, freeCodeCamp.org. Available at: https://www.freecodecamp.org/news/2d-array-in-java-two-dimensional-and-nested-arrays/ (Accessed: 22 November 2023). 
//2D array Java Tutorial #11 (2018) YouTube. Available at: https://www.youtube.com/watch?v=L3-q2GxAqZA (Accessed: 22 November 2023). 

public class EstateAgent implements IEstateAgent {
    String[] agents = {"Joe Bloggs", "Jane Doe"};
    double[][] monthlySales = {
            {800000, 1500000, 2000000},
            {700000, 1200000, 1600000}
    };

    @Override
    public double calculateTotalSales(double[] monthlySales) {
        double totalSales = 0;
        for (double sale : monthlySales) {
            totalSales += sale;
        }
        return totalSales;
    }

    @Override
    public double calculateCommission(double totalSales) {
        return 0.02 * totalSales;
    }

    @Override
    public int findTopAgentIndex(double[] totalSales) {
        double maxSales = totalSales[0];
        int topAgentIndex = 0;

        for (int i = 1; i < totalSales.length; i++) {
            if (totalSales[i] > maxSales) {
                maxSales = totalSales[i];
                topAgentIndex = i;
            }
        }

        return topAgentIndex;
    }

    public static void main(String[] args) {
        EstateAgent estateAgent = new EstateAgent();

        double[] agentTotalSales = new double[estateAgent.agents.length];
        double[] agentTotalCommission = new double[estateAgent.agents.length];

        // Calculate total sales and commission for each estate agent
        for (int i = 0; i < estateAgent.agents.length; i++) {
            agentTotalSales[i] = estateAgent.calculateTotalSales(estateAgent.monthlySales[i]);
            agentTotalCommission[i] = estateAgent.calculateCommission(agentTotalSales[i]);

            System.out.println(estateAgent.agents[i] + " Total Sales: R " + agentTotalSales[i]);
            System.out.println(estateAgent.agents[i] + " Total Commission: R " + agentTotalCommission[i]);
        }

        
        int topAgentIndex = estateAgent.findTopAgentIndex(agentTotalSales);
        System.out.println("\nTop Selling Estate Agent: " + estateAgent.agents[topAgentIndex]);
    }
}