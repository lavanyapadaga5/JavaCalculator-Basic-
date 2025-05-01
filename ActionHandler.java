package basicClculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ActionHandler implements ActionListener {
    JTextField display;
    double num1, num2, result;
    char operator;
    boolean operatorPressed;

    public ActionHandler(JTextField display) {
        this.display = display;
        this.operatorPressed = false;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // If the button is a number (0-9)
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (operatorPressed) {
                display.setText(command);
                operatorPressed = false;
            } else {
                display.setText(display.getText() + command);
            }
        }
        // Clear (C) button
        else if (command.charAt(0) == 'c') {
            display.setText("");
            num1 = num2 = result = 0;
            operatorPressed = false;
        }
        // Equals (=) button
        else if (command.charAt(0) == '=') {
            if (operator != '\0' && !display.getText().isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                operatorPressed = false;
            }
        }
        // Operator buttons (+, -, *, /)
        else if ("+-*/".indexOf(command.charAt(0)) != -1) {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                operatorPressed = true;
            }
        }
    }
}
