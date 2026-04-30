// Author: Corey Long
// Date: Apr 27, 2026


// IMPORTS
import java.util.*;

// START MAIN CLASS
public class PortfolioManager {

    private static ArrayList<TransactionHistory> portfolioList = new ArrayList<>();
    private static ArrayList<String> TickerCollector = new ArrayList<>();

    private static double getCashBalance() {
        double balance = 0.00;
        for (TransactionHistory t : portfolioList) {
            if (t.getTicker().equals("CASH")) {
                balance += t.getQty();
            }
        }
        return balance;
    }

    private static double getStockShare(String ticker) {
        double shares = 0.00;
        for (TransactionHistory t : portfolioList) {
            if (t.getTicker().equals(ticker)) {
                shares += t.getQty();
            }
        }
        return shares;
    }

    private static String getCurrentDate() {
        java.time.LocalDate today = java.time.LocalDate.now();
        return today.getMonthValue() + "/" + today.getDayOfMonth() + "/" + today.getYear();
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int userChoice = -1;
        double cashBalance;
        double shareBalance;
        String userTicker;
        double userQty;
        double userSharePrice;

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
                        cashBalance = getCashBalance();

                        // CHECK FUNDS AVAILABILITY
                        if (cashBalance < withdrawAmount) {
                            System.out.println("ERROR: Insufficient funds to complete this transaction.");
                            System.out.println("Transaction cancelled.\n");
                            break;
                        }

                        TransactionHistory withdraw = new TransactionHistory(
                            "CASH",
                            getCurrentDate(),
                            "WITHDRAW",
                            -withdrawAmount,
                            1.00
                        );
                        portfolioList.add(withdraw);
                        System.out.println("SUCCESS!");
                        System.out.println(withdrawAmount + " has been withdrawn from your account.\n");
                        break;

                    // STOCK - BUY
                    case 3:
                        System.out.print(" - Enter the Stock Ticker: ");
                        userTicker = scnr.nextLine().toUpperCase();
                        System.out.print(" - Enter the Quantity: ");
                        userQty = Double.parseDouble(scnr.nextLine());
                        System.out.print(" - Enter price per share: ");
                        userSharePrice = Double.parseDouble(scnr.nextLine());
                        System.out.println("");
                        double totalCost = userQty * userSharePrice;
                        cashBalance = getCashBalance();

                        // CHECK FUNDS AVAILABILITY
                        if (cashBalance < totalCost) {
                            System.out.println("ERROR: Insufficient funds to complete this transaction.");
                            System.out.println("Transaction cancelled.\n");
                            break;
                        }
                        // BUY STOCK
                        TransactionHistory stock = new TransactionHistory(
                            userTicker,
                            getCurrentDate(),
                            "BUY",
                            userQty,
                            userSharePrice
                        );

                        // SUBTRACT STOCK PRICE FROM CASH BALANCE
                        portfolioList.add(stock);
                        TransactionHistory cashOut = new TransactionHistory(
                            "CASH",
                            getCurrentDate(),
                            "WITHDRAW",
                            -totalCost,
                            1.00
                        );

                        portfolioList.add(cashOut);
                        System.out.println("SUCCESS!");
                        System.out.println(userQty + " shares of " + userTicker + " have been purchased at $" + userSharePrice + " per share.\n");
                        System.out.println("Total cost of this transaction: $" + totalCost + "\n");

                        break;

                    // STOCK - SELL
                    case 4:
                        System.out.print(" - Which stock would you like to sell? (Enter Ticker): ");
                        userTicker = scnr.nextLine().toUpperCase();
                        System.out.print(" - Quantity to sell: ");
                        userQty = Double.parseDouble(scnr.nextLine());
                        System.out.print(" - Enter value per share: ");
                        userSharePrice = Double.parseDouble(scnr.nextLine());
                        System.out.println("");
                        double totalSale = userQty * userSharePrice;
                        shareBalance = getStockShare(userTicker);
                        
                        // CHECK SHARE AVAILABILITY
                        if (shareBalance < userQty) {
                            System.out.println("ERROR: Insufficient shares to complete this transaction.");
                            System.out.println("Transaction cancelled.\n");
                            break;
                        }

                        // SELL STOCK
                        TransactionHistory stockOut = new TransactionHistory(
                            userTicker,
                            getCurrentDate(),
                            "SELL",
                            -userQty,
                            userSharePrice
                        );

                        portfolioList.add(stockOut);
                        TransactionHistory cashIn = new TransactionHistory(
                            "CASH",
                            getCurrentDate(),
                            "DEPOSIT",
                            totalSale,
                            1.00
                        );
                        portfolioList.add(cashIn);
                        System.out.println("SUCCESS!");
                        System.out.println(userQty + " shares of " + userTicker + " have been sold at $" + userSharePrice + " per share.\n");
                        System.out.println("Total value of this transaction: $" + totalSale + "\n");

                        break;

                    // PRINT TRANSACTION HISTORY
                    case 5:
                        System.out.println("");
                        System.out.println("                   Corey Long Brokerage Account");
                        System.out.println("                 ================================");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Date\t\tTicker\tQuantity\tCost Basis\tTrans Type");
                        System.out.println("===================================================================");
                        for (TransactionHistory t : portfolioList) {
                            System.out.println("\n" + t.getTransDate() + "\t" + t.getTicker() + "\t" + t.getQty() + "\t\t" + t.getCostBasis() + "\t\t" + t.getTransType());
                        }
                        break;

                    // PRINT PORTFOLIO
                    case 6:
                        
                        // ADD TIME FOR BEHIND JAVA DATE
                        java.time.LocalDateTime time = java.time.LocalDateTime.now();
                        String formatTime = time.getMonthValue() + "/" + time.getDayOfMonth() + "/" + time.getYear() + " " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + "\t";
                        
                        for (TransactionHistory t : portfolioList) {
                            if (!TickerCollector.contains(t.getTicker())) {
                                TickerCollector.add(t.getTicker());
                            }     
                        }
                        System.out.println("");
                        System.out.println("Portfolio as of: " +formatTime);
                        System.out.println("");
                        System.out.println("====================================");
                        System.out.println("Ticker\tQuantity");
                        System.out.println("=================");
                        for (String ticker : TickerCollector) {
                            System.out.println(ticker + "\t" + getStockShare(ticker));
                        }

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