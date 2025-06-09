package IndividualWork;

import lombok.Getter;
import lombok.Setter;

public enum Currency {
    MDL(1.0),
    USD(18.50),
    EUR(19.75),
    GBP(23.10),
    RON(4.10);

    private final double exchangeRate;

    Currency(double exchangeRate)
    {
        this.exchangeRate = exchangeRate;
    }

    public double getExchangeRate()
    {
        return exchangeRate;
    }

    public double convertToCurrency(double amountMDL)
    {
        return amountMDL / exchangeRate;
    }

    public double convertToMDL(double amountCurrency)
    {
        return amountCurrency * exchangeRate;
    }

}
