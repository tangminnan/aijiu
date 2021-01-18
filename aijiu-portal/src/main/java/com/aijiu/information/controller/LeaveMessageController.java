package com.aijiu.information.controller;

import com.aijiu.common.utils.PageUtils;
import com.aijiu.common.utils.Query;
import com.aijiu.common.utils.R;
import com.aijiu.common.utils.StringUtils;
import com.aijiu.information.domain.LeaveMessageDO;
import com.aijiu.information.domain.UserDO;
import com.aijiu.information.service.LeaveMessageService;
import com.aijiu.information.service.UserDOService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
	@Autowired
	private UserDOService userService;
	
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
	public R save(LeaveMessageDO leaveMessage){
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
	public R update(LeaveMessageDO leaveMessage){
		leaveMessageService.update(leaveMessage);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:leaveMessage:remove")
	public R remove(Long id){
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
	public R updateEnable1(Long id, Integer enable) {
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
	public R updateEnable2(Long id, Integer enable) {
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
	public R updateEnable5(Long id, Integer enable) {
		LeaveMessageDO leaveMessageDO = new LeaveMessageDO();
		leaveMessageDO.setId(id);
		leaveMessageDO.setTuijianFlag(enable);
		leaveMessageService.update(leaveMessageDO);
		return R.ok();
	}

	@GetMapping("/shoucang/{shoucangids}")
	public String shoucang(@PathVariable("shoucangids") String shoucangids,Model model){
		model.addAttribute("shoucangids",shoucangids);
		return "information/leavemessage/shoucang";
	}


	@ResponseBody
	@GetMapping("/listShoucang")
	public PageUtils listShoucang(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<UserDO> list = new ArrayList<>();
		String shoucangids = (String) params.get("shoucangids");
		if(StringUtils.isBlank(shoucangids) && !shoucangids.contains("::")){
			return  new PageUtils(list, 0);
		}
		String[] ids = shoucangids.split("::");
		Stream.of(ids).forEach(id ->{
			UserDO userDO = userService.get(Long.parseLong(id));
			list.add(userDO);
		});
		PageUtils pageUtils = new PageUtils(list, list.size());
		return pageUtils;
	}
	
}
