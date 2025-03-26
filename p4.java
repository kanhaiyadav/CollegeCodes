import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/* 
 * <applet code="CalculatorApplet" width="350" height="200"></applet>
 */
public class CalculatorApplet extends Applet {
    private TextField firstNumberField;
    private TextField secondNumberField;
    private TextField resultField;
    private Button addButton;
    
    public void init() {
        // Set layout
        setLayout(new GridLayout(4, 2, 10, 10));
        
        // Create components
        Label firstNumberLabel = new Label("First Number:");
        firstNumberField = new TextField(10);
        
        Label secondNumberLabel = new Label("Second Number:");
        secondNumberField = new TextField(10);
        
        Label resultLabel = new Label("Sum:");
        resultField = new TextField(10);
        resultField.setEditable(false);
        
        addButton = new Button("Add");
        
        // Add action listener to the button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse text input to doubles
                    double num1 = Double.parseDouble(firstNumberField.getText());
                    double num2 = Double.parseDouble(secondNumberField.getText());
                    
                    // Calculate the sum
                    double sum = num1 + num2;
                    
                    // Display the result
                    resultField.setText(String.valueOf(sum));
                } catch (NumberFormatException ex) {
                    resultField.setText("Invalid input");
                }
            }
        });
        
        // Add components to the applet
        add(firstNumberLabel);
        add(firstNumberField);
        add(secondNumberLabel);
        add(secondNumberField);
        add(resultLabel);
        add(resultField);
        add(new Label()); // Empty label for spacing
        add(addButton);
    }
}