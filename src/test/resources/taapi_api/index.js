const fs = require('fs');

// Reading api-key from key.txt
const key =  fs.readFileSync('./src/test/resources/taapi_api/key.txt', 'utf8');

const endpoint = process.argv[2];
const website = 'binance';
const currency = process.argv[3];
const interval = process.argv[4];

let ts = Date.now();

let date_ob = new Date(ts);
let seconds = date_ob.getSeconds();
let minutes = date_ob.getMinutes();
let hours = date_ob.getHours();
let date = date_ob.getDate();
let month = date_ob.getMonth() + 1;
let year = date_ob.getFullYear();

// Require taapi from node_modules
const taapi = require('taapi');

// Setup client with authentication
const client = taapi.client(key);

// Get technical indicator value for desired trading pair on desired time frame
client.getIndicator(endpoint, website, currency, interval).then(function(result) {

    // Make array and append data to it (array so that ".push" function works)
    let dataArray = [];
    let data =  {
        date: year + '/' + month + '/' + date + '/' + hours + '/' + minutes + '/' + seconds,
        endpoint: endpoint,
        currency: currency,
        interval: interval,
        result: result
    };

    dataArray.push(data);
    dataArray = JSON.stringify(dataArray);

    const path = './src/test/resources/taapi_api/results.json';

    // Check if "results.json" exists, and if not, create it
    if (!fs.existsSync(path)) {
        fs.openSync(path, 'a+');
    }

    // Write array data to "results.json"
    try {
        if (fs.readFileSync(path).length !== 0){
            let resultsjson = fs.readFileSync(path, 'utf-8');
            let results = JSON.parse(resultsjson);

            results.push(data);
            resultsjson = JSON.stringify(results);

            fs.writeFileSync(path, resultsjson,'utf-8');
        } else {
            fs.writeFileSync(path, dataArray,'utf-8');
        }
        console.log('Successfully written.');

    } catch (error) {
        console.error(error);
    }

});
