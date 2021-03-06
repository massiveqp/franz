angular.module("piclubApp", []).controller("EditCtrl", ['$http', function($http){
	var me = this;

	me.places = [
        {label: '漾学堂', id: 1},
        {label: '猎学堂', id: 2},
        {label: '常乐书房', id: 3}
    ];

	me.halfPrices = [
        {label: '无', id: 0},
        {label: '有', id: 1}
    ];

	//retrieve act
    me.act = {};
    var pathArray = window.location.pathname.split('/'),
        actId = pathArray[2],
        url = '/activity/' + actId;

    me.actId = actId;

    $http.get(url).then(function(response) {
        me.act = response.data;

        // adapt price
        me.act.price /= 100.0;

        //set place & half price
        me.selectedPlaceId = me.act.place;
        me.selectedHalfPrice = me.act.halfPrice;

        //set date
        var dtp = $('#dtp-editAct')[0];
        dtp.value = me.act.startTime;

    }, function(errResponse) {
        console.error('Error while fetch activity ' + actId);
    });

	// Modify act
	me.modifyAct = function() {
		me.act.place = me.selectedPlaceId;
        me.act.halfPrice = me.selectedHalfPrice;

		var dtp = $('#dtp-editAct')[0];
		me.act.startTime = dtp.value;

		// Prevent price changing in the input. Otherwise, if the update fails, the price would be a wrong number.
		var modifiedAct = angular.copy(me.act);
		modifiedAct.price *= 100;

		$http.post('/activity/' + me.actId, modifiedAct).then(function succ(response) {
                alert('修改成功！');

                window.location.href = '/activity/' + me.actId + '/details';
            }, function fail(response) {
                alert('修改失败！');
		        console.log(response);
        });
	};

	me.upload = function() {
		console.log('uploading');
	};
}]);