"use strict";
var JETLI_DEBUG_MODE = "cn.jetli.com" !== window.location.hostname, searchStr = window.location.search.toLowerCase();
(searchStr.indexOf("debug=1") >= 0 || searchStr.indexOf("debug=true") >= 0 || searchStr.indexOf("debug=on") >= 0 || searchStr.indexOf("debug=open") >= 0 || searchStr.indexOf("debugmode=1") >= 0 || searchStr.indexOf("debugmode=true") >= 0 || searchStr.indexOf("debugmode=on") >= 0 || searchStr.indexOf("debugmode=open") >= 0) && (JETLI_DEBUG_MODE = !0), JETLI_DEBUG_MODE && console.info("=== DEBUG_MODE on ===");
"use strict";
function _classCallCheck(instance, Constructor) {
    if (!(instance instanceof Constructor))throw new TypeError("Cannot call a class as a function")
}
var _typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (obj) {
    return typeof obj
} : function (obj) {
    return obj && "function" == typeof Symbol && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj
}, _createClass = function () {
    function defineProperties(target, props) {
        for (var i = 0; i < props.length; i++) {
            var descriptor = props[i];
            descriptor.enumerable = descriptor.enumerable || !1, descriptor.configurable = !0, "value" in descriptor && (descriptor.writable = !0), Object.defineProperty(target, descriptor.key, descriptor)
        }
    }

    return function (Constructor, protoProps, staticProps) {
        return protoProps && defineProperties(Constructor.prototype, protoProps), staticProps && defineProperties(Constructor, staticProps), Constructor
    }
}(), jlutil = function () {
    function jlutil() {
        _classCallCheck(this, jlutil)
    }

    return _createClass(jlutil, null, [{
        key: "getCurrentStyle", value: function (node) {
            var style = null;
            return style = window.getComputedStyle ? window.getComputedStyle(node, null) : node.currentStyle
        }
    }, {
        key: "getKeyCode", value: function (e) {
            e = e || window.event || "";
            var keyCode = e.keyCode || e.which;
            return keyCode
        }
    }, {
        key: "stopPropagation", value: function (e) {
            e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = !0
        }
    }, {
        key: "preventDefault", value: function (e) {
            return e && e.preventDefault ? e.preventDefault() : window.event.returnValue = !1, !1
        }
    }, {
        key: "doUntilImagesLoaded", value: function () {
            var images = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : [], callback = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : null, delay = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : 0;
            if (images instanceof Array) {
                for (var total = images.length, count = 0, _iterator = images, _isArray = Array.isArray(_iterator), _i = 0, _iterator = _isArray ? _iterator : _iterator[Symbol.iterator](); ;) {
                    var _ref;
                    if (_isArray) {
                        if (_i >= _iterator.length)break;
                        _ref = _iterator[_i++]
                    } else {
                        if (_i = _iterator.next(), _i.done)break;
                        _ref = _i.value
                    }
                    var img = _ref;
                    img instanceof Image && (img.onload = function () {
                        count++
                    })
                }
                var timer = setInterval(function () {
                    count === total && (clearInterval(timer), callback && setTimeout(callback(), delay))
                }, 100)
            }
        }
    }, {
        key: "fixElementRatio", value: function (elem) {
            var horizontal = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 1, vertical = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : 1;
            elem = $(elem);
            var w = elem.width(), h = w * vertical / horizontal;
            elem.height(h)
        }
    }, {
        key: "fixImageRatio", value: function () {
            var img_ratio_reg = /img-ratio-[0-9]+(.[0-9]+)*-[0-9]+(.[0-9]+)*/;
            $(".img-wrapper").each(function () {
                var ratio_str = $(this)[0].className.match(img_ratio_reg);
                if (null !== ratio_str) {
                    var h = ratio_str[0].split("-")[2], v = ratio_str[0].split("-")[3];
                    jlutil.fixElementRatio($(this), h, v)
                }
            })
        }
    }, {
        key: "containElement", value: function (elem) {
            var parent = elem.parent(), elem_w = elem.width(), elem_h = elem.height(), parent_w = parent.width(), parent_h = parent.height();
            if (elem_w / elem_h - parent_w / parent_h >= 0) {
                elem.css({height: "auto", width: "100%"});
                var target_h = elem.height(), mt = (parent_h - target_h) / 2;
                elem.css({"margin-top": mt})
            } else {
                elem.css({height: "100%", width: "auto"});
                var target_w = elem.width(), _ml = (parent_w - target_w) / 2;
                elem.css({"margin-left": _ml})
            }
        }
    }, {
        key: "containElements", value: function (elems) {
            elems.each(function () {
                jlutil.containElement($(this))
            })
        }
    }, {
        key: "containImages", value: function (images) {
            jlutil.doUntilImagesLoaded(images, function () {
                jlutil.containElements(images)
            })
        }
    }, {
        key: "coverElement", value: function (elem) {
            var parent = elem.parent(), elem_w = elem.width(), elem_h = elem.height(), parent_w = parent.width(), parent_h = parent.height();
            if (elem_w / elem_h - parent_w / parent_h >= 0) {
                elem.css({height: "100%", width: "auto"});
                var target_w = elem.width(), _ml2 = (parent_w - target_w) / 2;
                elem.css({"margin-left": _ml2})
            } else {
                elem.css({height: "auto", width: "100%"});
                var target_h = elem.height(), mt = (parent_h - target_h) / 2;
                elem.css({"margin-top": mt})
            }
        }
    }, {
        key: "coverElements", value: function (elems) {
            elems.each(function () {
                jlutil.coverElement($(this))
            })
        }
    }, {
        key: "coverImages", value: function (images) {
            jlutil.doUntilImagesLoaded(images, function () {
                jlutil.coverElements(images)
            })
        }
    }, {
        key: "centerAlignElement", value: function (elem, parent) {
            var elem_w = elem.width(), parent_w = parent.width();
            ml = (parent_w - elem_w) / 2, elem.css({"margin-left": ml})
        }
    }, {
        key: "displayError", value: function (errors, errorBlock) {
            errorBlock = $(errorBlock), 0 == errorBlock.find("ul").length && errorBlock.append("<ul></ul>");
            var errorList = errorBlock.find("ul");
            if (errorList.empty(), errors.length > 0)for (var _iterator2 = errors, _isArray2 = Array.isArray(_iterator2), _i2 = 0, _iterator2 = _isArray2 ? _iterator2 : _iterator2[Symbol.iterator](); ;) {
                var _ref2;
                if (_isArray2) {
                    if (_i2 >= _iterator2.length)break;
                    _ref2 = _iterator2[_i2++]
                } else {
                    if (_i2 = _iterator2.next(), _i2.done)break;
                    _ref2 = _i2.value
                }
                var error = _ref2;
                errorList.append("<li>" + error.msg + "</li>")
            }
        }
    }, {
        key: "getUrlSearchQuery", value: function () {
            var param = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "", str = decodeURIComponent(window.location.search.toLowerCase()), jsonObj = {};
            return /^\?/.test(str) && (str = str.substr(1)), str.indexOf("&") > -1 && (str = str.split("&").join('","')), str = str.split("=").join('":"'), str && (jsonObj = JSON.parse('{"' + str + '"}')), param ? jsonObj[param.toLowerCase()] : jsonObj
        }
    }, {
        key: "getUrlHash", value: function () {
            var removeHashTag = arguments.length > 0 && void 0 !== arguments[0] && arguments[0], hash = window.location.hash;
            return hash & removeHashTag && (hash = hash.substr(1)), hash
        }
    }, {
        key: "getImageCaptcha", value: function () {
            return "/account/imgCode"
        }
    }, {
        key: "isEmail", value: function () {
            var str = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "";
            return str.replace(/\s/g, ""), jlutil.REG_EMAIL.test(str)
        }
    }, {
        key: "isTel", value: function () {
            var str = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "";
            return str.replace(/\s/g, ""), jlutil.REG_TEL.test(str)
        }
    }, {
        key: "isMobileCN", value: function () {
            var str = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "";
            return str.replace(/\s/g, ""), jlutil.REG_MOBILE_CN.test(str)
        }
    }, {
        key: "checkPassword", value: function () {
            var pwd = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "";
            return jlutil.REG_PASSWORD.test(pwd)
        }
    }, {
        key: "compareString", value: function (str1, str2) {
            return str1 === str2
        }
    }, {
        key: "displayResponse", value: function () {
            var name = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "unknown", res = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
            JETLI_DEBUG_MODE && (console.log("Response from " + name), console.log(res))
        }
    }, {
        key: "formatDate", value: function () {
            var unFormattedDate = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : new Date, forceFullDigit = arguments.length > 1 && void 0 !== arguments[1] && arguments[1];
            if (!unFormattedDate instanceof Date)return unFormattedDate;
            var year = String(unFormattedDate.getFullYear()), month = String(unFormattedDate.getMonth() + 1), date = String(unFormattedDate.getDate());
            return forceFullDigit && (month.length < 2 && (month = "0" + month), date.length < 2 && (date = "0" + date)), year + "-" + month + "-" + date
        }
    }, {
        key: "createBlock", value: function (config) {
            var coverImage = config.cover_img || "/public/static/img/placeholder.jpg", link = config.id ? "/news/detail/id/" + config.id : "javascript:;", title = config.title || "Untitled", date = config.add_time || "0000-00-00", viewNum = config.view_count || 0, hasVideo = "2" == config.type, duration = config.duration || "", playBtn = hasVideo ? '<div class="btn-play"><span></span></div>' : "", durationDOM = duration ? '<div class="duration"><span>' + duration + "</span></div>" : "", tmpl = '<a class="item item-block" target="_blank" href="' + link + '" data-has-video="' + hasVideo + '">\n\t\t\t\t\t\t<div class="img-wrapper img-ratio-3-2">\n\t\t\t\t\t\t\t<img src="' + coverImage + '">\n\t\t\t\t\t\t\t<div class="mask"></div>\n\t\t\t\t\t\t\t' + playBtn + "\n\t\t\t\t\t\t\t" + durationDOM + '\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<div class="title">\n\t\t\t\t\t\t\t<p>' + title + '</p>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<div class="statics">\n\t\t\t\t\t\t\t<div class="date">' + date + '</div>\n\t\t\t\t\t\t\t<div class="attitude">' + viewNum + " views</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</a>";
            return $(tmpl)
        }
    }, {
        key: "createJetliNews", value: function (config) {
            var coverImage = config.cover_img || "/public/static/img/placeholder.jpg", link = config.id ? "/news/detail/id/" + config.id : "javascript:;", title = config.title || "Untitled", date = config.add_time || "0000-00-00", brief = config.introduction || "Your Text Here", tmpl = '<a href="' + link + '" target="_blank" class="item item-jetli-news">\n\t\t\t\t\t<div class="left">\n\t\t\t\t\t\t<div class="img-wrapper img-ratio-3-2">\n\t\t\t\t\t\t\t<img src="' + coverImage + '">\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\t\t\t\t\t<div class="right">\n\t\t\t\t\t\t<h2>' + title + '</h2>\n\t\t\t\t\t\t<p class="date">' + date + '</p>\n\t\t\t\t\t\t<p class="brief">' + brief + "</p>\n\t\t\t\t\t</div>\n\t\t\t\t</a>";
            return $(tmpl)
        }
    }, {
        key: "underscore2Camel", value: function () {
            var str = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "", force = arguments.length > 1 && void 0 !== arguments[1] && arguments[1];
            console.log("=== underscore2Camel ===");
            var str_type = "undefined" == typeof str ? "undefined" : _typeof(str);
            str = "object" == str_type ? JSON.stringify(str) : String(str);
            var str_arr = str.split("_");
            for (var i in str_arr)if (0 != i || force) {
                var part = str_arr[i];
                part.length > 1 && (part = part[0].toUpperCase() + part.substr(1), str_arr[i] = part)
            }
            var converted = str_arr.join("");
            return converted
        }
    }, {
        key: "getUserInfo", value: function () {
            var userInfoStr = Cookies.get("userInfo");
            if (!userInfoStr)return null;
            userInfoStr = decodeURIComponent(userInfoStr), JETLI_DEBUG_MODE && console.log("userInfo from cookie (decoded):", userInfoStr);
            var userInfo = "";
            try {
                return userInfo = JSON.parse(userInfoStr)
            } catch (e) {
                return console.error(e), null
            }
        }
    }, {
        key: "fixFooterPosition", value: function () {
            var win_h = $(window).height(), content_h = jlutil.mfMain.outerHeight() + jlutil.mfFooter.outerHeight();
            content_h <= win_h ? jlutil.mfFooter.addClass("fixed") : jlutil.mfFooter.removeClass("fixed")
        }
    }, {
        key: "fixMainSize", value: function () {
            var h_h = jlutil.mfHeader.length ? jlutil.mfHeader.height() : 0, f_h = jlutil.mfFooter.length ? jlutil.mfFooter.height() : 0, fpt = jlutil.mfFooter.length ? Number(jlutil.mfFooter.css("padding-top").slice(0, -2)) : 0, fpb = jlutil.mfFooter.length ? Number(jlutil.mfFooter.css("padding-bottom").slice(0, -2)) : 0, w_h = $(window).height() || 0, h = Math.max(w_h - h_h - f_h - fpt - fpb, 0);
            jlutil.mfMain.height(h)
        }
    }, {
        key: "test", value: function () {
            var o = {x: 255, y: {a: 123, b: "word", c: !0, d: new Date}, z: ["j", "k", "l"]};
            jlutil.traverseObject(o)
        }
    }, {
        key: "encodeUnicode", value: function (str) {
            for (var res = [], i = 0; i < str.length; i++)res[i] = ("00" + str.charCodeAt(i).toString(16)).slice(-4);
            return "\\u" + res.join("\\u")
        }
    }, {
        key: "decodeUnicode", value: function (str) {
            return str = str.replace(/\\/g, "%"), unescape(str)
        }
    }]), jlutil
}();
jlutil.REG_EMAIL = /^[a-zA-Z0-9\.\-\_]+\@[a-zA-Z0-9.]+([a-zA-Z0-9])+$/, jlutil.REG_TEL = /^\+?[0-9]+(\-?[0-9]+)*$/, jlutil.REG_MOBILE_CN = /^(\+86)?1[0-9]{10}$/, jlutil.REG_NUM = /^[0-9]+$/, jlutil.REG_PASSWORD = /^[a-zA-Z0-9]+$/, jlutil.mfHeader = $("#mf-header"), jlutil.mfFooter = $("#mf-footer"), jlutil.mfMain = $(".mf-main");
"use strict";
function _classCallCheck(instance, Constructor) {
    if (!(instance instanceof Constructor))throw new TypeError("Cannot call a class as a function")
}
var _createClass = function () {
    function defineProperties(target, props) {
        for (var i = 0; i < props.length; i++) {
            var descriptor = props[i];
            descriptor.enumerable = descriptor.enumerable || !1, descriptor.configurable = !0, "value" in descriptor && (descriptor.writable = !0), Object.defineProperty(target, descriptor.key, descriptor)
        }
    }

    return function (Constructor, protoProps, staticProps) {
        return protoProps && defineProperties(Constructor.prototype, protoProps), staticProps && defineProperties(Constructor, staticProps), Constructor
    }
}(), Dialog = function () {
    function Dialog() {
        _classCallCheck(this, Dialog)
    }

    return _createClass(Dialog, null, [{
        key: "fadein", value: function (dlg) {
            dlg = $(dlg), $("body").append(dlg), setTimeout(function () {
                dlg.hasClass("dialog") ? dlg.addClass("open") : dlg.find(".dialog").addClass("open")
            }, 100)
        }
    }, {
        key: "fadeout", value: function (dlg) {
            dlg = $(dlg), setTimeout(function () {
                dlg.remove()
            }, Dialog.ANIMATION_DURATION), dlg.hasClass("dialog") ? dlg.removeClass("open") : dlg.find(".dialog").removeClass("open")
        }
    }, {
        key: "showMask", value: function () {
            $(".dialog-mask").length || $("body").append('<div class="dialog-mask"></div>').addClass("scroll-lock"), $(".dialog-mask").css({display: "block"}).animate({opacity: ".75"}, Dialog.ANIMATION_DURATION)
        }
    }, {
        key: "hideMask", value: function () {
            var mask = $(".dialog-mask");
            mask.length && (setTimeout(function () {
                return mask.remove()
            }, Dialog.ANIMATION_DURATION), mask.animate({opacity: 0}, Dialog.ANIMATION_DURATION), $("body").removeClass("scroll-lock"))
        }
    }, {
        key: "info", value: function () {
            var title = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "街力", content = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "";
            title || (title = "街力");
            var tmpl = '<div class="dialog dialog-modal">\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t<header><p>' + title + '</p></header>\n\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t<p>' + content + '</p>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<div class="actions">\n\t\t\t\t\t\t\t<a href="javascript:;" class="btn btn-ok">好的</a>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>', dlg = $(tmpl);
            dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg)
            }), dlg.find(".actions .btn-ok").on("click", function () {
                Dialog.fadeout(dlg)
            }), Dialog.fadein(dlg)
        }
    }, {
        key: "confirm", value: function () {
            var title = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "街力提醒您", content = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "", yes = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : "Yes", no = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : "No", success = arguments.length > 4 && void 0 !== arguments[4] ? arguments[4] : null, fail = arguments.length > 5 && void 0 !== arguments[5] ? arguments[5] : null;
            title || (title = "街力提醒您");
            var tmpl = '<div class="dialog dialog-confirm">\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t\t<header><p>' + title + '</p></header>\n\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t<p>' + content + '</p>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<div class="actions">\n\t\t\t\t\t\t\t<a href="javascript:;" class="btn btn-yes">' + yes + '</a>\n\t\t\t\t\t\t\t<a href="javascript:;" class="btn btn-no">' + no + "</a>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>", dlg = $(tmpl);
            dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg)
            }), dlg.find(".actions .btn-yes").on("click", function () {
                Dialog.fadeout(dlg), success && success()
            }), dlg.find(".actions .btn-no").on("click", function () {
                Dialog.fadeout(dlg), fail && fail()
            }), Dialog.fadein(dlg)
        }
    }, {
        key: "option", value: function () {
            var title = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "街力提醒您", content = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "", options = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : [];
            title || (title = "街力提醒您");
            for (var tmpl = '<div class="dialog dialog-option">\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t\t<header><p>' + title + '</p></header>\n\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t<p>' + content + '</p>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<div class="actions clearfix-self">\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>', dlg = $(tmpl), _loop = function () {
                if (_isArray) {
                    if (_i >= _iterator.length)return "break";
                    _ref = _iterator[_i++]
                } else {
                    if (_i = _iterator.next(), _i.done)return "break";
                    _ref = _i.value
                }
                var option = _ref, btnTxt = option.button || "关闭", btn = $('<a href="javascript:;" class="btn">' + btnTxt + "</a>");
                btn.on("click", function () {
                    Dialog.fadeout(dlg), option.callback && option.callback()
                }), dlg.find(".actions").append(btn)
            }, _iterator = options, _isArray = Array.isArray(_iterator), _i = 0, _iterator = _isArray ? _iterator : _iterator[Symbol.iterator](); ;) {
                var _ref, _ret = _loop();
                if ("break" === _ret)break
            }
            dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg)
            }), Dialog.fadein(dlg)
        }
    }, {
        key: "toast", value: function () {
            var content = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "", duration = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : Dialog.TOAST_DURATION_DEFAULT, tmpl = '<div class="dialog dialog-toast">' + content + "</div>", dlg = $(tmpl);
            Dialog.fadein(dlg), setTimeout(function () {
                Dialog.fadeout(dlg)
            }, duration + 2 * Dialog.ANIMATION_DURATION)
        }
    }, {
        key: "upgradeBrowser", value: function () {
            var _ref2 = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {}, _ref2$enableCookie = _ref2.enableCookie, enableCookie = void 0 === _ref2$enableCookie || _ref2$enableCookie, _ref2$demoMode = _ref2.demoMode, demoMode = void 0 !== _ref2$demoMode && _ref2$demoMode, _ref2$demoIEVersion = _ref2.demoIEVersion, demoIEVersion = void 0 === _ref2$demoIEVersion ? 9 : _ref2$demoIEVersion, ua = window.navigator.userAgent.toLowerCase(), IE_INFO = {
                6: {
                    birth: 2001,
                    share: "0.44%"
                },
                7: {birth: 2007, share: "0.31%"},
                8: {birth: 2009, share: "3.12%"},
                9: {birth: 2011, share: "5.31%"},
                10: {birth: 2012, share: "0.89%"},
                11: {birth: 2013, share: "10.55%"}
            }, ieVersion = "", isOldIE = !1;
            if (demoMode)ieVersion = demoIEVersion, isOldIE = !0; else {
                if (ua.indexOf("msie") < 0)return;
                ieVersion = Number(ua.match(/msie\s\d+/)[0].toString().substr(5)), isOldIE = !0
            }
            console.log(""), console.log("demoMode: " + (demoMode ? "On" : "Off") + ", ieVersion: " + ieVersion + ", isOldIE: " + isOldIE);
            var thisYear = (new Date).getFullYear(), age = thisYear - IE_INFO[ieVersion].birth, share = IE_INFO[ieVersion].share;
            if (ieVersion < 9)return void alert("该站点不再支持IE" + ieVersion + "（发布于" + age + "年前，市场占有率仅剩" + share + "）访问，部分功能将无法使用，且不再维护，请升级您的浏览器以获得更好的浏览体验");
            var cookie = "", cookie_json = "";
            if (enableCookie && !demoMode && (cookie = document.cookie, cookie_json = JSON.parse('{"' + cookie.split("=").join('":"').split(";").join('","') + '"}')), demoMode || !enableCookie || !cookie_json.upgradeDialogShown) {
                var tmpl = '<div class="dialog" id="dialog-upgrade-browser">\n\t\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t\t<header>\n\t\t\t\t\t\t\t\t<p>少侠，这都' + thisYear + "年了，您还在用IE" + ieVersion + ' 呐?</p>\n\t\t\t\t\t\t\t</header>\n\t\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t\t<p>IE' + ieVersion + "已经是" + age + "年前的老古董了，动作慢，弱点多，功夫还不咋地，整个武林只剩下" + share + '的人还在用它战斗，而且还在减少。是时候换件更厉害的兵器了！</p>\n\t\t\t\t\t\t\t\t<blockquote class="related">\n\t\t\t\t\t\t\t\t\t<a href="https://www.microsoft.com/en-us/WindowsForBusiness/End-of-IE-support" target="_blank">微软：2016年起终止对旧版本IE浏览器的技术支持</a>\n\t\t\t\t\t\t\t\t\t<a href="https://zhuanlan.zhihu.com/p/24091492" target="_blank">天猫：2016年双十二过后将不再支持旧版本IE访问</a>\n\t\t\t\t\t\t\t\t</blockquote>\n\t\t\t\t\t\t\t\t<a class="btn btn-main go-upgrade"  href="http://browsehappy.com/" target="_blank">立即升级</a>\n\t\t\t\t\t\t\t\t<a class="btn btn-link not-upgrade" href="javascript:;">起开，7日之内莫要烦我</a>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>', dlg = $(tmpl);
                dlg.find(".close").on("click", function () {
                    Dialog.fadeout(dlg), Dialog.hideMask()
                }), dlg.find(".not-upgrade").on("click", function () {
                    Dialog.fadeout(dlg), Dialog.hideMask()
                }), Dialog.showMask(), Dialog.fadein(dlg), enableCookie && !demoMode && (document.cookie = "upgradeDialogShown=true; expires=" + (new Date).setDate(date.getDate() + 7).toGMTString())
            }
        }
    }, {
        key: "login", value: function () {
            var tmpl = '<div class="dialog" id="dialog-login">\n\t\t\t\t\t\t<div class="global-loading"></div>\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t<header>用户登录</header>\n\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t<form action="" method="post">\n\t\t\t\t\t\t\t\t<input type="text" class="account" placeholder="输入手机号/邮箱"/>\n\t\t\t\t\t\t\t\t<input type="password" class="password" placeholder="输入密码"/>\n\t\t\t\t\t\t\t\t<div class="captcha-wrapper">\n\t\t\t\t\t\t\t\t\t<input type="text" class="captcha" data-captcha-type="image" placeholder="请输入验证码"/>\n\t\t\t\t\t\t\t\t\t<div class="image-captcha">\n\t\t\t\t\t\t\t\t\t\t<img src="" alt="点击刷新" />\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-login">立即登录</a>\n\t\t\t\t\t\t\t</form>\n\t\t\t\t\t\t\t<div class="links">\n\t\t\t\t\t\t\t\t<a href="/index/forget" class="link" id="link-forget">忘记密码？</a>\n\t\t\t\t\t\t\t\t<a href="javascript:;"  class="link" id="link-register-tel">新用户注册</a>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t<div class="social-login">\n\t\t\t\t\t\t\t\t<p>社交账号登录</p>\n\t\t\t\t\t\t\t\t<div class="wrapper hide-weibo">\n\t\t\t\t\t\t\t\t\t<div class="item" id="login-with-weibo">\n\t\t\t\t\t\t\t\t\t\t<a class="icon" href="/account/thirdPartyLogin?type=sina"></a>\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t<div class="item" id="login-with-wechat">\n\t\t\t\t\t\t\t\t\t\t<a class="icon" href="/account/thirdPartyLogin?type=weixin"></a>\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>', dlg = $(tmpl), loading = dlg.find(".global-loading"), imageCaptcha = jlutil.getImageCaptcha();
            dlg.find(".image-captcha img").prop("src", imageCaptcha), dlg.find(".image-captcha").on("click", function () {
                var imageCaptcha = jlutil.getImageCaptcha();
                $(this).find("img").prop("src", imageCaptcha)
            }), dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg), Dialog.hideMask()
            }), dlg.find(".account").on("keydown", function (e) {
                var keyCode = jlutil.getKeyCode(e);
                13 == keyCode && dlg.find(".password").focus()
            }), dlg.find(".password").on("keydown", function (e) {
                var keyCode = jlutil.getKeyCode(e);
                13 == keyCode && dlg.find(".captcha").focus()
            }), dlg.find(".captcha").on("keydown", function (e) {
                var keyCode = jlutil.getKeyCode(e);
                13 == keyCode && dlg.find("#btn-login").trigger("click")
            }), dlg.find("#btn-login").on("click", function () {
                var account = $("#dialog-login .account").val().trim(), password = $("#dialog-login .password").val().trim(), imgCaptcha = $("#dialog-login .captcha").val().trim();
                return account ? password ? imgCaptcha ? jlutil.isEmail(account) || jlutil.isMobileCN(account) ? (loading.addClass("show"), void $.ajax({
                    url: "/account/login",
                    type: "POST",
                    dataType: "json",
                    data: {account: account, pwd: password, imgcode: imgCaptcha}
                }).done(function (res) {
                    if (jlutil.displayResponse("login", res), "1" == res.status)window.location.reload(); else {
                        Dialog.toast(res.msg);
                        var _imageCaptcha = jlutil.getImageCaptcha();
                        dlg.find(".image-captcha img").prop("src", _imageCaptcha)
                    }
                }).fail(function () {
                    jlutil.displayResponse("login", res)
                }).always(function () {
                    loading.removeClass("show")
                })) : void Dialog.toast("手机号/邮箱 格式不正确") : void Dialog.toast("请输入验证码") : void Dialog.toast("请输入密码") : void Dialog.toast("请输入手机号/邮箱")
            }), dlg.find("#link-register-tel").on("click", function () {
                Dialog.fadeout(dlg), Dialog.register("tel")
            }), dlg.find("#login-with-wechat").on("click", function () {
                console.log("login with wechat ...")
            }), dlg.find("#login-with-weibo").on("click", function () {
                console.log("login with weibo ...")
            }), Dialog.showMask(), Dialog.fadein(dlg), setTimeout(function () {
                dlg.find(".account").focus()
            }, 0)
        }
    }, {
        key: "register", value: function () {
            var mode = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "";
            if (JETLI_DEBUG_MODE && console.log("register dialog with mode: " + mode), !mode || "tel" !== mode && "email" !== mode)return void console.error("register with unknown mode");
            var tmpl = "tel" === mode ? '<div class="dialog" id="dialog-register-tel">\n\t\t\t\t<div class="global-loading"></div>\n\t\t\t\t<div class="close"></div>\n\t\t\t\t<header>新用户注册</header>\n\t\t\t\t<div class="content">\n\t\t\t\t\t<form action="" method="post">\n\t\t\t\t\t\t<input type="text" class="account" autocomplete="off" placeholder="输入手机号"/>\n\t\t\t\t\t\t<input type="password" class="password" autocomplete="off" placeholder="输入密码"/>\n\t\t\t\t\t\t<input type="password" class="password2" autocomplete="off" placeholder="确认密码"/>\n\t\t\t\t\t\t<div class="captcha-wrapper">\n\t\t\t\t\t\t\t<input type="text" class="captcha" data-captcha-type="image" placeholder="请输入验证码"/>\n\t\t\t\t\t\t\t<div class="image-captcha">\n\t\t\t\t\t\t\t\t<img src="" alt="点击刷新" />\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-register">注册账号</a>\n\t\t\t\t\t</form>\n\t\t\t\t\t<div class="links">\n\t\t\t\t\t\t<a href="javascript:;" class="link" id="link-register-email">使用邮箱注册</a>\n\t\t\t\t\t\t<a href="javascript:;" class="link" id="link-login">使用已有账号登录</a>\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t</div>' : '<div class="dialog" id="dialog-register-email">\n\t\t\t\t<div class="global-loading"></div>\n\t\t\t\t<div class="close"></div>\n\t\t\t\t<header>新用户注册</header>\n\t\t\t\t<div class="content">\n\t\t\t\t\t<form action="" method="post">\n\t\t\t\t\t\t<input type="text" class="account" autocomplete="off" placeholder="输入邮箱"/>\n\t\t\t\t\t\t<input type="password" class="password" autocomplete="off" placeholder="输入密码"/>\n\t\t\t\t\t\t<input type="password" class="password2" autocomplete="off" placeholder="确认密码"/>\n\t\t\t\t\t\t<div class="captcha-wrapper">\n\t\t\t\t\t\t\t<input type="text" class="captcha" data-captcha-type="image" placeholder="请输入验证码"/>\n\t\t\t\t\t\t\t<div class="image-captcha">\n\t\t\t\t\t\t\t\t<img src="" alt="点击刷新" />\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-register">注册账号</a>\n\t\t\t\t\t</form>\n\t\t\t\t\t<div class="links">\n\t\t\t\t\t\t<a href="javascript:;" class="link" id="link-register-tel">使用手机注册</a>\n\t\t\t\t\t\t<a href="javascript:;" class="link" id="link-login">使用已有账号登录</a>\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t</div>', dlg = $(tmpl), loading = dlg.find(".global-loading"), imageCaptcha = jlutil.getImageCaptcha();
            dlg.find(".image-captcha img").prop("src", imageCaptcha), dlg.find(".image-captcha").on("click", function () {
                var imageCaptcha = jlutil.getImageCaptcha();
                $(this).find("img").prop("src", imageCaptcha)
            }), dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg), Dialog.hideMask()
            }), dlg.find(".account").on("keydown", function (e) {
                var keyCode = jlutil.getKeyCode(e);
                13 == keyCode && dlg.find(".password").focus()
            }), dlg.find(".password").on("keydown", function (e) {
                var keyCode = jlutil.getKeyCode(e);
                13 == keyCode && dlg.find(".password2").focus()
            }), dlg.find(".password2").on("keydown", function (e) {
                var keyCode = jlutil.getKeyCode(e);
                13 == keyCode && dlg.find(".captcha").focus()
            }), dlg.find(".captcha").on("keydown", function (e) {
                var keyCode = jlutil.getKeyCode(e);
                13 == keyCode && dlg.find("#btn-register").trigger("click")
            }), dlg.find("#btn-register").on("click", function () {
                var account = $("#dialog-register-" + mode + " .account").val().trim(), password = $("#dialog-register-" + mode + " .password").val().trim(), password2 = $("#dialog-register-" + mode + " .password2").val().trim(), imgCaptcha = $("#dialog-register-" + mode + " .captcha").val().trim();
                return account ? password ? password2 ? imgCaptcha ? "tel" != mode || jlutil.isMobileCN(account) ? "email" != mode || jlutil.isEmail(account) ? jlutil.compareString(password, password2) ? jlutil.checkPassword(password) ? (loading.addClass("show"), void $.ajax({
                    url: "/account/getCaptcha",
                    type: "POST",
                    dataType: "json",
                    data: {mode: mode, account: account, imgcode: imgCaptcha}
                }).done(function (res) {
                    if (jlutil.displayResponse("sending captcha", res), "1" == res.status)Dialog.fadeout(dlg), Dialog.captcha("新用户注册", mode, "register", imgCaptcha, account, password); else {
                        Dialog.toast(res.msg);
                        var _imageCaptcha2 = jlutil.getImageCaptcha();
                        dlg.find(".image-captcha img").prop("src", _imageCaptcha2)
                    }
                }).fail(function () {
                    jlutil.displayResponse("sending captcha", res)
                }).always(function () {
                    loading.removeClass("show")
                })) : void Dialog.toast("密码仅支持大小写英文字母及阿拉伯数字") : void Dialog.toast("两次密码不一致") : void Dialog.toast("请输入正确的邮箱地址") : void Dialog.toast("请输入正确的手机号") : void Dialog.toast("请填写图形验证码") : void Dialog.toast("请确认密码") : void Dialog.toast("请输入密码") : void("tel" == mode ? Dialog.toast("请输入手机号") : Dialog.toast("请输入邮箱地址"))
            }), dlg.find("#link-login").on("click", function () {
                Dialog.fadeout(dlg), Dialog.login()
            }), "tel" === mode && dlg.find("#link-register-email").on("click", function () {
                Dialog.fadeout(dlg), Dialog.register("email")
            }), "email" === mode && dlg.find("#link-register-tel").on("click", function () {
                Dialog.fadeout(dlg), Dialog.register("tel")
            }), Dialog.showMask(), Dialog.fadein(dlg), setTimeout(function () {
                dlg.find(".account").focus()
            }, 0)
        }
    }, {
        key: "bindContact", value: function () {
            var mode = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "tel", modify = arguments.length > 1 && void 0 !== arguments[1] && arguments[1];
            if (JETLI_DEBUG_MODE && console.log("captcha with mode: " + mode + ", modify: " + modify), "tel" !== mode && "email" !== mode)return void console.error("try modify contact with unknown mode");
            var strModify = modify ? "修改" : "", strMode = "tel" == mode ? "手机" : "邮箱", strHeader = strModify + "绑定" + strMode, tmpl = "tel" === mode ? '<div class="dialog" id="dialog-bind-tel">\n\t\t\t\t<div class="global-loading"></div>\n\t\t\t\t<div class="close"></div>\n\t\t\t\t<header>' + strHeader + '</header>\n\t\t\t\t<div class="content">\n\t\t\t\t\t<p>请输入手机号</p>\n\t\t\t\t\t<input type="tel" class="account" placeholder="请输入手机号"/>\n\t\t\t\t\t<div class="captcha-wrapper">\n\t\t\t\t\t\t<input type="text" class="captcha" data-captcha-type="image" placeholder="请输入验证码"/>\n\t\t\t\t\t\t<div class="image-captcha">\n\t\t\t\t\t\t\t<img src="" alt="点击刷新" />\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-next">下一步</a>\n\t\t\t\t</div>\n\t\t\t</div>' : '<div class="dialog" id="dialog-bind-email">\n\t\t\t\t<div class="close"></div>\n\t\t\t\t<header>' + strHeader + '</header>\n\t\t\t\t<div class="content">\n\t\t\t\t\t<p>请输入邮箱地址</p>\n\t\t\t\t\t<input type="email" class="account" placeholder="请输入邮箱"/>\n\t\t\t\t\t<div class="captcha-wrapper">\n\t\t\t\t\t\t<input type="text" class="captcha" data-captcha-type="image" placeholder="请输入验证码"/>\n\t\t\t\t\t\t<div class="image-captcha">\n\t\t\t\t\t\t\t<img src="" alt="点击刷新" />\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-next">下一步</a>\n\t\t\t\t</div>\n\t\t\t</div>', dlg = $(tmpl), loading = dlg.find(".global-loading"), imageCaptcha = jlutil.getImageCaptcha();
            dlg.find(".image-captcha img").prop("src", imageCaptcha), dlg.find(".image-captcha").on("click", function () {
                var imageCaptcha = jlutil.getImageCaptcha();
                $(this).find("img").prop("src", imageCaptcha)
            }), dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg), Dialog.hideMask()
            }), dlg.find("#btn-next").on("click", function () {
                var account = dlg.find(".account").val().trim(), imgCaptcha = dlg.find(".captcha").val().trim();
                return account ? imgCaptcha ? "tel" != mode || jlutil.isMobileCN(account) ? "email" != mode || jlutil.isEmail(account) ? (loading.addClass("show"), void $.ajax({
                    url: "/account/getCaptcha",
                    type: "POST",
                    dataType: "json",
                    data: {mode: mode, account: account, imgcode: imgCaptcha}
                }).done(function (res) {
                    if (jlutil.displayResponse("sending captcha", res), "1" == res.status)Dialog.fadeout(dlg), Dialog.captcha(strHeader, mode, "bindContact", imgCaptcha, account); else {
                        Dialog.toast(res.msg);
                        var _imageCaptcha3 = jlutil.getImageCaptcha();
                        dlg.find(".image-captcha img").prop("src", _imageCaptcha3)
                    }
                }).fail(function () {
                    jlutil.displayResponse("sending captcha", res)
                }).always(function () {
                    loading.removeClass("show")
                })) : void Dialog.toast("邮箱格式不正确") : void Dialog.toast("手机号格式不正确") : void Dialog.toast("请填写图形验证码") : void Dialog.toast("tel" == mode ? "请输入手机号" : "请输入邮箱")
            }), Dialog.showMask(), Dialog.fadein(dlg)
        }
    }, {
        key: "captcha", value: function () {
            var title = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "街力", mode = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "", todo = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : "", imgCaptcha = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : "", account = arguments.length > 4 && void 0 !== arguments[4] ? arguments[4] : "", password = arguments.length > 5 && void 0 !== arguments[5] ? arguments[5] : "";
            if (JETLI_DEBUG_MODE && console.log("captcha with mode: " + mode + ", todo: " + todo), !mode || "tel" !== mode && "email" !== mode)return void console.error("try getting captcha with unknown mode");
            if (!todo || "register" !== todo && "bindContact" !== todo)return void console.error("invalid value for parameter todo");
            if (!imgCaptcha)return void console.error("imgCaptcha is required");
            if (!account)return void console.error("account is required");
            if (!jlutil.isEmail(account) && !jlutil.isMobileCN(account))return void console.error("invalid account");
            title || (title = "街力");
            var tmpl = "tel" === mode ? '<div class="dialog" id="dialog-captcha-tel">\n\t\t\t\t<div class="global-loading"></div>\n\t\t\t\t<div class="close"></div>\n\t\t\t\t<header>' + title + '</header>\n\t\t\t\t<div class="content">\n\t\t\t\t\t<p>为了账号安全，需要验证手机号有效性</p>\n\t\t\t\t\t<p>一条包含有验证码的短信已经发送到手机号：<span class="account">' + account + '</span></p>\n\t\t\t\t\t<form action="" method="post">\n\t\t\t\t\t\t<div class="captcha-wrapper">\n\t\t\t\t\t\t\t<input type="text" class="captcha" data-captcha-type="msg" placeholder="请输入验证码"/>\n\t\t\t\t\t\t\t<button class="btn btn-main-bordered" id="btn-send-captcha">重发<span class="count-down"></span></button>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-next">下一步</a>\n\t\t\t\t\t</form>\n\t\t\t\t</div>\n\t\t\t</div>' : '<div class="dialog" id="dialog-captcha-email">\n\t\t\t\t<div class="global-loading"></div>\n\t\t\t\t<div class="close"></div>\n\t\t\t\t<header>' + title + '</header>\n\t\t\t\t<div class="content">\n\t\t\t\t\t<p>为了账号安全，需要验证邮箱有效性</p>\n\t\t\t\t\t<p>一封包含有验证码的邮件已经发送到邮箱：<span class="account">' + account + '</span></p>\n\t\t\t\t\t<form action="" method="post">\n\t\t\t\t\t\t<div class="captcha-wrapper">\n\t\t\t\t\t\t\t<input type="text" class="captcha" data-captcha-type="msg" placeholder="请输入验证码"/>\n\t\t\t\t\t\t\t<button class="btn btn-main-bordered" id="btn-send-captcha">重发<span class="count-down"></span></button>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-next">下一步</a>\n\t\t\t\t\t</form>\n\t\t\t\t</div>\n\t\t\t</div>', dlg = $(tmpl), loading = dlg.find(".global-loading"), countDownLock = !1, countDown = function countDown() {
                var sec = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 1;
                isNaN(Number(sec)) || (sec <= 0 ? (countDownLock = !1, $("#btn-send-captcha .count-down").text("")) : (sec -= 1, sec > 0 ? $("#btn-send-captcha .count-down").text("(" + sec + ")") : $("#btn-send-captcha .count-down").text(""), setTimeout(function () {
                    countDown(sec)
                }, 1e3)))
            };
            dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg), Dialog.hideMask()
            }), dlg.find("form").on("submit", function () {
                return !1
            }), dlg.find(".captcha").on("keydown", function (e) {
                var keyCode = jlutil.getKeyCode(e);
                13 == keyCode && dlg.find("#btn-next").trigger("click")
            }), dlg.find("#btn-next").on("click", function () {
                var captcha = $("#dialog-captcha-" + mode).find(".captcha").val().trim();
                if (!captcha)return void Dialog.toast("请输入验证码");
                switch (todo) {
                    case"register":
                        loading.addClass("show"), $.ajax({
                            url: "/account/register",
                            type: "POST",
                            dataType: "json",
                            data: {mode: mode, account: account, pwd: password, code: captcha, channel: 1}
                        }).done(function (res) {
                            jlutil.displayResponse("submit register", res), 1 == res.status ? (Dialog.fadeout(dlg), Dialog.profile()) : Dialog.toast(res.msg)
                        }).fail(function () {
                            jlutil.displayResponse("submit register", res)
                        }).always(function () {
                            loading.removeClass("show")
                        });
                        break;
                    case"bindContact":
                        loading.addClass("show"), $.ajax({
                            url: "/user/bindContact",
                            type: "POST",
                            dataType: "json",
                            data: {mode: mode, account: account, code: captcha}
                        }).done(function (res) {
                            jlutil.displayResponse("submit bind contact", res), 1 == res.status ? (Dialog.fadeout(dlg), Dialog.bindContactSuccess(mode)) : Dialog.toast(res.msg)
                        }).fail(function () {
                            jlutil.displayResponse("submit bind contact", res)
                        }).always(function () {
                            loading.removeClass("show")
                        })
                }
            });
            var getMsgCaptcha = function getMsgCaptcha(mode, account, imgCaptcha) {
                $.ajax({
                    url: "/account/getCaptcha",
                    type: "POST",
                    dataType: "json",
                    data: {mode: mode, account: account, imgcode: imgCaptcha}
                }).done(function (res) {
                    jlutil.displayResponse("sending captcha", res), "1" == res.status ? (countDown(60), countDownLock = !0) : Dialog.imageCaptchaDialog(!1, function (captcha) {
                        getMsgCaptcha(mode, account, captcha)
                    })
                }).fail(function () {
                    jlutil.displayResponse("sending captcha", res)
                })
            };
            dlg.find("#btn-send-captcha").on("click", function () {
                countDownLock || getMsgCaptcha(mode, account, imgCaptcha)
            }), Dialog.showMask(), Dialog.fadein(dlg), setTimeout(function () {
                countDown(60), countDownLock = !0, dlg.find(".captcha").focus()
            }, 0)
        }
    }, {
        key: "bindContactSuccess", value: function () {
            var mode = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "tel";
            if (JETLI_DEBUG_MODE && console.log("captcha with mode: " + mode), "tel" !== mode && "email" !== mode)return void console.error("invalid value for parameter mode");
            var strMode = "tel" == mode ? "手机" : "邮箱", tmpl = '<div class="dialog" id="dialog-bind-success">\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t<header>绑定' + strMode + '</header>\n\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t<div class="img-wrapper">\n\t\t\t\t\t\t\t\t<img src="/public/static/img/icons/icon-success.png"/>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t<p>您已经成功绑定' + strMode + '</p>\n\t\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-finish">完成</a>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>', dlg = $(tmpl);
            dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg), Dialog.hideMask()
            }), dlg.find("#btn-finish").on("click", function () {
                Dialog.fadeout(dlg), Dialog.hideMask(), setTimeout(function () {
                    window.location.reload()
                }, Dialog.ANIMATION_DURATION)
            }), Dialog.showMask(), Dialog.fadein(dlg)
        }
    }, {
        key: "profile", value: function () {
            var supportedImageFormat = ["image/jpeg", "image/png", "image/bmp"], acceptStr = supportedImageFormat.join(","), tmpl = '<div class="dialog" id="dialog-profile">\n\t\t\t\t\t\t<div class="global-loading"></div>\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t<header>完善资料</header>\n\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t<form action="" method="post">\n\t\t\t\t\t\t\t\t<div class="avatar">\n\t\t\t\t\t\t\t\t\t<a class="image" href="javascript:;"></a>\n\t\t\t\t\t\t\t\t\t<a class="choose" href="javascript:;">选取头像</a>\n\t\t\t\t\t\t\t\t\t<input type="file" id="avatar-file" accept="' + acceptStr + '" />\n\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t<input type="text" class="nickname" placeholder="我的昵称"/>\n\t\t\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-fullwidth" id="btn-finish">完成</a>\n\t\t\t\t\t\t\t</form>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>', dlg = $(tmpl), loading = dlg.find(".global-loading");
            dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg), Dialog.hideMask()
            }), dlg.find(".avatar a").on("click", function () {
                $("#avatar-file").trigger("click")
            }), dlg.find("#avatar-file").change(function () {
                var files = $(this)[0].files;
                if (files.length) {
                    var file = files[0];
                    return console.log("file type:", file), supportedImageFormat.indexOf(file.type) < 0 ? void Dialog.toast("头像格式仅支持jpg、jpeg、png、bmp", 3e3) : void Dialog.imageCropper(file, function (dataURL) {
                        dlg.find(".image").css("background-image", "url(" + dataURL + ")"), dlg.find(".avatar").attr("data-file", dataURL)
                    })
                }
            }), dlg.find("#btn-finish").on("click", function () {
                var avatar = dlg.find(".avatar").attr("data-file"), nickname = dlg.find(".nickname").val();
                loading.addClass("show"), $.ajax({
                    url: "/user/editUserInfo",
                    type: "POST",
                    dataType: "json",
                    data: {avatar: avatar, nickname: nickname}
                }).done(function (res) {
                    jlutil.displayResponse("setting profile", res), "1" == res.status ? window.location.reload() : Dialog.toast(res.msg)
                }).fail(function () {
                    jlutil.displayResponse("setting profile", res)
                }).always(function () {
                    loading.removeClass("show")
                })
            }), Dialog.showMask(), Dialog.fadein(dlg)
        }
    }, {
        key: "imageCropper", value: function () {
            var file = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : null, callback = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : null, delay = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : 0;
            if (!file)return void console.error("No file specified");
            var tmpl = '<div class="dialog" id="dialog-image-cropper">\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t<header>图像剪裁</header>\n\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t<div class="img-container"></div>\n\t\t\t\t\t\t\t<div class="actions">\n\t\t\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-crop">裁剪</a>\n\t\t\t\t\t\t\t\t<a href="javascript:;" class="btn btn-grey btn-cancel">取消</a>\n\t\t\t\t\t\t\t\t<div class="clearfix"></div>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>', dlg = $(tmpl), $btnCrop = dlg.find(".btn-crop"), $btnCancel = dlg.find(".btn-cancel"), $image = null, destroyHandler = function () {
                $image.cropper("destroy"), Dialog.fadeout(dlg)
            };
            dlg.find(".close").on("click", function () {
                destroyHandler()
            });
            var reader = new FileReader;
            reader.readAsDataURL(file), reader.onload = function (e) {
                var dom = '<img src="' + e.target.result + '">';
                dlg.find(".img-container").html(dom), $image = dlg.find(".img-container > img"), $image.cropper({
                    viewMode: 1,
                    aspectRatio: 1,
                    dragMode: "move",
                    autoCropArea: 1,
                    restore: !1,
                    guides: !0,
                    center: !0,
                    highlight: !1,
                    movable: !1,
                    scalable: !1,
                    zoomable: !1,
                    cropBoxMovable: !0,
                    cropBoxResizable: !0
                }), $btnCrop.on("click", function () {
                    var croppedCanvas = $image.cropper("getCroppedCanvas"), dataUrl = croppedCanvas.toDataURL();
                    callback && setTimeout(function () {
                        return callback(dataUrl)
                    }, delay), destroyHandler()
                }), $btnCancel.on("click", destroyHandler)
            }, Dialog.fadein(dlg)
        }
    }, {
        key: "imagePreview", value: function () {
            var imgURL = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "", imgID = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "", filename = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : "";
            if ("string" != typeof imgURL)return void console.error("imgURL should be string");
            var tmpl = '<div id="dialog-image-preview-wrapper">\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t<div class="dialog" id="dialog-image-preview">\n\t\t\t\t\t\t\t<div class="content">\n\t\t\t\t\t\t\t\t<img src="' + imgURL + '"/>\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t<a href="down?image=' + imgID + '" download="' + filename + '" class="btn btn-main btn-fullwidth btn-download">保存图片</a>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<a href="javascript:;" class="btn-prev"></a>\n\t\t\t\t\t\t<a href="javascript:;" class="btn-next"></a>\n\t\t\t\t\t</div>', dlg = $(tmpl), containImage = function (imgURL) {
                var $content = dlg.find(".content"), $img = $content.find("img"), cw = $content.width(), ch = $content.height(), img = new Image;
                img.src = imgURL, img.onload = function () {
                    var iw = img.width, ih = img.height;
                    cw * ih > iw * ch ? ($img.removeClass("horizontal"), $img.addClass("vertical")) : ($img.removeClass("vertical"), $img.addClass("horizontal"))
                }
            };
            dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg), Dialog.hideMask()
            });
            var traverseConfig = {imgURL: imgURL, imgID: imgID, filename: filename};
            dlg.find(".btn-prev").on("click", function () {
                var _self = $('[data-id="' + traverseConfig.imgID + '"]'), target = _self.prev();
                return target.length ? (traverseConfig.imgURL = target.find("img").attr("src").replace(/mid_/, ""), traverseConfig.imgID = target.attr("data-id"), traverseConfig.filename = target.find("p").text(), dlg.find("img").attr("src", traverseConfig.imgURL), containImage(traverseConfig.imgURL), void dlg.find(".btn-download").attr({
                    href: "down?image=" + traverseConfig.imgID,
                    download: traverseConfig.filename
                })) : void Dialog.toast("已经是第一张了")
            }), dlg.find(".btn-next").on("click", function () {
                var _self = $('[data-id="' + traverseConfig.imgID + '"]'), target = _self.next();
                return target.length ? (traverseConfig.imgURL = target.find("img").attr("src").replace(/mid_/, ""), traverseConfig.imgID = target.attr("data-id"), traverseConfig.filename = target.find("p").text(), dlg.find("img").attr("src", traverseConfig.imgURL), containImage(traverseConfig.imgURL), void dlg.find(".btn-download").attr({
                    href: "down?image=" + traverseConfig.imgID,
                    download: traverseConfig.filename
                })) : void Dialog.toast("这是当前最后一张了")
            }), Dialog.showMask(), setTimeout(function () {
                containImage(traverseConfig.imgURL)
            }, 0), Dialog.fadein(dlg), $(window).on("resize", function () {
                containImage(traverseConfig.imgURL)
            })
        }
    }, {
        key: "imageCaptchaDialog", value: function () {
            var useMask = arguments.length > 0 && void 0 !== arguments[0] && arguments[0], callback = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : null, tmpl = '<div class="dialog" id="dialog-image-captcha">\n\t\t\t\t\t\t<div class="close"></div>\n\t\t\t\t\t\t<div class="captcha-wrapper">\n\t\t\t\t\t\t\t<input type="text" class="captcha" data-captcha-type="image" placeholder="请输入验证码"/>\n\t\t\t\t\t\t\t<div class="image-captcha">\n\t\t\t\t\t\t\t\t<img src="" alt="点击刷新" />\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t<a href="javascript:;" class="btn btn-main btn-ok">确定</a>\n\t\t\t\t\t</div>', dlg = $(tmpl);
            dlg.find(".close").on("click", function () {
                Dialog.fadeout(dlg), useMask && Dialog.hideMask()
            });
            var imageCaptcha = jlutil.getImageCaptcha();
            dlg.find(".image-captcha img").prop("src", imageCaptcha), dlg.find(".image-captcha").on("click", function () {
                var imageCaptcha = jlutil.getImageCaptcha();
                $(this).find("img").prop("src", imageCaptcha)
            }), dlg.find(".btn-ok").on("click", function () {
                var captcha = dlg.find(".captcha").val();
                return captcha ? (Dialog.fadeout(dlg), useMask && Dialog.hideMask(), void(callback && "function" == typeof callback && callback(captcha))) : void Dialog.toast("请输入图形验证码")
            }), useMask && Dialog.showMask(), Dialog.fadein(dlg)
        }
    }]), Dialog
}();
Dialog.ANIMATION_DURATION = 500, Dialog.TOAST_DURATION_DEFAULT = 1e3;
"use strict";
$(document).ready(function () {
    var userInfo = jlutil.getUserInfo();
    JETLI_DEBUG_MODE && (console.log("userInfo: ", userInfo), console.log("")), window.user = {
        isLoggedin: !!userInfo,
        info: userInfo
    }, setTimeout(function () {
        jlutil.fixImageRatio()
    }, 0);
    var header = $("#mf-header"), drawer = $("#mf-drawer");
    $("#mf-footer"), $(".mf-main");
    drawer.find("input").on("click", function () {
        jlutil.stopPropagation()
    }), drawer.find(".search-btn").on("click", function () {
        jlutil.stopPropagation();
        var searchTxt = $('#mf-drawer input[type="search"]').val();
        if (searchTxt) {
            var query = encodeURIComponent(searchTxt);
            window.location.href = "/index/search?q=" + query
        }
    }), drawer.find('input[type="search"]').on("keydown", function (e) {
        var keyCode = jlutil.getKeyCode(e);
        if (13 == keyCode) {
            var searchTxt = $(this).val();
            if (!searchTxt)return;
            var query = encodeURIComponent(searchTxt);
            window.location.href = "/index/search?q=" + query
        }
    }), drawer.find(".links li.has-qrcode").hover(function () {
        $(window).width() > 1024 && $(this).find(".qrcode").addClass("show")
    }, function () {
        $(this).find(".qrcode").removeClass("show")
    }), $(".hamburger").on("click", function () {
        jlutil.stopPropagation(), $("#mf-drawer, #ad-top, #mf-header, #mf-footer, .mf-main").toggleClass("open")
    }), header.find(".search-btn").on("click", function () {
        jlutil.stopPropagation();
        var searchInput = header.find('.search-box input[type="search"]'), searchTxt = searchInput.val();
        if (searchInput.hasClass("open"))if (searchTxt) {
            var query = encodeURIComponent(searchTxt);
            window.location.href = "/index/search?q=" + query
        } else searchInput.removeClass("open"); else searchInput.addClass("open").focus()
    }), header.find('.search-box input[type="search"]').on("keydown", function (e) {
        var keyCode = jlutil.getKeyCode(e);
        if (13 == keyCode) {
            var searchTxt = $(this).val();
            if (!searchTxt)return;
            var query = encodeURIComponent(searchTxt);
            window.location.href = "/index/search?q=" + query
        }
    }), header.find(".user-center").on("click", function () {
        jlutil.stopPropagation(), user.isLoggedin ? $("#mf-header .options").toggleClass("open") : Dialog.login()
    }), $(document).on("click", function (e) {
        drawer.is(e.target) || drawer.has(e.target).length || ($("#mf-drawer, #ad-top, #mf-header, #mf-footer, .mf-main").removeClass("open"), user.isLoggedin && $("#mf-header .options").removeClass("open"))
    });
    var fixHeaderPosition = function () {
        var ad = $("#ad-top");
        if (ad.length) {
            var st = $(window).scrollTop(), adh = ad.outerHeight();
            st >= adh ? header.addClass("fixed") : header.removeClass("fixed")
        } else header.addClass("fixed")
    }, fixHeaderHeight = function () {
        var ah = $("#ad-top").length ? $("#ad-top").outerHeight() : 0, ww = $(window).width(), st = $(window).scrollTop(), limit = 100;
        ww > 1024 && st - ah > limit ? (header.addClass("shrink"), drawer.addClass("shrink")) : (header.removeClass("shrink"), drawer.removeClass("shrink"))
    }, commonInit = function () {
        Dialog.upgradeBrowser();
        var avatar = header.find(".user-center .avatar"), avatarStr = "";
        if (user.isLoggedin) {
            var headImg = userInfo.head_img || "/public/static/img/avatar-default.png";
            avatarStr = '<div class="avatar-wrapper">\n\t\t\t\t\t\t\t<img src="' + headImg + '">\n\t\t\t\t\t\t</div>'
        } else avatarStr = '<span><i class="fa fa-user-circle-o"></i></span>';
        avatar.html(avatarStr), user.isLoggedin && $.ajax({
            url: "/user/countUnreadNotify",
            type: "GET",
            dataType: "json"
        }).then(function (res) {
            return JETLI_DEBUG_MODE && jlutil.displayResponse("getting unread notification count", res), res
        }).done(function (res) {
            if ("1" == res.status) {
                var hasNotification = Number(res.data) > 0;
                avatar.attr("data-has-notification", hasNotification), hasNotification && $("#notification-count").text("(" + res.data + ")")
            }
        }), setTimeout(function () {
            jlutil.fixImageRatio(), fixHeaderHeight(), fixHeaderPosition(), jlutil.fixFooterPosition()
        }, 0)
    };
    commonInit(), $(window).on("resize", function () {
        jlutil.fixImageRatio(), fixHeaderHeight(), jlutil.fixFooterPosition()
    }), $(window).on("scroll", function () {
        fixHeaderPosition(), fixHeaderHeight(), jlutil.fixFooterPosition()
    }), "onhashchange" in window && $(window).on("hashchange", function () {
        setTimeout(function () {
            jlutil.fixFooterPosition()
        }, 0)
    })
});