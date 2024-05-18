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

    // If found you qr code
    function onScanSuccess(decodeText, decodeResult) {
       // alert("You Qr is : " + decodeText, decodeResult);
        try{
            document.getElementsByClassName("qrhead")[0].style.display='none';
            document.querySelector('#html5-qrcode-button-camera-stop').click();
            document.getElementsByClassName("paymentFields")[0].style.display='block';
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
    document.getElementsByClassName("loader")[0].style.display='block';
    let delayInMilliseconds = 1000; //1 second

    setTimeout(function() {
        //your code to be executed after 1 second
        document.getElementsByClassName("loader")[0].style.display='none';
        document.getElementsByClassName("payfs")[0].style.display='none';
        document.getElementsByClassName("paymentFields")[0].style.display='none';
        document.getElementById("paymentFields").style.display='none';
        document.getElementsByClassName("paymentSuccess")[0].style.display='block';
    }, delayInMilliseconds);

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
    var last4 = document.getElementById("paymentVisa").value;
    callApi("/topUp", "tripId=356784&date=May 16&transType=CASH&lastFour="+last4+"&cardType=visa&topUpAmount="+t+"&service=WALLET&item=TOPUP")

    document.getElementsByClassName("wallet-top-up-div")[0].style.display='none';
    document.getElementsByClassName("topupsuccess")[0].style.display='block';
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
            console.log(this.responseText);
        }
    }
    // Sending our request
    xhr.send(body);

}