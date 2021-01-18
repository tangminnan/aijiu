package com.aijiu.information.controller;

import com.aijiu.common.utils.PageUtils;
import com.aijiu.common.utils.Query;
import com.aijiu.common.utils.R;
import com.aijiu.information.domain.MyBingzhengDO;
import com.aijiu.information.service.MyBingzhengService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 病症记录表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 17:07:20
 */
 
@Controller
@RequestMapping("/information/myBingzheng")
public class MyBingzhengController {
	@Autowired
	private MyBingzhengService myBingzhengService;
	
	@GetMapping()
	@RequiresPermissions("information:myBingzheng:myBingzheng")
	String MyBingzheng(){
	    return "information/myBingzheng/myBingzheng";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:myBingzheng:myBingzheng")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MyBingzhengDO> myBingzhengList = myBingzhengService.list(query);
		int total = myBingzhengService.count(query);
		PageUtils pageUtils = new PageUtils(myBingzhengList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:myBingzheng:add")
	String add(){
	    return "information/myBingzheng/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:myBingzheng:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MyBingzhengDO myBingzheng = myBingzhengService.get(id);
		model.addAttribute("myBingzheng", myBingzheng);
	    return "information/myBingzheng/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:myBingzheng:add")
	public R save(MyBingzhengDO myBingzheng){
		if(myBingzhengService.save(myBingzheng)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:myBingzheng:edit")
	public R update(MyBingzhengDO myBingzheng){
		myBingzhengService.update(myBingzheng);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:myBingzheng:remove")
	public R remove(Long id){
		if(myBingzhengService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:myBingzheng:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		myBingzhengService.batchRemove(ids);
		return R.ok();
	}
	
}
