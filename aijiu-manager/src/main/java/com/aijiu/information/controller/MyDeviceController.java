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

import com.aijiu.information.domain.MyDeviceDO;
import com.aijiu.information.service.MyDeviceService;


/**
 * 我的设备表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 16:47:11
 */
 
@Controller
@RequestMapping("/information/myDevice")
public class MyDeviceController {
	@Autowired
	private MyDeviceService myDeviceService;
	
	@GetMapping()
	@RequiresPermissions("information:myDevice:myDevice")
	String MyDevice(){
	    return "information/myDevice/myDevice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:myDevice:myDevice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MyDeviceDO> myDeviceList = myDeviceService.list(query);
		int total = myDeviceService.count(query);
		PageUtils pageUtils = new PageUtils(myDeviceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:myDevice:add")
	String add(){
	    return "information/myDevice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:myDevice:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MyDeviceDO myDevice = myDeviceService.get(id);
		model.addAttribute("myDevice", myDevice);
	    return "information/myDevice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:myDevice:add")
	public R save( MyDeviceDO myDevice){
		if(myDeviceService.save(myDevice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:myDevice:edit")
	public R update( MyDeviceDO myDevice){
		myDeviceService.update(myDevice);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:myDevice:remove")
	public R remove( Long id){
		if(myDeviceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:myDevice:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		myDeviceService.batchRemove(ids);
		return R.ok();
	}
	
}
