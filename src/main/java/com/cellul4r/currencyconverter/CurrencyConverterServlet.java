package com.cellul4r.currencyconverter;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "currencyConverter", value = "/currency-converter")
public class CurrencyConverterServlet extends HttpServlet {

    private final Gson gson = new Gson();

    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        // check type of request
        if("list".equals(type)) {

            // format currency string array to json
            String[] oldCurrencyList = CurrencyConverter.currencyList;
            String[] currencyList = Arrays.copyOf(oldCurrencyList, oldCurrencyList.length);
            for(int i = 0; i < currencyList.length; i++) {
                currencyList[i] = String.format("\"%s\"", currencyList[i]);
            }
            String currencysString = Arrays.toString(currencyList);

            // set up response to send back
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            resp.getWriter().write(currencysString);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        CurrencyConverter currencyConverter = gson.fromJson(reader, CurrencyConverter.class);
        currencyConverter.convert();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String json = gson.toJson(currencyConverter);
        System.out.println(json);
        resp.getWriter().write(json);
    }
}