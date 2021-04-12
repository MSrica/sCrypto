/*
GUIDE - for first use
-------------------
1. Install NodeJS
2. Run CMD
3. npm init -f
4. npm install taapi --save
5. Run file , in this case, node taapiConnector.js
*/

var key = ("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImZyYW4uZ3JlbmtvQGdtYWlsLmNvbSIsImlhdCI6MTYxODI1MTcxMSwiZXhwIjo3OTI1NDUxNzExfQ.amcLz0gQU6UqXp8b--8WCGFCFRo9tYy9lZS7-U5uLag");
var endpoint = "rsi";
var website = "binance";
var currency = "BTC/USDT";
var interval = "1m";


const fs = require('fs');
const path = './results.txt';


let ts = Date.now();

let date_ob = new Date(ts);
let seconds = date_ob.getSeconds();
let minutes = date_ob.getMinutes();
let hours = date_ob.getHours();
let date = date_ob.getDate();
let month = date_ob.getMonth() + 1;
let year = date_ob.getFullYear();





// Require taapi (using the NPM client: npm i taapi --save)
const taapi = require("taapi");

// Setup client with authentication
const client = taapi.client(key);

// Get the BTC/USDT RSI value on the 1 minute time frame from binance
client.getIndicator(endpoint, website, currency , interval).then(function(result) {
    console.log("Result: ", result);
    fs.writeFileSync('results.txt',year + "/" + month + "/" + date +'//' + hours+':'+ minutes+ ':'+seconds +  '  ' + endpoint + '__' +  currency + '__'+ interval+ ' ==> ' + result.value);
    fs.appendFileSync('results.txt','\n');

      console.log('Uspješno zapisano u datoteku');
});
