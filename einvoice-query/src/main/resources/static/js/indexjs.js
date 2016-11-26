$(function () {
    var InvoiceCode = "";
    var InvoiceNumber = "";
    var CurrHeight = window.screen.availHeight - 700;
    $(".divfoot").css("margin-top", CurrHeight + "px");
    $(".epmaindiv").css("margin-top", window.screen.availHeight * 1 / 6 + "px");

    //console.log(CurrHeight);
    $("#btnclick").bind("click", function () {
        var txtvaluse = $("#mytxtorder").val();

        if (txtvaluse.length > 35) {
            alert("您输入的订单号长度超过了限制");
            return false;
        }

        if (txtvaluse != "") {
            $(".epmaindiv").removeAttr("style");
            $(".epmaindiv").attr("class", "epmaindiv-st2");
            $(".divimg").attr("class", "divimg-st2");



            $(".divbtncheck").attr("class", "divbtncheck-st2");
            $(".divtable").attr("class", "divtable-st2");

            $(".divfoot").attr("class", "divfoot-st2");

            $(".downloadup").attr("class", "downloadup-st2");
            $(".myShowRemrk").hide();
            $LoadO2OInvoice(txtvaluse);

            // $("#downloadup").bind("click", function () { LoadO2OInvoicePDf("/Home/ImportTemplate?InvoiceCode= " + InvoiceCode + "&InvoiceNumber=" + InvoiceNumber + "") });

            // $("#downloadup").bind("click", function () { LoadO2OInvoicePDf("/Home/index"); });
            //   LoadO2OInvoicePDf("/ElectronicTicket/ImportTemplate?id= " + Tickettion[0].data.Id);
        }
        else {
            $(".epmaindiv-st2").attr("class", "epmaindiv");
            $(".divimg-st2").attr("class", "divimg");
            $(".divbtncheck-st2").attr("class", "divbtncheck");
            $(".divtable-st2").attr("class", "divtable");
            $(".divfoot-st2").attr("class", "divfoot");
            $(".downloadup-st2").attr("class", "downloadup");
            $(".myShowRemrk").show();

        }
    });
    //如果订单号为空就直接查询数据
    if (OrderNum != null && OrderNum != "") {
        $("#mytxtorder").val(OrderNum);
        $("#btnclick").click();
    }

});

//PDF路径
var PdfPath = "";
var data_EInvoiceItemList = "";


//加载O2O发票信息
$LoadO2OInvoice = function (ordernum) {
    //$("#downloadup").unbind("click");//卸载绑定事件
    if (ordernum.length > 35) {
        alert("您输入的订单号长度超过了限制");
        window.location.reload();
    }
    $.ajax({
        type: "POST",
        url: "/EInvoiceSearch",
        data: { orderno: ordernum },
        datatype: "json",
        //  timeout: 2000, //超时时间设置，单位毫秒
        beforeSend: function () {
            $.ShowLoadDialog();
        },
        success: function (data) {
            $.ShowLoadDialogClose();
            var MainResultHtml = "";    //电子发票主页面
            $('#receivePoints').hide();
            $('#luckDraw').hide();
            var data_EInvoiceList = "";
            if (data.indexOf('***') == -1) {
                data_EInvoiceList = data;

            }
            else {
                data_EInvoiceList = data.split("***")[0];
                data_EInvoiceItemList = data.split("***")[1];
            }

            var JsonData = JSON.parse(data_EInvoiceList);
            var currData = JsonData["Data"];
            if (currData != "" && currData != null) {
                /**绑定触发事件**************************/
                $("#downloadup").show();
                if (data_EInvoiceItemList == "") {
                    PdfPath = currData[0].PDFPath;
                }
                if (currData.length > 0) {
                    // 加载按钮
                    $.get('/Home/IsKuYouInvoice', { data: currData[0].SellerTrxCode }, function (result) {
                        if (result.Success == true) {
                            $('#receivePoints').show();
                            $('#luckDraw').show();
                            var mObj = JSON.parse(result.Message);
                            if (mObj != null) {
                                $('#receivePoints').click(function () {
                                    window.location.href = mObj.UrlOne;
                                });
                                $('#luckDraw').click(function () {
                                    window.location.href = mObj.UrlTwo;
                                });
                            }
                        }
                    });
                }
                /**************加载主数据*****************/
                for (var index in currData) {

                    MainResultHtml += "<tr onclick=\"$LoadO2OInvoiceItem('" + currData[index].UpdatedUser + "',this)\">";
                    if (index == 0) {
                        InvoiceCodeNumber = currData[index].ChangeNumber;

                        $("#downloadup").bind("click", function () { LoadO2OInvoicePDf("/Home/ImportTemplate?InvoiceCodeNumber= " + InvoiceCodeNumber) });
                        //  MainResultHtml += "<td style=\"width:50px;\" ><input type=\"checkbox\"  checked=\"checked\" id=\"EIvoiceId_" + currData[index].Id + "\" onclick='clickItem("+this.id+",'" + currData[index].InvoiceCode + "','" + currData[index].InvoiceNumber + "')'/></td>";
                        // MainResultHtml += "<td style=\"width:50px;\" ><input type='checkbox' name='demo' value=" + 9 + " id=\"EIvoiceId_" + currData[index].Id + "\"  onclick='chk(" + this.id + ")'/>" + "</td>";
                        MainResultHtml += "<td style=\"width:50px;\" ><input type=\"checkbox\"  checked=\"checked\"/></td>";
                        MainResultHtml += "<td style=\"width:220px;\" >" + currData[index].SellerName + "</td>";
                        MainResultHtml += "<td >" + currData[index].OrderDate + "</td>";
                        MainResultHtml += "<td >" + currData[index].MadeOutDate + "</td>";
                        MainResultHtml += "<td>" + currData[index].PlatformNumber + "</td>";
                        MainResultHtml += "<td >" + currData[index].Amount + "</td>";
                        MainResultHtml += "<td>" + currData[index].SumTrxAmount + "</td>";
                        MainResultHtml += "<td>" + currData[index].SumAmount + "</td>";
                        MainResultHtml += "<td>" + currData[index].EInvType + "</td>";
                        MainResultHtml += "<td>" + currData[index].InvoiceCode + "</td>";
                        MainResultHtml += "<td>" + currData[index].InvoiceNumber + "</td>";
                    }
                    else {
                        MainResultHtml += "<td><input type=\"checkbox\" /></td>";
                        MainResultHtml += "<td style=\"width:220px;\" >" + currData[index].SellerName + "</td>";
                        MainResultHtml += "<td >" + currData[index].OrderDate + "</td>";
                        MainResultHtml += "<td >" + currData[index].MadeOutDate + "</td>";
                        MainResultHtml += "<td >" + currData[index].PlatformNumber + "</td>";
                        MainResultHtml += "<td >" + currData[index].Amount + "</td>";
                        MainResultHtml += "<td >" + currData[index].SumTrxAmount + "</td>";
                        MainResultHtml += "<td >" + currData[index].SumAmount + "</td>";
                        MainResultHtml += "<td >" + currData[index].EInvType + "</td>";
                        MainResultHtml += "<td >" + currData[index].InvoiceCode + "</td>";
                        MainResultHtml += "<td >" + currData[index].InvoiceNumber + "</td>";
                    }

                    MainResultHtml += "</tr>";


                }
            }
            else {
                MainResultHtml = "<tr><td colspan='10' style=\"background-color: rgb(243, 241, 241);\" >暂无数据</td></tr>";
                rsultHtml = "<tr><td colspan='10' >暂无数据</td></tr>";
                $("#downloadup").hide();
                $("#dtbodyvadetaillu").html("");
            }

            //数据内容
            $("#dtbodyvalu").html(MainResultHtml);

            //改变表格颜色
            $("#dtbodyvalu tr td").mouseover(function () {
                $(this).parent().find("td").css("background-color", "#d9eaf5");
            });
            $("#dtbodyvalu tr td").mouseout(function () {
                var bgc = $(this).parent().attr("bg");
                $(this).parent().find("td").css("background-color", bgc);
            });

            var color = "#f3f1f1"
            $("#dtbodyvalu tr:even td").css("background-color", color);  //改变偶数行背景色
            /* 把背景色保存到属性中 */
            $("#dtbodyvalu tr:even").attr("bg", color);
            $("#dtbodyvalu tr:odd").attr("bg", "#fff");

            $("#dtbodyvalu tr")[0].click();

        },
        complete: function (XMLHttpRequest, textStatus) {
            if (textStatus == 'timeout') {//超时,status还有success,error等值的情况
                $.ShowLoadDialogClose();
                var MainResultHtml = "<tr><td colspan='10' style=\"background-color: rgb(243, 241, 241);\" >数据请求超时</td></tr>";
                //数据内容
                $("#dtbodyvalu").html(MainResultHtml);
                $("#downloadup").hide();
                $("#dtbodyvadetaillu").html("");
            }
        },
        error: function () {
            $.ShowLoadDialogClose();
            var MainResultHtml = "<tr><td colspan='10' style=\"background-color: rgb(243, 241, 241);\" >数据请求错误</td></tr>";
            //数据内容
            $("#dtbodyvalu").html(MainResultHtml);

            $("#downloadup").hide();
            $("#dtbodyvadetaillu").html("");
        }
    });
}

//function chk(id) {
//    alert(id);
//    var obj = document.getElementsByName('demo'); //选择所有name="'demo'"的对象，返回数组
//    //取到对象数组后，我们来循环检测它是不是被选中
//    var s = '';
//    for (var i = 0; i < obj.length; i++) {
//        if (obj[i].checked) { s += obj[i].value + ',' }; //如果选中，将value添加到变量s中
//    }
//    //那么现在来检测s的值就知道选中的复选框的值了
//    alert(s == '' ? '你还没有选择任何内容！' : s);
//}

//加载O2O发票详细信息
$LoadO2OInvoiceItem = function (eid, obj) {
    $(obj).siblings().find("input:checkbox").removeAttr("checked");
    $(obj).find("input:checkbox").attr("checked", true);
    $.ShowLoadDialogClose();

    if (data_EInvoiceItemList == "") {
        // PdfPath = path;
        $.ajax({
            type: "POST",
            url: "/Home/EInvoiceItemLoad",
            data: { EidSecret: eid },
            datatype: "json",
            beforeSend: function () {
                // $.ShowLoadDialog();
            },
            success: function (data) {
                data_EInvoiceItemList = data;
                var rsultHtml = "";         //电子发票详细页面
                var JsonData = JSON.parse(data_EInvoiceItemList);
                var currData = JsonData["Data"];
                if (currData != "") {
                    for (var index in currData) {
                        rsultHtml += "<tr>";
                        rsultHtml += "<td>" + currData[index].Id + "</td>";
                        rsultHtml += "<td style=\"width:220px;\">" + currData[index].GoodsName + "</td>";
                        rsultHtml += "<td>" + currData[index].Marque + "</td>";
                        rsultHtml += "<td>" + currData[index].UnitPrice + "</td>";
                        rsultHtml += "<td>" + currData[index].Quantity + "</td>";
                        rsultHtml += "<td>" + currData[index].TrxRate + "</td>";
                        rsultHtml += "<td>" + currData[index].TrxAmount + "</td>";
                        rsultHtml += "<td>" + currData[index].SumAmount + "</td>";
                        rsultHtml += "</tr>";
                    }
                }
                else {
                    rsultHtml = "<tr><td colspan='9' >暂无数据</td></tr>";
                }
                //数据内容
                $("#dtbodyvadetaillu").html(rsultHtml);
                //改变表格颜色
                $("#dtbodyvadetaillu tr td").mouseover(function () {
                    $(this).parent().find("td").css("background-color", "#d9eaf5");
                });
                $("#dtbodyvadetaillu tr td").mouseout(function () {
                    var bgc = $(this).parent().attr("bg");
                    $(this).parent().find("td").css("background-color", bgc);
                });

                var color = "#f3f1f1"
                $("#dtbodyvadetaillu tr:odd td").css("background-color", color);  //改变偶数行背景色
                /* 把背景色保存到属性中 */
                $("#dtbodyvadetaillu tr:odd").attr("bg", color);
                $("#dtbodyvadetaillu tr:even").attr("bg", "#fff");

                data_EInvoiceItemList = "";
            },
            complete: function (XMLHttpRequest, textStatus) {
            },
            error: function () {

            }
        });
    }
    else {
        var rsultHtml = "";         //电子发票详细页面
        var JsonData = JSON.parse(data_EInvoiceItemList);
        var currData = JsonData["Data"];
        if (currData != "") {
            for (var index in currData) {
                rsultHtml += "<tr>";
                rsultHtml += "<td>" + currData[index].Id + "</td>";
                rsultHtml += "<td style=\"width:220px;\">" + currData[index].GoodsName + "</td>";
                rsultHtml += "<td>" + currData[index].Marque + "</td>";
                rsultHtml += "<td>" + currData[index].UnitPrice + "</td>";
                rsultHtml += "<td>" + currData[index].Quantity + "</td>";
                rsultHtml += "<td>" + currData[index].TrxRate + "</td>";
                rsultHtml += "<td>" + currData[index].TrxAmount + "</td>";
                rsultHtml += "<td>" + currData[index].SumAmount + "</td>";
                rsultHtml += "</tr>";
            }
        }
        else {
            rsultHtml = "<tr><td colspan='9' >暂无数据</td></tr>";
        }
        //数据内容
        $("#dtbodyvadetaillu").html(rsultHtml);
        //改变表格颜色
        $("#dtbodyvadetaillu tr td").mouseover(function () {
            $(this).parent().find("td").css("background-color", "#d9eaf5");
        });
        $("#dtbodyvadetaillu tr td").mouseout(function () {
            var bgc = $(this).parent().attr("bg");
            $(this).parent().find("td").css("background-color", bgc);
        });

        var color = "#f3f1f1"
        $("#dtbodyvadetaillu tr:odd td").css("background-color", color);  //改变偶数行背景色
        /* 把背景色保存到属性中 */
        $("#dtbodyvadetaillu tr:odd").attr("bg", color);
        $("#dtbodyvadetaillu tr:even").attr("bg", "#fff");
        data_EInvoiceItemList = "";
    }

    //},
    //complete: function (XMLHttpRequest, textStatus) {
    //},
    //error: function () {

    //}
    //});
}


////打印O2O发票明细
//$LoadO2OInvoicePDf = function (urlpath) {
//    if (urlpath != "") {
//        //window.open(urlpath);
//        window.location.href = urlpath;
//    }
//}


//打印O2O发票明细
function LoadO2OInvoicePDf(urlpath) {
    if (PdfPath != "") {
        window.location.href = PdfPath;
    }
    else {
        //console.log(urlpath);
        var iWidth = 800; //弹出窗口的宽度;
        var iHeight = 800; //弹出窗口的高度;
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
        var opencase = "height=" + iHeight + ", width=" + iWidth + ",top=" + iTop + ", left=" + iLeft + ", toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no";
        //window.open(urlpath, 'newwindow', opencase) //这句要写成一行
        var newTab = window.open(urlpath, 'newwindow', opencase);
        //newTab.location.href = urlpath;
        //window.showModalDialog(urlpath);
    }
}

function CheckLenght() {
    var vl = $("#mytxtorder").val();
    if (vl.length > 40) {
        alert("输入的订单号超过了字数限制");
    }
}

function clickItem(eid, InvoiceCode, InvoiceNumber) {
    var box = $('#' + eid);
    if (box.attr('checked') == 'checked') {
        alert("选中");
    }
    else {
        alert("未选中");


    }
}