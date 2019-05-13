package com.kenneth.client.event;

import org.springframework.context.ApplicationEvent;

/**
 * @auther qinkai
 * @data 2019/5/11 10:50
 */
public class RemoteAppEvent extends ApplicationEvent {
    /**
     * 事件传输类型 HTTP RPC MQ
     */
    private String type;
    /**
     * 应用名称
     */
    private final String appName;
    /**
     * 是否广播到集群
     */
    private final boolean isCluster;

    /**
     * @param source 事件源
     * @param appName 应用程序名称
     * @param isCluster 是否集群
     */
    public RemoteAppEvent(Object source, String appName, boolean isCluster) {
        super(source);
        this.appName = appName;
        this.isCluster = isCluster;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppName() {
        return appName;
    }

}
