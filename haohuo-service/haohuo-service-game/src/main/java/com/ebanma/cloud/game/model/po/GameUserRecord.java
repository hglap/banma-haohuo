package com.ebanma.cloud.game.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "game_user_record")
@Data
public class GameUserRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 奖品类型
     */
    @Column(name = "present_type")
    private String presentType;

    /**
     * 奖品数量
     */
    @Column(name = "present_count")
    private Integer presentCount;

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