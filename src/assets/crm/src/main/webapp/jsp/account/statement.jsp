<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>对帐单</title>
<style type="text/css">
@page{
     size:  auto;   /* auto is the initial value */
     margin: 0mm;  /* this affects the margin in the printer settings */
     }
body{
	font-family: 微软雅黑;
	padding: 0;
	margin: 0;
}
#wrapper {
    text-align: center;
    background-color: #eeeeee;
    padding: 10px 0;
}
.page_view{
	margin:0 auto;
	width:800px;
	text-align: left;
	border-radius: 3px;
	box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.1);
	padding: 2cm;
	border: 1px solid #c3c3c3;
	border-bottom: 1px solid #e2e2e2;
	background-color: #fff;
}
header{
	font-size: 2em; 
	font-weight: bold;
	text-align: center;
}
p{
	line-height: 2em;
	letter-spacing:0.1em;
}
p.section{
	text-indent: 2em;
}
.highlight{
	color:#000;
	padding:0 0.1em;
}
.underline{
	border: 0; 
	border-bottom: 1px solid #333; 
	padding: 0 5px;
	font-size: 1em;
    font-family: Microsoft Yahei;
}
</style>
</head>
<body>
	<div id="wrapper">
	<!--startprint-->
		<div class="page_view" id="print">
			<header>对帐单</header>
			
			<div>
				<p>
					名称：<span class="underline">${custName }</span>
				</p>
				<p class="section">
					感谢贵公司对我公司的大力支持，为使双方长期合作，做到帐帐相符，我公司现将截止<span class="highlight">${lastDay }</span>的往来款与您进行核对，请贵公司在百忙之中给予协作。核对无误后请回复，谢谢！
				</p>
				<p class="section">
					截止<span class="highlight">${lastDay }</span>贵公司在我司账户里保证金共计￥
					<span contenteditable id="marginVal" class="highlight underline" onbeforeeditfocus ="this.oldData=this.firstChild.data" 
									onblur="this.oldData==this.firstChild.data?void(0):triggerNumberToCN(this);" >${margin }</span>元，
					大写：<span contenteditable id="marginCNVal" class="highlight underline" >${marginCN }</span>   
				</p>
				<p class="section">
					截止3月31日我公司已安排调拨我司客户的产品价值人民币￥<span contenteditable class="highlight underline">${finished }</span>元的产品到贵司库房代为保管；
					现可用保证金额度为人民币￥<span contenteditable class="highlight underline">${marginLeft }</span>元。<%-- 还拟调拨价值人民币￥<span class="highlight">${processing }</span>元的产品到贵公司库房代为保管。 --%>
				</p>
				<p class="section">
					如贵司将${periodStr }月已发往贵司代为保管的产品全部代发完毕，则应得服务费人民币￥<span contenteditable class="highlight underline">0.00</span>元 。截止<span class="highlight">${lastDay }</span>我司未向贵司支付服务费。
				</p>
				<p class="section">
					以上信息如有异议，请于一个月内与我公司进行核对，否则视为相符。
				</p>
				<p>
					单位：应城益盐堂物流有限公司 
					<br>
					联系人：${operator.name }（财务部）
					<br>
					电话：${operator.contactTel }
					<br>
					日期：${current }
				</p>
				<p>
					签名：（盖章）
					<br>
					如不符,请列出明细：
					<br>
					<br>
					<br>
					<br>
				</p>
			</div>
		</div>
	<!--endprint-->
	<div style="width: 100%; text-align: center;margin-top: 12px;">
        <input style="display:none; text-align: center; line-height: 30px; height: 38px; width: 80px;" id="btnPrint" type="button" value="打印" onclick=preview() />  
    </div>
	</div>
	<script type="text/javascript">
	    //局部打印
	    function preview(){
	        bdhtml=window.document.body.innerHTML;//获取当前页的html代码  
	        sprnstr="<!--startprint-->";//设置打印开始区域  
	        eprnstr="<!--endprint-->";//设置打印结束区域  
	        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17); //从开始代码向后取html  
	        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html  
	        window.document.body.innerHTML=prnhtml;
	        var printEle = document.getElementById('print');
	        printEle.style.border = '0';
	        window.print();
	        printEle.style.border = '1px solid #c3c3c3';
	        window.document.body.innerHTML=bdhtml;  
	    }
	    
	    //触发大写金额变更
	    function triggerNumberToCN(num){
	    	var text = num.innerText;
	    	var pureNum = text.replace(/,/g,'');
	    	if(!isNaN(pureNum)){
	    		var textCN = numberToCN(pureNum);
	    		document.getElementById('marginCNVal').innerText=textCN;
	    	}
	    }
	    
	    //金额大写
	    function numberToCN(num) {  
		  var strOutput = "";  
		  var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';  
		  num += "00";  
		  var intPos = num.indexOf('.');  
		  if (intPos >= 0)  
		    num = num.substring(0, intPos) + num.substr(intPos + 1, 2);  
		  strUnit = strUnit.substr(strUnit.length - num.length);  
		  for (var i=0; i < num.length; i++)  
		    strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);  
		    return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");  
		};
		
    </script>
</body>
</html>