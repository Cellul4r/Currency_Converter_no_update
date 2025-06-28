package com.cellul4r.currencyconverter;

public class CurrencyConverter {

    private static final String[] EXCHANGE_RATEs_STR = {"AUD-AUD", "AUD-CNY", "AUD-EUR", "AUD-JPY", "AUD-SGD", "AUD-THB", "AUD-VND",
            "CNY-AUD", "CNY-CNY", "CNY-EUR", "CNY-JPY", "CNY-SGD", "CNY-THB", "CNY-VND",
            "EUR-AUD", "EUR-CNY", "EUR-EUR", "EUR-JPY", "EUR-SGD", "EUR-THB", "EUR-VND",
            "JPY-AUD", "JPY-CNY", "JPY-EUR", "JPY-JPY", "JPY-SGD", "JPY-THB", "JPY-VND",
            "SGD-AUD", "SGD-CNY", "SGD-EUR", "SGD-JPY", "SGD-SGD", "SGD-THB", "SGD-VND",
            "THB-AUD", "THB-CNY", "THB-EUR", "THB-JPY", "THB-SGD", "THB-THB", "THB-VND",
            "VND-AUD", "VND-CNY", "VND-EUR", "VND-JPY", "VND-SGD", "VND-THB", "VND-VND",};

    private static final double[] EXCHANGE_RATEs = {1.00, 4.55, 0.58, 93.36, 0.84, 21.41, 16044.68,
            0.22, 1.00, 0.13, 20.55, 0.19, 4.71, 3580.58,
            1.73, 7.84, 1.00, 161.05, 1.45, 36.95, 27681.00,
            0.011, 0.049, 0.0062, 1.00, 0.0090, 0.23, 171.87,
            1.19, 5.40, 0.69, 111.26, 1.00, 25.44, 19079.89,
            0.047, 0.213, 0.027, 4.36, 0.039, 1.00, 749.56,
            0.000062, 0.00028, 0.000036, 0.0058, 0.000052, 0.0013, 1.00};

    public static final String[] currencyList = {"AUD-Australian Dollar", "CNY-ChineseYen", "EUR-Euro", "JPY-Japanese Yen",
            "SGD-Singapore Dollar", "THB-Thai Baht", "VND-Vietnamese Dong"};

    private String srcCurrency;
    private double srcCurrencyAmount;
    private String targetCurrency;
    private double targetCurrencyAmount;

    public CurrencyConverter(String srcCurrency, double srcCurrencyAmount, String targetCurrency) {
        this.srcCurrency = srcCurrency;
        this.srcCurrencyAmount = srcCurrencyAmount;
        this.targetCurrency = targetCurrency;
        this.targetCurrencyAmount = convert();
    }

    private double convert() {
        String exchange = srcCurrency.split("-")[0] + "-" + targetCurrency.split("-")[0];
        for(int i = 0; i < EXCHANGE_RATEs.length; i++) {
            if(exchange.equalsIgnoreCase(currencyList[i])) {
                return targetCurrencyAmount = EXCHANGE_RATEs[i] * srcCurrencyAmount;
            }
        }
        return 0.0;
    }

    public String getSrcCurrency() {
        return srcCurrency;
    }

    public double getSrcCurrencyAmount() {
        return srcCurrencyAmount;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public double getTargetCurrencyAmount() {
        return targetCurrencyAmount;
    }
}
