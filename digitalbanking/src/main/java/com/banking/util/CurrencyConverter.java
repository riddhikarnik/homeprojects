package com.banking.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import com.banking.entity.Currency;


public class CurrencyConverter {
    private static Properties property = new Properties();

    private CurrencyConverter(){
    }

    public static BigDecimal buyEuro(Currency senderCurrency, BigDecimal amount) {
        return amount.divide(new BigDecimal(getExchangeRate(senderCurrency)), 2, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal sellEuro(Currency recipientCurrency, BigDecimal amount){
        return amount.multiply(new BigDecimal(getExchangeRate(recipientCurrency))).setScale(2, BigDecimal.ROUND_DOWN);
    }

    public static double getExchangeRate(Currency currency) {
        double result = 0;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("exchange_rates/rates.properties")){
            property.load(input);
            String currentCurrency = currency.toString().toLowerCase();
            result = Double.parseDouble(property.getProperty(currentCurrency));
        } catch (IOException ignored){}
        return result;
    }

    public static BigDecimal convertMoney(Currency senderCurrency, Currency recipientCurrency, BigDecimal amount) {
        BigDecimal euro = buyEuro(senderCurrency, amount);
        return sellEuro(recipientCurrency, euro);
    }
}
