
package MyFunds;


// MAIN CLASS


import java.util.Scanner;



public class Menu {
    
    Scanner sc = new Scanner(System.in);
    Saving s1 = new Saving(5, 0.02);
    Checking ch1 = new Checking(300, 0.001);
    

// ------------------------------ SERVICE METODS ---------------------------

    // print form for doSaving and doChecking methods
    
    public void printBalance(MyBank acc) {
        System.out.println("\n\n--------------------------------------"
                        + "\n  Balance of your " + acc.getAccType() + " is "  
                        + "\n--------------------------------------"
                        + "\n" + "\t\t" +  acc.getCurrBal() + "$  "
                        + "\n--------------------------------------");
    }
    
    
    //print form for checking and saving menu
    
    public void printCheckSavMenu() {
        System.out.println("Choose an option to continue: \nA: Deposit "
                + "\nB: Withdrawal \nC: Report \nD: Return to MyBank menu"
                + "\n--------------------------------------");
    }
    
    
    // exit method
    
    private void finish() {
        System.out.println("\nDo you want to make another operation? (y/n)");
        String f = sc.nextLine();
        while ((f.equalsIgnoreCase("y") == false) && (f.equalsIgnoreCase("n") == false)) {
            System.out.println("Press \"y\" or \"n\"");
            f = sc.nextLine();            
        }
        if (f.equalsIgnoreCase("y")) {
            perform();  
        } else {
            System.out.println("Thank you! See you next time!");
        }
    }

// ----------------------------- VALIDATORS -------------------------------
    
    // validation for main menu
    
    public String menuVal() {
        String p = sc.nextLine();
        while (((p.toUpperCase().equals("A") || p.toUpperCase().equals("B") ||
               p.toUpperCase().equals("C"))) == false) {
            System.out.println("The value is not correct. Please enter \"A\" or \"B\" "
                    + "or \"C\"");
            p = sc.nextLine();
        }
        return p.toUpperCase();
    }
    
    //validation for checking and saving menu
    
    public String checkSaveVal() {
        String p = sc.nextLine();
        while (((p.toUpperCase().equals("A") || p.toUpperCase().equals("B") ||
               p.toUpperCase().equals("C") || p.toUpperCase().equals("D"))) == false) {
            System.out.println("The value is not correct. Please enter \"A\" or \"B\" "
                    + "or \"C\" or \"D\"");
            p = sc.nextLine();
        }
        return p.toUpperCase();
    }
    

// ------------------------------ ACTION METHODS ---------------------------
    
    // main menu
    
    public void doMyBank() {
        System.out.println("\n\n  Welcome to MyBank app!\n"
                         + "\n------------------------"
                         + "\n        MAIN MENU        "
                         + "\n------------------------"
                         + "\nA: Saving \nB: Checking \nC: Exit"
                         + "\n------------------------");
        switch (menuVal()) {
            case "A": doSaving();
                      finish();
                      break;
            case "B": doChecking();
                      finish();
                      break;
            case "C": System.out.println("Thank you! See you next time!");
                      break;
            
        }
    }
    
    public void doSaving() {
        printBalance(s1);
        printCheckSavMenu();
        switch (checkSaveVal()) {
            case "A": s1.makeDep();
                      break;
            case "B": s1.makeWithdraw();
                      break;
            case "C": s1.doMonthlyReport();
                      break;
            case "D": doMyBank();
        }
    }
    
    public void doChecking() {
        printBalance(ch1);
        printCheckSavMenu();
        switch (checkSaveVal()) {
            case "A": ch1.makeDep();
                      break;
            case "B": ch1.makeWithdraw();
                      break;
            case "C": ch1.doMonthlyReport();
                      break;
            case "D": doMyBank();
        }
    }
    
    
    // ------------------------ PERFORM AND MAIN ---------------------------
    
    public void perform() {
        doMyBank();
    }
    
    public static void main(String []Args) {
        Menu doIt = new Menu();
        doIt.perform();
    }
    
}
