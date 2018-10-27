var globalType=new PlatformDeployType("","glTypeTree",{onClick:onClick,url:glTypeTreeUrl});
$(function (){
	
	//加载菜单 
    globalType.loadGlobalTree();
	//布局
    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
});
//左击
function onClick(treeNode){
	
/*	if(treeNode.isleaf==false){
			leftClickUrl = __ctx+'/deviceCode/deviceCodePath/device/list.ht';
			alert("elseurl="+leftClickUrl);	
	}
	else{
			leftClickUrl = __ctx+'/platform/extension/platformDeploy/showlist.ht?typeId='+treeNode.typeId;
			alert("elseurl="+leftClickUrl);	
			}
	*/
	var url = leftClickUrl;
	
	if($.isEmpty(treeNode.parentId) || treeNode.parentId == 0 )
		{leftClickUrl = __ctx+'/deployCode/deployCodePath/deploy/showlist.ht?typeId='+treeNode.typeId;
		//alert("haha"+leftClickUrl);
		url = leftClickUrl;
		}
	else
		{
		leftClickUrl = __ctx+'/testdefineCode/testdefineCodePath/testdefine/chooselist.ht?typeId='+treeNode.typeId+'&parentId='+treeNode.parentId;
		if(treeNode.parentId == 10000001200013 || treeNode.parentId == 10000001200009)
			leftClickUrl = __ctx+'/deployCode/deployCodePath/deploy/platformdevicelist1.ht?typeId='+treeNode.typeId+'&parentId='+treeNode.parentId;
		
	
			url= leftClickUrl;
			
		}
	//alert("url="+url);	
	//alert("typeId="+treeNode.typeId);
	//alert("isLeaf="+treeNode.isLeaf);
	$("#defFrame").attr("src",url);
	
	
	

};


//展开收起
function treeExpandAll(type){
	globalType.treeExpandAll(type);
};
