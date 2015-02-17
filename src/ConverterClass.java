
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/*
 * You are permitted to copy, distribute and/or modify this software or its source code.
 * There is no warranty for this program, implying that in no event required by applicable
 * law or agreed to in writing will the copyright owner be liable to you for damages.
 * That said, I do agree to any and all fun and awsomity this product might cause. Have Fun!:-)
 */

/**
 *
 * @author Witwicky
 */
public class ConverterClass {
    
    //create object and reference to class currency
    CurrencyClass Currency = new CurrencyClass();
    
    //declare variables to use.
    int test;
    double dollarCurrency;
    double euroCurrency;
    double poundCurrency;
    long additive = 1;
    String currencyType;
    String input;
    String stringValue;
    double currencyValue;
    //double dollarRate = 91.6683;
    //double euroRate = 103.7058;
    //double poundRate = 137.7058;
    Boolean error = false;
    
    //method to check currency type
    public void checkCurrency() {
        if(input.endsWith("$") || input.endsWith("d")) {
            currencyType = "Dollar";
            stringValue = input.substring(0, (input.length() - 1));
        } //end of dollar if
        
        else if(input.endsWith("#") || input.endsWith("e") || input.endsWith("€")) {
            currencyType = "Euro";
            stringValue = input.substring(0, (input.length() - 1));
        } //end of euro if
        
        else if(input.endsWith("&") || input.endsWith("p") || input.endsWith("£")) {
            currencyType = "Pound";
            stringValue = input.substring(0, (input.length() - 1));
        } //end of pound if
        
        else {
            JOptionPane.showMessageDialog(null, "No valid currency letter found. Please enter value again", "Error Message",ERROR_MESSAGE );
            error = true;
        } //end of else
    } //end of method to check currency.
    
    //method to check and set the additive
    public void setAdditive() {
        //convert char to string
        //if condition to split the last character.
        if(input.charAt((input.length() - 2)) == 'k') {
            //get currency string and convert to integer
            stringValue = input.substring(0, (input.length() - 2));
            additive = 1000;
        } //end of first if
        
        else if(input.charAt((input.length() - 2)) == 'm') {
            //get currency string, last letter and convert to integer
            stringValue = input.substring(0, (input.length() - 2));
            additive = 1000000;
        } //end of second if
        
        else if(input.charAt((input.length() - 2)) == 'b') {
            stringValue = input.substring(0, (input.length() - 2));
            additive = 1000000000;
        } //end of third if
        
        else if(input.charAt((input.length() - 2)) == 't') {
            stringValue = input.substring(0, (input.length() - 2));
            additive = 1000000000;
        } //end of trillion if
    } //end of method check Additive
    
    //method to convert string to integer
    public void setValue() {
        try {
            currencyValue = Double.parseDouble(stringValue);
        }
        catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Please ensure you have entered numbers only", "Input Mismatch", ERROR_MESSAGE);
            error = true;
        }
    } //end of method setValue
    
    //method to check which currency to use and convert
    public void convert() {
        //method to convert kenyan value to set desired currency
        switch (currencyType) {
        //end of dollar switch
            case "Dollar":
                currencyValue *= Currency.getCurrency(currencyType);
                break;
        //end of dollar switch
            case "Euro":
                currencyValue *= Currency.getCurrency(currencyType);
                break;
        //end of euro switch
            case "Pound":
                currencyValue *= Currency.getCurrency(currencyType);
                break;
        //end of pound switch
            default:
                JOptionPane.showMessageDialog(null, "An unknown problem has occured", "Error", INFORMATION_MESSAGE);
                error = true;
                break; //end of default.
        } //end of switch
        
        //include the additive
        currencyValue *= additive;
        
    } //end of convert method
    
    //method to convert currency to string
    public String toString() {
        return String.format("%.2f", currencyValue);
    } //end of toString method
} //end of converter class
