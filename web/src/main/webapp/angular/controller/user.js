'use strict';

angular.module('users_management')
    .controller('userController', function ($scope, $http) {
        $http.get('/web/sport/user').success(function(users){
            //var cls=JSON.parse(clss[0]);
            $scope.users = users;
        });


        $scope.deleteEmp = function(id){
            $http.delete('/web/sport/regist_emp/' + id)
                .success(function(){
                    $location.path('/web/sport/regist_emp');
                });
        }
        $scope.updateCls = function(id,name){
            $("#updatename").val(name);
            $("#updateTable").show();
            //$http.delete('/web/sport/regist_emp/' + id)
            //    .success(function(){
            //        $location.path('/web/sport/regist_emp');
            //    });
        }
    });