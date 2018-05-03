(function () {

    // Enter Global Config Values & Instantiate ADAL AuthenticationContext
    window.config = {
        instance: 'https://login.partner.microsoftonline.cn/',
        tenant: 'common',
        clientId: 'd4d74ed3-c458-4c23-92b9-358edb803395',
        postLogoutRedirectUri: window.location.origin,
        cacheLocation: 'localStorage', // enable this for IE, as sessionStorage does not work for localhost.
    };
    var authContext = new AuthenticationContext(config);

    var $freeAccountLink = $('#free_link');
    var $signInButton = $("#login_link");
    var $signOutButton = $("#logout_link");
    var $errorMessage = $(".app-error");

    // Check For & Handle Redirect From AAD After Login
    var isCallback = authContext.isCallback(window.location.hash);
    authContext.handleWindowCallback();
    $errorMessage.html(authContext.getLoginError());

    if (isCallback && !authContext.getLoginError()) {
        window.location = authContext._getItem(authContext.CONSTANTS.STORAGE.LOGIN_REQUEST);
    }

    // Check Login Status, Update UI
    var user = authContext.getCachedUser();
    if (user) {
        $freeAccountLink.addClass("hidden");
        $signInButton.addClass("hidden");
        $signOutButton.removeClass("hidden");
    } else {
        $freeAccountLink.removeClass("hidden");
        $signInButton.removeClass("hidden");
        $signOutButton.addClass("hidden");
    }

    // Register NavBar Click Handlers
    $signOutButton.click(function () {
        authContext.logOut();
    });
    $signInButton.click(function () {
        authContext.login();
    });
}());
