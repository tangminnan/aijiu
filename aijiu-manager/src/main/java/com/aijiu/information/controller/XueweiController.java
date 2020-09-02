package com.aijiu.information.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aijiu.common.config.BootdoConfig;
import com.aijiu.common.utils.FileUtil;
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

import com.aijiu.information.domain.XueweiDO;
import com.aijiu.information.service.XueweiService;

/**
 * 穴位详情表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-02 11:16:43
 */
 
@Controller
@RequestMapping("/information/xuewei")
public class XueweiController {
	@Autowired
	private XueweiService xueweiService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("information:xuewei:xuewei")
	String Xuewei(){
	    return "information/xuewei/xuewei";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:xuewei:xuewei")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XueweiDO> xueweiList = xueweiService.list(query);
		int total = xueweiService.count(query);
		PageUtils pageUtils = new PageUtils(xueweiList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:xuewei:add")
	String add(){
	    return "information/xuewei/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:xuewei:edit")
	String edit(@PathVariable("id") Long id,Model model){
		XueweiDO xuewei = xueweiService.get(id);
		model.addAttribute("xuewei", xuewei);
	    return "information/xuewei/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:xuewei:add")
	public R save( XueweiDO xuewei){
		try {
			if(xuewei.getImgFile()!=null && xuewei.getImgFile().getSize()>0) {
				String fileName = xuewei.getImgFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(xuewei.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				xuewei.setXueweiUrl("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}
		xuewei.setDeleteFlag(0);
		xuewei.setAddTime(new Date());
		if(xueweiService.save(xuewei)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:xuewei:edit")
	public R update( XueweiDO xuewei){
		try {
			if(xuewei.getImgFile()!=null && xuewei.getImgFile().getSize()>0) {
				String fileName = xuewei.getImgFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(xuewei.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				xuewei.setXueweiUrl("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}
		xueweiService.update(xuewei);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:xuewei:remove")
	public R remove( Long id){
		XueweiDO xueweiDO = new XueweiDO();
		xueweiDO.setId(id);
		xueweiDO.setDeleteFlag(1);
		if(xueweiService.update(xueweiDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:xuewei:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		xueweiService.batchRemove(ids);
		return R.ok();
	}
	
}
