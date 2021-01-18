
var prefix = "/information/leaveMessage"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listShoucang", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
                                shoucangids:$("#shoucangids").val()
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
                            {
                                field : 'name',
                                title : '真实姓名'
                            },
                            {
                                field : 'heardUrl',
                                title : '头像' ,
                                formatter : function(value, row, index) {
                                    var e = '<div class="image"><img width="90" height="100" alt="image" class="img-responsive" src="' + value + '"></div>'
                                    return e;
                                }
                            },

                            {
                                field : 'phone',
                                title : '手机号'
                            },
                            {
                                field : 'email',
                                title : '邮箱'
                            },

                            {
                                field : 'nickname',
                                title : '昵称'
                            }
                        ]
					});
	
	
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function shenhe(id){
	layer.open({
		type : 2,
		title : '审核',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '360px' ],
		content : prefix + '/shenhe/' + id // iframe的url
	});
}

function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '800px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
    var addPage=layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
    layer.full(addPage);
}
function updateEnable1(id,enable){
	var isEnable = 0;
	if($(enable).prop("checked")){
		isEnable = 1;
	}
	
	$.ajax({
		url : prefix + "/updateEnable1",
		type : "post",
		data : {
			'id' : id,
			'enable' : isEnable
		},
		dataType: 'JSON',
		async : false,
		success : function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				reLoad();
			} else {
				layer.msg(r.msg);
			}
		}
	});
}
function updateEnable2(id,enable){
	var isEnable = 0;
	if($(enable).prop("checked")){
		isEnable = 1;
	}
	
	$.ajax({
		url : prefix + "/updateEnable2",
		type : "post",
		data : {
			'id' : id,
			'enable' : isEnable
		},
		dataType: 'JSON',
		async : false,
		success : function(r) {
			if (r.code == 0) {
				layer.msg(r.msg);
				reLoad();
			} else {
				layer.msg(r.msg);
			}
		}
	});
}
function updateEnable5(id,enable){
    var isEnable = 0;
    if($(enable).prop("checked")){
        isEnable = 1;
    }

    $.ajax({
        url : prefix + "/updateEnable5",
        type : "post",
        data : {
            'id' : id,
            'enable' : isEnable
        },
        dataType: 'JSON',
        async : false,
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}



function myask(id){
	window.location.href="/information/ask/"+id;
}

function comment(id){
	window.location.href="/information/comment/"+id;
}

function xiazai(id){
	window.location.href="/information/leavemessage/download/"+id;
}

function pinglun(id){
	window.location.href="/information/leaveComment/"+id;
}

function shoucang(){
    window.location.href="/information/leavemessage/shoucang"+id;
}