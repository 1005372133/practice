package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-12-22 10:23:39
 */
@Data
@TableName("task")
public class TaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String createuser;
	/**
	 * 
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date begintime;
	/**
	 * 
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date endtime;
	/**
	 * 
	 */
	private Date createtime;
	/**
	 * 
	 */
	private String getuser;
	/**
	 * 
	 */
	private String flag;
	/**
	 *
	 */
	private String contest;


	/**
	 * 用户ID列表
	 */
	@TableField(exist=false)
	private List<Integer> userList;


}
