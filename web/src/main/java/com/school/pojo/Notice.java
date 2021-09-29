package com.school.pojo;

import java.io.Serializable;

/**
 * 寻物启事帖子实体对象
 *
 * @author hnuer
 */
public class Notice implements Serializable {

    /**
     * 告示id
     */
    private Long noticeId;

    /**
     * 告示发布者
     */
    private User noticePublisher;

    /**
     * 物品种类
     */
    private String thingType;

    /**
     * 拾取者联系电话
     */
    private int phoneNumber;

    /**
     * 拾取者QQ号码
     */
    private int QQAccount;

    /**
     * 拾取的地点
     */
    private String location;

    /**
     * 图片存储的地址
     */
    private String ImageUrl;

    /**
     * 拾取的物品简介
     */
    private String introduction;

}
