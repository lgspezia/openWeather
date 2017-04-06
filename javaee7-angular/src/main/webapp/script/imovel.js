var app = angular.module('imoveis', ['ngResource', 'ngGrid', 'ui.bootstrap']);

// Create a controller with name personsListController to bind to the grid section.
app.controller('imoveisListController', function ($scope, $rootScope, imovelService) {
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.imoveis = {paginaAtual: 1};
    $scope.filterOpt = { filterTxt: '' , useExternalFilter: false};
    $scope.entries = [];
    
    $scope.gridOptions = {
        data: 'imoveis.lista',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,
        
        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'tipo', displayName: 'Tipo' },
            { field: 'descricao', displayName: 'Descricao' },
            { field: 'endereco', displayName: 'Endereco' },
            { field: '', width: 26, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        showFilter: true,
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('imovelSelecionado', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listImoveisArgs = {
            page: $scope.imoveis.paginaAtual,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        imovelService.get(listImoveisArgs, function (data) {
            $scope.imoveis = data;
        })
    };
    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('apagaImovel', row.entity.id);
    };

    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo.fields[0]', function () {
        $scope.refreshGrid();
    }, true);

    // Do something when the grid is sorted.
    // The grid throws the ngGridEventSorted that gets picked up here and assigns the sortInfo to the scope.
    // This will allow to watch the sortInfo in the scope for changed and refresh the grid.
    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });

    // Picks the event broadcasted when a person is saved or deleted to refresh the grid elements with the most
    // updated information.
    $scope.$on('refreshGrid', function () {
        $scope.refreshGrid();
    });

    // Picks the event broadcasted when the form is cleared to also clear the grid selection.
    $scope.$on('clear', function () {
        $scope.gridOptions.selectAll(false);
    });
});

// Create a controller with name personsFormController to bind to the form section.
app.controller('imoveisFormController', function ($scope, $rootScope, imovelService) {
    // Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.imovel = null;
        // For some reason, I was unable to clear field values with type 'url' if the value is invalid.
        // This is a workaroud. Needs proper investigation.
        document.getElementById('fotoUrl').value = null;
        // Resets the form validation state.
        $scope.imovelForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };

    
    // Calls the rest method to save a person.
    $scope.atualizaImovel = function () {
        imovelService.save($scope.imovel).$promise.then(
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a save message.
                $rootScope.$broadcast('imovelSalvo');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('erro!');
            });
    };

    // Picks up the event broadcasted when the person is selected from the grid and perform the person load by calling
    // the appropiate rest service.
    $scope.$on('imovelSelecionado', function (event, id) {
        $scope.imovel = imovelService.get({id: id});
    });

    // Picks us the event broadcasted when the person is deleted from the grid and perform the actual person delete by
    // calling the appropiate rest service.
    $scope.$on('apagaImovel', function (event, id) {
        imovelService.deletes({id: id}).$promise.then(						//////// trocado . por , ***
            function () {
                // Broadcast the event to refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // Broadcast the event to display a delete message.
                $rootScope.$broadcast('imovelApagado');
                $scope.clearForm();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });
    });
});

// Create a controller with name alertMessagesController to bind to the feedback messages section.
app.controller('alertMessagesController', function ($scope) {
    // Picks up the event to display a saved message.
    $scope.$on('imovelSalvo', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Imovel salvo com sucesso!' }
        ];
    });

    // Picks up the event to display a deleted message.
    $scope.$on('imovelApagado', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Imovel apagado com sucesso!' }
        ];
    });

    // Picks up the event to display a server error message.
    $scope.$on('error', function () {
        $scope.alerts = [
            { type: 'danger', msg: 'Houve um problema na conexao, tente novamente!' }
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
});

// controlador do filtro
app.controller('imoveisFilterController', function ($scope, $rootScope, imovelService) {
	
	   $scope.sortInfo = {fields: ['id'], directions: ['asc']};
	    $scope.imoveis = {paginaAtual: 1};
	    $scope.filterOpt = { filterTxt: ''};
	    
	    $scope.gridOptions = {
	        data: 'imoveis.lista',
	        useExternalSorting: true,
	        sortInfo: $scope.sortInfo,
	        filterOpt: $scope.filterOpt,

	        columnDefs: [
	            { field: 'id', displayName: 'Id' },
	            { field: 'tipo', displayName: 'Tipo' },
	            { field: 'descricao', displayName: 'Descricao' },
	            { field: 'endereco', displayName: 'Endereco' }	            
	        ],
	        
	        multiSelect: false,
	        selectedItems: [],
	        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
	        afterSelectionChange: function (rowItem) {
	            if (rowItem.selected) {
	                $rootScope.$broadcast('imovelSelecionado', $scope.gridOptions.selectedItems[0].id);
	            }
	        }
	        
	};
	
	// Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listImoveisArgs = {
            page: $scope.imoveis.paginaAtual,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        imovelService.get(listImoveisArgs, function (data) {
            $scope.imoveis = data;
        })
    };

    $scope.$watch('sortInfo.fields[0]', function () {
        $scope.refreshGrid();
    }, true);
    
    $scope.buscaImovel = function() {
    	lista = $scope.imoveis;
    	alert(" alerta! " + imoveis.lista + " busca! " );
        return $scope.imovel + " busca! " ;
    }; 
    
   
    $scope.buscarImovel = function(name){
    	//alert(" alerta! ");
    	$scope.greeting = 'hello';
    	$scope.search =  name;
    	$scope.imovel;
        lista = $scope.imoveis;
        if(name != null){
        	for ( var imv in $scope.lista) {
           	 if($scope.search == imv) {
           		 return $scope.search
	           	 } else {
	           		 return $scope.lista = {};
	           	 }
        	}  
        }
       
    else{
      return "Nome nao existente";
        }
    };
    
});

// Service that provides imovel operations
app.factory('imovelService', function ($resource) {
    return $resource('resources/imoveis/:id');
});
