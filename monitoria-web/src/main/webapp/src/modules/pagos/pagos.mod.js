(function (ng) {
    //Definición del módulo
var mod = ng.module("PagosModule", ['ui.router']);

      mod.constant("pagosContext", "api/pagos");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/pagos/';
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/pagos");
            //Definición del estado 'monitor' donde se listan los monitores.
            $stateProvider.state('pagosList', {
                //Url que aparecerá en el navegador
                url: '/pagos',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'getPagos.html',
                        controller: 'pagosCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });
        }
    ]);

})(window.angular);
