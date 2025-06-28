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
                const option = document.createElement("option");
                option.value = currencyCode;
                option.textContent = currencyCode;
                select.appendChild(option);
            })
        } else {
            console.error("Failed to load currencies for ", id, xhttp.status);
        }

    }

    // send request
    xhttp.open("GET", "currency-converter?type=list", true);
    xhttp.send();
}

function submitForm(formId) {
    const form = document.getElementById(formId);
    form.addEventListener("submit", function(e) {
        // prevent reloading page
        e.preventDefault();

        const data = {
            amount: parseFloat(document.getElementById("amount").value),
            srcCurrency: document.getElementById("srcCurrency").value,
            targetCurrency: document.getElementById("targetCurrency").value
        };

        fetch("currency-converter",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        }).then(res => res.text())
            .then(result => {
                document.getElementById("result").textContent = result;
            })
            .catch(err => console.error("Error: ", err));
    })
}

loadCurrencyOptions("srcCurrency");
loadCurrencyOptions("targetCurrency");
submitForm("convertCurrency");
