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
 * @date 2020-01-06 22:11:32
 */
@Data
@TableName("score")
public class ScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer userid;
	/**
	 * 
	 */
	private String qzscoure;
	/**
	 * 
	 */
	private String qzcomment;
	/**
	 * 
	 */
	private String qmcsoure;
	/**
	 * 
	 */
	private String qmcomment;

}
