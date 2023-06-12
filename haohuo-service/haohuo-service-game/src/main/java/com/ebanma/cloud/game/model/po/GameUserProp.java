package com.ebanma.cloud.game.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "game_user_prop")
@Data
@NoArgsConstructor
public class GameUserProp  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 道具编码
     */
    @Column(name = "prop_code")
    private Integer propCode;

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