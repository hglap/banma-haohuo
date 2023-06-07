package com.ebanma.cloud.game.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "game_user_prop")
@Data
public class GameUserProp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 道具类型
     */
    @Column(name = "prop_type")
    private String propType;

    /**
     * 道具剩余数量
     */
    @Column(name = "prop_remain_count")
    private Integer propRemainCount;

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