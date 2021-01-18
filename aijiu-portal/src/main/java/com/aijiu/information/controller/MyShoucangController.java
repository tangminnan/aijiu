package com.aijiu.information.controller;

import com.aijiu.common.utils.PageUtils;
import com.aijiu.common.utils.Query;
import com.aijiu.common.utils.R;
import com.aijiu.information.domain.MyShoucangDO;
import com.aijiu.information.service.MyShoucangService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 收藏表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 15:30:13
 */
 
@Controller
@RequestMapping("/information/myShoucang")
public class MyShoucangController {
	@Autowired
	private MyShoucangService myShoucangService;
	
	@GetMapping()
	@RequiresPermissions("information:myShoucang:myShoucang")
	String MyShoucang(){
	    return "information/myShoucang/myShoucang";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:myShoucang:myShoucang")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MyShoucangDO> myShoucangList = myShoucangService.list(query);
		int total = myShoucangService.count(query);
		PageUtils pageUtils = new PageUtils(myShoucangList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:myShoucang:add")
	String add(){
	    return "information/myShoucang/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:myShoucang:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MyShoucangDO myShoucang = myShoucangService.get(id);
		model.addAttribute("myShoucang", myShoucang);
	    return "information/myShoucang/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:myShoucang:add")
	public R save(MyShoucangDO myShoucang){
		if(myShoucangService.save(myShoucang)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:myShoucang:edit")
	public R update(MyShoucangDO myShoucang){
		myShoucangService.update(myShoucang);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:myShoucang:remove")
	public R remove(Long id){
		if(myShoucangService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:myShoucang:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		myShoucangService.batchRemove(ids);
		return R.ok();
	}
	
}
