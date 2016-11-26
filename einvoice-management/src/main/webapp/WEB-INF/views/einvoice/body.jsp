<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>项目明细</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/einvoice/detail/${id}',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'pk_einvoicebody',
            sortName : 'pk_einvoicebody',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '100',
                title : '行号',
                field : 'row_no'
            }, {
                width : '100',
                title : '发票行性质',
                field : 'fphxz',
                sortable : true,
                formatter : function(value, row, index) {
                    //0正常行、1折扣行、2被折扣行
                    switch (value) {
                        case 0:
                            return '正常行';
                        case 1:
                            return '折扣行';
                        case 2:
                            return '被折扣行';

                    }
                }},
                {
                width : '150',
                title : '名称',
                field : 'xmmc'
            } , {
                width : '80',
                title : '计量单位',
                field : 'dw'
            }, {
                width : '100',
                title : '规格型号',
                field : 'ggxh'
            } ,{
                width : '100',
                title : '项目数量',
                field : 'xmsl'
            },{
                width : '100',
                title : '项目单价',
                field : 'xmdj'
            },{
                width : '100',
                title : '项目金额',
                field : 'xmje'
            }, {
                width : '100',
                title : '税率',
                field : 'sl'
            },{
                width : '100',
                title : '税额',
                field : 'se'
            }] ],
            onLoadSuccess:function(data){
            },
            toolbar : '#toolbar'
        });
    });
    
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',fit:true,border:false">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
    </div>
</body>
</html>