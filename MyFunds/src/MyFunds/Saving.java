
package MyFunds;


public class Saving extends MyBank {
    
    /* --------------------- CONSTRUCTORS ----------------------------- */
    
    public Saving(double startBal, double annRate) {
        super.setCurrBal(startBal);
        super.setAnnRate(annRate);
        super.setStartBal(startBal);
        super.setAccType("SAVING account");
        super.setStatus(startBal > 25);
        super.setStatusName();
        
    }
    
    // ------------------------ SERVICE METHODS ------------------------------
    
    
    // print form for "operation impossible" case 
    public void inactive() {
        System.out.println("Sorry, the operation cannot be done. "
                             + "\nYour account is inactive because of unsuffiсient balance."
                             + "\nThe balance must be more than 25$"
                             + "\nNow the balance of your saving account is " 
                             + super.getCurrBal());
    }
    
    // print form for "below 25" case
    public void printAttention() {
        System.out.println("ATTENTION! Your saving account balance is below "
                               + "25$. \nThe balance must be raised above 25$ to do  "
                               + "withdrawals  \n Next minimum deposit should be "  
                             + (double)Math.round((25.01 - super.getCurrBal())*100d)/100d 
                                     + "$");
    }
    
    /* -------------------- ACTION METHODS --------------------------- */
    
    
    public void makeWithdraw() {
        if (!super.getStatus()) {
            inactive();
        } else {
            super.setWithAm();
            if ((super.getCurrBal() - super.getWithAm()) >= 0) {
                super.makeWithdraw(super.getWithAm());
                if (super.getCurrBal() >= 25) {
                    super.setStatus(true);
                    super.printBalance();
                } else {
                    super.setStatus(false);
                    printAttention();
                }
            } else {
                System.out.println("Sorry, the operation cannot be done, because "
                        + "the balance will goes below zero.");
            }    
       }
    super.setStatusName();   //it is used only in "saving" class as long as there's no need in such a method in "checking" 
    }
    
    
    public void makeDep() {
        super.setDepAm();
        if ((super.getStatus()) || ((!super.getStatus()) && ((super.getCurrBal() + super.getDepAm()) > 25))) {
            super.makeDep(super.getDepAm());
            super.setStatus(super.getCurrBal() >= 25);
            super.printBalance();
        } else {
            super.makeDep(super.getDepAm());
            printAttention();
        }
    super.setStatusName();    
    }
    
    public void doMonthlyReport() {
        if (super.getNumWithdraw() > super.getFREEWITHDRAWALS()) {
            super.setSChargeM(1*(super.getNumWithdraw() - super.getFREEWITHDRAWALS()));
        }
        super.doMonthlyRep();
        System.out.println(" Account status \t\t" + super.getStatusName());
    }

}