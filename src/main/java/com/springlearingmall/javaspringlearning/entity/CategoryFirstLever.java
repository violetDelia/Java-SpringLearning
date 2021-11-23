package com.springlearingmall.javaspringlearning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author LFR
 * @since 2021-10-26
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class CategoryFirstLever implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private Integer lever;

    private Integer isDiscard;

    private String bannerImage;

    private String rightImage;


}
