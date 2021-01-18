package com.aijiu.information.controller;

import com.aijiu.common.utils.PageUtils;
import com.aijiu.common.utils.Query;
import com.aijiu.common.utils.R;
import com.aijiu.information.domain.AttentionDO;
import com.aijiu.information.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-09 10:21:12
 */
 
@Controller
@RequestMapping("/information/attention")
public class AttentionController {
	@Autowired
	private AttentionService attentionService;
	
	@GetMapping()
	String Attention(Model model){
	    return "information/attention/attention";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AttentionDO> attentionList = attentionService.list(query);
		int total = attentionService.count(query);
		PageUtils pageUtils = new PageUtils(attentionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "information/attention/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		AttentionDO attention = attentionService.get(id);
		model.addAttribute("attention", attention);
	    return "information/attention/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save(AttentionDO attention){
		if(attentionService.save(attention)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(AttentionDO attention){
		attentionService.update(attention);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove(Long id){
		if(attentionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids){
		attentionService.batchRemove(ids);
		return R.ok();
	}
	
}
