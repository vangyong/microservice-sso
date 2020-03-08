package cn.segema.cloud.common.vo;

import java.io.Serializable;

import cn.segema.cloud.common.constants.CommonConstant;

public class ResultVO implements Serializable{
	
	private static final long serialVersionUID = -5817878425419706227L;
	//1:成功，0:失败
	private int status;
	/**
	 * @fields record 消息对象
	 */
	private Object message;
	/**
	 * @fields record 数据对象
	 */
	private Object data;

	public ResultVO() {
		super();
	}
	/**
	 * @param status 状态
	 * @param message 消息
	 */
	public ResultVO(int status, Object message) {
		this.status = status;
		this.message = message;
	}
	/**
	 * @param status 状态
	 * @param message 消息
	 * @param data 数据
	 */
	public ResultVO(int status, Object message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}



	/**
	 * @description: 添加成功结果信息
	 * @return: void
	 */
	public void addSuccess(Object message) {
		this.message = message;
		this.status =CommonConstant.MAGIC_ONE;
	}
	/**
	 * @description: 添加错误消息
	 * @return: void
	 */
	public void addFailed(Object message) {
		this.message = message;
		this.status = CommonConstant.MAGIC_ZERO;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
