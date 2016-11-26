<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <title>发票列表</title>
    <script type="text/javascript">
        var dataGrid;
        $(function() {
            dataGrid = $('#dataGrid').datagrid({
                url : '${path }/einvoice/dataGrid',
                striped : true,
                rownumbers : true,
                pagination : true,
                singleSelect : true,
                idField : 'pk_einvoicehead',
                sortName : 'pk_einvoicehead',
                sortOrder : 'asc',
                pageSize : 20,
                pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
                columns : [ [ {
                    width : '120',
                    title : '发票流水号',
                    field : 'fpqqlsh',
                }, {
                    width : '120',
                    title : '商户订单号',
                    field : 'order_no',
                },{
                    width : '120',
                    title : '销售方纳税人识别号',
                    field : 'xsf_nsrsbh',
                },{
                    width : '120',
                    title : '销售方名称',
                    field : 'xsf_mc',
                },{
                    width : '120',
                    title : '购买方名称',
                    field : 'gmf_mc'
                },  {
                    width : '60',
                    title : '开票类型',
                    field : 'kplx',
                    formatter : function(value, row, index) {
                        switch (value) {
                            case '0':
                                return '蓝票';
                            case '1':
                                return '红票';
                        }
                    }
                }, {
                    width : '80',
                    title : '开票人',
                    field : 'kpr',
                    sortable : true
                },{
                    width : '80',
                    title : '合计金额',
                    field : 'hjje',
                    sortable : true
                }, {
                    width : '80',
                    title : '合计税额',
                    field : 'hjse',
                    sortable : true
                },{
                    width : '150',
                    title : '状态',
                    field : 'status',
                    sortable : true,
                    formatter : function(value, row, index) {
                        switch (value) {
                            case 1:return '开票数据接收成功';
                            case 2:return '开票数据接收失败';
                            case 3:return '生成开票XML成功';
                            case 4:return '生成开票XML失败';
                            case 5:return '开票成功';
                            case 6:return '开票失败';
                            case 7:return '生成PDF成功';
                            case 8:return '生成PDF失败';
                            case 9:return '电子签名成功';
                            case 10:return '电子签名失败';
                            case 11:return '结果已回传';
                            case 12:return '蓝票已冲红';
                        }
                    }
                },{
                    width : '120',
                    title : '发票代码',
                    field : 'fp_dm'
                },{
                    width : '120',
                    title : '发票号码',
                    field : 'fp_hm'
                },{
                    width : '130s',
                    title : '创建时间',
                    field : 'createtime',
                },{
                    field : 'pk_einvoicehead',
                    title : '项目信息',
                    width : 130,
                    formatter : function(value, row, index) {
                        var str = '';
                        str += '<a href="/einvoice/body/'+value+'" target="_blank">详情</a>';
                        return str;
                    }}] ],
                onLoadSuccess:function(data){
                },
                toolbar : '#toolbar'
            });
        });
        function searchFun() {
            dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        }
        function cleanFun() {
            $('#searchForm input').val('');
            dataGrid.datagrid('load', {});
        }

    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
    <form id="searchForm">
        <table>
            <tr>
                <th>发票流水号:</th>
                <td><input name="fpqqlsh" placeholder="请输入发票流水号"/></td>
                <th>商户订单号:</th>
                <td><input name="order_no" placeholder="请输入商户订单号"/></td>
                <th>销售方名称:</th>
                <td><input name="xsf_mc" placeholder="销售方名称"/></td>
                <th>发票状态</th>
                <td>
                    <select name="status" placeholder="请选择发票状态">
                        <option value="0">请选择发票状态</option>
                        <option value="1">开票数据接收成功</option>
                        <option value="2">开票数据接收失败</option>
                        <option value="3">生成开票XML成功</option>
                        <option value="4">生成开票XML失败</option>
                        <option value="5">开票成功</option>
                        <option value="6">开票失败</option>
                        <option value="7">生成PDF成功</option>
                        <option value="8">生成PDF失败</option>
                        <option value="9">电子签名成功</option>
                        <option value="10">电子签名失败</option>
                        <option value="11">结果已回传</option>
                        <option value="12">蓝票已冲红</option>
                    </select>
                </td>
                <th>请求日期:</th>
                <td>
                    从<input name="submit_begin" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />到<input name="submit_end" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div data-options="region:'center',fit:true,border:false">
    <table id="dataGrid" data-options="fit:true,border:false"></table>
</div>
<div id="toolbar" style="display: none;">

</div>
</body>
</html>