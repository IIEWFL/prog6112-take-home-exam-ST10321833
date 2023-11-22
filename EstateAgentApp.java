import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; James Hartman and Hartman, J. (2023) Java Swing Tutorial: How to create a GUI application in Java, Guru99. Available at: https://www.guru99.com/java-swing-gui.html (Accessed: 22 November 2023). 
//NetBeans, A. (no date) Apache NetBeans 19, Introduction to GUI Building. Available at: https://netbeans.apache.org/tutorial/main/kb/docs/java/gui-functionality/ (Accessed: 22 November 2023). 
//Swing in java: Creating GUI using Java Swing (2022) Edureka. Available at: https://www.edureka.co/blog/java-swing/ (Accessed: 22 November 2023). 

interface IEstateAgent {
    double calculateCommission(String propertyPrice, String agentCommission);

    boolean validateData(EstateAgentData dataToValidate);
}

class EstateAgentData {
    String agentLocation;
    String agentName;
    double propertyPrice;
    double commissionPercentage;

    EstateAgentData(String agentLocation, String agentName, double propertyPrice, double commissionPercentage) {
        this.agentLocation = agentLocation;
        this.agentName = agentName;
        this.propertyPrice = propertyPrice;
        this.commissionPercentage = commissionPercentage;
    }
}

class EstateAgent implements IEstateAgent {
    @Override
    public double calculateCommission(String propertyPrice, String agentCommission) {
        double price = Double.parseDouble(propertyPrice);
        double commission = Double.parseDouble(agentCommission);
        return (commission / 100) * price;
    }

    @Override
    public boolean validateData(EstateAgentData dataToValidate) {
        return !dataToValidate.agentLocation.isEmpty() &&
               !dataToValidate.agentName.isEmpty() &&
               dataToValidate.propertyPrice > 0 &&
               dataToValidate.commissionPercentage > 0;
    }
}

public class EstateAgentApp extends JFrame {
    private JTextField agentNameField, propertyPriceField, commissionPercentageField;
    private JComboBox<String> locationComboBox;
    private JTextArea reportTextArea;

    public EstateAgentApp() {
        setTitle("Estate Agent Commission App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createComponents();
        addComponents();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createComponents() {
        agentNameField = new JTextField();
        propertyPriceField = new JTextField();
        commissionPercentageField = new JTextField();
        reportTextArea = new JTextArea();
        locationComboBox = new JComboBox<>(new String[]{"Cape Town", "Durban", "Pretoria"});

        createMenu();
    }

    private void addComponents() {
        setLayout(new GridLayout(2, 1));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Agent Location:"));
        inputPanel.add(locationComboBox);
        inputPanel.add(new JLabel("Agent Name:"));
        inputPanel.add(agentNameField);
        inputPanel.add(new JLabel("Property Price:"));
        inputPanel.add(propertyPriceField);
        inputPanel.add(new JLabel("Commission Percentage:"));
        inputPanel.add(commissionPercentageField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton processButton = new JButton("Process Report");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processReport();
            }
        });
        buttonPanel.add(processButton);

        add(inputPanel);
        add(buttonPanel);

        reportTextArea.setEditable(false);
        add(new JScrollPane(reportTextArea));
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processReportMenuItem = new JMenuItem("Process Report");
        processReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processReport();
            }
        });
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        JMenuItem saveReportMenuItem = new JMenuItem("Save Report");
        saveReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReport();
            }
        });

        toolsMenu.add(processReportMenuItem);
        toolsMenu.add(clearMenuItem);
        toolsMenu.add(saveReportMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
    }

    private void processReport() {
        String agentLocation = (String) locationComboBox.getSelectedItem();
        String agentName = agentNameField.getText();
        String propertyPriceStr = propertyPriceField.getText();
        String commissionPercentageStr = commissionPercentageField.getText();

        try {
            double propertyPrice = Double.parseDouble(propertyPriceStr);
            double commissionPercentage = Double.parseDouble(commissionPercentageStr);

            EstateAgent estateAgent = new EstateAgent();
            EstateAgentData inputData = new EstateAgentData(agentLocation, agentName, propertyPrice, commissionPercentage);

            if (estateAgent.validateData(inputData)) {
                double commission = estateAgent.calculateCommission(propertyPriceStr, commissionPercentageStr);
                String report = "Agent Location: " + agentLocation +
                                "\nAgent Name: " + agentName +
                                "\nProperty Price: " + propertyPriceStr +
                                "\nCommission Percentage: " + commissionPercentageStr +
                                "\nCommission Earned: " + commission;
                reportTextArea.setText(report);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check the data and try again.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format. Please enter valid numbers.");
        }
    }

    private void saveReport() {
        String report = reportTextArea.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write(report);
            JOptionPane.showMessageDialog(this, "Report saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving the report.");
        }
    }

    private void clearFields() {
        agentNameField.setText("");
        propertyPriceField.setText("");
        commissionPercentageField.setText("");
        reportTextArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EstateAgentApp();
            }
        });
    }
}
