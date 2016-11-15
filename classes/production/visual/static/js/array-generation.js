/*
 Написать методы, генерирующие массивы целых чисел заданной длинны следующими

 способами:

 1) Уже отсортированного массива

 (1, 2, 3, 7, ....., max);

 2) Отсортированного массива, в конец которого дописан случайный элемент

 (1, 2, 3, 7, ....., max, X);

 3) Массива, отсортированного в обратном порядке

 (max, ... , 7, 3, 2, 1);

 4) Массива, содержащего элементы, расположенные случайным образом.
 */

(function () {
    var app = angular.module("myApp", []);

    app.controller("SortController", function ($scope, $http) {

        $scope.groups = [
            "Array sorted in ascending order",
            "Array sorted in ascending order and a random element at the end",
            "Array sorted in descending order",
            "Array filled with randomly distributed values"
        ];

        $scope.send = function(input) {
            alert(input);
        }
    });
}());
