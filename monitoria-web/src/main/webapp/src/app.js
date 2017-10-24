(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
       
        // Internal modules dependencies       
        'BibliotecasModule',
        'MonitoresModule',
        'PagosModule',
        'SedesModule',
	'IdiomasModule',
        'MonitoriasModule',
        'ValoracionesModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);

