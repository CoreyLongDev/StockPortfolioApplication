import java.util.*;

/**
 * PortfolioManager.java
 * Main class for the program. manages transactions in the stock portfolio. 
 * Author: Corey Long
 * Date: Apr 27, 2028
 */

// IMPORTS


// START MAIN CLASS
public class PortfolioManager {

    private ArrayList<TransactionHistory> portfolioList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int userChoice = -1;
        // MENU SYSTEM
        do { 
            System.out.println("Corey Long Brokerage Account");
            System.out.println("0 - Exit");
            System.out.println("1 - Deposit Cash");
            System.out.println("2 - Withdraw Cash");
            System.out.println("3 - Buy Stock");
            System.out.println("4 - Sell Stock");
            System.out.println("5 - Display Transaction History");
            System.out.println("6 - Display Portfolio");
            System.out.print("Enter option (0 to 6) : ");

            if (scnr.hasNextInt()) {
                userChoice = scnr.nextInt();
            } else {
                System.out.println("Invalid Selection. Please try again with an option (0 to 6)");
                scnr.nextInt();
                continue;
            }
        } while (userChoice != 0);

        scnr.close();
    }
}