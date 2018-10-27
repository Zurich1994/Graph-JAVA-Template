package com.hotent.platform.controller.extension;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.deployCode.model.deployCodePath.Deploy;
import com.hotent.deployCode.service.deployCodePath.DeployService;
import com.hotent.deviceCode.model.deviceCodePath.Device;
import com.hotent.deviceCode.service.deviceCodePath.DeviceService;
import com.hotent.platform.model.bpm.TaskAmount;
import com.hotent.platform.model.extension.PlatformDeployType;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysTypeKey;
import com.hotent.platformCode.model.platformCodePath.Platform;
import com.hotent.platformCode.service.platformCodePath.PlatformService;


@Controller
@RequestMapping("/platform/extension/platformDeploy/")
public class PlatformDeployController extends BaseController{
//	@Resource
//	private SbService sbService;
//	@Resource
//	private BsService bsService;
	@Resource
	private DeployService deployService;
	@Resource
	private PlatformService platformService;

	/**
	 * 自动测试设备平台tree
	 */
	@RequestMapping("showTree")
	@ResponseBody
	public Set<PlatformDeployType> showTree(HttpServletRequest request) {
	Set<PlatformDeployType> PlatformDeployTypeSet = new LinkedHashSet<PlatformDeployType>();
	List<Platform> PlatformList = platformService.getAll();		
	List<HashMap> DeployList = deployService.getSbPt();	
	
	for(Platform pf : PlatformList){	
		PlatformDeployType pdt = new PlatformDeployType();
		pdt.setTypeId(String.valueOf(pf.getId()));
		pdt.setTypeName(pf.getPlatformname());
		pdt.setParentId("0"); 
		pdt.setIsLeaf(false);
		PlatformDeployTypeSet.add(pdt);
	}
	for(int i=0;i<DeployList.size();i++){
		PlatformDeployType pdt = new PlatformDeployType();
		HashMap hm = DeployList.get(i);
		pdt.setTypeId(String.valueOf(hm.get("ID"))); 
		pdt.setTypeName(String.valueOf(hm.get("F_DEVICENAME")));	
		pdt.setParentId(String.valueOf(hm.get("F_platformID")));
		pdt.setIsLeaf(true);
		PlatformDeployTypeSet.add(pdt);

	}
	return PlatformDeployTypeSet;
}
	
	
//	public Set<PlatformDeployType> showTree(HttpServletRequest request) {
//		Set<PlatformDeployType> PlatformDeployTypeSet = new LinkedHashSet<PlatformDeployType>();
//		List<Sb> SbList = sbService.getAll();		
//		List<HashMap> BsList = bsService.getSbPt();	
//		for(Sb sb : SbList){	
//			PlatformDeployType pdt = new PlatformDeployType();
//			pdt.setTypeId(String.valueOf(sb.getId()));
//			pdt.setTypeName(sb.getSbname());
//			pdt.setParentId("0"); 
//			pdt.setIsLeaf(false);
//			PlatformDeployTypeSet.add(pdt);
//		}
//		for(int i=0;i<BsList.size();i++){
//			PlatformDeployType pdt = new PlatformDeployType();
//			HashMap hm = BsList.get(i);
//			pdt.setTypeId(String.valueOf(hm.get("ID")));
//			pdt.setTypeName(String.valueOf(hm.get("F_ptfwname")));
//			pdt.setParentId(String.valueOf(hm.get("F_sbID")));
//			pdt.setIsLeaf(true);
//			PlatformDeployTypeSet.add(pdt);
//
//		}
//		return PlatformDeployTypeSet;
//	}
	
}
