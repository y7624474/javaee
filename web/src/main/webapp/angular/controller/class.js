'use strict';

angular.module('users_management')
    .controller('clsController', function ($scope, $http,$route,$location) {
        $http.get('/web/sport/class').success(function(clss){
            //var cls=JSON.parse(clss[0]);
            $scope.clss = clss;
            $http.get('/web/sport/class/getcoach').success(function(coachs){
                //var cls=JSON.parse(clss[0]);
                $scope.coachs = coachs;
                //console.log(cls);
            });
        });



        $scope.addCls = function(cls){
            $http.post('/web/sport/class/',
                { classname: cls.classname, time: cls.time, coach: cls.coach}
            )
                .success(function(){
                    $route.reload();
                });
        };


        $scope.delCls = function(id){
            $http.delete('/web/sport/class/' + id)
                .success(function(){
                    $route.reload();
                });
        };

        $scope.updateCls = function(id,name,coach){
            $("#updatename").val(name);
            $('#updatecoach').val(coach);
            $("#updateTable").show();
            //$http.delete('/web/sport/regist_emp/' + id)
            //    .success(function(){
            //        $location.path('/web/sport/regist_emp');
            //    });
        }
    });