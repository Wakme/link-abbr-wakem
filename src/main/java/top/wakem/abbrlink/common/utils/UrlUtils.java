package top.wakem.abbrlink.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class UrlUtils {

    public static boolean isUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        String regex = "^((https|http|ftp|rtsp|mms)?://)"  //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        return url.matches(regex);
    }

    public static String preHandleUrl(String link) {
        if (StringUtils.isBlank(link)) {
            return "";
        }
        if (!link.startsWith("http")) {
            link = "http://" + link;
        }
        return link;
    }
}
