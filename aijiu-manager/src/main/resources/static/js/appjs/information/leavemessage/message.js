
var prefix = "/information/leaveMessage"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
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
								userId:$("#user_id option:selected").val(),
								auditStatus:$("#auditStatus").val(),

								addStaTime:$("#addStaTime").val(),
								addEndTime:$("#addEndTime").val()
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
									field : 'id', 
									title : '编号'
								},
																{
                                field : 'nickName',
                                title : '发帖人'
                            },
                           /* {
                                field : 'heardUrl',
                                title : '头像'
                            },*/

							/*{
									field : 'showhide', 
									title : '其他用户是否可见',
									formatter : function(value,row,index){
										if(value == 0)
											return "可见";
										if(value == 1)
											return "不可见";
									}
								},*/
								
								
								{
									field : 'auditStatus', 
									title : '审核状态',
									formatter : function(value,row,index){
										if(value == 1)
											return "未审核";
										if(value == 2)
											return "审核通过";
										if(value == 3)
											return "审核未通过";
									}
								},
																{
									field : 'shoucangcount',
									title : '收藏数'
								},
                            {
                                field : 'pingluncount',
                                title : '评论数'
                            },
																/*{
									field : 'addDigest', 
									title : '帖子是否加精',
									formatter : function(value, row, index) {
										var str = '';
										
										str +=' <div class="switch onoffswitch col-sm-1"> ';
											str +=' <div class="onoffswitch"> ';
												str +=' <input name="allowComment" '; 
												//帖子是否加精 0：否1：是
												if(row.addDigest == 1)
													str += ' checked="" ';
													
												str +=' type="checkbox" onchange="updateEnable1(' +row.id+ ',this)" value="' +row.id+ '" class="onoffswitch-checkbox" id="example1' +row.id+ '">  ';
												str +=' <label class="onoffswitch-label" for="example1' +row.id+ '">  ';
													str +=' <span class="onoffswitch-inner"></span> ';
													str +=' <span class="onoffswitch-switch"></span> ';
														str +=' </label> ';
											str +=' </div>';
										str +=' </div>';
										return str;
									}
								},								{
									field : 'topTheme',
                                title : '帖子是否置顶',
									formatter : function(value, row, index) {
										var str = '';
										
										str +=' <div class="switch onoffswitch col-sm-1"> ';
											str +=' <div class="onoffswitch"> ';
												str +=' <input name="allowComment" '; 
												//帖子是否置顶 0：否1：是
												if(row.topTheme == 1)
													str += ' checked="" ';
													
												str +=' type="checkbox" onchange="updateEnable2('+row.id+',+this)" value="' +row.id+ '" class="onoffswitch-checkbox" id="example2' +row.id+ '">  ';
												str +=' <label class="onoffswitch-label1" for="example2' +row.id+ '">  ';
													str +=' <span class="onoffswitch-inner1"></span> ';
													str +=' <span class="onoffswitch-switch1"></span> ';
														str +=' </label> ';
											str +=' </div>';
										str +=' </div>';
										return str;
									}
								},*/
                            {
                                field : 'tuijianFlag',
                                title : '帖子是否推荐',
                                formatter : function(value, row, index) {
                                    var str = '';

                                    str +=' <div class="switch onoffswitch col-sm-1"> ';
                                    str +=' <div class="onoffswitch"> ';
                                    str +=' <input name="allowComment" ';
                                    //帖子是否推荐 0：否1：是
                                    if(row.tuijianFlag == 1)
                                        str += ' checked="" ';

                                    str +=' type="checkbox" onchange="updateEnable5(' +row.id+ ',this)" value="' +row.id+ '" class="onoffswitch-checkbox" id="example1' +row.id+ '">  ';
                                    str +=' <label class="onoffswitch-label" for="example1' +row.id+ '">  ';
                                    str +=' <span class="onoffswitch-inner"></span> ';
                                    str +=' <span class="onoffswitch-switch"></span> ';
                                    str +=' </label> ';
                                    str +=' </div>';
                                    str +=' </div>';
                                    return str;
                                }
                            },
                            {
                                field : 'publishTime',
                                title : '发贴时间'
                            },
                            {
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
									/*	var a = '<a class="btn  btn-xs btn-danger'+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
										+ row.id
										+ '\')"><i class="fa fa-remove"></i></a> ';*/
										var a='<button type="button" class="btn btn-xs btn-danger " onclick="remove(\''+row.id+'\')">删除</button>  ';
										var e='<button type="button" class="btn  btn-xs btn-info" onclick="edit(\''+row.id+'\')">查看内容</button>  ';
										var d= '<button type="button" class="btn btn-xs btn-success" onclick="shenhe(\''+row.id+'\')">审核</button>  ';
                                        var f= '<button type="button" class="btn btn-xs btn-success" onclick="pinglun(\''+row.id+'\')">评论人</button>  ';
                                        var g= '<button type="button" class="btn btn-xs btn-success" onclick="shoucang(\''+row.shoucangids+'\')">收藏人</button>  ';
										if(row.auditStatus == 1){//未审核
											return a+e+d;
										}
										else if(row.auditStatus == 3){//审核不通过
											return a+e;
										}
										else{//审核通过
											return a+e+f+g;
										}
									}
								} ]
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

function shoucang(shoucangids){
	if(shoucangids=="null")
		shoucangids="00";
    window.location.href="/information/leaveMessage/shoucang/"+shoucangids;
}