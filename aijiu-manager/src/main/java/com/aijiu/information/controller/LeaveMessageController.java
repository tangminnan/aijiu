package com.aijiu.information.controller;

import java.util.List;
import java.util.Map;

import com.aijiu.common.utils.PageUtils;
import com.aijiu.common.utils.Query;
import com.aijiu.common.utils.R;
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

import com.aijiu.information.domain.LeaveMessageDO;
import com.aijiu.information.service.LeaveMessageService;

/**
 * 发帖记录表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-18 15:15:55
 */
 
@Controller
@RequestMapping("/information/leaveMessage")
public class LeaveMessageController {
	@Autowired
	private LeaveMessageService leaveMessageService;
	
	@GetMapping()
	@RequiresPermissions("information:leaveMessage:leaveMessage")
	String LeaveMessage(){
	    return "information/leaveMessage/message";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:leaveMessage:leaveMessage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LeaveMessageDO> leaveMessageList = leaveMessageService.list(query);
		int total = leaveMessageService.count(query);
		PageUtils pageUtils = new PageUtils(leaveMessageList, total);
		return pageUtils;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:leaveMessage:add")
	public R save( LeaveMessageDO leaveMessage){
		if(leaveMessageService.save(leaveMessage)>0){
			return R.ok();
		}
		return R.error();
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long  id,Model model){
		LeaveMessageDO leaveMessageDO = leaveMessageService.get(id);
		model.addAttribute("message",leaveMessageDO);
		return "information/leaveMessage/edit";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:leaveMessage:edit")
	public R update( LeaveMessageDO leaveMessage){
		leaveMessageService.update(leaveMessage);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:leaveMessage:remove")
	public R remove( Long id){
		LeaveMessageDO leaveMessageDO = new LeaveMessageDO();
		leaveMessageDO.setDeleteFlag(1);
		leaveMessageDO.setId(id);
		if(leaveMessageService.update(leaveMessageDO)>0){
		return R.ok();
		}
		return R.error();
	}

	@GetMapping("/shenhe/{id}")
	String shenhe(@PathVariable("id") Long id,Model model){
		LeaveMessageDO message = leaveMessageService.get(id);
		model.addAttribute("message", message);
		return "information/leavemessage/shenhe";
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable1")
	public R updateEnable1(Long id,Integer enable) {
		LeaveMessageDO leaveMessageDO = new LeaveMessageDO();
		leaveMessageDO.setId(id);
		leaveMessageDO.setAddDigest(enable);
		leaveMessageService.update(leaveMessageDO);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable2")
	public R updateEnable2(Long id,Integer enable) {
		LeaveMessageDO leaveMessageDO = new LeaveMessageDO();
		leaveMessageDO.setId(id);
		leaveMessageDO.setTopTheme(enable);
		leaveMessageService.update(leaveMessageDO);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable5")
	public R updateEnable5(Long id,Integer enable) {
		LeaveMessageDO leaveMessageDO = new LeaveMessageDO();
		leaveMessageDO.setId(id);
		leaveMessageDO.setTuijianFlag(enable);
		leaveMessageService.update(leaveMessageDO);
		return R.ok();
	}
	
}
