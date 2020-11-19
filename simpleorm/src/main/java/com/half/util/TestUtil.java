package com.half.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @Author: wangwei
 * @Date: 2020-05-16 14:28
 */
public class TestUtil {

    static Font f1 = new Font("Helvetica", Font.PLAIN, 18);
    static Font f2 = new Font("“Helvetica", Font.BOLD, 10);

    public static void test1(String[] args) {
        String[] srcs = new String[]{"http://img3.laibafile.cn/p/l/316621248.jpg", "http://img3.laibafile.cn/p/s/316621248.jpg", "https://img3.laibafile.cn/p/mh/316782676.jpg"};
        String re = "https?://[^/]+/p/[^/]+/(\\S)";
        for (String src : srcs) {
            System.out.println(src.replaceAll(re, "https://img3.tianyaui.com/p/m/$1"));
        }

        //()子表达式
        String[] srcs1 = new String[]{"滚滚看世界 　【持续更新】", "[评论随笔]我喜欢的古典诗词（第1部已出版）", "〖天涯头条〗漫谈日本情色电影五十年（图文连载）", "[版务处理]价值万亿的“国宝”——刘同庆传（一段绝对真实的历史)"};
        // [\(（\[【〖]    [^\)）\]】〗]     [\)）\]】〗]
        // + 1+  * 0+  ？ 0 1
        String re1 = "[\\(（\\[【〖][^\\)）\\]】〗]*[\\)）\\]】〗]";
        for (String src : srcs1) {
            System.out.println(src.replaceAll(re1, ""));
        }
    }

    public static void test2(String[] args) throws Exception {
        int width = 200, height = 250;
        //创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = image.createGraphics();
//指定要绘制的字符串
        String amount = "ddsewe";
        Font font1 = new Font("微软雅黑", Font.BOLD, 32);// 添加字体的属性设置
        graphics.setFont(font1);
        Color color1 = new Color(100, 0, 0);
        graphics.setColor(color1);

        // 截取研报标题 如: 科技：MWC：5G和边缘计算设备设计生产思路更加明确      展示为   科技：
        graphics.drawString("都是撒大声地", 20, 200);

        ImageIO.write(image, "png", new File("abc.png"));
    }

    public static void main(String[] args) {

    }

}
