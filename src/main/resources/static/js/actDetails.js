angular.module("piclubApp", []).controller("actDetailsCtrl", ['$http', function($http){
	var me = this;

    me.places = [
        {label: '漾学堂', id: 1},
        {label: '猎学堂', id: 2},
        {label: '常乐书房', id: 3}
    ];

	//retrieve act details
    me.act = {};
    var pathArray = window.location.pathname.split('/'),
        actId = pathArray[2],
        url = '/activity/' + actId;
    me.actId = actId;

    $http.get(url).then(function(response) {
        me.act = response.data;

        // adapt price and place
        me.act.price /= 100.0;
        for (var i = 0; i < me.places.length; i++) {
            if (me.places[i].id !== me.act.place) continue;
            me.act.place = me.places[i].label;
            break;
        }
    }, function(errResponse) {
        console.error('Error while fetching activity ' + actId);
    });

    // retrieve all enrollments
    me.enrollments = {};
    me.refreshEnroll = function() {
        $http.get('/enrollments?actId=' + me.actId).then(function (resp) {
            me.enrollments = resp.data;
            console.log(me.enrollments);

        }, function (reason) {
            alert('获取报名信息失败！');
            console.log('Error when fetching enrollments!');
        });
    };

    me.refreshEnroll();

    // Enroll by username & actId
    me.enrollment = {};
    me.enrollment.activityId = me.actId;
    me.enroll = function () {
        $http.post('/enrollments', me.enrollment).then(function (value) {
            alert('报名成功！');
            me.refreshEnroll();

        }, function (reason) {
            alert('报名失败！');
        });

        $('#enrollModal').modal('toggle');
    };

    me.cancelEnroll = function () {

    };

	me.deleteAct = function () {
        console.log('delete act!...');
        $http.delete('/activity/' + me.actId, null).then(function succ(response) {
            alert('删除成功！');

            window.location.href = '/';
        }, function fail(response) {
            alert('删除失败！');
            console.log(response);
        });
    }
}]);