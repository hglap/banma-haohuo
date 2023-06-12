package com.ebanma.cloud.game.model.po;

import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "game_user_record")
@Data
@NoArgsConstructor
public class GameUserRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

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
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifiedTime;

    public GameUserRecord(String userId,String userName, GamePresentRuleVO presentDraw) {
        this.userId = userId;
        this.userName = userName;
        this.presentCode = presentDraw.getPresentCode();
        this.presentCount = presentDraw.getPresentCount();
        this.createTime = new Date();
        this.modifiedTime = new Date();
    }

}