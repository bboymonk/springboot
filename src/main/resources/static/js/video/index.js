"use strict";
$(document).ready(function () {
    var bannerSwiper = new Swiper("#banner-swiper", {
        pagination: ".swiper-pagination",
        nextButton: ".swiper-button-next",
        prevButton: ".swiper-button-prev",
        paginationClickable: !0,
        loop: !0,
        autoplay: 5e3
    });
    $("#banner-swiper .swiper-slide").hover(function () {
        bannerSwiper.stopAutoplay()
    }, function () {
        bannerSwiper.startAutoplay()
    }), $("#mf-drawer .navigation li").eq(0).addClass("active");
    var nextPage = 1, amount = 6, list = $("#latest .list"), hasMore = !0, loadContent = function () {
        var page = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0, amount = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 6;
        $.ajax({
            url: "/news/getList",
            type: "GET",
            dataType: "json",
            data: {category: "2", cur_page: page, per_page: amount}
        }).done(function (res) {
            if (jlutil.displayResponse("loading more for /index/index", res), "1" == res.status) {
                for (var _iterator = res.data.list, _isArray = Array.isArray(_iterator), _i = 0, _iterator = _isArray ? _iterator : _iterator[Symbol.iterator](); ;) {
                    var _ref;
                    if (_isArray) {
                        if (_i >= _iterator.length)break;
                        _ref = _iterator[_i++]
                    } else {
                        if (_i = _iterator.next(), _i.done)break;
                        _ref = _i.value
                    }
                    var article = _ref, newBlock = jlutil.createBlock(article);
                    list.append(newBlock)
                }
                res.data.list.length < amount ? (hasMore = !1, $(".btn-load").removeClass("show"), nextPage > 1 && $(".bottom-line").addClass("show")) : ($(".btn-load").addClass("show"), $(".bottom-line").removeClass("show"), nextPage++), jlutil.fixImageRatio()
            } else Dialog.toast(res.msg)
        }).fail(function (res) {
            console.log("error"), console.log(res)
        })
    };
    loadContent(nextPage, amount), $("#latest .btn-load").on("click", function () {
        loadContent(nextPage, amount)
    });
    var autoLogin = jlutil.getUrlSearchQuery("autologin");
    "true" != autoLogin && "1" != autoLogin || Dialog.login();
    var forceAdVal = jlutil.getUrlSearchQuery("forcead"), enableAd = !1, forceAd = "true" === forceAdVal || "1" === forceAdVal || "on" === forceAdVal, seenAd = Cookies.get("seen_ad_init"), win_w = $(window).width();
    enableAd && win_w > 1024 && (!seenAd || forceAd) && $.ajax({
        url: "/index/getAd",
        type: "GET",
        dataType: "json"
    }).done(function (res) {
        if (jlutil.displayResponse("getting ad top", res), "1" == res.status) {
            var videoSrc = res.data || "", propagandaTmpl = '<section id="propaganda-top">\n\t\t\t\t\t\t\t\t<div class="propaganda-top-wrapper">\n\t\t\t\t\t\t\t\t\t<video src="' + videoSrc + '" autoplay></video>\n\t\t\t\t\t\t\t\t\t<span class="close"></span>\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t</section>\n\t\t\t\t\t\t\t<section id="propaganda-top-placeholder"></section>';
            $("#mf-header").before($(propagandaTmpl));
            var PropagandaTop = $("#propaganda-top"), closeBtn = PropagandaTop.find(".close"), adVideo = PropagandaTop.find("video"), placeholder = (PropagandaTop.find(".propaganda-top-wrapper"), $("#propaganda-top-placeholder")), closePropagandaTop = function () {
                jlutil.stopPropagation(), PropagandaTop.addClass("closed"), placeholder.height(0), $("#mf-header").css({top: "auto"}), setTimeout(function () {
                    PropagandaTop.remove(), placeholder.remove()
                }, 1100)
            };
            if (closeBtn.on("click", closePropagandaTop), adVideo.length) {
                var video = adVideo[0];
                adVideo.on("ended", function () {
                    return closePropagandaTop()
                }), adVideo.on("loadedmetadata", function () {
                    var videoH = video.videoHeight || 0, ph = Math.min(videoH, 325);
                    placeholder.height(ph), $("#mf-header").css({top: ph})
                })
            }
            var cookieName = "seen_ad_init", cookieVal = "1", today = new Date, thisYear = today.getFullYear(), thisMonth = today.getMonth(), thisDate = today.getDate(), expireDate = new Date(thisYear, thisMonth, thisDate, 23, 59, 59).toString();
            document.cookie = cookieName + "=" + cookieVal + ";expires=" + expireDate
        }
    }).fail(function () {
        console.log("error on getting ad top")
    })
});