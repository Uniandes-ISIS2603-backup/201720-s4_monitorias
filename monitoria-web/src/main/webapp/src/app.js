(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
       
        // Internal modules dependencies       
        'BibliotecasModule',
        'recursosModules',
        'MonitoresModule',
        'PagosModule',
        'SedesModule',
        'HorariosModule',
        'CalendarioModule',
        'EstudiantesModule',
        'salonesModules',
	'IdiomasModule',
        'MonitoriasModule',
        'ValoracionesModule',
        'ActividadesModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);

