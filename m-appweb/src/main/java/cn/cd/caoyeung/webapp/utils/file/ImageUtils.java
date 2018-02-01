package cn.cd.caoyeung.webapp.utils.file;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtils {
	public static BufferedImage drawCode(String number){
		/*
		 * 一、绘图  
		 */
		//step1, 创建一个内存映像(画布)
		BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
		//step2,获得画笔
		Graphics g = image.getGraphics();
		//step3,给笔设置颜色
		g.setColor(new Color(255,255,255));
		//step4,设置画布的背景颜色
		g.fillRect(0, 0, 80, 30);
		//step5,给笔设置颜色和字体
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),
				r.nextInt(255),r.nextInt(255)));
		g.setFont(new Font(null,Font.ITALIC,24));
		//step6,生成一个随机数
		//step7,绘制图片
		g.drawString(number, 5,26);
		//step8,加一些干扰线
		for(int i = 0; i < 8; i ++){
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30),r.nextInt(80), r.nextInt(30));
		}
		return image;
	}
	public static String  getNumber(int size) {
		String number = "";
		String chars = "ABCDEFGHIJKLM" +
				"NOPQRSTUVWXYZ0123456789";
		Random r = new Random();
		for(int i = 0; i < size; i ++){
			number += chars.charAt(
					r.nextInt(chars.length()));
		}
		return number;
		}

}
