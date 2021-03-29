
package MyFunds;


public class Checking extends MyBank {
    
    /* --------------------- CONSTRUCTORS ----------------------------- */
    
    public Checking(double startBal, double annRate) {
        super.setCurrBal(startBal);
        super.setAnnRate(annRate);
        super.setStartBal(startBal);
        super.setStatus(true);
        super.setAccType("CHECKING account");
    }
    
       
    /* -------------------- ACTION METHODS --------------------------- */
    
   public void makeWithdraw() {
        if (super.getCurrBal() > 0) {
            super.setWithAm();
            if ((super.getCurrBal() - super.getWithAm()) >= 0 ) {
                super.makeWithdraw(super.getWithAm());
                super.printBalance();
            } else {
                super.setCurrBal(super.getCurrBal() - super.getPENALTYCHARGE());
                super.setPChargeM(getPENALTYCHARGE());
                System.out.println("ATTENTION! The operation has not been done"
                            + ", \nbecause the balance goes below ZERO. Service charge "
                            + "\nof " + super.getPENALTYCHARGE() + "$ has been taken from the balance. Now balance of "
                            + "\n your checking account = " + super.getCurrBal() + "$");
                
            }
        } else {
            System.out.println("Operation cannot be done, because the balance is less "
                + "\n or equal to ZERO");
        }
    }
    
    public void makeDep() {
        super.setDepAm();
        if ((super.getCurrBal() + super.getDepAm()) < 0) {
                super.makeDep(super.getDepAm());
                System.out.println("ATTENTION! The balance of your checking account"
                        + "is still below ZERO. It is " + super.getCurrBal() + "$ now.");
        } else {
            super.makeDep(super.getDepAm());
            super.printBalance();
        }
    }
    
    public void doMonthlyReport() {
        
        super.setSChargeM(5 + 0.1*super.getNumWithdraw());
        super.doMonthlyRep();
    }
}
