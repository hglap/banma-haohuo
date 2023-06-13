package com.ebanma.cloud.game.model.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "game_rule")
@Data
public class GameRule implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 蛋类型
     */
    @Column(name = "egg_type")
    private String eggType;

    /**
     * 奖品类型
     */
    @Column(name = "present_code")
    private Integer presentCode;

    /**
     * 奖品数量
     */
    @Column(name = "present_count")
    private Integer presentCount;

    /**
     * 蛋概率
     */
    @Column(name = "egg_odd")
    private Double eggOdd;

    @Column(name = "present_odd")
    private Double presentOdd;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    private Date modifiedTime;

}