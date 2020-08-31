const BTC_module = require('./miningPool.js');
const {createInterface} = require("readline");

const reader = createInterface({
    input: process.stdin,
    output: process.stdout
});

// callback으로 구현된 함수를 Promise로 래핑
function question(text) {
    return new Promise((resolve, reject) => {
        reader.question(text, data => {
            resolve(data)
        })
    })
}

async function main() {
    while(true) {
        const answer = await question("[1]Print Nodes [2]Create Node [3]Start Mining [4]Stop Mining [5] Exit >> ");
        switch (answer) {
            case "1":
                BTC_module.emit("list_nodes");
                break;
            case "2":
                BTC_module.emit("create_node");
                break;
            case "3":
                let start_node = await question("Enter the node_id >> ");
                BTC_module.emit("start_node", start_node);
                break;
            case "4":
                let stop_node = await question("Enter the node_id >> ");
                BTC_module.emit("stop_node", stop_node);
                break;
            case "5":
                BTC_module.emit("exit");
                break;
            default:
                console.log("Enter again");
                break;
        }
    }
}

main();