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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aijiu.information.domain.UserDO;
import com.aijiu.information.service.UserDOService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-17 14:43:46
 */
 
@Controller
@RequestMapping("/information/user")
public class UserDOController {
	@Autowired
	private UserDOService userService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("information:user:user")
	String User(){
	    return "information/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:user:add")
	String add(){
	    return "information/user/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:user:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UserDO user = userService.get(id);
		model.addAttribute("user", user);
	    return "information/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:user:add")
	public R save( UserDO user){
		try {
			if(user.getImgFile()!=null && user.getImgFile().getSize()>0) {
				String fileName = user.getImgFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(user.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				user.setHeardUrl("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}
		user.setDeleteFlag(0);
		user.setRegisterTime(new Date());
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:user:edit")
	public R update( UserDO user){
		try {
			if(user.getImgFile()!=null && user.getImgFile().getSize()>0) {
				String fileName = user.getImgFile().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				FileUtil.uploadFile(user.getImgFile().getBytes(), bootdoConfig.getUploadPath(), fileName);
				user.setHeardUrl("/files/" + fileName);
			}
		} catch (Exception e) {
			return R.error();
		}
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:user:remove")
	public R remove( Long id){
		UserDO userDO = new UserDO();
		userDO.setId(id);
		userDO.setDeleteFlag(1);
		if(userService.update(userDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:user:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		userService.batchRemove(ids);
		return R.ok();
	}
	
}
