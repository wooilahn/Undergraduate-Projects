
const { EventEmitter } = require("events");
const myEventEmitter = new EventEmitter();

const nodes = [];
const activeNodes = [];

var id = 0;

myEventEmitter.on("list_nodes", () => { // print current node lists
    for(let i=0; i<nodes.length; i++){
        console.log(nodes[i]);
    }
});

myEventEmitter.on("create_node", () => { // Create new node
    let node = { node_id: 0, coins: 0 };
    node.node_id = id++;
    console.log(node);

    nodes.push(node);
});

myEventEmitter.on("start_node", (start_node_id) => { // start mining with designated node
    let node_id = parseInt(start_node_id);
    activeNodes[node_id] = setInterval(() => {nodes[node_id].coins++;}, 300);
    console.log("Node " + start_node_id + " started mining");
});

myEventEmitter.on("stop_node", (stop_node_id) => { // stop mining with designated node
    let node_id = parseInt(stop_node_id);
    clearInterval(activeNodes[node_id]);
    console.log("Node " + stop_node_id + " is stopped");
});

myEventEmitter.on("exit", () => { // program exit
    console.log("Good bye!");
    process.exit();
});

module.exports = myEventEmitter;
