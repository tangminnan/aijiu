package com.aijiu.information.controller;

import com.aijiu.common.utils.PageUtils;
import com.aijiu.common.utils.Query;
import com.aijiu.common.utils.R;
import com.aijiu.information.domain.MyHelpDO;
import com.aijiu.information.service.MyHelpService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 帮助与反馈
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-30 09:49:12
 */
 
@Controller
@RequestMapping("/information/myHelp")
public class MyHelpController {
	@Autowired
	private MyHelpService myHelpService;
	
	@GetMapping()
	@RequiresPermissions("information:myHelp:myHelp")
	String MyHelp(){
	    return "information/myHelp/myHelp";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:myHelp:myHelp")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MyHelpDO> myHelpList = myHelpService.list(query);
		int total = myHelpService.count(query);
		PageUtils pageUtils = new PageUtils(myHelpList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:myHelp:add")
	String add(){
	    return "information/myHelp/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		MyHelpDO myHelp = myHelpService.get(id);
		model.addAttribute("myHelp", myHelp);
	    return "information/myHelp/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:myHelp:add")
	public R save(MyHelpDO myHelp){
		if(myHelpService.save(myHelp)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(MyHelpDO myHelp){
		myHelpService.update(myHelp);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:myHelp:remove")
	public R remove(Long id){
		if(myHelpService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:myHelp:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		myHelpService.batchRemove(ids);
		return R.ok();
	}
	
}
