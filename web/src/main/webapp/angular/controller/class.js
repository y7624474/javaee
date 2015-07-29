'use strict';

angular.module('users_management')
    .controller('clsController', function ($scope, $http) {
        $http.get('/web/sport/class').success(function(emps){
            $scope.emps = emps;
        });

        $scope.deleteEmp = function(id){
            $http.delete('/web/sport/regist_emp/' + id)
                .success(function(){
                    $location.path('/web/sport/regist_emp');
                });
        }
    });