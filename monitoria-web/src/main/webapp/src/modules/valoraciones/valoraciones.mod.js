/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
var mod = ng.module("ValoracionesModule", ['ui.router']);

      mod.constant("valoracionesContext", "api/valoraciones");
    
     
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
          
            var basePath = 'src/modules/valoraciones/';
            
            $urlRouterProvider.otherwise("/valoraciones");
            
            $stateProvider.state('valoracionesList', {
               
                url: '/valoraciones',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'getValoraciones.html',
                        controller: 'valoracionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }           
                    }).state('deleteValoracion', {
                       url: '/valoraciones/:id',
                       param:{id:null},
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/valoraciones.delete.html',
                        controller: 'valoracionDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }  
            });
        }
    ]);

})(window.angular);

