/**
 * Created by hikuley on 25.09.2017.
 */


app.controller('loginCtrl', function ($scope, customerService) {

    $scope.loginRequest = null;
    $scope.newCustomerRequest = null;

    $scope.toggleLogin = function () {
        var name = $(".toggle span").text() == "Giriş" ? "Üyelik için tıklayınız." : "Giriş";
        $(".toggle span").text(name);
        $('.form').animate({
            height: "toggle",
            'padding-top': 'toggle',
            'padding-bottom': 'toggle',
            opacity: "toggle"
        }, "slow");
    };

    $scope.login = function () {

        if ($scope.loginForm.$valid) {
            customerService.login($scope.loginRequest).then(function (response) {
                var result = response.data;
                if (result.status == true) {
                    window.location = "/";
                }
                else {
                    alert("Giriş yapmak için tekrar deneyiniz");
                }
            });
        }
        else {
            alert("Giriş yapmak için alanları düzgün doldurunuz");
        }


    };

    $scope.signUp = function () {
        if ($scope.signUpForm.$valid) {
            customerService.signUp($scope.newCustomerRequest).then(function (response) {
                var result = response.data;
                if (result.status == true) {

                    alert("Üyelik tamamlandı giriş yapabilirsiniz.");

                    var name = $(".toggle span").text() == "Giriş" ? "Üyelik için tıklayınız." : "Giriş";
                    $(".toggle span").text(name);
                    $('.form').animate({
                        height: "toggle",
                        'padding-top': 'toggle',
                        'padding-bottom': 'toggle',
                        opacity: "toggle"
                    }, "slow");

                }
                else {
                    alert("Üyelik başlatamadınız daha sonra tekrar deneyiniz");
                }
            }, function () {
                alert("Üyelik başlatamadınız daha sonra tekrar deneyiniz");
            });
        }
        else {
            alert("Üyelik başlatmak için alanları düzgün doldurunuz");
        }

    }

});