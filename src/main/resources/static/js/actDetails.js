angular.module("piclubApp", []).controller("actDetailsCtrl", ['$http', function($http){
	var me = this;

	//todo create a codeStore
    me.places = [
        {label: '漾学堂', id: 1},
        {label: '猎学堂', id: 2},
        {label: '常乐书房', id: 3}
    ];

    me.payStatusEnum = [
        '未付款',
        '已付款'
    ];

    me.halfPriceFlag = [
        '无',
        '有'
    ];

    me.makerFlags = [
        {label: '否', id: 0},
        {label: '是', id: 1}
    ];
    me.selectedMaker = 0;

	//retrieve act details
    me.act = {};
    var pathArray = window.location.pathname.split('/'),
        actId = pathArray[2],
        url = '/activity/' + actId;
    me.actId = actId;

    $http.get(url).then(function(response) {
        me.act = response.data;

        // adapt price and place & half price
        me.act.price /= 100.0;

        for (var i = 0; i < me.places.length; i++) {
            if (me.places[i].id !== me.act.place) continue;
            me.act.place = me.places[i].label;
            break;
        }

        me.act.halfPrice = me.halfPriceFlag[me.act.halfPrice]

    }, function(errResponse) {
        console.error('Error while fetching activity ' + actId);
    });

    // retrieve all enrollments
    me.enrollments = {};
    me.refreshEnroll = function() {
        $http.get('/enrollments?actId=' + me.actId).then(function (resp) {
            me.enrollments = resp.data;

            // todo adapt user level
            for (var i = 0; i < me.enrollments.length; i++) {
                var code = me.enrollments[i].userLevel;
                me.enrollments[i].userLevel = me.makerFlags[code].label;
            }

        }, function (reason) {
            alert('获取报名信息失败！');
            console.log('Error when fetching enrollments!');
        });
    };

    me.refreshEnroll();

    // Enroll by username & actId
    me.enrollment = {};
    me.enroll = function () {
        me.enrollment.activityId = me.actId;
        me.enrollment.userLevel = me.selectedMaker;

        $http.post('/enrollments', me.enrollment).then(function (value) {
            alert('报名成功！');
            me.refreshEnroll();

        }, function (reason) {
            alert('报名失败！');
        });

        $('#enrollModal').modal('toggle');

        //clear inputs
        me.enrollment = {};
    };

    // Cancel enroll by enrollment ID
    me.cancelEnrollId = undefined;
    me.cancelEnroll = function () {
        $http.delete('/enrollment/' + me.cancelEnrollId).then(function (value) {
            alert('撤销成功');
            me.refreshEnroll();
            // clear data field in modal
            me.cancelEnrollId = null;
        }, function (reason) {
            alert('撤销失败')
        });

        $('#cancelEnrollModal').modal('toggle');
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