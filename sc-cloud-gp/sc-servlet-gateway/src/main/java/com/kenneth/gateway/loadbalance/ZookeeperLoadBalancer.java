package com.kenneth.gateway.loadbalance;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther qinkai
 * @data 2019/5/10 13:35
 */
public class ZookeeperLoadBalancer extends RoundRobinRule {

    private DiscoveryClient discoveryClient;

    private Map<String, BaseLoadBalancer> loadBalancerMap = new ConcurrentHashMap<>();

    public ZookeeperLoadBalancer(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        updateServers();
    }

    /**
     * 更新所有服务器
     */
    @Scheduled(fixedRate = 5000)
    private void updateServers() {
        discoveryClient.getServices().stream()
                .forEach(serviceName -> {
                    BaseLoadBalancer loadBalancer = new BaseLoadBalancer();
                    loadBalancerMap.put(serviceName,loadBalancer);
                    List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
                    serviceInstances.forEach(serviceInstance -> {
                        Server server = new Server(serviceInstance.isSecure() ? "https://" : "http://",
                                serviceInstance.getHost(), serviceInstance.getPort());
                        loadBalancer.addServer(server);
                    });
                });
    }

    public Server chooseServer(Object key) {
        if(key instanceof String){
            String serviceName = String.valueOf(key);
            BaseLoadBalancer baseLoadBalancer = loadBalancerMap.get(serviceName);
            return super.choose(baseLoadBalancer,serviceName);
        }
        return null;
    }
}
