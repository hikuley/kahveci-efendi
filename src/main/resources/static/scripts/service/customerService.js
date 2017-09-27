app.service("customerService", function ($http) {

    this.login = function (request) {
        return $http.post("/api/customer/login", request);
    };

    this.signUp = function (request) {
        return $http.post("/api/customer/signUp", request);
    };

});