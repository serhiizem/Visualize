(function () {

    var app = angular.module('sort', []);

    app.controller('SortController', function ($scope) {

        $scope.sorts = [{
            value: 'BUBBLE_SORT',
            label: 'Bubble sort'
        }, {
            value: 'INSERTION_SORT',
            label: 'Insertion Sort'
        }, {
            value: 'SELECTION_SORT',
            label: 'Selection Sort'
        }];

        $('form').on('submit', function (e) {
            e.preventDefault();
        });

        $(document).on('click', '#send', function () {
            sendArray();
        });

        function sendArray() {
            var array = $("#array").val().split(" ");
            stompClient.send("/app/sort", {},
                JSON.stringify({'array': array, 'sortType': $scope.sortType.value}));
        }

        var stompClient = null;

        $(document).ready(function () {

            var socket = new SockJS('/visual-alg');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected to the socket: ' + frame);
                stompClient.subscribe('/visualize/sorting', function (sortedArray) {
                    console.log("subscription receipts:" + JSON.parse(sortedArray.body).content);
                    draw(JSON.parse(sortedArray.body).intermediate);
                });
            });
        });

        function Shape(x, y, w, h) {
            this.x = x; //The x-coordinate of the upper-left corner of the rectangle
            this.y = y; //The y-coordinate of the upper-left corner of the rectangle
            this.w = w; //The width of the rectangle, in pixels
            this.h = h; //The height of the rectangle, in pixels
        }

        function draw(sortedArray) {
            // get canvas element.
            var elem = document.getElementById('sortCanvas');

            // check if context exist
            if (elem.getContext) {
                var rectangles = [];

                for (var i = 0; i < sortedArray.length; i++) {
                    rectangles.push(new Shape(i * 50, 100, 50, sortedArray[i]));
                }

                var context = elem.getContext('2d');

                context.clearRect(0, 0, elem.width, elem.height);

                for (var i in rectangles) {

                    var rec = rectangles[i];

                    var gradient = context.createLinearGradient(0, 0, 0, 170);
                    gradient.addColorStop(0.2, "red");
                    gradient.addColorStop(1, "white");
                    context.fillStyle = gradient;

                    context.fillRect(rec.x, rec.y, rec.w, -rec.h);
                    context.strokeRect(rec.x, rec.y, rec.w, -rec.h);
                    context.fillStyle = "#000000";
                    context.font = "20px Verdana";
                    context.fillText(rec.h, rec.x + rec.w / 4, rec.y + 20);
                }
            }
        }
    })
})();