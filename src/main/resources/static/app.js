var stompClient = null;

function connect() {
    // This matches the endpoint in your WebSocketConfig
    var socket = new SockJS('/ws-chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        // Subscribe to the topic where Kafka Consumer pushes data
        stompClient.subscribe('/topic/public', function (response) {
            showGreeting(JSON.parse(response.body).content);
        });
    });
}

function sendMessage() {
    var msg = document.getElementById('message').value;
    // Sends to @MessageMapping("/chat") in your Controller
    stompClient.send("/app/chat", {}, JSON.stringify({'sender' : 'WEB','content': msg}));
}

function showGreeting(message) {
    var li = document.createElement("li");
    li.appendChild(document.createTextNode(message));
    document.getElementById("responses").appendChild(li);
}

// Connect automatically on load
connect();