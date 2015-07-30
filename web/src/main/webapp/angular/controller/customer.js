'use strict';

angular.module('users_management')
    .controller('cusController', function ($scope, $http) {
        $http.get('/web/sport/customer').success(function(cuss){
            $scope.cuss = cuss;
            $http.get('/web/sport/class/getcoach').success(function(coachs){
                //var cls=JSON.parse(clss[0]);
                $scope.coachs = coachs;
                //console.log(cls);
            });
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