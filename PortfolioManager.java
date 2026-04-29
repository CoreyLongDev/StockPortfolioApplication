// Author: Corey Long
// Date: Apr 27, 2026


// IMPORTS
import java.util.*;

// START MAIN CLASS
public class PortfolioManager {

    private static ArrayList<TransactionHistory> portfolioList = new ArrayList<>();

    private static String getCurrentDate() {
        java.time.LocalDate today = java.time.LocalDate.now();
        return today.getMonthValue() + "/" + today.getDayOfMonth() + "/" + today.getYear();
    }
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

            try {
                userChoice = Integer.parseInt(scnr.nextLine().trim());

                switch (userChoice) {
                    // DEPOSIT
                    case 1:
                        System.out.println("");
                        System.out.print(" - Enter deposit amount: ");
                        double depositAmount = Double.parseDouble(scnr.nextLine());

                        TransactionHistory deposit = new TransactionHistory(
                            "CASH",
                            getCurrentDate(),
                            "DEPOSIT",
                            depositAmount,
                            1.00
                        );
                        portfolioList.add(deposit);
                        System.out.println("SUCCESS!");
                        System.out.println(depositAmount + " has been deposited into your account.\n");

                        break;
                    // WITHDRAW
                    case 2:
                        System.out.println("");
                        System.out.print(" - Enter withdrawal amount: ");
                        double withdrawAmount = Double.parseDouble(scnr.nextLine());

                        TransactionHistory withdraw = new TransactionHistory(
                            "CASH",
                            getCurrentDate(),
                            "WITHDRAW",
                            withdrawAmount,
                            1.00
                        );
                        portfolioList.add(withdraw);
                        System.out.println("SUCCESS!");
                        System.out.println(withdrawAmount + " has been withdrawn from your account.\n");
                        break;
                    // STOCK - BUY
                    case 3:
                        System.out.println("");
                        System.out.print("");

                        break;

                    // STOCK - SELL
                    case 4:
                        System.out.println("");
                        System.out.println(" - choice Four has been made.");
                        System.out.println("");
                        break;

                    // PRINT TRANSACTION HISTORY
                    case 5:
                        System.out.println("");
                        System.out.println("Date\t\tTicker\tQuantity\tCost Basis\tTrans Type");
                        System.out.println("===================================================================");
                        for (TransactionHistory t : portfolioList) {
                            System.out.println("\n" + t.getTransDate() + "\t" + t.getTicker() + "\t " + t.getQty() + "\t\t" + t.getCostBasis() + "\t\t" + t.getTransType());
                        }
                        break;

                    // PRINT PORTFOLIO
                    case 6:
                        System.out.println("");
                        System.out.println(" - choice Six has been made.");
                        System.out.println("");
                        break;

                    default:
                        System.out.println("");
                        System.out.print(" - Invalid Selection, Please choose an option (0 to 6) : ");
                        System.out.println("");
                } 
            } catch (NumberFormatException e) {
                    System.out.println("");
                    System.out.print(" - Invalid Selection, Please Try Again.");
                    System.out.println("");
                    System.out.println("");
            }
        
        // EXIT SYSTEM
        } while (userChoice != 0);

        scnr.close();
    }
}