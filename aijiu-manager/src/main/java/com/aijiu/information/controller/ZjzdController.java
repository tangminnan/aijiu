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

import com.aijiu.information.domain.ZjzdDO;
import com.aijiu.information.service.ZjzdService;

/**
 * 症状指导
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-02 14:51:01
 */
 
@Controller
@RequestMapping("/information/zjzd")
public class ZjzdController {
	@Autowired
	private ZjzdService zjzdService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("information:zjzd:zjzd")
	String Zjzd(){
	    return "information/zjzd/zjzd";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:zjzd:zjzd")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZjzdDO> zjzdList = zjzdService.list(query);
		int total = zjzdService.count(query);
		PageUtils pageUtils = new PageUtils(zjzdList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:zjzd:add")
	String add(){
	    return "information/zjzd/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:zjzd:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ZjzdDO zjzd = zjzdService.get(id);
		model.addAttribute("zjzd", zjzd);
	    return "information/zjzd/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:zjzd:add")
	public R save( ZjzdDO zjzd){
		try {
			if(zjzd.getImgFile()!=null && zjzd.getImgFile().getSize()>0) {
				String fileName = zjzd.getImgFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(zjzd.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				zjzd.setXueweiUrl("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}

		try {
			if(zjzd.getVideoFile()!=null && zjzd.getVideoFile().getSize()>0) {
				String fileName = zjzd.getVideoFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(zjzd.getVideoFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				zjzd.setVideo("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}

		zjzd.setDeleteFlag(0);
		zjzd.setAddTime(new Date());
		if(zjzdService.save(zjzd)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:zjzd:edit")
	public R update( ZjzdDO zjzd){
		try {
			if(zjzd.getImgFile()!=null && zjzd.getImgFile().getSize()>0) {
				String fileName = zjzd.getImgFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(zjzd.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				zjzd.setXueweiUrl("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}

		try {
			if(zjzd.getVideoFile()!=null && zjzd.getVideoFile().getSize()>0) {
				String fileName = zjzd.getVideoFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(zjzd.getVideoFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				zjzd.setVideo("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}

		zjzdService.update(zjzd);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:zjzd:remove")
	public R remove( Long id){
		ZjzdDO zjzdDO = new ZjzdDO();
		zjzdDO.setId(id);
		zjzdDO.setDeleteFlag(1);
		if(zjzdService.update(zjzdDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:zjzd:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		zjzdService.batchRemove(ids);
		return R.ok();
	}
	
}
