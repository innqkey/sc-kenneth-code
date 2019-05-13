package com.kenneth.gateway.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 服务网关路由规则
 * /{service-name}/{service-uri}
 * /geteway/rest-api/hello-world --> 127.0.0.1:8080/hello-world
 * http://localhost:20000/gateway/server-application/say?message=kenneth
 * @auther qinkai
 * @data 2019/5/10 11:18
 */
@WebServlet(name = "gateway", urlPatterns = "/gateway/*")
public class GatewayServlet extends HttpServlet {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 随机选择一台服务器
     *
     * @param serviceName
     * @return
     */
    private ServiceInstance randomChoose(String serviceName) {
        //获取服务列表(服务IP,端口，是否为https)
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        //获得服务实例数
        int size = serviceInstances.size();
        //随机获取数组下标
        int index = new Random().nextInt(size);
        return serviceInstances.get(index);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //${service-name}/${service-uri}
        String pathInfo = request.getPathInfo();
        String[] parts = StringUtils.split(pathInfo.substring(1), "/");
        //获取服务名称
        String serviceName = parts[0];
        //获取服务uri
        String serviceURI = "/" + parts[1];
        //随机选择一台服务实例
        ServiceInstance serviceInstance = randomChoose(serviceName);
        //构建目标服务 url --> scheme://ip:port/serviceURI
        String targetURL = buildTargetURL(serviceInstance, serviceURI, request);
        //构建转发客户端
        RestTemplate restTemplate = new RestTemplate();
        //构造Request实体
        try {
            RequestEntity<byte[]> requestEntity = createRequestEntity(request, targetURL);
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(requestEntity, byte[].class);
            writeHeaders(responseEntity, response);
            writeBody(responseEntity, response);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出body部分
     * @param responseEntity
     * @param response
     */
    private void writeBody(ResponseEntity<byte[]> responseEntity, HttpServletResponse response) throws IOException {
        if (responseEntity.hasBody()) {
            byte[] body = responseEntity.getBody();
            //输出二进制
            ServletOutputStream outputStream = response.getOutputStream();
            //输出 ServletOutputStream
            outputStream.write(body);
            outputStream.flush();
        }
    }

    /**
     * 输出header
     * @param responseEntity
     * @param response
     */
    private void writeHeaders(ResponseEntity<byte[]> responseEntity, HttpServletResponse response) {
        //获得相应头
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        //输出转发 Response 头
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String headerName = entry.getKey();
            List<String> headerValues = entry.getValue();
            for (String headerValue : headerValues){
                response.addHeader(headerName,headerValue);
            }
        }
    }

    /**
     * 构造请求实体
     *
     * @param request
     * @param targetURL
     * @return
     */
    private RequestEntity<byte[]> createRequestEntity(HttpServletRequest request, String url) throws URISyntaxException, IOException {
        //获取当前请求方法
        String method = request.getMethod();
        //转换 HttpMethod
        HttpMethod httpMethod = HttpMethod.resolve(method);
        byte[] body = createRequestBody(request);
        MultiValueMap<String, String> headers = createRequestHeaders(request);
        RequestEntity<byte[]> requestEntity =
                new RequestEntity<byte[]>(body, headers, httpMethod, new URI(url));
        return requestEntity;
    }

    /**
     * 构造请求体
     * @param request
     * @return
     */
    private byte[] createRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    /**
     * 构造请求头
     * @param request
     * @return
     */
    private MultiValueMap<String, String> createRequestHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames){
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for(String headerValue : headerValues){
                headers.add(headerName,headerValue);
            }
        }
        return headers;
    }

    /**
     * 构建目标URL
     *
     * @param serviceInstance
     * @param serviceURI
     * @param request
     * @return
     */
    private String buildTargetURL(ServiceInstance serviceInstance, String serviceURI, HttpServletRequest request) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(serviceInstance.isSecure() ? "https://" : "http://")
                .append(serviceInstance.getHost())
                .append(":")
                .append(serviceInstance.getPort())
                .append(serviceURI);
        String queryString = request.getQueryString();
        if (StringUtils.hasText(queryString)) {
            urlBuilder.append("?").append(queryString);
        }
        return urlBuilder.toString();
    }
}

















































