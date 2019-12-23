var app = angular.module("CompanyManagement", []);
 
// Controller Part
app.controller("CompanyController", function($scope, $http) {
 
 
    $scope.companies = [];
    $scope.companyForm = {
    	id: 1,
        name: ""
    };
 
    // Now load the data from server
    _refreshCompanyData();
 
    // HTTP POST/PUT methods for add/edit company  
    // Call: http://localhost:8080/company
    $scope.submitCompany = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.companyForm.id == -1) {
            method = "POST";
            url = '/company';
        } else {
            method = "PUT";
            url = '/company';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.companyForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createCompany = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete company by id
    // Call: http://localhost:8080/company/{id}
    $scope.deleteCompany = function(company) {
        $http({
            method: 'DELETE',
            url: '/company/' + company.id
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editCompany = function(company) {
        $scope.companyForm.id = company.id;
        $scope.companyForm.name = company.name;
    };
 
    // Private Method  
    // HTTP GET- get all employees collection
    // Call: http://localhost:8080/companies
    function _refreshCompanyData() {
        $http({
            method: 'GET',
            url: '/companies'
        }).then(
            function(res) { // success
                $scope.companies = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
    	_refreshCompanyData();
        _clearFormData();
    }
 
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
 
    // Clear the form
    function _clearFormData() {
        $scope.companyForm.id = -1;
        $scope.companyForm.name = ""
    };
});