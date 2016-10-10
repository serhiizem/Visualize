(function () {

    var stompClient = null;

    var eventsApp = angular.module("sortApp", []);

    eventsApp.controller("SortController", function ($scope) {

        $scope.checkState = function () {
            stompClient.send("/app/hello", {}, "Checking State");
        };

        $scope.hideButton = function () {
            $(".hideMe").click(function () {
                $(this).hide();
            })
        };

        $scope.evaluate = function () {
            stompClient.send("/app/array", {}, "Array mock");
        };
    });

    function showGreeting(message) {
        console.log("In show greeting");
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }



    $(document).on('click', '#connect', function() {

        console.log("In connect function");

        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected to the socket: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                showGreeting(greeting.body);
            });
        });
    });
})();