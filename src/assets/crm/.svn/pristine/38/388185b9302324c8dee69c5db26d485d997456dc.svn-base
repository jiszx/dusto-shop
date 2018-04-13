<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  
  <head>
    <title>送货通知</title>
    <meta name="Author" content="CRM">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <style type="text/css">
    	body { 
    		margin: 24px 5px; 
    	} 
    	.head { 
    		padding: 8px 6px; 
    		color: #3D9970; 
    		font-size: 12px; 
    		font-weight: 300; 
    		font-family: "Microsoft YaHei"; 
    		-webkit-font-smoothing: antialiased; 
    	} 
    	table { 
    		width: 100%; 
    		border: 0; 
    		border-collapse:collapse; 
    		border-spacing:0; 
    	} 
    	th,td { 
    		font-weight: normal;
    	}
      table th{
      	text-align: center;
          white-space: nowrap;
      }
      .dashedTabel td, .dashedTabel th{
        padding: 2px;
      	border-right: 1px dashed;
        border-bottom: 1px dashed;
      }
    </style>
  </head>
  
  <body>
    <table style="background-color: #f7f9fa" width="100%">
      <tbody>
        <tr>
          <td align="center" style="background-color: #F7F9FA" width="100%">
            <table style="background-color: #f7f9fa; width: 640px">
              <tbody>
                <tr>
                  <td>
                    <table style="width: 100%">
                      <tbody>
                        <tr>
                          <td height="40" style="font-size: 0px; line-height: 0px">&nbsp;</td></tr>
                        <tr>
                          <td align="right" style="font-size: 12px; color: #b6c2cc">这是系统邮件，请勿回复</td>
                          <td width="35">&nbsp;</td></tr>
                        <tr>
                          <td height="20" style="font-size: 0px; line-height: 0px">&nbsp;</td></tr>
                      </tbody>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>
                    <table style="width: 100%; border-radius: 4px; border: 1px solid #dedede; margin: 0 auto; background-color: #ffffff">
                      <tbody>
                        <tr>
                          <td style="padding: 45px 35px 45px 35px" align="left">
                            <table>
                              <tbody>
                                <tr>
                                  <td>
                                    <table width="100%">
                                      <tbody>
                                        <tr>
                                          <td valign="middle" style="vertical-align: middle; width: 78px;">
                                            <div class="head">益盐堂CRM</div></td>
                                          <td align="left" style="font-size: 24px; color: #202121">&nbsp;&nbsp;${subject}</td></tr>
                                      </tbody>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td style="height: 12px; font-size: 0px; line-height: 0px; border-top: 3px solid #3D9970;">&nbsp;</td></tr>
                                <tr>
                                  <td>
                                    <table>
                                      <tbody>
                                        <tr>
                                          <td>
                                            <table>
                                              <tbody>
                                                <tr>
                                                  <td>
                                                    <table width="100%" class="mail-mobile-no-fullwidth">
                                                      <tbody>
                                                        <tr>
                                                          <td height="15" style="font-size: 0px; line-height: 0px">&nbsp;</td></tr>
                                                        <tr>
                                                          <td width="22">
                                                            <img src="cid:user-24.png">
                                                          <td width="8"></td>
                                                          <td align="left">
                                                            <span style="font-size: 13px; color: #666">${userName}</span></td>
                                                        </tr>
                                                        <tr>
                                                          <td height="12" style="font-size: 0px; line-height: 0px">&nbsp;</td></tr>
                                                      </tbody>
                                                    </table>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td>
                                                    <table>
                                                      <tbody>
                                                        <tr>
                                                          <td style="word-break: break-all">
                                                            <div style="font-size: 13px; line-height: 22px; text-decoration: none; color: #333; display: block">
                                                            您好！<br/>CRM订单 ${content} 需要送货，请及时处理！如需打印配送单，请点击<a href="${printUrl}">此处</a>。
                                                            </div>
                                                          </td>
                                                        </tr>
                                                      </tbody>
                                                    </table>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td height="28" style="font-size: 0px; line-height: 0px">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                  <td>
                                                    <table>
                                                        <tr>
                                                          <td style="word-break: break-all;border-top: 1px dashed;">
                                                            <div style="text-align: center; position: relative; top: -12px; ">
                                                              <div style="background: #fff; margin:0 auto; width: 85px; text-align: center; ">订单信息</div>
                                                            </div>
															                              <div style="line-height: 22px; text-decoration: none; display: block">
                                                              <table class="order-head" style="border: 0; text-align: left;width: 100%;margin-top: 6px;padding: 2px;">
                                                                     <tr>
                                                                         <td>订单日期：${(order.order.createTs)?string("yyyy-MM-dd")}</td>
                                                                         <td>最晚送货日期：2013-02-13</td>
                                                                         <td style="width: 1px;white-space: nowrap;">订单编码：${order.order.id?c }</td>
                                                                     </tr>
                                                                     <tr>
                                                                         <td>收货方名称：${order.customer.name!'' }</td>
                                                                         <td>客户编码：${order.order.merchCustId?c }</td>
                                                                         <td>收货方联系人：${order.customer.contactName!'' }</td>
                                                                     </tr>
                                                                     <tr>
                                                                         <td colspan="2">收货地址：${order.customer.address!'' }</td>
                                                                         <td>联系电话：${order.customer.tel!'' }</td>
                                                                     </tr>
                                                              </table>
                                                            </div>
                                                          </td>
                                                        </tr>
                                                    </table>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td>
                                                    <table>
                                                      <tbody>
                                                        <tr>
                                                          <td style="word-break: break-all">
                                                            <div>
                                                                    <table class="dashedTabel" style="border: 1px solid; text-align: left;width: 100%;margin-top: 10px;font-weight: bold;border-collapse: collapse; text-align: center;">
                                                                           <tr>
                                                                               <th colspan="5">产品基本信息</th>
                                                                               <th colspan="5">送货信息</th>
                                                                           </tr>
                                                                           <tr style="border-bottom: 1px solid;">
                                                                               <th>品牌</th>
                                                                               <th>产品编码</th>
                                                                               <th>产品名称</th>
                                                                               <th>箱规</th>
                                                                               <th>单位</th>
                                                                               <th>送货箱数</th>
                                                                               <th>送货包数</th>
                                                                               <th>箱重(吨)</th>
                                                                               <th>总重量(吨)</th>
                                                                               <th>备注</th>
                                                                           </tr>
                                                                            <#assign totalBox=0>
                                                                            <#assign totalBag=0>
                                                                            <#assign totalBoxWeight=0>
                                                                            <#assign totalFullWeight=0>

                                                                            <#list order.orderItems as item>
                                                                              <#assign totalBox = ((item.orderSplits.num!0)/(item.materialBase.attribute6?number)) + totalBox />
                                                                              <#assign totalBag = item.orderSplits.num + totalBag />
                                                                              <#assign totalBoxWeight = item.boxWeight + totalBoxWeight />
                                                                              <#assign totalFullWeight = item.totalWeight + totalFullWeight />
                                                                              <tr>
                                                                                  <td style="white-space: nowrap;">${item.materialBase.brand!'' }</td>
                                                                                  <td style="white-space: nowrap;">${item.materialBase.sapId!'' }</td>
                                                                                  <td>${item.materialBase.materialName!'' }</td>
                                                                                  <td style="white-space: nowrap;">${item.materialBase.attribute6!'' }</td>
                                                                                  <td style="white-space: nowrap;">${item.materialBase.unit!'' }</td>
                                                                                  <td style="white-space: nowrap;">${((item.orderSplits.num!0)/(item.materialBase.attribute6?number)) }</td>
                                                                                  <td style="white-space: nowrap;">${item.orderSplits.num!0}</td>
                                                                                  <td style="white-space: nowrap;">${item.boxWeight!0}</td>
                                                                                  <td style="white-space: nowrap;">${item.totalWeight!0}</td>
                                                                                  <td></td>
                                                                              </tr>
                                                                            </#list>
                                                                                <tr>
                                                                                    <td>合计</td>
                                                                                    <td></td>
                                                                                    <td></td>
                                                                                    <td></td>
                                                                                    <td></td>
                                                                                    <td>${totalBox }</td>
                                                                                    <td>${totalBag }</td>
                                                                                    <td>${totalBoxWeight }</td>
                                                                                    <td>${totalFullWeight }</td>
                                                                                    <td></td>
                                                                                </tr>
                                                                    </table>
                                                              </div>
                                                          </td>
                                                        </tr>
                                                      </tbody>
                                                    </table>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td height="28" style="font-size: 0px; line-height: 0px">&nbsp;</td></tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                        <tr>
                                          <td height=1 style="font-size: 0px; line-height: 0px; border-top: 1px #f1f4f6 solid">&nbsp;</td></tr>
                                        <tr>
                                          <td>
                                            <table align="left" style="border: 1px solid #FFFFFF; border-collapse: collapse">
                                              <tbody>
                                                <tr>
                                                  <td bgcolor="#FFFFFF">
                                                    <table align="right">
                                                      <tbody>
                                                        <tr>
                                                          <td style="font-size: 12px; font-weight: bold; float: right; color: #3C8DBC; text-align: right; padding: 12px 24px 2px 0px">${date?string("yyyy-MM-dd")}</td></tr>
                                                      </tbody>
                                                    </table>
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>
                    <table>
                      <tbody>
                        <tr>
                          <td style="padding: 20px 25px 45px 25px; font-size: 12px; color: #b6c2cc; line-height: 17px; text-align: center;" align="center">这封邮件的收件地址是
                            <a href="mailto:${mailAddress}" target="_blank">${mailAddress}</a>；
                            <br>© 2017 益盐堂CRM</td></tr>
                      </tbody>
                    </table>
                  </td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
  </body>
</html>