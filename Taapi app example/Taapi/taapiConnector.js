/*
GUIDE - for first use
-------------------
1. Install NodeJS
2. Run CMD
3. npm init -f
4. npm install taapi --save
5. Run file , in this case, node taapiConnector.js


Add txt file on Taapi/key.txt which containts Taapi.io key. Key is located on Trello.

Endpoints can be located on : https://taapi.io/indicators/
*/

var fs = require('fs');
var key =  fs.readFileSync('Taapi/key.txt', 'utf8');

var endpoint = process.argv[2];
var website = "binance";
var currency = process.argv[3];
var interval = process.argv[4];


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
    fs.appendFileSync('Taapi/results.txt',year + "/" + month + "/" + date +'//' + hours+':'+ minutes+ ':'+seconds +  '  ' + endpoint + '__' +  currency + '__'+ interval+ ' ==> ' + result.value);
    fs.appendFileSync('Taapi/results.txt','\n');

      console.log('Uspješno zapisano u datoteku');
});
