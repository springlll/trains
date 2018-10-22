/**
 * 原js为Lorvens模板
 * 由原js修改
 */
//预操作
(function ($) {
    "use strict"; //严格模式

    // 侧边栏
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
        //侧边栏改变宽度，图表刷新适应新状态
        if ($('#pressureChart').length > 0) {
            setTimeout(function () {
                $('#pressureChart').highcharts().reflow();
            }, 250);
        }
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
})(jQuery);
//预操作结束