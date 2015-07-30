'use strict';

angular.module('users_management')
    .controller('empController', function ($scope, $http,$route) {
        $http.get('/web/sport/regist_emp').success(function(emps){
            var ss=emps;
            console.log(ss);
            $scope.emps = emps;
        });

        $scope.deleteEmp = function(id){
            console.log(id);
            $http.delete('/web/sport/regist_emp/' + id)
                .success(function(){
                    console.log("!!!@@@@@@@@");
                    $route.reload();
                });
        }
        $scope.addEmp = function(emp){
            $http.post('/web/sport/regist_emp/',
                { name: emp.name, role: emp.role, idEmployee: emp.idEmployee}
            )
                .success(function(){
                    $route.reload();
                });
        }
    });