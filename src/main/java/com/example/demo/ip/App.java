package com.example.demo.ip;

import com.example.demo.enums.MessageEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    private static final String localip = "localip";
    private static final String netip = "netip";


    public static void main(String[] args) throws Exception {
/*        if(rs.getInt("sorting_state")==0){
            nm.setSortingStateByValue("待完成");
        }else if (rs.getInt("sorting_state")==1){
            nm.setSortingStateByValue("补码异常");
        }else if (rs.getInt("sorting_state")==2){
            nm.setSortingStateByValue("异常");
        }else if (rs.getInt("sorting_state")==3){
            nm.setSortingStateByValue("完成");
        }else if (rs.getInt("sorting_state")==4){
            nm.setSortingStateByValue("异形件");
        }*/
    }

    public static Map<String, String> getRealIp() throws Exception {
        Map<String, String> map = new HashMap<>();

        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }

        map.put("localip", localip);
        map.put("netip", netip);
        return map;
    }


    public static String getV4IP() {
        String ip = "";
        String chinaz = "ht" + "tp" + ":/" + "/i" + "p.ch" + "in" + "az." + "co" + "m";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       // Pattern p = Pattern.compile("\\<dd class\\="\\&quot;fz24&quot;">(.*?)\\&lt;\\/dd&gt;");
       // Matcher m = p.matcher(inputLine.toString());
//        if (m.find()) {
//            String ipstr = m.group(1);
//            ip = ipstr;
//        }
        return ip;
    }
}
