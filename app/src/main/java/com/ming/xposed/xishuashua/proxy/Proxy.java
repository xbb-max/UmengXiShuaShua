package com.ming.xposed.xishuashua.proxy;


import com.ming.util.common.Util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Proxy {
    private final static String proxyString = "http://s.zdaye.com/StApiEditIP.html?u=xx&p=xx&api=apikey&i=2,http://s.zdaye.com/?api=apikey&px=2";
    private final static String HOST = "https://ulogs.umeng.com/unify_logs";
    public static LinkedList<String> ipList = new LinkedList<String>();
    public static String[] proxyList;

    private static int index;
    private static int count;

    public static HttpItem setProxy() {
        if (ipList == null || ipList.isEmpty()) {
        	Util.debug("Create proxy start");
            ipList = createProxyIp(index % proxyList.length);
        }
        if (ipList == null || ipList.isEmpty()) {
        	Util.debug("Create proxy fail:"+count);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
            count++;
            if (count == 3) {
                count = 0;
                index++;
            }
            return null;
        } else {
            String ipString = ipList.poll();
            String[] split = ipString.split(":");
            if (split != null && split.length == 2) {
                return new HttpItem(split[0], Integer.valueOf(split[1]));
            }
            return null;
        }

    }

    public static HttpItem getHttpItem() {
        if (proxyList == null) {
            if (proxyString != null && !"".equals(proxyString)) {
                proxyList = proxyString.split(",");
            } else {
                Util.debug("proxy string not valid!!!");
                return null;
            }
        }
        HttpItem httpItem = null;
        do {
            httpItem = setProxy();
        } while ((httpItem == null) || (!getNetStatus(httpItem.ip, httpItem.port)));
        return httpItem;
    }

    private static LinkedList<String> createProxyIp(int index) {
        LinkedList<String> list = new LinkedList<String>();
        String url = proxyList[index];
        String result = null;
        try {
            HttpURLConnection httpurlconnection = (HttpURLConnection) (new URL(url)).openConnection();
            httpurlconnection.setRequestMethod("GET");
            httpurlconnection.setConnectTimeout(3000);
            httpurlconnection.setReadTimeout(3000);
            int status = httpurlconnection.getResponseCode();
            if (status == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream(), "gb2312"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                result = sb.toString();
            }
        } catch (Exception e) {
        }
        if (result != null && result.length() > 0) {
            String[] lines = result.split("\\n");
            if (lines != null) {
                for (int i = 0; i < lines.length; i++) {
                    if (Pattern.matches("\\d+\\.\\d+\\.\\d+\\.\\d+\\:\\d+", lines[i])) {
                        list.add(lines[i]);
                    }
                }
            } else {
                Util.debug("get proxy ip fail !!!" + url + " reuslt:" + result + "");
            }
        } else {
            Util.debug("get proxy ip fail !!!" + url + " reuslt:" + result + "");
        }
        return list;
    }

    private static boolean getNetStatus(String host, int port) {
//    	Util.debug("proxy getNetStatus:"+host);
//        HttpPost localHttpPost = new HttpPost(HOST);
//        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
//        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 1000);
//        HttpConnectionParams.setSoTimeout(localBasicHttpParams, 3000);
//        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
//
//        HttpHost proxy = new HttpHost(host, port);
//        localDefaultHttpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
//        try {
//            String data = "{}";
//
//            data = "content=" + data;
//            InputStreamEntity inputStreamEntity = new InputStreamEntity(new ByteArrayInputStream(data.getBytes("utf-8")),
//                    data.getBytes("utf-8").length);
//            localHttpPost.setEntity(inputStreamEntity);
//
//            HttpResponse response = localDefaultHttpClient.execute(localHttpPost);
//
//            if (response.getStatusLine().getStatusCode() == 200) {
//                return true;
//            }
//        } catch (ClientProtocolException localClientProtocolException) {
//            //localClientProtocolException.printStackTrace();
//        } catch (IOException localIOException) {
//            //localIOException.printStackTrace();
//        }
//        Util.debug("proxy request return fail:"+host);
        return false;
    }
}
