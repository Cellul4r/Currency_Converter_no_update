function loadCurrencyOptions() {

    // create XMLHttpRequest Object
    const xhttp = new XMLHttpRequest();

    // execute when the response is ready
    xhttp.onload = function() {
        if(xhttp.status === 200) {
            // parse json to get the list of currencies
            const data = JSON.parse(xhttp.responseText);
            const select = document.getElementById("srcCurrency");
            select.innerHTML = "";

            // add options to "select"
            data.forEach(function(currencyCode) {
                const option = document.createElement("option")
                option.value = currencyCode;
                option.textContent = currencyCode;
                select.appendChild(option);
            })
        }

    }

    // send request
    xhttp.open("GET", "currency-converter?type=list", true);
    xhttp.send();
}

window.onload = loadCurrencyOptions;