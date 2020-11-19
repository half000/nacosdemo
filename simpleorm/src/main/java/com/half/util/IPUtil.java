package com.half.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 提供跟IP相关的一些处理方法。
 *
 * @author zengxp
 */
public class IPUtil {

    private static List<InetAddress> localAddressList;
    private static Pattern ipPattern = Pattern.compile("\\b(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\b");

    /**
     * 初始化LOCALIP
     */
    static {
        localAddressList = getLocalAddresses();
    }

    public static void main(String[] args) {
        //Arrays.stream(StringUtils.tokenizeToStringArray("ssd,sdsd,dfd",",")).forEach(System.out::println);
        getLocalAddresses().forEach(System.out::println);
        //getLocalIP(true);
    }

    /**
     * 把IP地址转换成整形
     *
     * @param ip
     * @return
     */
    public static long convertIpToInt(String ip) {
        //	 创建IP数组
        String[] ipArray = StringUtils.split(ip, ".");
        //创建IP整形
        long ipInt = 0;
        //对数组进行循环
        try {
            for (int i = 0; i < ipArray.length; i++) {
                if (ipArray[i] == null || ipArray[i].trim().equals("")) {
                    ipArray[i] = "0";
                }
                if (new Integer(ipArray[i].toString()).intValue() < 0) {
                    Double j = new Double(Math.abs(new Integer(ipArray[i].toString()).intValue()));
                    ipArray[i] = j.toString();
                }
                if (new Integer(ipArray[i].toString()).intValue() > 255) {
                    ipArray[i] = "255";
                }
            }
            ipInt = new Double(ipArray[0]).longValue() * 256 * 256 * 256 + new Double(ipArray[1]).longValue() * 256 * 256
                    + new Double(ipArray[2]).longValue() * 256 + new Double(ipArray[3]).longValue();
            //返回整形
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipInt;
    }

    /**
     * 得到用户客户端的IP地址。
     *
     * @param request
     * @return ip地址
     * @deprecated user getUserIP(HttpServletRequest request) instead
     */
    public static String getIpAddress(HttpServletRequest request) {
        return getUserIP(request);
    }

    /**
     * 取得本机IP（多网卡主机，默认取非内网IP，如果非内网IP也有多个，取获取到的第一个）
     *
     * @return 本机IP
     */
    public static String getLocalIP() {
        return getLocalIP(false);
    }

    /**
     * 取得本机IP地址 （多网卡主机返回其中一张网卡的IP）
     *
     * @param isInter 是否返回内网IP（默认网段为19.2.）
     * @return 本机ip地址
     */
    public static String getLocalIP(boolean isInter) {
        //初始化本地地址列表
        if (localAddressList == null) {
            localAddressList = getLocalAddresses();
        }
        String localIP = "";
        for (InetAddress ia : localAddressList) {
            String ip = ia.getHostAddress();
            //忽略ipv6地址和loopback地址
            if (ia instanceof Inet6Address || ip.startsWith("127")) {
                continue;
            }
            //记录找到的第一个ipv4地址
            if (StringUtils.isEmpty(localIP)) {
                localIP = ip;
            }
            String internalIP = null;
            if (isInter) {
                //先用getLocalHost()获取本机IP,没有再用上面获取到的;
                try {
                    internalIP = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (!StringUtils.isEmpty(internalIP) && !internalIP.startsWith("127")) {
                    return internalIP;
                } else {
                    //通过getLocalHost没有获取到内网IP
                    if (ip.startsWith("19.")) {
                        return ip;
                    }
                }
            }

            if (isInter && ip.startsWith("19.")) {
                return ip;
            }
            if (!isInter && !ip.startsWith("19.")) {
                return ip;
            }
        }
        return localIP;
    }

    /**
     * 判断地址是否为IP地址
     *
     * @param address 网络地址
     * @return 是IP地址返回true，否则返回false
     */
    public static boolean isIP(String address) {
        //匹配ip的正则表达式

        return ipPattern.matcher(address).matches();
    }

    /**
     * 取得本机网络地址列表
     *
     * @return
     */
    public static List<InetAddress> getLocalAddresses() {
        if (localAddressList == null) {
            localAddressList = new ArrayList<InetAddress>();
            try {
                //获取可用的网络接口
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces != null && interfaces.hasMoreElements()) {
                    NetworkInterface interfaceN = interfaces.nextElement();
                    //获取网络接口上的网络地址
                    Enumeration<InetAddress> ienum = interfaceN.getInetAddresses();
                    while (ienum.hasMoreElements()) {
                        InetAddress ia = ienum.nextElement();
                        System.out.println(ia.getHostAddress() + " " + ia.isSiteLocalAddress());
                        //添加网络地址到本机网络地址列表
                        localAddressList.add(ia);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return localAddressList;
    }

    /**
     * 获取用户终端的IP地址
     *
     * @param request http请求
     * @return 用户终端的IP地址
     */
    public static String getUserIP(HttpServletRequest request) {
        return getClientIP(request, 0);
    }

    /**
     * 获取Wap调用的用户IP
     *
     * @param request
     * @return
     */
    public static String getWapUserIP(HttpServletRequest request) {
        return getClientIP(request, 1);
    }

    /**
     * 获取最接近的客户端IP
     */
    public static String getNearestClientIP(HttpServletRequest request) {
        return getClientIP(request, 2);
    }

    /**
     * 根据指定模式获取客户端IP
     *
     * @param request http请求
     * @param mode    0:user ip, 1:wap user ip, 2: nearest client ip
     * @return
     */
    private static String getClientIP(HttpServletRequest request, int mode) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        } else {
            String[] ips = ip.split(",");
            for (int i = 0; i < ips.length; i++) {
                ip = ips[i].trim();
                switch (mode) {
                    case 0:
                        //user ip
                        if (!(
                                ip.startsWith("10.") ||
                                        ip.startsWith("192.168") ||
                                        ip.startsWith("172.16.") ||
                                        ip.startsWith("19.2.168") ||
                                        ip.startsWith("19.2.169") ||
                                        ip.startsWith("19.2.170") ||
                                        ip.startsWith("19.2.171") ||
                                        ip.startsWith("19.2.172") ||
                                        ip.startsWith("19.2.173") ||
                                        ip.startsWith("19.2.174") ||
                                        "".equals(ip) ||
                                        ip.equalsIgnoreCase("unknown")
                        )) {
                            return ip.trim();
                        }
                        break;
                    case 1:
                        //wap user ip
                        if (!(
                                ip.startsWith("192.168") ||
                                        ip.startsWith("172.16.") ||
                                        ip.startsWith("19.2.168") ||
                                        ip.startsWith("19.2.169") ||
                                        ip.startsWith("19.2.170") ||
                                        ip.startsWith("19.2.171") ||
                                        ip.startsWith("19.2.172") ||
                                        ip.startsWith("19.2.173") ||
                                        ip.startsWith("19.2.174") ||
                                        "".equals(ip) ||
                                        ip.equalsIgnoreCase("unknown")
                        )) {
                            return ip.trim();
                        }
                        break;
                    case 2:
                        //nearest client ip
                        if (!(
                                ip.startsWith("10.") ||
                                        ip.startsWith("192.168") ||
                                        ip.startsWith("172.16.") ||
                                        "".equals(ip) ||
                                        ip.equalsIgnoreCase("unknown")
                        )) {
                            return ip.trim();
                        }
                        break;
                    default:
                }
                ip = null;
            }
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        } else {
            return ip.trim();
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip.trim();
    }


}
