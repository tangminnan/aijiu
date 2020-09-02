package com.aijiu.information.controller;

import java.util.List;
import java.util.Map;

import com.aijiu.common.utils.PageUtils;
import com.aijiu.common.utils.Query;
import com.aijiu.common.utils.R;
import com.aijiu.information.domain.LeaveMessageDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aijiu.information.domain.LeaveCommentDO;
import com.aijiu.information.service.LeaveCommentService;

/**
 * 帖子评价表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-27 14:14:30
 */
 
@Controller
@RequestMapping("/information/leaveComment")
public class LeaveCommentController {
	@Autowired
	private LeaveCommentService leaveCommentService;
	
	@GetMapping("/{id}")
	String LeaveComment(@PathVariable("id") Long id,Model model){
		model.addAttribute("id",id);
	    return "information/leaveComment/leaveComment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LeaveCommentDO> leaveCommentList = leaveCommentService.list(query);
		int total = leaveCommentService.count(query);
		PageUtils pageUtils = new PageUtils(leaveCommentList, total);
		return pageUtils;
	}
	


	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		LeaveCommentDO leaveComment = leaveCommentService.get(id);
		model.addAttribute("leaveComment", leaveComment);
	    return "information/leaveComment/edit";
	}
	


	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(leaveCommentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable")
	public R updateEnable(Long id,Integer enable) {
		LeaveCommentDO leaveCommentDO = new LeaveCommentDO();
		leaveCommentDO.setId(id);
		leaveCommentDO.setIsEnable(enable);
		leaveCommentService.update(leaveCommentDO);
		return R.ok();
	}
	
}
