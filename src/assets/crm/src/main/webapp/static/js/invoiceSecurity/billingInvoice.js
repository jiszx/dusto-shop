var a;
var iRet;
// var a;
$(function() {
	if (!!window.ActiveXObject || "ActiveXObject" in window
			|| window.ActiveXObject) {
		// a = new ActiveXObject("TaxCardX.GoldTax");
		a = document.getElementById("MyTaxCard");
		//iRet = a.TestMessage();
		//a.InitGoldTax();
		//a.CertPassWord = "123456";
	} else {
		alert("浏览器不支持");
	}
	$("#btn-open").bind("click", OpenCard);
	$("#ben-invoice").bind("click", Invoice);
	$("#btn-close").bind("click", CloseCard);
})
function OpenCard() {
	a.OpenCard();
	if (a.RetCode == "1011" || a.RetCode == "3001") {
		var mess = "金税卡开启成功！";
		$.messager.popup(mess);
	} else {
		$.messager.popup("金税卡开启失败，错误代码：" + a.RetCode);
	}
}
// 开具发票
function Invoice() {
	OpenCard();
	for (var i = 0; i < invoicesData.length; i++) {
		a.InvInfoInit();// 初始化发票整体信息各项属性
		a.ClearInvList();// 清楚明细表全部行
		a.InfoKind = invoiceType;// 发票类型
		a.InfoInvoicer = infoInvoicer;// 开票人
		a.InfoClientName = invoicesData[i].header.billCompany;// 购方名称
		if (invoicesData[i].header.billTaxNo == null) {
			a.InfoClientTaxCode = '';
		} else {
			a.InfoClientTaxCode = invoicesData[i].header.billTaxNo;// - 购方税号
		}
		a.InfoClientBankAccount = invoicesData[i].header.billBankNo;// 购方开户行及账号
		a.InfoClientAddressPhone = invoicesData[i].header.billAddr;// 购方地址电话
		a.InfoSellerBankAccount = invoicesData[i].header.saleBankNo;// 销方开户行及账号
		a.InfoSellerAddressPhone = invoicesData[i].header.saleAddr;// 销方地址电话
		a.InfoTaxRate = invoicesData[i].header.tax;// - 税率，17、13、6、4等
		a.InfoNotes = '';// - 备注
		a.InfoChecker = invoicesData[i].header.checker;// - 复核人，可为空
		a.InfoCashier = '';// - 收款人，可为空
		// a.InfoBillNumber = invoicesData[i].header.orderId;// - 销售单据编号，可为空
		a.InfoListName = '';// 如不为空，则开具销货清单，此为发票上商品名称栏的清单信息，应为"(详见销货清单)"字样
		// 发票明细超过8行，将自动开具销货清单
		for (var j = 0; j < invoicesData[i].items.length; j++) {
			a.ListGoodsName = invoicesData[i].items[j].sku;// 商品名称
			a.ListTaxItem = invoicesData[i].header.taxItem;// 税目，4位数字，商品所属类别
			a.ListStandard = invoicesData[i].items[j].specifications;// 规格信息
			a.ListUnit = invoicesData[i].items[j].unit;// 单位
			a.ListNumber = invoicesData[i].items[j].num;// 数量
			a.ListPrice = invoicesData[i].items[j].price;// 单价
			// a.InfoTaxRate = data[i].INFO_TAX_RATE;
			a.ListPriceKind = 1;// 含税价标识，1含税价，0不含税价
			a.ListAmount = 0;// 金额，可以为空（0）由开票软件计算，如果传入则需要满足计算关系
			a.ListTaxAmount = 0;// 税额，0：接口软件计算，如果传入需满足计算关系
			a.AddInvList();
		}
		a.Invoice();// 开具发票
		if (a.RetCode == "4011") {
			var mess = "开票成功！\n\n";
			mess += "发票整体信息如下：\n";
			mess += "合计不含税金额:" + a.InfoAmount;
			mess += "\n合计税额:" + a.InfoTaxAmount;
			mess += "\n开票日期:" + a.InfoDate;
			mess += "\n所属月份:" + a.InfoMonth;
			mess += "\n发票十位代码:" + a.InfoTypeCode;
			mess += "\n发票号码:" + a.InfoNumber;
			$.messager.popup(mess);
			a.InfoShowPrtDlg = 0;

			a.GoodsListFlag = 0;
			$.post("invoiceSecurity/callBackInvoiceNo", {
				"invoiceId" : invoicesData[i].header.invoiceNo,
				"orderId" : invoicesData[i].header.orderId,
				"infoNumber" : a.InfoNumber,
				"infoTypeCode" : a.InfoTypeCode,
				"infoMonth" : a.InfoMonth,
				"infoDate" : a.InfoDate,
				"infoAmount" : a.InfoAmount,
				"infoTaxAmount" : a.InfoTaxAmount,
				"sourcesType" : sourcesType,// 来源：1订单，2应收发票
				"isIsmerge" : isIsmerge,// 1普通开票，2合并开票
				"invoicesType" : invoicesType,// 发票类型
				"ids" : ids
			}, function(res) {
				if (errorCode == '0') {
					$.messager.popup("回写金穗发票编号成功！");
					// 发票打印
					a.PrintInv();
					if (a.RetCode == "5011") {
						$.messager.popup("发票打印成功");
					}
				} else {
					$.messager.popup("开票失败，错误代码：" + a.RetCode);
				}
			});
			a.CloseCard();// 关闭金税卡
		}
	}
	CloseCard();
};
function CloseCard() {
	a.CloseCard();
}

/*
 * function PrintInv() { a.InfoKind = invoiceType;//发票类型 a.GoodsListFlag =0;
 * a.PrintInv(); if (a.RetCode == "5011") $.messager.popup("发票打印成功！"); else
 * $.messager.popup("发票打印失败，错误代码：" + a.RetCode); }
 */