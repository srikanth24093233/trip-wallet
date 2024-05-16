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
    document.getElementsByClassName("paymentFields")[0].style.display='none';
    document.getElementsByClassName("paymentSuccess")[0].style.display='block';
}
