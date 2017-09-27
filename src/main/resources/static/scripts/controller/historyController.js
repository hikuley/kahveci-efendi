app.controller('historyCtrl', function ($scope, orderProductService) {

    $scope.orderDoneList = null;

    $scope.init = function () {
        var customerId = $("#customerId").val();
        orderProductService.getOrderHistory(customerId).then(function (response) {
            $scope.orderDoneList = response.data;
        }, function () {
            alert("Sipariş geçmişi yüklenemedi, tekrar deneyiniz.");
        });
    };

});