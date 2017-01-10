(function () {

    var app = angular.module('sort', []);

    app.controller('SortController', function ($scope) {

        $scope.sorts = [{
            value: 'BUBBLE_SORT',
            label: 'Bubble sort'
        }, {
            value: 'BUBBLE_SORT_REVERSE',
            label: 'Bubble sort(reversed)'
        }, {
            value: 'INSERTION_SORT',
            label: 'Insertion Sort'
        }, {
            value: 'SELECTION_SORT',
            label: 'Selection Sort'
        }, {
            value: 'QUICK_SORT',
            label: 'Quick sort'
        }, {
            value: 'MERGE_SORT',
            label: 'Merge sort'
        }, {
            value: 'JAVA_SORT',
            label: 'Java sort'
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
                    draw(JSON.parse(sortedArray.body));
                });
            });
        });

        function Shape(x, y, w, h) {
            this.x = x; //The x-coordinate of the upper-left corner of the rectangle
            this.y = y; //The y-coordinate of the upper-left corner of the rectangle
            this.w = w; //The width of the rectangle, in pixels
            this.h = h; //The height of the rectangle, in pixels
        }

        function normalizeArray(array) {
            var normalizedArray = [];
            var theBiggestElement = findTheBiggestElement(array);
            for (var i = 0; i < array.length; i++) {
                normalizedArray.push((array[i] / theBiggestElement) * 100);
            }
            return normalizedArray;
        }

        function findTheBiggestElement(array) {
            var biggest = array[0];
            for (var i = 1; i < array.length; i++) {
                if(array[i] > biggest) biggest = array[i];
            }
            return biggest;
        }


        function draw(sortRepresentation) {

            var sortedArray = sortRepresentation.intermediateResult;
            // get canvas element.
            var canvas = document.getElementById('sortCanvas');

            // check if context exist
            if (canvas.getContext) {
                var rectangles = [];

                var normalizedArray = normalizeArray(sortedArray);

                for (var i = 0; i < normalizedArray.length; i++) {
                    rectangles.push(new Shape(i * 50 + canvas.width / sortedArray.length, canvas.height / 2, 50, normalizedArray[i]));
                }

                var context = canvas.getContext('2d');

                context.clearRect(0, 0, canvas.width, canvas.height);

                for (var i in rectangles) {

                    var rec = rectangles[i];
                    var text = sortedArray[i];

                    var gradient = context.createLinearGradient(0, 0, 0, 170);
                    gradient.addColorStop(0.2, "red");
                    gradient.addColorStop(1, "white");
                    context.fillStyle = gradient;

                    //TODO: change fill coordinates to match new rectangle coordinates
                    context.fillRect(rec.x, rec.y, rec.w, -rec.h);
                    context.strokeRect(rec.x, rec.y, rec.w, -rec.h);
                    context.fillStyle = "#000000";
                    context.font = "20px Arial";
                    context.fillText(text, rec.x + rec.w / 4, rec.y + 20);
                }
            }
        }
    })
})();