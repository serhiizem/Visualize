var stompClient = null;

(function () {

    function showGreeting(message) {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }

    function connect() {
        $("#connect").click(function () {
            $(this).hide();
        })
    }

    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
})();

/*function connect() {
 console.log("Connect section");
 var socket = new SockJS('/gs-guide-websocket');
 stompClient = Stomp.over(socket);
 stompClient.connect({}, function (frame) {
 console.log('Connected: ' + frame);
 stompClient.subscribe('/topic/greetings', function (greeting) {
 showGreeting(JSON.parse(greeting.body).content);
 });
 });
 }*/

