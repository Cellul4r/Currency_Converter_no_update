function loadCurrencyOptions() {
    const xhttp = new XMLHttpRequest();

    xhttp.onload = function() {
        if(xhttp.status === 200) {
            const data = JSON.parse(this.responseText);
            const select = document.getElementById("srcCurrency");
            select.innerHTML = "";

            data.forEach(function(currencyCode) {
                const option = document.createElement("option")
                option.value = currencyCode;
                option.textContent = currencyCode;
                select.appendChild(option);
            })
        }
    };

    xhttp.open("GET", "currency_converter?type=list", true)
    xhttp.send();
}