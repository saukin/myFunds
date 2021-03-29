
// BLUE PRINT


package MyFunds;

import java.util.Scanner;

public abstract class MyBank {
    private double startBal; //starting balance this month
    private double currBal; //current balance
    private double totalDep; //total of deposits this month
    private int numDep = 0; //number of deposits this month
    private double totalWithdraw; //total of withdrawals this month
    private int numWithdraw = 0; //number of withdrawals this month
    private final int FREEWITHDRAWALS = 4; //number of free withdrawals
    private double annRate; //annual rate
    private double sChargeM; //service charges this month
    private final double PENALTYCHARGE = 15; //service charge when withdrawal excess checking balance
    private boolean status; //status of account
    private double depAm; //deposit amount
    private double withAm; //withdrawal amount;
    private String accType = ""; //type of account
    private String statusName = ""; //status name for monthly report
    private double monthlyInterest; // monthly interest for report
    private double pChargeM; // saving field for penalty to avoid its double subtraction
    
    Scanner sc = new Scanner(System.in);
    
    // ----------------------------CONSTRUCTORS-------------------
    
    public MyBank() {
        
    }
    
    public MyBank(double startBal, double annRate) {
        currBal = startBal;
        this.annRate = annRate;
        this.startBal = startBal;
        totalDep = 0;
        numDep = 1;
        totalWithdraw = 0;
        numWithdraw = 0;
        sChargeM = 0;
        status = (startBal > 25);
    }
    
    // -----------------------SETTERS AND GETTERS-------------------
    
    
    
    public double getStartBal() {
        return startBal;
    }
    
    public void setStartBal(double startBal) {
        this.startBal = startBal;
    }
    
    public double getCurrBal() {
        return round(currBal);
    }
    
    public void setCurrBal(double currBal) {
        this.currBal = currBal;
    }
    
    public double getAnnRate() {
        return annRate;
    }
    
    public void setAnnRate(double annRate) {
        this.annRate = annRate;
    }
    
    public double getTotalDep() {
        return totalDep;
    }
    
    public void setTotalDep(double totalDep) {
        this.totalDep = totalDep;
    }
    
    public int getNumDep() {
        return numDep;
    }
    
    public void setNumDep(int numDep) {
        this.numDep = numDep;
    }
    
    public double getTotalWithdraw() {
        return totalWithdraw;
    }
    
    public void setTotalWithdraw(double totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }
    
    public int getNumWithdraw() {
        return numWithdraw;
    }
    
    public int getFREEWITHDRAWALS() {
        return FREEWITHDRAWALS;
    }
    
    public void setNumWithdraw(int numWithdraw) {
        this.numWithdraw = numWithdraw;
    }
    
    public double getSChargeM() {
        return sChargeM;
    }
    
    public double getPENALTYCHARGE() {
        return PENALTYCHARGE;
    }
    
    public void setSChargeM(double sChargeM) {
        this.sChargeM += sChargeM;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void setAccType(String accType) {
        this.accType = accType;
    }
    
    public String getAccType() {
        return accType;
    }
    
    public void setStatusName() {
        if (status) {
            statusName = "active";
        } else {
            statusName = "inactive";
        }
    }
    
    public String getStatusName() {
        return statusName;
    }
    
    public void setPChargeM(double pChargeM) {
        this.pChargeM += pChargeM;
    }
    
    public double getPChargeM() {
        return round(pChargeM);
    }
    
    public double getDepAm() {
        return depAm;
    }

    public void setDepAm() {
        this.depAm = depVal();
    }

    public double getWithAm() {
        return withAm;
    }

    public void setWithAm() {
        this.withAm = withVal();
    }
    
    
 // ------------------------- SERVICE METHODS --------------------------   

    // round function to display two decimal characters in double value
    public double round(double a) {
       return (double)Math.round(a*100d)/100d;
    }
    
    //print form for finishing deposits and withdrawals, it is used as "super" in subclasses
    public void printBalance() {
        System.out.println("Now balance of your " + accType + " is " + round(currBal) + "$");
    }
    
    
// --------------------------- VALIDATORS AND INPUT -------------------------------
    
    //validation of deposit value
    public double depVal() {
        double am;
        System.out.println("What amount do you want to deposit?");
        if (sc.hasNextDouble()) {
            am = sc.nextDouble();
        } else {
            am = 0; 
        }
        sc.nextLine();
        
        while (am <= 0) {
            System.out.println("Deposit must be a POSITIVE NUMBER");
            if (sc.hasNextDouble()) {
                am = sc.nextDouble();
            } else {
            am = 0;
            }
        sc.nextLine();    
        }
        return am;
    }
    
    //validation of withdrawal value
    public double withVal() {
        double am;
        System.out.println("What amount do you want to withdraw?");
        if (sc.hasNextDouble()) {
            am = sc.nextDouble();
        } else {
            am = 0; 
        }
        sc.nextLine();
        
        while (am <= 0) {
            System.out.println("Withdrawal must be a POSITIVE NUMBER");
            if (sc.hasNextDouble()) {
                am = sc.nextDouble();
            } else {
            am = 0;
            }
        sc.nextLine();    
        }
        return am;
    }


// --------------------------- ACTION METHODS --------------------------
    
    
    public void makeDep(double depAm) {
        currBal += depAm;
        totalDep += depAm;
        numDep++;
    }
    
    public void makeWithdraw(double withAm) {
        currBal -= withAm;
        totalWithdraw += withAm;
        numWithdraw++;
    }
    
    
    public void calculateInterest() {
        if (currBal >= 0) {                         // to avoid interest calculation for negative balance
            monthlyInterest = currBal*(annRate/12);
            currBal += monthlyInterest;
        }    
    }
    
    public void doMonthlyRep() {
        currBal -= sChargeM;
        calculateInterest();
        System.out.println("-----------------------------------------"
                         + "\n\t\t MONTHLY REPORT"
                         + "\n-----------------------------------------"
                         + "\n Starting balance \t\t" + round(startBal)
                         + "\n Total amount of deposit \t" + round(totalDep)
                         + "\n Number of deposits \t\t" + numDep
                         + "\n Total amount of withdrawals \t" + round(totalWithdraw)
                         + "\n Number of withdrawals \t\t" + numWithdraw
                         + "\n Service charges \t\t" + (round(sChargeM) + round(pChargeM))
                         + "\n Current balance \t\t" + round(currBal)
                         + "\n Monthly interest \t\t" + round(monthlyInterest));
        totalDep = 0;
        numDep = 0;
        totalWithdraw = 0;
        numWithdraw = 0;
        sChargeM = 0;
        pChargeM = 0;
        startBal = currBal;
    }
}
