package org.qbuild.radiance.url;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlManager {
    static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Whale/3.22.205.18 Safari/537.36";
    static String regex = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

    public static String getHtml(String url) {
        try {
            String html = SSLHelper.getConnection(url).userAgent(userAgent).get().html();
            return html;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTitle(String html) {
        String titleStartTag = "<title>";
        String titleEndTag = "</title>";
        int startIndex = html.indexOf(titleStartTag);
        int endIndex = html.indexOf(titleEndTag);
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex)
            return html.substring(startIndex + titleStartTag.length(), endIndex);
        return "";
    }

    public static boolean isContainUrl(String message) {
        if (message.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }
}
