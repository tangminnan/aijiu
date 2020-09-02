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

import com.aijiu.information.domain.ScoresDO;
import com.aijiu.information.service.ScoresService;
/**
 * 积分表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-01 10:34:25
 */
 
@Controller
@RequestMapping("/information/scores")
public class ScoresController {
	@Autowired
	private ScoresService scoresService;
	
	@GetMapping()
	@RequiresPermissions("information:scores:scores")
	String Scores(){
	    return "information/scores/scores";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:scores:scores")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoresDO> scoresList = scoresService.list(query);
		int total = scoresService.count(query);
		PageUtils pageUtils = new PageUtils(scoresList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:scores:add")
	String add(){
	    return "information/scores/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:scores:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoresDO scores = scoresService.get(id);
		model.addAttribute("scores", scores);
	    return "information/scores/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:scores:add")
	public R save(ScoresDO scores){
		if(scoresService.save(scores)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:scores:edit")
	public R update( ScoresDO scores){
		scoresService.update(scores);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:scores:remove")
	public R remove( Long id){
		if(scoresService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:scores:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoresService.batchRemove(ids);
		return R.ok();
	}
	
}
