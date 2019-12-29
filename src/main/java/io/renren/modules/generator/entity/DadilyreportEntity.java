package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-12-22 10:23:39
 */
@Data
@TableName("dadilyreport")
public class DadilyreportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Date time;
	/**
	 * 
	 */
	private String user;
	/**
	 * 
	 */
	private String score;
	/**
	 * 
	 */
	private String  opinion;

	/**
	 *
	 */
	private String zhidaoscore;
	/**
	 *
	 */
	private String  zhidaoopinion;

}
