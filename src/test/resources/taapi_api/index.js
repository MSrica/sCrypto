let fs = require('fs');

// Reading api-key from key.txt
let key =  fs.readFileSync('./src/test/resources/taapi_api/key.txt', 'utf8');

// Without this api keys don't match on Linux
if (process.platform === 'linux') {
    key = key.replace('\n', '');
}

let endpoint = process.argv[2];
let website = "binance";
let currency = process.argv[3];
let interval = process.argv[4];

let ts = Date.now();

let date_ob = new Date(ts);
let seconds = date_ob.getSeconds();
let minutes = date_ob.getMinutes();
let hours = date_ob.getHours();
let date = date_ob.getDate();
let month = date_ob.getMonth() + 1;
let year = date_ob.getFullYear();

// Require taapi (using the NPM client from npm i taapi)
const taapi = require("taapi");

// Setup client with authentication
const client = taapi.client(key);

// Get technical indicator value for desired trading pair on desired time frame
client.getIndicator(endpoint, website, currency, interval).then(function(result) {

    ////////////////////////// DEBUG //////////////////////////
    // Get value only, without string
    // let value = JSON.stringify(result);
    // value = value.replace('{"value":', '');
    // value = value.replace('}', '');
    // console.log(process.argv[2] + " = ", value);

    // Get all data and write it to results.json
    let data = {
      date: year + "/" + month + "/" + date + '/' + hours + '/' + minutes + '/' + seconds,
      endpoint: endpoint,
      currency: currency,
      interval: interval,
      result: result
    };

    data = JSON.stringify(data);
    fs.appendFileSync('./src/test/resources/taapi_api/results.json', data);

    console.log('Successfully written.');
});
