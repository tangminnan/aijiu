package com.aijiu.information.controller;

import com.aijiu.common.config.BootdoConfig;
import com.aijiu.common.utils.FileUtil;
import com.aijiu.common.utils.PageUtils;
import com.aijiu.common.utils.Query;
import com.aijiu.common.utils.R;
import com.aijiu.information.domain.DeviceDO;
import com.aijiu.information.service.DeviceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 设备表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-19 10:57:05
 */
 
@Controller
@RequestMapping("/information/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("information:device:device")
	String Device(){
	    return "information/device/device";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:device:device")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeviceDO> deviceList = deviceService.list(query);
		int total = deviceService.count(query);
		PageUtils pageUtils = new PageUtils(deviceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:device:add")
	String add(){
	    return "information/device/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:device:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DeviceDO device = deviceService.get(id);
		model.addAttribute("device", device);
	    return "information/device/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:device:add")
	public R save(DeviceDO device){
		try {
			if(device.getImgFile()!=null && !device.getImgFile().isEmpty()) {
				String fileName = device.getImgFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(device.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				device.setIcon("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}

		try {
			if(device.getDetailFile()!=null && !device.getDetailFile().isEmpty()) {
				String fileName = device.getDetailFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(device.getDetailFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				device.setDetailImg("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}

		device.setAddTime(new Date());
		device.setDeleted(0);
		if(deviceService.save(device)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:device:edit")
	public R update(DeviceDO device){
		try {
			if(device.getImgFile()!=null && !device.getImgFile().isEmpty()) {
				String fileName = device.getImgFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(device.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				device.setIcon("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}

		try {
			if(device.getDetailFile()!=null && !device.getDetailFile().isEmpty()) {
				String fileName = device.getDetailFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(device.getDetailFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				device.setDetailImg("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}
		deviceService.update(device);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:device:remove")
	public R remove(Integer id){
		DeviceDO deviceDO = new DeviceDO();
		deviceDO.setDeleted(1);
		deviceDO.setId(id);
		if(deviceService.update(deviceDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:device:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		deviceService.batchRemove(ids);
		return R.ok();
	}
	
}
