package com.hhnz.jco.service;

import java.util.List;

import com.hhnz.jco.model.TRfcExecute;
import com.hhnz.jco.model.TRfcExecuteHistory;
import com.hhnz.util.AjaxDTO;

/**
 * @author: chaoyang.ren
 * @date:2016年11月10日
 * @time:上午11:10:48
 * @email:chaoyang.ren@foxmail.com
 */
public interface IRFCExecuteService {
	/**
	 * 保存rfc执行信息
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午2:13:30
	 * @param rfcExecute
	 */
	public void save(TRfcExecute rfcExecute);
	
	/**
	 * 添加到执行历史
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午2:13:41
	 * @param rfcExecute
	 */
	public void addToHistory(TRfcExecute rfcExecute);

	/**
	 * 分页查询当前任务
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午3:03:06
	 * @param bean
	 * @param re
	 * @return
	 */
	public AjaxDTO searchCurrentExecutions(AjaxDTO bean, TRfcExecute re);

	/**
	 * 分页查询历史任务
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午3:12:43
	 * @param bean
	 * @param reh
	 * @return
	 */
	public AjaxDTO searchHistoryExecutions(AjaxDTO bean, TRfcExecuteHistory reh);

	/**
	 * 根据序列号查询执行任务
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午4:16:28
	 * @param serialNo
	 * @return
	 */
	public TRfcExecute findBySerialNo(String serialNo);

	/**
	 * 根据条件查询列表
	 * @author: chaoyang.ren 
	 * @date:2016年11月29日  下午2:13:38
	 * @param re
	 * @return
	 */
	public List<TRfcExecute> searchCurrentExecutions(TRfcExecute re);

	/**
	 * @author: chaoyang.ren 
	 * @date:Jan 4, 2017  10:07:36 AM
	 * @param rfcExecute
	 */
	void refreshParam(TRfcExecute rfcExecute);
}
