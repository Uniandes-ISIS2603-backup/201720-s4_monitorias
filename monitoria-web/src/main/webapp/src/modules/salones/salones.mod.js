(function (ng) {
    //Definición del módulo
var mod = ng.module("salonesModules", ['SedesModule','ui.router']);

    mod.constant("salonesContext","salones");
    mod.constant("sedesContext", "api/sedes");
     
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/salones/';
           
            //Mostrar la lista de sedes será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/salonesList");
            
            $stateProvider.state('salones',{
             url:'/salones',
             abstract: true,
             parent: 'sedeDetail',
             views:
             {
                 childrenView:
                 {
                     templateUrl: basePath + 'salones.html'
                 }
             }
            }).state('salonesList', {
                //Url que aparecerá en el navegador
                url: '/{sedeId:int}/list',
                parent:'salones',
                param:
                {
                    sedeId: null
                },
                views: 
                {
                    listView: 
                    {
                        templateUrl: basePath + 'salones.list.html',
                        controller: 'salonesCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('salonDelete',{
                        url: '{sedeId:int}/delete/{salonId:int}',
                        parent: 'salones',
                        param:
                        {
                            sedeId: null,
                            salonId:null
                        },
                        views:
                        {
                            listView:
                            {
                                templateUrl: basePath + 'delete/salones.delete.html',
                                controller:'salonDeleteCtrl'
                            }
                        }
            }).state('salonUpdate',{
                url:'/{sedeId:int}/update/{salonId:int}',
                parent:'salones'
                ,param:
                {
                    sedeId:null,
                    salonId:null
                }
                ,views:{
                    'detailView':{
                        templateUrl: basePath + 'update/salones.update.html'
                        ,controller: 'salonesUpdateCtrl'
                    }
                }
            });
        }
    ]);

})(window.angular);