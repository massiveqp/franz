angular.module("piclubApp", []).controller("activityCtrl", [function(){
	var me = this;
	me.submit = function(){
		console.log('Hello moto');
	};

	me.upload = function(){
		console.log('uploading');
	}
}]);