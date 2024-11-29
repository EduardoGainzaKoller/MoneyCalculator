package software.ulpgc.moneycalculator.model;

import java.time.LocalDate;

public class ExchangeRate {
    private final LocalDate date;
    private final double rate;
    private final Currency from;
    private final Currency to;

    public ExchangeRate(LocalDate date, double rate, Currency from, Currency to) {
        this.date = date;
        this.rate = rate;
        this.from = from;
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "date=" + date +
                ", rate=" + rate +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
