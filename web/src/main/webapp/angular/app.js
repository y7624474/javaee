'use strict';

angular.module('users_management', ['ngRoute'])
    .config(function($routeProvider){
        $routeProvider
            .when('/user', {
                templateUrl: 'user.html',
                controller: 'userController'
            })
            .when('/regist_emp', {
                templateUrl: 'regist_employee.html',
                controller: 'empController'
            })
            .when('/customer', {
                templateUrl: 'customer.html',
                controller: 'cusController'
            })
            .when('/class', {
                templateUrl: 'class.html',
                controller: 'clsController'
            })
            .when('/private', {
                templateUrl: 'private.html',
                controller: 'priController'
            });


    });