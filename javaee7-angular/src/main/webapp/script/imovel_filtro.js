var app = angular.module('imoveis', ['ngResource', 'ngGrid', 'ui.bootstrap']);

// Create a controller with name personsListController to bind to the grid section.


// controlador do filtro
app.controller('imoveisFilterController', function ($scope, $rootScope, imovelService) {
	
	   $scope.sortInfo = {fields: ['id'], directions: ['asc']};
	    $scope.imoveis = {paginaAtual: 1};
	    $scope.filterOpt = { filterTxt: ''};
	    $scope.entries = [];
	    $scope.filterName = '';
	    $scope.filterCity = '';
	    
	    $scope.gridOptions = {
	        data: 'imoveis.lista',
	        useExternalSorting: true,
	        sortInfo: $scope.sortInfo,
	        //filterOpt: $scope.filterOpt,
	        
	        columnDefs: [
	            { field: 'id', displayName: 'Id' },
	            { field: 'tipo', displayName: 'Tipo' },
	            { field: 'descricao', displayName: 'Descricao' },
	            { field: 'endereco', displayName: 'Endereco' }	            
	        ],
	        
	        filterOpt: { filterTxt: '', useExternalFilter: false}, showFilter: true,
	        multiSelect: false,
	        selectedItems: [],
	        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
	        afterSelectionChange: function (rowItem) {
	            if (rowItem.selected) {
	                $rootScope.$broadcast('imovelSelecionado', $scope.gridOptions.selectedItems[0].id);
	            }
	        }
	        	    
	    };
	    

	    // A modifiable limit, modify through newFilter so data is refiltered
	    $scope.lowerLimit = 50;
	
	
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
    
    // novos
    $scope.$watch('filterName', function (value) {

        setFilterText();
    });

    $scope.$watch('filterCity', function (value) {

        setFilterText();
    });

    function setFilterText()
    {
        $scope.filterOptions.filterText = 'Name: ' + $scope.filterName + ';City:' + $scope.filterCity;
    }
});


// Service that provides imovel operations
app.factory('imovelService', function ($resource) {
    return $resource('resources/imoveis/:id');
});

//Create a controller with name alertMessagesController to bind to the feedback messages section.
app.controller('alertMessagesController', function ($scope) {
   

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



