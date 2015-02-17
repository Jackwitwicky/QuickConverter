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

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public class CurrencyClass {
    //currency to create files to contain currency rates of dollar
    //euro and pound
    //and provide methods to allow them to be set
    
    //Scanner to read values from the file
    Scanner scannerInput;
    
    //Formatter to write files to the file
    Formatter formatterInput;
    
    //string variables to hold the various currencies
    String dollarString;
    String euroString;
    String poundString;
    double tempDollarRate = 91.5422;
    double tempEuroRate = 104.6697;
    double tempPoundRate = 141.1111;
    double dollarRate;
    double euroRate;
    double poundRate;
    
    //file variables
    String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Converter Application";
    String dollarPath = path + File.separator + "dollarFile.txt";
    String euroPath = euroPath = path + File.separator + "euroFile.txt";;
    String poundPath = poundPath = path + File.separator + "poundFile.txt";;
    String name = System.getProperty("user.name");
    String welcomeMessage = "\tWelcome back, " + name + "." +
            "\nThe necessary files have been loaded successfully.";
    String firstRunMessage = "Hi " + name + ",\n" + 
            "It appears this is the first time running this application.\nSome necessary files must be created before we start.";
    File dollarFile;
    File euroFile;
    File poundFile;
    
    Boolean scannerInputOpen = false;
    Boolean formatterInputOpen = false;
    
    Boolean dialog = false;
    
    //check if the folder exists and create it if not
    public Boolean checkFolder() {
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Converter Application";
        File customDir = new File(path);
        
        if(customDir.exists()) {
            JOptionPane.showMessageDialog(null, welcomeMessage, "Welcome", INFORMATION_MESSAGE);
        }
        else {
            int showConfirmDialog = JOptionPane.showConfirmDialog(null, firstRunMessage, "First Run", OK_CANCEL_OPTION,INFORMATION_MESSAGE);
            //perform operation that has been chosen
            if(showConfirmDialog == 0) {
                customDir.mkdirs();
            dollarFile = new File(dollarPath);
            euroFile = new File(euroPath);
            poundFile = new File(poundPath);
            
            //try to create the files
            try{
                dollarFile.createNewFile();
                euroFile.createNewFile();
                poundFile.createNewFile();
                setCurrency("Dollar", (Double.toString(tempDollarRate)));
                setCurrency("Euro", (Double.toString(tempEuroRate)));
                setCurrency("Pound", (Double.toString(tempPoundRate)));
                JOptionPane.showMessageDialog(null, "The necessary files have successfully been created.\nThe currency rates have been set as per 13/2/15.\nPleas update them as soon as possible", "All Done", INFORMATION_MESSAGE);
                
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, "The files could not be created, you could try doing it manually.", "Unfortunate Error", ERROR_MESSAGE);
            } //end of catch block
            
            //open dialog
            int showConfirmDialog2 = JOptionPane.showConfirmDialog(null, "Would you like to open the Application Guide?", "Open Guide", OK_CANCEL_OPTION,INFORMATION_MESSAGE);
            if(showConfirmDialog2 == 0) {
                dialog = true;
            }

            } //end of if selection was okay
            else {
                JOptionPane.showMessageDialog(null, "The necessary files are needed to run. The application will now exit.", "Goodbye", INFORMATION_MESSAGE);
                System.exit(0);
            } //else the selection was no
            
        } //if folder does not exist
        
        return dialog;
    } //end of method check folder
    
    
    //method to open the file
    public void openFile(String currencyType) {
        //try to open the file
        try {
            if(currencyType.equals("Dollar")) {
                scannerInput = new Scanner(new File(dollarPath));
                scannerInputOpen = true;
            }
            else if(currencyType.equals("Euro")) {
                scannerInput = new Scanner(new File(euroPath));
                scannerInputOpen = true;
            }
            else if(currencyType.equals("Pound")) {
                scannerInput = new Scanner(new File(poundPath));
                scannerInputOpen = true;
            } //end of if to set the file
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "This is embarrassing, the file could not be opened", ":-(", ERROR_MESSAGE);
        }
    }
    
    //method to read the currencies in the file
    public void readFile(String currencyType) {
        if(currencyType.equals("Dollar")) {
            dollarString = scannerInput.next();
        } //end of dollar if
        else if(currencyType.equals("Euro")) {
                euroString = scannerInput.next();
        } //end of euro if
        else if(currencyType.equals("Pound")) {
                poundString = scannerInput.next();
        } //end of pound if
    } //end of method read file
    
    //method to write new currencies to the file
    public void writeFile(String currencyType) {
        //try to write to file
        try {
            if(currencyType.equals("Dollar")) {
                formatterInput = new Formatter(dollarPath);
                formatterInputOpen = true;
            }
            else if(currencyType.equals("Euro")) {
                formatterInput = new Formatter(euroPath);
                formatterInputOpen = true;
            }
            else if(currencyType.equals("Pound")) {
                formatterInput = new Formatter(poundPath);
                formatterInputOpen = true;
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "This is embarrassing, the file could not be written to.", ":-(", ERROR_MESSAGE);
        } //end of try and catch
    } //end of method write file
    
    //method to add records to the file
    public void addRecord(String newCurrency) {
        formatterInput.format("%s", newCurrency);
    }
    
    //method to close the file
    public void closeFile() {
        if(scannerInputOpen) {
            scannerInput.close();
        }
        if(formatterInputOpen) {
            formatterInput.close();
        }
    } //end of method to close files
    //method to set the currencies.
    public Boolean setCurrency(String currencyType, String newCurrency) {
        double tempCurrency;
        Boolean error = false;
        //try to convert new currency to double before adding it to file
        try {
            tempCurrency = Double.parseDouble(newCurrency);
            writeFile(currencyType);
            addRecord(newCurrency);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "The input is not a proper number.", "Input Error", ERROR_MESSAGE);
            error = true;
        } //end of catch block
        
        //close the file
        closeFile();
        
        return error;
    } //end method to set the currency
    
    //method to get the currencies
    public double getCurrency(String currencyType) {
        //hold the new currency to return
        double tempCurrency = 0.0;
        openFile(currencyType);
        readFile(currencyType);
        
        //parse the strings to double
        if(currencyType.equals("Dollar")) {
            dollarRate = Double.parseDouble(dollarString);
            tempCurrency = dollarRate;
        }
        else if(currencyType.equals("Euro")) {
            euroRate = Double.parseDouble(euroString);
            tempCurrency = euroRate;
        }
        else if(currencyType.equals("Pound")) {
            poundRate = Double.parseDouble(poundString);
            tempCurrency = poundRate;
        } //end of if
        
        //close any open files
        closeFile();
        return tempCurrency;
    } //end of get method
}
