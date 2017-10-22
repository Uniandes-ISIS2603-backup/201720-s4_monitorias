(function (ng) {
    //Definición del módulo
var mod = ng.module("BibliotecasModule", ['ui.router']);

      mod.constant("bibliotecasContext", "api/bibliotecas");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/bibliotecas/';
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/bibliotecasList");
            //Definición del estado 'BibliotecaList' donde se listan las bibliotecas.
            $stateProvider.state('bibliotecasList', {
                //Url que aparecerá en el navegador
                url: '/bibliotecas/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bibliotecas.list.html',
                        controller: 'bibliotecasCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });
        }
    ]);

})(window.angular);

