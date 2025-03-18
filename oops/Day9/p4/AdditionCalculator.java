import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdditionCalculator extends JFrame implements ActionListener {
    // Declare components
    private JTextField firstNumberField, secondNumberField, sumField;
    private JLabel firstNumberLabel, secondNumberLabel, sumLabel;
    private JButton addButton;
    
    public AdditionCalculator() {
        // Set up the JFrame
        setTitle("Addition Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Create components
        firstNumberLabel = new JLabel("First Number:");
        firstNumberField = new JTextField(10);
        
        secondNumberLabel = new JLabel("Second Number:");
        secondNumberField = new JTextField(10);
        
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        
        sumLabel = new JLabel("Sum:");
        sumField = new JTextField(10);
        sumField.setEditable(false); // Make sum field read-only
        
        // Add components to the frame
        add(firstNumberLabel);
        add(firstNumberField);
        add(secondNumberLabel);
        add(secondNumberField);
        add(addButton);
        add(sumLabel);
        add(sumField);
        
        // Center the frame on the screen
        setLocationRelativeTo(null);
        
        // Display the frame
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                // Get the input values
                double firstNumber = Double.parseDouble(firstNumberField.getText());
                double secondNumber = Double.parseDouble(secondNumberField.getText());
                
                // Calculate the sum
                double sum = firstNumber + secondNumber;
                
                // Display the result
                sumField.setText(String.valueOf(sum));
            } catch (NumberFormatException ex) {
                // Handle invalid input
                JOptionPane.showMessageDialog(this, 
                    "Please enter valid numbers in both fields.", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        // Create the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdditionCalculator();
            }
        });
    }
}