(function () {
    var app = angular.module("generationApp", []);

    app.controller("SortController", function ($scope, $http) {

        $scope.groups = [
            "Array sorted in ascending order",
            "Array sorted in ascending order and a random element at the end",
            "Array sorted in descending order",
            "Array filled with randomly distributed values"
        ];

        $scope.generateXls = function () {
            var range = {
                "arraySize" : $scope.arraySize,
                "minValue"  : $scope.minValue,
                "maxValue"  : $scope.maxValue
            };

            $http({
                url:"/generateXls",
                method: "POST",
                data: range
            }).success(function (data) {
                console.log(data);
            })
        }
    });
}());
