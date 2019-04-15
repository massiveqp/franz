angular.module("piclubApp", []).controller("FinanceCtrl", ['$http', function($http){
	var me = this;

	me.payStatusEnum = [
        '未付款',
        '已付款'
    ];

    me.makerFlags = [
        {label: '否', id: 0},
        {label: '是', id: 1}
    ];

	//retrieve act
    me.act = {};
    var pathArray = window.location.pathname.split('/'),
        actId = pathArray[2],
        url = '/activity/' + actId;
    me.actId = actId;

    me.getAct = function () {
        $http.get('/activity/' + actId).then(function (resp) {
            console.log(resp.data);
            me.act = resp.data;
        }, function (reason) {
            console.log(reason);
        });
    };
    me.getAct();


    // retrieve all enrollments & count makers
    me.enrollments = [];
    me.makerCnt = 0;
    me.refreshEnroll = function() {
        $http.get('/enrollments?actId=' + me.actId).then(function (resp) {
            me.enrollments = resp.data;

            //todo create a codeStore & adapt user level
            for (var i = 0; i < me.enrollments.length; i++) {
                var code = me.enrollments[i].payStatus;
                me.enrollments[i].payStatusLabel = me.payStatusEnum[code];

                var isMaker = me.enrollments[i].userLevel;
                if (isMaker === 1) me.makerCnt++;
                me.enrollments[i].userLevelLabel = me.makerFlags[isMaker].label;
            }
        }, function (reason) {
            alert('获取报名信息失败！');
            console.log('Error when fetching enrollments! reason:' + reason);
        });
    };

    me.refreshEnroll();
    
    me.togglePayStatus = function (enrollment) {
        if (enrollment.payStatus === 1) enrollment.payStatus = 0;
        else enrollment.payStatus = 1;

        $http.post('/enrollment/' + me.actId + '/payStatus', enrollment).then(function (resp) {
            me.refreshEnroll();
        }, function (reason) {
            alert('改变支付状态失败！')
            console.log(reason)
        });
    };
    
    me.toggleCheckedIn = function (enrollment) {

    };

    //
    me.calc = {};
    me.calculate = function () {
        console.log(me.act);
        var price = me.act.price / 100;
        var manCount = me.enrollments.length;

        console.log('makerCnt: ' + me.makerCnt);

        var actIncome = price / 2 * me.makerCnt + price * (manCount - me.makerCnt) - (me.calc.cost === undefined ? 0 : me.calc.cost);
        var incomeToTransfer = actIncome + (me.calc.otherIncome === undefined ? 0 : me.calc.otherIncome);

        alert(me.act.activityName
            + '\n总人数' + manCount + '，创客' + me.makerCnt + '，非创客' + (manCount - me.makerCnt)
            + '\n活动创收：' + actIncome + '\n应转总计：' + incomeToTransfer);

        $('#calcModal').modal('toggle');
        me.calc.otherIncome = undefined;
        me.calc.cost = undefined;
    }

}]);