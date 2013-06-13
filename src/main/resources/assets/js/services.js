angular.module('library.services', [ 'ngResource' ])

.factory('bookService', function($resource) {
	return $resource('library/book/:bookId', {}, {});
})

.service('userService', function() {
	this.currentUser = null;
	this.isLoggedIn = function() { return this.currentUser !== null; };
})

.factory('loginService', function($http) {
	return {
		setHeaders : function(username, password) {
			$http.defaults.headers.common['Authorization'] = 'Basic ' + Base64.encode(username + ':' + password);
		}
	};
	
});

