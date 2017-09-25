/**
 * Created by hikuley on 25.09.2017.
 */


app.controller('loginCtrl', function ($scope) {

    $scope.loginRequest = null;
    $scope.newCustomerRequest = null;

    $scope.toggleLogin = function () {
        var name = $(".toggle span").text() == "Giriş" ? "Üyelik için tıklayınız." : "Giriş";
        $(".toggle span").text(name);
        // Switches the forms
        $('.form').animate({
            height: "toggle",
            'padding-top': 'toggle',
            'padding-bottom': 'toggle',
            opacity: "toggle"
        }, "slow");
    }

    $scope.login = function () {
        alert();
    }

    $scope.register = function () {
        alert();
    }

});