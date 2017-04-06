/**
 * 
 */
var app = angular.module('myApp', ['ngGrid']);
    app.controller('MyCtrl', function($scope) {
      $scope.filterOptions = {
        filterText: ''
      };

      $scope.myData = [{name: "Moroni", age: 50},
                       {name: "Tiancum", age: 43},
                       {name: "Jacob", age: 27},
                       {name: "Nephi", age: 29},
                       {name: "Enos", age: 34}];

      $scope.gridOptions = {
        data: 'myData',
        filterOptions: $scope.filterOptions
      };
    });

app.controller('imoveisListController', function ($scope, $rootScope, imovelService) {
	
	$scope.sortInfo = {fields: ['id'], directions: ['asc']};
    
	$scope.gridOptions = {
	        data: 'imoveis.lista',
	 
	        columnDefs: [
	            { field: 'id', displayName: 'Id' },
	            { field: 'tipo', displayName: 'Tipo' },
	            { field: 'descricao', displayName: 'Descricao' },
	            { field: 'endereco', displayName: 'Endereco' },
	            { field: '', width: 26, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
	        ]
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
		
});

function ImovelSearchController ($scope) {
	$scope.items = [
	                {
	        			url: 'imovel.tipo',
	        			title: 'imovel.proprietario',
	        			image: 'imovel.fotoUrl'
	        		}
	               ];
}


angular.module('sortApp', [])
.controller('mainController', function($scope) {
  $scope.sortType     = 'name'; // set the default sort type
  $scope.sortReverse  = false;  // set the default sort order
  $scope.searchFish   = '';     // set the default search/filter term
  
  $scope.sushi = [
                  { name: 'Cali Roll', fish: 'Crab', tastiness: 2 },
                  { name: 'Philly', fish: 'Tuna', tastiness: 4 },
                  { name: 'Tiger', fish: 'Eel', tastiness: 7 },
                  { name: 'Rainbow', fish: 'Variety', tastiness: 6 }
                ];
  
});


var myApp = angular.module('googleMapApp', ['ui.bootstrap','ngGrid']);

myApp.controller('MyCtrl', function($scope,$http) {
    $scope.pagingOptions = {
      pageSizes: [3, 5, 8],
      pageSize: 3,
      currentPage: 1
    };
    $scope.totalServerItems = 0;
    $scope.setPagingData = function(data,page,pageSize){
      var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
      $scope.myData = pagedData;
      $scope.totalServerItems = data.length;
      if (!$scope.$$phase) {
        $scope.$apply();
      }
    };
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
      setTimeout(function () {
        var data;
        if (searchText) {
          var ft = searchText.toLowerCase();
          $http.get('../wp-content/themes/ang/json-grid.php').success(function (largeLoad) {
            data = largeLoad.filter(function(item) {
              return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
            });
            $scope.setPagingData(data,page,pageSize);
          });
        } else {
          $http.get('../wp-content/themes/ang/json-grid.php').success(function (largeLoad) {
            $scope.setPagingData(largeLoad,page,pageSize);
          });
        }
      }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
      if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
      }
    }, true);

    $scope.$watch('filterOptions', function (newVal, oldVal) {
      if (newVal !== oldVal) {
        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
      }
    }, true);

    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    };
    $scope.gridOptions = {
      data: 'myData',
      enablePaging: true,
      showFooter: true,
      totalServerItems: 'totalServerItems',
      pagingOptions: $scope.pagingOptions,
      filterOptions: $scope.filterOptions
    };
});

angular.module('myReverseFilterApp', []).filter('reverse', function() {
	  return function(input, uppercase) {
	    input = input || '';
	    var out = "";
	    for (var i = 0; i < input.length; i++) {
	      out = input.charAt(i) + out;
	    }
	    // conditional based on optional argument
	    if (uppercase) {
	      out = out.toUpperCase();
	    }
	    return out;
	  };
	})
	.controller('MyController', ['$scope', function($scope) {
	  $scope.greeting = 'hello';
	}]);