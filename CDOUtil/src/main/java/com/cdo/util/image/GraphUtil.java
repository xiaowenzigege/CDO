package com.cdo.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.FileInputStream;
import com.cdo.util.exception.ImageException;

public class GraphUtil {

	public static ByteArrayInputStream createRandImage(String randCode) throws ImageException{
				// 在内存中创建图象
				int width = 75, height = 30;
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				// 获取图形上下文
				Graphics g = image.getGraphics();
				// 生成随机类
				Random random = new Random();
				// 设定背景色
				// g.setColor(getRandColor(200, 250));
				g.setColor(new Color(24, 102, 160));
				g.fillRect(0, 0, width, height);
				// 设定字体
				g.setFont(new Font("Times New Roman", Font.PLAIN + Font.BOLD, 27));
				// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
				g.setColor(getRandColor(160, 200));
				for (int i = 0; i < 155; i++) {
					int x = random.nextInt(width);
					int y = random.nextInt(height);
					int xl = random.nextInt(12);
					int yl = random.nextInt(12);
					g.drawLine(x, y, x + xl, y + yl);
				}
				// 取随机产生的认证码(4位数字)
				for (int i = 0; i < randCode.length(); i++) {
					String rand =randCode.charAt(i)+"";								
					// 将认证码显示到图象中
					// g.setColor(new Color(20 + random.nextInt(110), 20 +
					// random.nextInt(110), 20 + random.nextInt(110)));
					// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
					g.setColor(new Color(241, 254, 255));
					g.drawString(rand, 16 * i + 6, 25);
				}				
				// 图象生效
				g.dispose();
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				ByteArrayInputStream input=null;
				try{
					ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
					ImageIO.write(image, "JPEG", imageOut);
					imageOut.close();
					input = new ByteArrayInputStream(output.toByteArray());					
				}catch(Exception ex){					
					throw new ImageException(ex.getMessage(),ex.getCause());
				}
				return input;						
	}

	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	
	/**
	 * 
	 * @param srcImgPath 待切割图片路径
	 * @param destImgPath 切割后图片路径
	 * @param destImgW 所需宽度
	 * @param destImgH 所需高度
	 * @throws ImageException 
	 */
	public static void createJPEG(String srcImgPath , String destImgPath, int destImgW , int destImgH) throws ImageException{	  
	    createImage(srcImgPath, destImgPath, destImgW, destImgH, "JPEG");
	} 
	/**
	 * 
	 * @param srcImgPath 原始图片路径
	 * @param destImgPath 目标图片保存路径
	 * @param destImgW 目标图片宽度
	 * @param destImgH 目标图片高度
	 * @param formatName 文件格式,如:JPG,JPEG,PNG,BMP等图片格式
	 * @throws ImageException 
	 */
	public static void createImage(String srcImgPath , String destImgPath, int destImgW , int destImgH,String formatName) throws ImageException{

		//原图片等比例缩小或放大之后的图片
	    int narrowImgW ;
	    int narrowImgH ;
	    //原图片大小
	    int srcImgW ;
	    int srcImgH ;
	    try {
			formatName=formatName.toUpperCase();
			if(formatName.equals("JPG")){
				formatName="JPEG";
			}			
	        BufferedImage bi = ImageIO.read(new File(srcImgPath));
	        srcImgW = bi.getWidth();
	        srcImgH = bi.getHeight();
	        // 转换图片尺寸与目标尺寸比较 ， 如果转换图片较小，说明转换图片相对于目标图片来说高较小，需要以高为基准进行缩放。
	        if((float )srcImgW /srcImgH > (float)destImgW / destImgH){
	            narrowImgW = ( int)(((float )destImgH / (float)srcImgH)*srcImgW);
	            narrowImgH = destImgH;
	            //按照原图以高为基准等比例缩放、或放大。这一步高为所需图片的高度，宽度肯定会比目标宽度宽。
	            int cutNarrowImgSize = (narrowImgW - destImgW)/2;
	            BufferedImage narrowImg = new BufferedImage(narrowImgW, narrowImgH,BufferedImage.TYPE_INT_RGB);
	            narrowImg.getGraphics().drawImage(bi.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_SMOOTH ), 0, 0, null);
	            //等比例缩放完成后宽度与目标尺寸宽度相比较 ， 将多余宽的部分分为两份 ，左边删除一部分
	            Image image = narrowImg.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_DEFAULT );

	            CropImageFilter cropFilter = new CropImageFilter(cutNarrowImgSize, 0, narrowImgW-cutNarrowImgSize, narrowImgH);
	            Image img = Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(image.getSource(), cropFilter));
	            BufferedImage cutLiftNarrowImg = new BufferedImage( narrowImgW-cutNarrowImgSize, narrowImgH,BufferedImage.TYPE_INT_RGB );
	            cutLiftNarrowImg.getGraphics().drawImage(img, 0, 0, null);
	            //右边删除一部分
	            image = cutLiftNarrowImg.getScaledInstance(narrowImgW-cutNarrowImgSize, narrowImgH, Image.SCALE_DEFAULT );
	            cropFilter = new CropImageFilter(0, 0, narrowImgW-cutNarrowImgSize*2, narrowImgH);
	            img = Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(image.getSource(), cropFilter));
	            BufferedImage cutRightNarrowImg = new BufferedImage( narrowImgW-cutNarrowImgSize*2, narrowImgH,BufferedImage.TYPE_INT_RGB );
	            Graphics g = cutRightNarrowImg.getGraphics();
	            g.drawImage(img, 0, 0, null); // 绘制截取后的图
	            g.dispose();
	            //输出为文件 最终为所需要的格式
	            ImageIO. write(cutRightNarrowImg,formatName, new File(destImgPath));
	        }else{ //以宽度为基准
	            narrowImgW = destImgW;
	            narrowImgH = ( int) (((float )destImgW / (float)srcImgW)*srcImgH);
	            int cutNarrowImgSize = (narrowImgH - destImgH)/2;

	            BufferedImage narrowImg = new BufferedImage(narrowImgW, narrowImgH,BufferedImage.TYPE_INT_RGB);
	            narrowImg.getGraphics().drawImage(bi.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_SMOOTH ), 0, 0, null);

	            Image image = narrowImg.getScaledInstance(narrowImgW, narrowImgH, Image.SCALE_DEFAULT );
	            CropImageFilter cropFilter = new CropImageFilter(0, cutNarrowImgSize, narrowImgW, narrowImgH-cutNarrowImgSize);
	            Image img = Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(image.getSource(), cropFilter));
	            BufferedImage cutTopNarrowImg = new BufferedImage( narrowImgW, narrowImgH-cutNarrowImgSize,BufferedImage. TYPE_INT_RGB);
	            cutTopNarrowImg.getGraphics().drawImage(img, 0, 0, null);

	            image = cutTopNarrowImg.getScaledInstance(narrowImgW, narrowImgH-cutNarrowImgSize, Image. SCALE_DEFAULT);
	            cropFilter = new CropImageFilter(0, 0, narrowImgW, narrowImgH-cutNarrowImgSize*2);
	            img = Toolkit.getDefaultToolkit().createImage( new FilteredImageSource(image.getSource(), cropFilter));
	            BufferedImage cutBottomNarrowImg = new BufferedImage( narrowImgW, narrowImgH-cutNarrowImgSize*2,BufferedImage. TYPE_INT_RGB);
	            Graphics g = cutBottomNarrowImg.getGraphics();
	            g.drawImage(img, 0, 0, null);
	            g.dispose();
	            ImageIO. write(cutBottomNarrowImg,formatName, new File(destImgPath));
	        }
	    }catch (Exception ex) {
	    	throw new ImageException(ex.getMessage(),ex.getCause());
	    }
	} 
	/**
	 * 
	 * @param srcImgPath 原始图片路径
	 * @param destImgPath 目标图片保存路径
	 * @param destImgW 目标图片宽度
	 * @param destImgH 目标图片高度
	 * @param proportion 是否等比缩放
	 * @throws ImageException
	 */
	public static void resizePNG(String srcImgPath, String destImgPath, int destImgW, int destImgH,boolean proportion) throws ImageException {
		resizeImage(srcImgPath, destImgPath, destImgW, destImgH, proportion, "PNG"); 		
	}
	/**
	 * 
	 * @param srcImgPath 原始图片路径
	 * @param destImgPath 目标图片保存路径
	 * @param destImgW 目标图片宽度
	 * @param destImgH 目标图片高度
	 * @param proportion 是否等比缩放
	 * @param formatName 如:JPG,JPEG,PNG,BMP等图片格式
	 * @throws ImageException
	 */
	public static void resizeImage(String srcImgPath, String destImgPath, int destImgW, int destImgH,boolean proportion,String formatName) throws ImageException {
		try{ 
	        File f2 = new File(srcImgPath);  
	        BufferedImage bi2 = ImageIO.read(f2);  
	        int newWidth;
	        int newHeight;
		    // 判断是否是等比缩放
		    if (proportion == true) {
				 // 为等比缩放计算输出的图片宽度及高度
				 double rate1 = ((double) bi2.getWidth(null)) / (double) destImgW + 0.1;
				 double rate2 = ((double) bi2.getHeight(null)) / (double) destImgH + 0.1;
				 // 根据缩放比率大的进行缩放控制
				 double rate = rate1 < rate2 ? rate1 : rate2;
				 newWidth = (int) (((double) bi2.getWidth(null)) / rate);
				 newHeight = (int) (((double) bi2.getHeight(null)) / rate);	        
		    }else{
		        newWidth = destImgW; // 输出的图片宽度
		        newHeight = destImgH; // 输出的图片高度
		   }		        
           
		   BufferedImage to = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_RGB);  
           Graphics2D g2d = to.createGraphics();  
           to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth,newHeight,Transparency.TRANSLUCENT);  
           g2d.dispose();  
           
           g2d = to.createGraphics();  
           Image from = bi2.getScaledInstance(newWidth, newHeight, bi2.SCALE_AREA_AVERAGING);  
           g2d.drawImage(from, 0, 0, null);
           g2d.dispose();  
           ImageIO.write(to,formatName,new File(destImgPath));  
           }catch(Exception ex) {  
        	   throw new ImageException(ex.getMessage(),ex.getCause()); 
           }  		
	}
	
	public static void main(String []args){	    
		try{
//	    String url="D:/DEV/pic/6239936_092702973000.jpg";
	    String url="D:/DEV/pic/20190307185852.png";
	    FileInputStream fileId=new FileInputStream(url);
		BufferedImage bufferedImage=ImageIO.read(fileId);
		int width1080 = bufferedImage.getWidth();
		int height1080 = bufferedImage.getHeight();
		//根据缩放 得到上传图片相对应的720分表率的宽度和高度
		int width720 = (int)Math.ceil(width1080*2/3);
		int height720 = (int)Math.ceil(height1080*2/3);	
		String formatName=url.substring(url.lastIndexOf(".")+1).toUpperCase();
		if(formatName.equals("JPG")){
			formatName="JPEG";
		}
		String path="D:/DEV/pic";
//		ImageUtils.resizeImage(url, path+"/test720.jpg", width720, height720, false,"PNG");	
//		createThumb(url,path+"/test720_1.png",width720,height720,formatName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
