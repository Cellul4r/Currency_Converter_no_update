function loadCurrencyOptions(id) {

    // create XMLHttpRequest Object
    const xhttp = new XMLHttpRequest();
    // execute when the response is ready
    xhttp.onload = function() {
        if(xhttp.status === 200) {
            // parse json to get the list of currencies
            const data = JSON.parse(xhttp.responseText);
            const select = document.getElementById(id);
            select.innerHTML = "";

            // add options to "select"
            data.forEach(function(currencyCode) {
                // append more option in the select field
                const option = document.createElement("option");
                // set value of each options
                option.value = currencyCode;
                option.textContent = currencyCode;
                select.appendChild(option);
            })
        } else {
            console.error("Failed to load currencies for ", id, xhttp.status);
        }

    }

    // send request with GET method to get currency list with json formatted
    xhttp.open("GET", "currency-converter?type=list", true);
    xhttp.send();
}

function submitForm(formId) {
    const form = document.getElementById(formId);
    // send request when this form was submitted
    form.addEventListener("submit", function(e) {
        // prevent reloading page
        e.preventDefault();

        const data = {
            srcCurrencyAmount: parseFloat(document.getElementById("srcCurrencyAmount").value),
            srcCurrency: document.getElementById("srcCurrency").value,
            targetCurrency: document.getElementById("targetCurrency").value
        };

        // send request with POST method to CurrencyConvertServlet.java
        // and get response back about currency converting result with json formatted
        fetch("currency-converter",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        }).then(res => res.json())
            .then(result => {
                document.getElementById("result").textContent =
                `${result.srcCurrencyAmount} ${result.srcCurrency} = 
                ${result.targetCurrencyAmount.toFixed(7)} ${result.targetCurrency}`;
            })
            .catch(err => console.error("Error: ", err));
    })
}

loadCurrencyOptions("srcCurrency");
loadCurrencyOptions("targetCurrency");
submitForm("convertCurrency");
