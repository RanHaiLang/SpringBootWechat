$(function () {
    /*日期选择器格式*/
    $('#datetimepicker').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left",
        startDate: "2000-11-21 10:00",
        minuteStep: 30,
        minView: 1,
    });
    $("#datetimepicker-start").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left",
        startDate: "2000-11-21 10:00",
        minuteStep: 30,
        minView: 1,
    });
    $("#datetimepicker-end").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left",
        startDate: "2000-11-21 10:00",
        minuteStep: 30,
        minView: 1,
    });
})

