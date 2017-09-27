/**
 * Created by hikuley on 25.09.2017.
 */

app.controller('homeCtrl', function ($scope, orderProductService) {

    $scope.products = null;
    $scope.orderList = null;
    $scope.selectedProduct = null;
    $scope.selectedAddon = null;
    $scope.count = 1;
    $scope.orderTotalPrice = 0.00;

    $scope.calculatedPrice = null;

    $scope.init = function () {
        orderProductService.getAllProduct().then(function (response) {
            $scope.products = response.data;
        }, function () {
            alert("Ürünler Yüklenemedi");
        });
    };

    $scope.openOrderModal = function (product) {

        $scope.selectedProduct = null;
        $scope.count = 1;
        $scope.selectedAddon = null;
        $scope.orderNote = null;
        $("input[type=checkbox]").prop("checked", false);
        $scope.selectedProduct = product;


        $scope.openModal();
    };

    $scope.plus = function () {
        $scope.count++;
    };

    $scope.minus = function () {
        if ($scope.count > 1) {
            $scope.count--;
        }
    };

    $scope.addToOrdersList = function () {
        var order = {
            "orderNote": $scope.orderNote == undefined ? null : $scope.orderNote,
            "price": $scope.count * $scope.selectedProduct.price,
            "piece": $scope.count,
            "product": $scope.selectedProduct,
            "addons": $scope.selectedAddon

        };

        if ($scope.orderList == null)
            $scope.orderList = [];

        $scope.orderTotalPrice += $scope.count * $scope.selectedProduct.price;

        angular.forEach($scope.selectedAddon, function (addon, key) {
            $scope.orderTotalPrice += addon.price * $scope.count
        });

        $scope.orderList.push(order);

        $scope.closeModal();
    }

    $scope.checkedAddon = function (product) {
        if ($scope.selectedAddon == null) {
            $scope.selectedAddon = [];
        }
        $scope.selectedAddon.push(product);
    }

    $scope.openModal = function () {
        $('[data-remodal-id=productDetail]').remodal().open();
    }

    $scope.closeModal = function () {
        $('[data-remodal-id=productDetail]').remodal().close();
    }


    $scope.calculatedDiscountOrder = function () {
        orderProductService.applyDiscount($scope.orderList).then(function (response) {
            $scope.calculatedPrice = response.data;
            $('[data-remodal-id=orderDetail]').remodal().open();
        }, function () {
            alert("İşleminiz gerçekleşmedi, lütfen daha sonra tekrar deneyiniz.");
        })

    }

    $scope.orderDone = function () {
        orderProductService.orderDone($scope.calculatedPrice).then(function (response) {
            alert("Siparişiniz alınmıştır.");
            $scope.orderList = null;
            $('[data-remodal-id=orderDetail]').remodal().close();
        }, function () {
            alert("İşleminiz gerçekleşmedi, lütfen daha sonra tekrar deneyiniz.");
        });
    }


});