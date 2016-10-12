(function () {

    var stompClient = null;

    var eventsApp = angular.module("sortApp", []);

    eventsApp.controller("SortController", function ($scope) {

        $scope.checkState = function () {
            stompClient.send("/app/hello", {}, "Checking State");
        };

        $scope.evaluate = function () {
            stompClient.send("/app/array", {}, "Array mock");
        };
    });

    function showGreeting(message) {
        console.log("In show greeting");
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }

    $(document).ready(function() {

        console.log("In connect function");

        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected to the socket: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                console.log("subscription recepts:" + JSON.parse(greeting.body).content);
                draw(JSON.parse(greeting.body).intermediate);
            });
        });
    });

    function sendArray() {
        console.log("In sendArray");
        var array = $("#array").val().split(" ");
        console.log("Array in console: " + array);
        stompClient.send("/app/array", {}, JSON.stringify(array));
    }

    $('form').on('submit', function (e) {
        e.preventDefault();
    });

    $(document).on('click', '#send', function () {
        console.log("Click done");
        sendArray();
    });

    function Shape(x, y, w, h) {
        this.x = x; //The x-coordinate of the upper-left corner of the rectangle
        this.y = y; //The y-coordinate of the upper-left corner of the rectangle
        this.w = w; //The width of the rectangle, in pixels
        this.h = h; //The height of the rectangle, in pixels
    }

    function draw(sort) {
        // get canvas element.
        var elem = document.getElementById('sortCanvas');

        console.log("Obtained message contained element sort[1]: " + sort[1]);
        // check if context exist
        if (elem.getContext) {
            var myRect = [];

            for (var i = 0; i < sort.length; i++) {
                myRect.push(new Shape(i*50, 100, 50, -sort[i]));
            }

            context = elem.getContext('2d');
            for (var i in myRect) {
                oRec = myRect[i];

                var my_gradient = context.createLinearGradient(0, 0, 0, 170);
                my_gradient.addColorStop(0.2, "black");
                my_gradient.addColorStop(1, "white");
                context.fillStyle = my_gradient;

                context.fillRect(oRec.x, oRec.y, oRec.w, oRec.h);
                context.strokeRect(oRec.x, oRec.y, oRec.w, oRec.h);
                context.fillText("value", oRec.x + oRec.w / 4, oRec.y + 10);
            }
        }
    }
})();