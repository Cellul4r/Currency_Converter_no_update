package com.cellul4r.currencyconverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "currencyConverter", value = "/currency-converter")
public class CurrencyConverterServlet extends HttpServlet {

    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        // check type of request
        if("list".equals(type)) {

            // format currency string array to json
            String[] currencyList = Arrays.copyOf(this.currencyList, this.currencyList.length);
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

    }
}