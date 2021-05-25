const fs = require('fs');

// Reading api-key from key.txt
const key =  fs.readFileSync('./src/test/resources/taapi_api/key.txt', 'utf8');

const indicatorList = ['macd', 'ma'];
const website = 'binance';
const symbol = process.argv[2];
const interval = process.argv[3];

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

// Get MACD and MA200 value for desired trading pair on desired time frame
requestIndicator(indicatorList);

// result: indicator value, indicator: macd/ma (0/1)
function write(result, indicator) {
    // Make array and append data to it (array so that ".push" function works)
    let dataArray = [];
    let data =  {
        date: year + '/' + month + '/' + date + '/' + hours + '/' + minutes + '/' + seconds,
        indicator: indicatorList[indicator],
        symbol: symbol,
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
        if (!indicator){
            console.log('Successfully written MACD.');
        } else {
            console.log('Successfully written MA');
        }

    } catch (error) {
        console.error(error);
    }
}

function rejected(error, indicator){
    console.error(error);

    let counterMacd = 1;
    let counterMa = 1;
    // If indicator is MACD {request macd} else {request ma200}
    if (!indicator) {
        console.log('\tmacd try' + counterMacd++);
        requestIndicator('macd');
    }
    else {
        console.log('\tma try' + counterMa++);
        requestIndicator('ma200');
    }
}

function requestIndicator(indicatorList) {
    indicatorList.forEach(indicator =>  {
        if (indicator === 'macd')
            // Request MACD
            client.getIndicator(indicatorList[0], website, symbol, interval).then(function(result) {
                write(result, 0);
            }).catch(e => rejected(e, 0));
        else if (indicator === 'ma')
            // Request MA200
            client.getIndicator(indicatorList[1], website, symbol, interval, {optInTimePeriod: 200}, 0, 300).then(function(result) {
                write(result, 1);
            }).catch(e => rejected(e, 1));
    });
}
