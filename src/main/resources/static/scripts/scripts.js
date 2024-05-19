/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

// script.js file

function domReady(fn) {
    if (
        document.readyState === "complete" ||
        document.readyState === "interactive"
    ) {
        setTimeout(fn, 1000);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

domReady(function () {
    var urlparam = window.location.search;
    if(urlparam){
        document.getElementById("walletModal1").click();
    }
    // If found you qr code
    function onScanSuccess(decodeText, decodeResult) {
        const myArray = decodeText.split("_");
        var posname = myArray[0];
        var check = myArray[1];
        var itemname = myArray[2];
        var itemcost = myArray[3];
        var subtotal = myArray[4];
        var tax = myArray[5];
        var total = myArray[6];
        document.getElementById("posname").innerHTML=posname;
        document.getElementById("checkno").innerHTML=check;
        document.getElementById("itemname").innerHTML=itemname;
        document.getElementById("itemcost").innerHTML=itemcost;
        document.getElementById("subtotal").innerHTML=subtotal;
        document.getElementById("tax").innerHTML=tax;
        document.getElementById("total").innerHTML=total;
        document.getElementById("totalpt").innerHTML="(246 points)";

        try{
            document.getElementsByClassName("qrhead")[0].style.display='none';
            document.getElementsByClassName("qrlink")[0].style.display='none';
            document.querySelector('#html5-qrcode-button-camera-stop').click();
            document.getElementsByClassName("paymentFields")[0].style.display='block';
            document.getElementsByClassName("checkDialog")[0].style.display='block';

        }catch(e) {
            console.log(e)
        }
    }

    let htmlscanner = new Html5QrcodeScanner(
        "my-qr-reader",
        { fps: 10, qrbos: 250 }
    );
    htmlscanner.render(onScanSuccess);
});

function hidePay(){

    var t = document.getElementById("total").innerHTML;
    var cardType = document.getElementById("paymentCC").value;
    var last4 = document.getElementById("paymentLast4").value;
    var bonRadio = document.getElementById("paymentB");
    var ptAmt = 0;
    var toupAmt = 0;
    var transType = "CASH";
    var item = document.getElementById("posname").innerHTML;
    var pointsBalance = document.getElementById("pointsBalance").innerHTML;
    if(bonRadio.checked){
        ptAmt = t*10;
        if(ptAmt > pointsBalance){
            alert('Sorry not enough points balance. Please topup points balance');
            return;
        }
        transType="POINTS";
    }
    if(document.getElementById("paymentCC").checked){
        toupAmt = t;
    }

    callApi("/topUp", "tripId=356784&date=May 16&transType="+transType+"&lastFour="+last4+"&cardType=visa&topUpAmount="+toupAmt+"&pointsAmount="+ptAmt+"&service=POS&item="+item)
    document.getElementsByClassName("loader")[0].style.display='none';
    document.getElementsByClassName("paymentFields")[0].style.display='none';
    document.getElementById("paymentFields").style.display='none';
    document.getElementsByClassName("paymentSuccess")[0].style.display='block';

    let delayInMilliseconds = 1000; //1 second

    setTimeout(function() {

    }, delayInMilliseconds);
    var urlparam = window.location.search;
    if(urlparam) {
        window.location.href = window.location.href;
    }
    else{
            window.location.href = window.location.href + "?reload";
        }


}
function topUp(){
    let span = document.getElementById("planned");
    let span1 = document.getElementById("balance");
    span.textContent = "$3000";
    span1.textContent = "$1100";
}

function showPay(){
    document.getElementsByClassName("paymentFields")[0].style.display='none';
    document.getElementsByClassName("paymentSuccess")[0].style.display='none';
    document.getElementsByClassName("qrhead")[0].style.display='block';
}

function walletTopUp(){
    var t = document.getElementById("topUpAmount").value;
    var cardType = document.getElementById("paymentVisa").value;
    var last4 = document.getElementById("paymentLast4").value;
    var bonRadio = document.getElementById("paymentBonvoy");
    var ptAmt = 0;
    var toupAmt = 0;
    var transType = "CASH";
    if(bonRadio.checked){
        ptAmt = t;
        transType="POINTS";
    }
    if(document.getElementById("paymentVisa").checked){
        toupAmt = t;
    }

    callApi("/topUp", "tripId=356784&date=May 16&transType="+transType+"&lastFour="+last4+"&cardType=visa&topUpAmount="+toupAmt+"&pointsAmount="+ptAmt+"&service=WALLET&item=TOPUP")

    document.getElementsByClassName("wallet-top-up-div")[0].style.display='none';
    document.getElementsByClassName("topupsuccess")[0].style.display='block';
    setTimeout(function() {
        //
    }, 1000);
    var urlparam = window.location.search;
    if(urlparam) {
        window.location.href = window.location.href;
    }else{
        window.location.href = window.location.href + "?reload";
    }
}

function showWalletTopUp(){

    document.getElementsByClassName("wallet-top-up-div")[0].style.display='block';
    document.getElementsByClassName("topupsuccess")[0].style.display='none';
}
function buildWallet() {
 callApi("/topUp", "tripId=356784&date=May 16&transType=CASH&lastFour=4567&cardType=visa&topUpAmount=320.50&service=WALLET")
}
function fetchSummary() {
    callApi("/fetchSummary", "tripId=44444")
}
function callApi(url, body){
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //console.log(this.responseText);
        }
    }
    // Sending our request
    xhr.send(body);

}