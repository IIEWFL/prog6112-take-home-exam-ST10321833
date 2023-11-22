

package com.mycompany.iestateagent;

public interface IEstateAgent {
    double calculateTotalSales(double[] monthlySales);
    double calculateCommission(double totalSales);
    int findTopAgentIndex(double[] totalSales);
}
