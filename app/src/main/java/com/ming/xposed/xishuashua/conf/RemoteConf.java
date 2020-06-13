package com.ming.xposed.xishuashua.conf;


import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.ming.util.common.Util;
import com.ming.xposed.xishuashua.bean.RemoteData;
import com.ming.xposed.xishuashua.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class RemoteConf {
    private static final String URL ="http://127.0.0.1:8080/servlet/GetTask";
    private final Context context;

    MingConf mingConf;
    public RemoteConf(Context context) {
        this.context = context;
        mingConf = new MingConf(context);
    }

    public RemoteData makeConf() {
        RemoteData remoteData = new RemoteData();
//        remoteData.device =  mingConf.makeConf();
        remoteData.num = 2;



//        RemoteData remoteData = readData();
//        if (remoteData != null && remoteData.isVaild()) {
//            return remoteData;
//        }
        return remoteData;
    }


    public String conf;
    public String deviceConf;
    public int bind_status;
    int count = 0;
    public void loadConf()
    {

        try
        {

            //Environment.getExternalStorageDirectory().getPath()
            bind_status = 3;
            deviceConf = null;
            String dbPath = context.getCacheDir().getPath()+"/umengdb";
            Util.debug("---------------load---conf-----dbPath----:"+dbPath);
            new File(dbPath).mkdirs();
            File[] files = new File(dbPath).listFiles();
            Util.debug("---------------load---conf-----total----:"+files.length);
            if(files!=null&&files.length<30)
            {
                bind_status = 2;
                deviceConf = mingConf.makeConf();
                Util.writeFileData(dbPath+"/"+System.currentTimeMillis()+".xml",deviceConf.getBytes("utf-8"));
            }
            else
            {
                bind_status = 3;
                byte[] data = Util.readFileData(files[count].getPath());
                deviceConf = new String(data,"utf-8");
                Util.debug("---------------load---conf-----count----:"+count);
                count++;
                if(count>files.length-1)
                {
                    count = 0;
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




    }

    public int loadNetConf()
    {
        int ret = 0;
        try
        {
            deviceConf = null;
            String url = "http://47.105.150.189/bind/ServiceCenter.do?action=getconf2&data=HQdLXSpWV1dZS1xYGAdLXSpZV1dXS1xYDQdLXSpYV1dXS1xYCh1LXSp&format=json&pv=3&debug=true";
            Util.debug(url);
            byte[] data = Util.getHttpData(url,false);
            String str = new String(data,"utf-8");
            Util.debug(str);
            JSONObject json = new JSONObject(str);
            JSONArray result = json.getJSONArray("result");
            if(result.length()>0)
            {
                bind_status = result.getJSONObject(0).getInt("bind_status");

//                String dbPath = context.getCacheDir().getPath()+"/umengnetdb";
//                Util.debug("---------------load---conf-----dbPath----:"+dbPath);
//                new File(dbPath).mkdirs();

//                bind_status = 2;

                if(bind_status==3)
                {
                    conf = result.getJSONObject(0).getJSONObject("phone_info").getString("info");
                    //data = Util.readFileData(dbPath+"/"+confID+".xml");
                    if(conf!=null)
                    {
                        try {
                            deviceConf = new String (Base64.decode(URLDecoder.decode(conf,"utf-8").getBytes()));
                        }catch (Exception e){

                        }
                    }
                }
                else if (bind_status==2)
                {
//                    confID = System.currentTimeMillis()+"";
                    deviceConf = mingConf.makeConf();
                    //Util.writeFileData(dbPath+"/"+confID+".xml",deviceConf.getBytes("utf-8"));
                }
                if (TextUtils.isEmpty(deviceConf))
                {
                    return -1;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }



    public void uploadConf(String id,int bind_status)
    {
        try
        {
//            String json = GetEncryptPara(conf);
            String url = "http://47.105.150.189/bind/ServiceCenter.do?action=upPhoneInfo2&bind_status="+bind_status+"&android_info="+id +"&id="+System.currentTimeMillis();
            Util.debug(url);
            byte[] data = Util.getHttpData(url,false);
            String str = new String(data,"utf-8");
            Util.debug(str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    public static String GetDecryptPara(String para) {
        StringBuffer sb = new StringBuffer();
        try {
            String urlEncoder = java.net.URLDecoder.decode(para, "UTF-8");
//            String crc = MD5Utils.MD5(urlEncoder);
//            byte buffer[] = new byte[urlEncoder.length()];
//            buffer = Encrypt(urlEncoder.getBytes());
//            urlEncoder = null;
//            String Base64str = new String(Base64.encode(buffer));
//            buffer = null;
            String upload = urlEncoder;
//            upload = java.net.URLDecoder.decode(urlEncoder, "UTF-8");
//            Base64str = null;
//            sb.append("verf=");
//            sb.append(crc);
//            sb.append("&pv=3");
//            sb.append("&data=");
            sb.append(upload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public static String GetEncryptPara(String para) {
        StringBuffer sb = new StringBuffer();
        try {
            String urlEncoder = java.net.URLEncoder.encode(para, "UTF-8");
//            String crc = MD5Utils.MD5(urlEncoder);
//            byte buffer[] = new byte[urlEncoder.length()];
//            buffer = Encrypt(urlEncoder.getBytes());
//            urlEncoder = null;
//            String Base64str = new String(Base64.encode(buffer));
//            buffer = null;
            String upload = "";
            upload = java.net.URLEncoder.encode(urlEncoder, "UTF-8");
//            Base64str = null;
//            sb.append("verf=");
//            sb.append(crc);
//            sb.append("&pv=3");
//            sb.append("&data=");
            sb.append(upload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public RemoteData readData() {
        InputStream is = null;
        BufferedReader bufferedReader = null;
        StringBuilder returnString = new StringBuilder();
        RemoteData remoteData = new RemoteData();
        try {
            HttpURLConnection httpurlconnection = (HttpURLConnection) (new URL(URL)).openConnection();
            httpurlconnection.setRequestMethod("GET");
            httpurlconnection.setConnectTimeout(3000);
            httpurlconnection.setReadTimeout(5000);
            int status = httpurlconnection.getResponseCode();
            if (status == 200) {
                is = httpurlconnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String lineStr;
                int index = 0;
                while ((lineStr = bufferedReader.readLine()) != null) {
                    if (index == 0) {
                        lineStr = lineStr.trim();
                        remoteData.channel = lineStr;
                    } else if (index == 1) {
                        lineStr = lineStr.trim();
                        remoteData.type = lineStr;
                    } else if (index == 2) {
                        lineStr = lineStr.trim();
                        remoteData.num = Util.getInt(lineStr);
                    } else if (index == 3) {
                        lineStr = lineStr.trim();
                        remoteData.event = lineStr;
                    } else {
                        returnString.append(lineStr);
                        returnString.append("\n");
                    }
                    index++;
                }

                remoteData.device = returnString.toString();
                return remoteData;
            }

        } catch (Exception e) {
        	Util.debug("get remote data error:"+e.getMessage());
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }
}
