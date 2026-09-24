import java.util.*;

class Stock {
    String symbol;
    String name;
    double price;

    Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    void displayStock() {
        System.out.printf("%-10s %-20s ₹%.2f%n", symbol, name, price);
    }
}

class Transaction {
    String type;
    String stockSymbol;
    int quantity;
    double price;

    Transaction(String type, String stockSymbol, int quantity, double price) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
    }

    void displayTransaction() {
        System.out.printf(
            "%-8s %-10s %-10d ₹%.2f%n",
            type, stockSymbol, quantity, price
        );
    }
}

class User {
    String name;
    double balance;
    HashMap<String, Integer> portfolio;
    ArrayList<Transaction> transactions;

    User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        portfolio = new HashMap<>();
        transactions = new ArrayList<>();
    }

    void buyStock(Stock stock, int quantity) {
        double totalCost = stock.price * quantity;

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        if (balance >= totalCost) {
            balance -= totalCost;

            portfolio.put(
                stock.symbol,
                portfolio.getOrDefault(stock.symbol, 0) + quantity
            );

            transactions.add(
                new Transaction("BUY", stock.symbol, quantity, stock.price)
            );

            System.out.println("Stock purchased successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    void sellStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        int owned = portfolio.getOrDefault(stock.symbol, 0);

        if (owned >= quantity) {
            balance += stock.price * quantity;

            portfolio.put(stock.symbol, owned - quantity);

            transactions.add(
                new Transaction("SELL", stock.symbol, quantity, stock.price)
            );

            System.out.println("Stock sold successfully.");
        } else {
            System.out.println("You do not own enough shares.");
        }
    }

    void displayPortfolio(ArrayList<Stock> stocks) {
        System.out.println("\n===== PORTFOLIO =====");
        System.out.printf("Cash Balance: ₹%.2f%n", balance);

        double portfolioValue = 0;

        for (Stock stock : stocks) {
            int quantity = portfolio.getOrDefault(stock.symbol, 0);

            if (quantity > 0) {
                double value = quantity * stock.price;
                portfolioValue += value;

                System.out.printf(
                    "%s - %d shares - ₹%.2f%n",
                    stock.symbol, quantity, value
                );
            }
        }

        System.out.printf("Stock Value: ₹%.2f%n", portfolioValue);
        System.out.printf(
            "Total Portfolio Value: ₹%.2f%n",
            balance + portfolioValue
        );
    }

    void displayTransactions() {
        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.printf(
            "%-8s %-10s %-10s %-10s%n",
            "Type", "Stock", "Quantity", "Price"
        );

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("AAPL", "Apple", 180.00));
        stocks.add(new Stock("GOOG", "Google", 150.00));
        stocks.add(new Stock("AMZN", "Amazon", 175.00));
        stocks.add(new Stock("TSLA", "Tesla", 250.00));

        User user = new User("Shruti", 10000.00);

        int choice;

        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n===== MARKET DATA =====");
                    System.out.printf(
                        "%-10s %-20s %s%n",
                        "Symbol", "Company", "Price"
                    );

                    for (Stock stock : stocks) {
                        stock.displayStock();
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = sc.next().toUpperCase();

                    Stock buyStock = findStock(stocks, buySymbol);

                    if (buyStock != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = sc.nextInt();

                        user.buyStock(buyStock, quantity);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = sc.next().toUpperCase();

                    Stock sellStock = findStock(stocks, sellSymbol);

                    if (sellStock != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = sc.nextInt();

                        user.sellStock(sellStock, quantity);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 4:
                    user.displayPortfolio(stocks);
                    break;

                case 5:
                    user.displayTransactions();
                    break;

                case 6:
                    System.out.println("Thank you for using the Stock Trading Platform.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }

    static Stock findStock(ArrayList<Stock> stocks, String symbol) {

        for (Stock stock : stocks) {
            if (stock.symbol.equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }
}