'use strict';

angular.module('users_management')
    .controller('priController', function ($scope, $http) {
        $http.get('/web/sport/private').success(function(pris){
            $scope.pris = pris;
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