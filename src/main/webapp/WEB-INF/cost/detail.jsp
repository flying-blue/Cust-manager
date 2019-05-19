<%@page pageEncoding="utf-8"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>客户管理</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />       
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <c:import url="../logo.jsp"></c:import>      
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <c:import url="../menu.jsp"></c:import>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${cost.costId }"  name="id"/></div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${cost.name }" name="name" /></div>
                <div class="text_info clearfix"><span>资费状态：</span></div>
                <div class="input_info">
                    <select class="readonly" disabled  name="status">
                        <option ${cost.status==0?'selected':'' } >开通</option>
                        <option ${cost.status==1?'selected':'' } >暂停</option>
                    </select>                        
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="radFeeType" ${cost.costType==1?'checked':'' } id="monthly" disabled="disabled" value="1"/>
                    <label for="monthly">包月</label>
                    <input type="radio" name="radFeeType" ${cost.costType==2?'checked':'' } id="package" disabled="disabled" value="2"/>
                    <label for="package">套餐</label>
                    <input type="radio" name="radFeeType" ${cost.costType==3?'checked':'' } id="timeBased" disabled="disabled" value="3"/>
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" class="readonly" readonly value="${cost.baseDuration }"  name="baseDuration"/>
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text"  class="readonly" readonly value="${cost.baseCost }" name="baseCost" />
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text"  class="readonly" readonly value="${cost.unitCost }" name="unitCost" />
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="${cost.creatime }"  name="creatime" /></div>      
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="${cost.startime }" name="startime"/></div>      
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70 readonly" name="descr" readonly >${cost.descr }</textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="history.back()" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[优秀的技师，舒适的环境，给您更好的体验]</p>
            <p>版权所有(C)美康堂养生服务有限公司 </p>
        </div>
    </body>
</html>
