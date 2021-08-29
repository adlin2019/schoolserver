package com.school.pojo;

import com.school.constant.HttpStatus;

import java.util.HashMap;

/**
 * 响应实体
 *
 * @author hnuer
 */
public class ResInfo extends HashMap<String, Object> {

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 空返回信息
     */
    public ResInfo() {

    }

    /**
     * 初始化返回信息
     * @param code
     * @param msg
     */
    public ResInfo(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化返回信息
     * @param code
     * @param msg
     * @param data
     */
    public ResInfo(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 操作成功
     * @param msg
     * @param data
     * @return
     */
    public static ResInfo success(String msg, Object data) {

        return new ResInfo(HttpStatus.SUCCESS, msg, data);

    }


    /**
     * 操作成功
     * @param msg
     * @return
     */
    public static ResInfo success(String msg) {

        return ResInfo.success(msg, null);

    }


    /**
     * 操作成功
     * @return
     */
    public static ResInfo success() {
        return ResInfo.success("操作成功");
    }


    /**
     * 操作失败
     * @param msg
     * @param data
     * @return
     */
    public static ResInfo error(String msg, Object data) {

        return new ResInfo(HttpStatus.ERROR, msg, data);

    }


    /**
     * 操作失败
     * @param msg
     * @return
     */
    public static ResInfo error(String msg) {

        return ResInfo.error(msg, null);
    }

    /**
     * 操作失败
     * @return
     */
    public static ResInfo error() {
        return ResInfo.error("操作失败");
    }


}
