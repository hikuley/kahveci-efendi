
$(document).ready(function () {

    $.toogleLogin = function () {
        var name = $(".toggle span").text() == "Login" ? "Register" : "Login";
        $(".toggle span").text(name);
        // Switches the forms
        $('.form').animate({
            height: "toggle",
            'padding-top': 'toggle',
            'padding-bottom': 'toggle',
            opacity: "toggle"
        }, "slow");
    }

});