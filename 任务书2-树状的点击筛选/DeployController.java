
package com.hotent.deployCode.controller.deployCodePath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fr.third.org.hsqldb.Session;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.deployCode.model.deployCodePath.Deploy;
import com.hotent.deployCode.service.deployCodePath.DeployService;
import com.hotent.deviceCode.model.deviceCodePath.Device;
import com.hotent.olddeploy.controller.olddeploy.OlddeployController;
import com.hotent.olddeploy.model.olddeploy.Olddeploy;
import com.hotent.olddeploy.service.olddeploy.OlddeployService;
import com.hotent.platform.model.extension.PlatformDeployType;
import com.hotent.platformCode.model.platformCodePath.Platform;
import com.hotent.sbb.model.sbb.WSbb;
import com.hotent.core.web.ResultMessage;
import com.hotent.csxdyb.model.csxdyb.WCsxdyb;
import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 * 对象功能:部署表 控制器类
 */
@Controller
@RequestMapping("/deployCode/deployCodePath/deploy/")
public class DeployController extends BaseController
{
	@Resource
	private DeployService deployService;
	@Resource
	private OlddeployService olddeployService;
    Olddeploy olddeploy = new Olddeploy();
    OlddeployController olddc = new OlddeployController();
	/**
	 * 添加或更新部署表。
	 * @param request
	 * @param response
	 * @param deploy 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新部署表")
	public void save(HttpServletRequest request, HttpServletResponse response,Deploy deploy) throws Exception
	{
		String resultMsg=null;		
		try{
			if(deploy.getId()==null){
				Long id=UniqueIdUtil.genId();
				deploy.setId(id);
				deployService.add(deploy);
				resultMsg=getText("添加","部署表");
			}else{
			    deployService.update(deploy);
				resultMsg=getText("更新","部署表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得部署表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看部署表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Deploy> list=deployService.getAll(new QueryFilter(request,"deployItem"));
		ModelAndView mv=this.getAutoView().addObject("deployList",list);
		
		return mv;
	}
	
	@RequestMapping("showlist")
	@Action(description="查看特定部署表分页列表")
	public ModelAndView showlist(HttpServletRequest request) throws Exception{
//		Set<PlatformDeployType> PlatformDeployTypeSet = new LinkedHashSet<PlatformDeployType>();
//		Platform platform = new Platform();		
//		long  tid = Long.valueOf(request.getParameter("typeId"));
//		platform.setId(tid);
		List<HashMap> DeployList = deployService.getSbPt();	
		List<PlatformDeployType> platformDeployTypes = new ArrayList<PlatformDeployType>();
		for(int i=0;i<DeployList.size();i++){
			PlatformDeployType pdt = new PlatformDeployType();
			HashMap hm = DeployList.get(i);
			if(!hm.get("F_platformID").equals(request.getParameter("typeId")))
				continue;	
			pdt.setId(Long.valueOf(String.valueOf(hm.get("ID"))));
			pdt.setTypeId(String.valueOf(hm.get("F_platformID"))); 
			pdt.setTypeName(String.valueOf(hm.get("F_DEVICENAME")));	
			//pdt.setParentId(String.valueOf(hm.get("F_platformID")));
			//pdt.setIsLeaf(true);
			pdt.setDeviceID(String.valueOf(hm.get("F_deviceID")));
			pdt.setURL(String.valueOf(hm.get("F_URL")));
			pdt.setServiceno(String.valueOf(hm.get("F_serviceno")));
			pdt.setDeviceIP(String.valueOf(hm.get("F_DEVICEIP")));
			platformDeployTypes.add(pdt);
			
//			Session  session = HttpSession
//			request.getSession("F_platformID");
		}
		request.setAttribute("pid", request.getParameter("typeId"));
		ModelAndView mv=this.getAutoView().addObject("deployShowList",platformDeployTypes);
		return mv;
	}

	//wyx
	@RequestMapping("platformdevicelist")
	@Action(description="查看特定部署表分页列表")
	public ModelAndView platformdevicelist(HttpServletRequest request) throws Exception{
		List<HashMap> DeployList = deployService.getSbPtfw();	
		List<Deploy> deploy = new ArrayList<Deploy>();
		for(int i=0;i<DeployList.size();i++){
			Deploy dp =new Deploy();
			HashMap hm = DeployList.get(i);
			dp.setId(Long.valueOf(String.valueOf(hm.get("ID"))));
			dp.setPlatformName(String.valueOf(hm.get("F_PLATFORMNAME")));
			dp.setDeviceName(String.valueOf(hm.get("F_DEVICENAME")));
			dp.setDeviceIp(String.valueOf(hm.get("F_DEVICEIP")));
			deploy.add(dp);
		}
		ModelAndView mv=this.getAutoView().addObject("platformdeviceList",deploy);	
		return mv;
	}
	
	//wyx
	@RequestMapping("platformdevicelist1")
	@Action(description="查看特定部署表分页列表")
	public ModelAndView platformdevicelist1(HttpServletRequest request) throws Exception{
		String deployID = request.getParameter("typeId");
		List<HashMap> DeployList = deployService.getSbPtfw1(deployID);
		List<Deploy> deploy = new ArrayList<Deploy>();
		for(int i=0;i<DeployList.size();i++){
			Deploy dp =new Deploy();
			HashMap hm = DeployList.get(i);
			dp.setPlatformID(String.valueOf(hm.get("ID")));			
			dp.setPlatformName(String.valueOf(hm.get("F_PLATFORMNAME")));
			dp.setDeviceName(String.valueOf(hm.get("F_DEVICENAME")));
			dp.setDeviceIp(String.valueOf(hm.get("F_DEVICEIP")));
			deploy.add(dp);
		}
		
		ModelAndView mv=this.getAutoView().addObject("platformdeviceList",deploy)
										  .addObject("deployID",deployID);	
		return mv;
	}


	/**
	 * 删除部署表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除部署表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			deployService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除部署表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑部署表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑部署表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String platformID = RequestUtil.getString(request, "platformID");
		//System.out.println(platformID);
		String returnUrl=RequestUtil.getPrePage(request);
		Deploy deploy=deployService.getById(id);
		if(deploy==null){
			deploy=new Deploy();
			deploy.setPlatformID(platformID);
		}
		
		//request.setAttribute("platformID", platformID);
		return getAutoView().addObject("deploy",deploy)
							.addObject("returnUrl",returnUrl);
	}
	

	/**
	 * 取得部署表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看部署表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Deploy deploy=deployService.getById(id);
		return getAutoView().addObject("deploy", deploy);
	}
	
	/**
	 * 测试部署表
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("test")
	@Action(description="测试")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Deploy deploy=deployService.getById(id);
		return getAutoView().addObject("deploy", deploy);
	}
	
	@RequestMapping("build")
	@Action(description="导入数据")
	public void build(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		String resultMsg=null;		
		Deploy deploy = new Deploy();
		
		try{
			List<Olddeploy> list=olddeployService.getAll();
			for(Olddeploy w: list){
			Long id=UniqueIdUtil.genId();
			deploy.setId(id);
			deploy.setPlatformID(w.getPlatformID());
			deploy.setDeviceID(w.getDeviceID());
			deploy.setURL(w.getURL());
			deploy.setServiceno(w.getServiceno());
			deployService.add(deploy);}
			resultMsg=getText("生成","deploy");
			message=new ResultMessage(ResultMessage.Success, "生成成功!");
		}catch(Exception e){
			message=new ResultMessage(ResultMessage.Fail, "生成失败" + e.getMessage());
		}
		
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
}
