app.service("orderProductService", function ($http) {

    this.getAllProduct = function () {
        return $http.get("/api/product/all");
    };

    this.applyDiscount = function (request) {
        return $http.post("/api/order/applyDiscount", request);
    };

    this.orderDone = function (request) {
        return $http.post("/api/order/orderDone", request);
    };

});