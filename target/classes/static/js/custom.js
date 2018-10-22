/**
 * 原js为Lorvens模板
 * 由原js修改
 */

//图表
var chart;

//pressurePageData
var ppd;

//各种试验
var breakTests = [];

//股道
var tracks = [];

//信号灯
var lightData;

//刷新时间
var refreshTime = 1500;

var chartDataSelected = "";

//预操作
(function ($) {
    "use strict"; //严格模式

    // 侧边栏
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
        //侧边栏改变宽度，图表刷新适应新状态
        setTimeout(function () {
            $('#pressureChart').highcharts().reflow();
        }, 250);
    });
    var url = window.location.pathname;
    var parent = $("[href='" + url + "']").parent();
    if (!parent.hasClass("nav-level-top")) {
        parent = parent.parent().parent();
    }
    parent.addClass("active");

    // 加载动画
    var window_var = $(window);
    window_var.on('load', function () {
        $(".loading").fadeOut("fast");
    });

    // 滚动到顶部
    $(window).scroll(function () {
        if ($(this).scrollTop() > 50) {
            $('#back-to-top').fadeIn();
        } else {
            $('#back-to-top').fadeOut();
        }
    });

    // 使body滚动到0px位置
    $('#back-to-top').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 800);
        return false;
    });

    // 主题换色
    var theme_settings = $(".theme-settings").find(".theme-color");
    theme_settings.on('click', function () {
        var val = $(this).attr('data-color');
        $('#style_theme').attr('href', '/css/' + val + '.css');
        $(".logo-change").attr('src', 'img/logo-' + val + '.png');

        theme_settings.removeClass('theme-active');
        theme_settings.addClass('theme-active');
        return false;
    });
    $(".theme-click").on('click', function () {
        $("#switcher").toggleClass("active");
        return false;
    });

    //根据显示股道数调整行高
    var signalTable = $(".signal-table");
    var lineCount = signalTable.find("tr").length;
    if (lineCount < 10) {
        lineCount = 10;
    }
    if (lineCount > 18) {
        lineCount = 18;
    }
    signalTable.css("line-height", 3.1 - (lineCount - 10) / 5);

    // 信号灯悬浮
    $("[data-toggle='popover']").popover({
        html: true,
        title: getLightDataDetailTitle(),
        trigger: "click",
        placement: "bottom",
        delay: {show: 10, hide: 10},
        container: "body",
        content: getLightDataDetailHtml()
    }).on("show.bs.popover", function () {
        $("[data-toggle='popover']").not(this).popover('hide');
        setTimeout(function () {
            $(".popover-body").css("padding-top", "0px").css("padding-bottom", "0px").css("padding-left", "0px").css("padding-right", "0px");
            $(".popover-header").css("background-color", "#707070");
        }, 50);
    }).on("shown.bs.popover", function () {
        refreshPopover();
    });

    //图表数据悬浮
    $("#chartTitle").popover({
        html: true,
        title: "<span class='text-light'>选区数据</span>",
        trigger: "manual",
        placement: "top",
        container: "body",
        content: getChartDataSelected()
    }).on("show.bs.popover", function () {
        setTimeout(function () {
            $(".popover-body").css("padding-top", "0px").css("padding-bottom", "0px").css("padding-left", "0px").css("padding-right", "0px");
            $(".popover-header").css("background-color", "#707070");
        }, 50);
    });

    // 悬浮窗点击别处关闭
    $('body').on('click', function (event) {
        var target = $(event.target);
        if (!target.hasClass('popover') //弹窗内部点击不关闭
            && target.parents('.popover-content').length === 0
            && target.parents('.popover-header').length === 0
            && target.parents('.popover').length === 0/*
            && target.parent('td').parent('tr').parent("tbody").parent(".pop-table").length === 0
            && target.parent('tr').parent("tbody").parent(".pop-table").length === 0
            && target.parent("tbody").parent(".pop-table").length === 0
            && target.parent(".pop-table").length === 0*/
            && target.data("toggle") !== "popover") {
            //弹窗触发列不关闭，否则显示后隐藏
            $('[data-toggle="popover"]').popover('hide');
            $("#pressureChart").popover("hide");
        }
    });

    //股道选中
    $("#dataTables tbody tr").on("click", function () {
        if (!$(this).hasClass("track-selected")) {
            var track = $(this).attr("track");
            $(this).parent().find("tr.track-selected").toggleClass("track-selected");//取消原先选中行
            $(this).toggleClass("track-selected");//设定当前行为选中行
            $("#pressureShowingTrack").text("切换中");
            reloadBreakTests($(this).find(".test-mode-select").val(), track);
        }
    });

    $(".light-btn").on("click", function (event) {
        event.stopPropagation();
    });

    $("select").on("click", function (event) {
        event.stopPropagation();
    });

    //试风数据图
    Highcharts.setOptions({global: {useUTC: false}});
    chart = Highcharts.chart('pressureChart', {
        chart: {
            zoomType: 'x', // 水平缩放
            height: 400,
            resetZoomButton: {
                relativeTo: "plot",
                position: {
                    align: "center",
                    verticalAlign: "bottom",
                    y: -40
                }
            },
            events: {
                selection: function (e) {
                    if (e.resetSelection) {
                        $("#chartTitle").popover("hide");
                        return;
                    }
                    var series = e.target.series;
                    for (var i = 0; i < series.length; i++) {
                        if (series[i].name === "风压值") {
                            var s = series[i];
                            setTimeout(function () {
                                var max = s.dataMax;
                                var min = s.dataMin;
                                var startValue = s.processedYData[0];
                                var endValue = s.processedYData[s.processedYData.length - 1];
                                var minus = Math.abs(s.processedYData[0] - s.processedYData[s.processedYData.length - 1]);
                                var xTime = new Date(s.processedXData[s.processedXData.length - 1] - s.processedXData[0] - 3600000 * 8).format('h:mm:ss');
                                $("#chartTitle").popover("show");
                                $(".popover-chart-data").html("<table class='table table-bordered table-hover custom-table pop-table'><tbody>" +
                                    "<tr><td>最高</td><td class='bg-white'>" + max + "</td><td>起始</td><td class='bg-white'>" + startValue + "</td><td>差值</td><td class='bg-white'>" + minus + "</td></tr>" +
                                    "<tr><td>最低</td><td class='bg-white'>" + min + "</td><td>结束</td><td class='bg-white'>" + endValue + "</td><td>区间</td><td class='bg-white'>" + xTime + "</td></tr>" +
                                    "</tbody></table>");
                            }, 50);
                        }
                    }
                }
            }
        },
        title: {
            text: null
        },
        xAxis: {
            crosshair: true, // 启用星线
            type: 'datetime',
            dateTimeLabelFormats: {
                day: '%H:%M:%S'
            },
            tickIntervalPixel: 80 //刻度步长 像素
        },
        yAxis: {
            title: null,
            tickPositions: [0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700]
        },
        tooltip: {
            shared: true, //合并提示框
            valueSuffix: 'kPa' //单位
        },
        legend: {
            enabled: false // 关闭图例
        },
        credits: {
            enabled: false
        },
        plotOptions: {
            series: {
                cursor: 'pointer',
                events: {
                    click: function (e) {
                        console.log(e.point.x + " " + e.point.y);
                        console.log(e.point);
                    }
                }
            }
        }
    });
})(jQuery);
//预操作结束

//获取pressurePageData，并初始化图表内容
function initParam(param) {
    ppd = param;
    breakTests = ppd.breakTests;
    tracks = ppd.tracks;
    initBreakTestFillColor();
    var tr1 = $("#dataTables>tbody>tr").first().toggleClass("track-selected");
    var track = tr1.attr("track");
    $("#pressureShowingTrack").text(track);
    reloadBreakTests($(this).find("#testMode_" + track).value, track);
    doRefresh();
}

//初始化图表内容样式
function initBreakTestFillColor() {
    for (var i = 0; i < breakTests.length; i++) {
        if (breakTests[i] != null && breakTests[i].status == 1) {
            chart.addSeries({
                id: breakTests[i].name,
                name: breakTests[i].name,
                type: 'arearange',
                fillColor: breakTests[i].fillColor,
                lineColor: breakTests[i].fillColor,
                fillOpacity: 0.3,
                marker: {
                    states: {
                        hover: {
                            enabled: false //关闭鼠标指向时的点显示
                        }
                    },
                    enabled: false //关闭点显示
                }
            });
        }
    }
    chart.addSeries({
        id: "mainPressure",
        name: "风压值",
        type: "spline",
        color: "#f7a35c",
        cropThreshold: 1,
        allowPointSelect: true,
        marker: {
            symbol: "circle",
            enabled: false
        }
    });
}

//执行延时刷新
function doRefresh() {
    setTimeout(function () {
        var track = $(".track-selected").first().attr("track");
        refreshScreen(track);
    }, refreshTime);
}

//刷新屏幕内容
function refreshScreen(track) {
    var _track = track;
    if (_track == null) {
        doRefresh();
    }
    $.ajax({
        type: "GET",
        url: "get-realtime-pressure-data/" + _track,
        dataType: "json",
        success: function (data) {
            //峰值与实时值
            var realtimePressureMap = data.realtimePressureMap;
            var peakPressureMap = data.peakPressureMap;
            lightData = data.lightData;
            if (realtimePressureMap.length == 0 && lightData.length == 0) {
                refreshTime = 4000;
            }
            else {
                refreshTime = 1500;
            }
            //信号灯按钮与压力显示
            var btns = $(".light-info");
            btns.addClass("invisible");
            btns.addClass("btn-dark");
            for (var t in tracks) {
                if (peakPressureMap[t] != null) {
                    $("#pressurePeak_" + t).text(peakPressureMap[t]);
                }
                else {
                    $("#pressurePeak_" + t).text("-");
                }
                if (realtimePressureMap[t] != null) {
                    $("#pressureRealtime_" + t).text(realtimePressureMap[t]);
                    btns.filter("[track='" + t + "']").removeClass("invisible");
                }
                else {
                    $("#pressureRealtime_" + t).text("-");
                }
            }
            for (var i = 0; i < lightData.length; i++) {
                btns.each(function () {
                    var btn = $(this);
                    if (btn.attr("track") == lightData[i].track) {
                        btn.removeClass("invisible");
                        if (btn.attr("direction") == lightData[i].direction) {
                            btn.removeClass("btn-dark");
                            btn.removeClass("btn-danger");
                            btn.removeClass("btn-warning");
                            btn.removeClass("btn-outline-dark");
                            if (lightData[i].inPlaceTime != "-") {
                                btn.addClass("btn-warning");
                            } else {
                                btn.addClass("btn-dark");
                            }
                            if (lightData[i].upSignalTime != "-") {
                                btn.addClass("btn-danger");
                            }
                            if (lightData[i].downSignalTime != "-") {
                                btn.addClass("btn-outline-dark");
                            }
                        }
                    }
                });
            }
            refreshPopover();
            //图表
            var pressureData = data.pressuresAndTime;
            for (var i = 0; i < breakTests.length; i++) {
                if (breakTests[i].status == 1) {
                    if (pressureData.length > 0) {
                        //一分钟更新一次图表右边界
                        var timestart = pressureData[0][0];
                        var timeend = pressureData[pressureData.length - 1][0];
                        var xtimeend = Math.ceil(timeend / (60 * 1000)) * 60 * 1000 + 60 * 1000;
                        //各个试验的范围涂色
                        chart.get(breakTests[i].name).update({
                            data: [[timestart, breakTests[i].min, breakTests[i].max], [xtimeend, breakTests[i].min, breakTests[i].max]]
                        });
                    }
                    else {
                        //为空则不显示
                        chart.get(breakTests[i].name).update({
                            data: pressureData
                        });
                    }
                }
            }
            //试风数据图数据
            chart.get("mainPressure").update({
                lineColor: "#f7a35c",
                data: pressureData
            });
            //界面其他数据元素
            var selectedData = data.realtimePressureData;
            var selected = $(".track-selected").first().attr("track");
            if (selectedData != null) {
                if (selectedData.track == selected) {
                    $("#pressureShowingTrack").text(selected);
                }
                $("#pressureShowingMachineNumber").text(selectedData.machineNumber);
                $("#pressureShowingVoltage").text(selectedData.voltage);
                $("#pressureShowingPressure").text(selectedData.pressure);
            }
            else {
                $("#pressureShowingTrack").text(selected);
                $("#pressureShowingMachineNumber").text("无");
                $("#pressureShowingVoltage").text("0.0");
                $("#pressureShowingPressure").text("0");
            }
        },
        error: function (xhr, status, error) {
            console.log("无法连接到服务器");
        },
        complete: function (xhr, status) {
            doRefresh(); //循环
        }
    });
}

//刷新悬浮窗
function refreshPopover() {
    var btn = $("button[aria-describedby^='popover']").first();
    var track = btn.attr("track");
    var directionName = btn.attr("directionName");
    var direction = btn.attr("direction");
    $(".popover-title-track").text(track);
    $(".popover-title-direction").text(directionName);
    var ld;
    for (var i = 0; i < lightData.length; i++) {
        if (lightData[i].track == track && lightData[i].direction == direction) {
            ld = lightData[i];
        }
    }
    if (ld != null) {
        $(".popover-table-name").text(ld.workerName);
        $(".popover-table-voltage").text(ld.voltage + "V");
        switch (ld.lightStatus) {
            case 2:
                $(".popover-table-light").text("灯闪");
                break;
            case 0:
                $(".popover-table-light").text("灯灭");
                break;
            case 1:
                $(".popover-table-light").text("灯亮");
                break;
        }
        $(".popover-table-in").text(ld.inPlaceTime);
        $(".popover-table-up").text(ld.upSignalTime);
        $(".popover-table-down").text(ld.downSignalTime);
        $(".popover-table-signal").text(ld.signalValue);
        $(".popover-table-train").text(ld.trainStatus);
        ld = null;
    }
}

function reloadBreakTests(selectedTestModeId, track) {
    if (selectedTestModeId != null && $(".track-selected").attr("track") == track) {
        var testModes = ppd.testModes;
        $(".break-test-tr").addClass("tr-hidden");
        for (var i = 0; i < testModes.length; i++) {
            if (testModes[i].id == selectedTestModeId) {
                var ids = testModes[i].breakTestIds.split(",");
                for (var j = 0; j < ids.length; j++) {
                    $(".break-test-tr").each(function () {
                        if (ids[j] == $(this).attr("break-test-id")) {
                            $(this).removeClass("tr-hidden");
                        }
                    });
                }
            }
        }
    }
}

//悬浮窗标题栏
function getLightDataDetailTitle() {
    return "<span class='text-light'>信号灯数据&emsp;第&ensp;<span class='text-warning popover-title-track'>-</span>&ensp;股道&emsp;<span class='text-warning popover-title-direction'>未知</span></span>";
}

//悬浮窗内容栏
function getLightDataDetailHtml(dataId) {
    return "<table class='table table-bordered table-hover custom-table pop-table'><tbody>" +
        "<tr><td><b>作业者</b></td><td class='bg-white popover-table-name'>未打卡</td><td><b>电压</b></td><td class='bg-white popover-table-voltage'>-</td></tr>" +
        "<tr><td><b>到位</b></td><td class='bg-white popover-table-in'>-</td><td><b>状态</b></td><td class='bg-white popover-table-light'>离线</td></tr>" +
        "<tr><td><b>上信号</b></td><td class='bg-white popover-table-up'>-</td><td><b>下信号</b></td><td class='bg-white popover-table-down'>-</td></tr>" +
        "<tr><td><b>信号值</b></td><td class='bg-white popover-table-signal'>-</td><td><b>车状态</b></td><td class='bg-white popover-table-train'>-</td></tr>" +
        "<tr><td colspan='4'>" +
        "<button class='btn btn-sm btn-primary light-btn'>历史照片</button>&ensp;" +
        "<button class='btn btn-sm btn-info light-btn'>检测状态</button>&ensp;" +
        "<button class='btn btn-sm btn-dark light-btn'>关机</button></td></tr>" +
        "<tr><td colspan='4' class='bg-white'>暂无照片</td></tr>" +
        "</tbody></table>";
}

//选中图表数据表格
function getChartDataSelected() {
    return "<div class='popover-chart-data'></div>";
}

//日期格式化
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};