import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Stock {
    private String symbol;
    private int quantity;
    private double price;
    private LocalDate date;

    public Stock(String symbol, int quantity, double price, LocalDate date) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.date=date;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getValue() {
        return quantity * price;
    }

    public String toString() {
        return symbol + "\t" + quantity + "\t\t" + price + "\t\tRs." + getValue();
        }

    public LocalDate getDate() {
        return date;
    }

    public Object getCompanyName() {
        return symbol;
    }

    public double calculateProfitLoss(int sellingQuantity, double sellingPrice, LocalDate sellingDate) {
        long holdingPeriodInDays = ChronoUnit.DAYS.between(date, sellingDate);
        double taxRate = (holdingPeriodInDays >= 365) ? 0.1 : 0.15;

        double capitalGain = (sellingPrice - price) * sellingQuantity;
        double taxAmount = capitalGain * taxRate;
        double netProfitLoss = capitalGain - taxAmount;

        return netProfitLoss;
    }

}