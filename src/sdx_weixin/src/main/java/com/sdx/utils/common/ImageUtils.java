package com.sdx.utils.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 图片处理工具
 * @author 田广文
 * @date 2012-2-16 上午09:58:32
 */
public class ImageUtils
{
    protected static final Log log = LogFactory.getLog(ImageUtils.class);

    public static void main(String[] args)
    {
        // testCodePic();
        testShopPic();
    }

    public static void testShopPic()
    {
        createStackCodePic("E:\\download\\test.png", new File("E:\\download\\4.jpg"), new File("E:\\download\\1.png"), 230, 150);
    }

    public static void testCodePic()
    {
        int width = 160;
        int height = 120;
        int fontSize = 21;
        int x = 11;
        int y = 28;
        int rowSpace = 28;
        createAssistCodePic("test.png", "ＴＴＫＶＶＯ●\nＫＶＶＴＶＫＴ\nＫＶＴＺＶＶＶ\nＶＺＴＺＫＯ●", width, height, fontSize, x, y, rowSpace);
    }

    public static boolean createAssistCodePic(String fileName, String assistCode, int width, int height, int fontSize, int x, int y,
            int rowSpace)
    {
        String[] lineText = assistCode.split("\n");
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, 480, 320);
        g2d.setColor(new Color(0, 0, 0));// 设置字体样式
        Font font = new Font("Arial Bold", Font.BOLD, fontSize);
        g2d.setFont(font);
        for (int i = 0; i < lineText.length; i++)
        {
            g2d.drawString(lineText[i], x, y + i * rowSpace);
        }
        g2d.dispose();
        // 保存文件
        try
        {
            ImageIO.write(image, "png", new File(fileName));
            return true;
        }
        catch (IOException e)
        {
            log.error("create png file error", e);
        }
        return false;
    }

    /**
     * 生成叠加图方法
     * @param fileName
     *        保存文件名称
     * @param bgFile
     *        底层图片
     * @param upFile
     *        顶层图片
     * @param bgWidth
     *        目标图片宽
     * @param bgHeight
     *        目标图片高
     * @param upWidth
     *        顶层图片宽
     * @param upHeight
     *        顶层图片高
     * @param bgX
     *        底层图片X位置
     * @param bgY
     *        底层图片Y位置
     * @param upX
     *        顶层图片X位置
     * @param upY
     *        底层图片Y位置
     * @return
     */
    public static boolean createStackCodePic(String fileName, File bgFile, File upFile, int bgWidth, int bgHeight, int upWidth,
            int upHeight, int bgX, int bgY, int upX, int upY)
    {
        try
        {
            // 计算顶层图片位置
            upX = bgWidth - upWidth;
            upY = 0;

            // 生成目标文件
            BufferedImage image = new BufferedImage(bgWidth, bgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            // 添加背景图片
            image = g2d.getDeviceConfiguration().createCompatibleImage((int) (bgWidth + upWidth / 2), (int) (bgHeight + upHeight / 2),
                    Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = image.createGraphics();
            g2d.setColor(new Color(255, 255, 255));
            g2d.setStroke(new BasicStroke(1));
            g2d.drawImage(image, bgX, bgY, (int) (bgWidth + upWidth / 2), (int) (bgHeight + upHeight / 2), null);

            // 添加第一层图片
            Image bgImg = null;
            if (new File(bgFile.getPath()).exists())
            {
                bgImg = ImageIO.read(bgFile);
            }
            g2d.drawImage(bgImg, bgX, bgY, bgWidth, bgHeight, null);

            // 添加第二层图片
            Image upImg = null;
            if (new File(upFile.getPath()).exists())
            {
                upImg = ImageIO.read(upFile);
            }
            g2d.drawImage(upImg, upX + (int) (upWidth / 2), bgHeight - (int) (upWidth / 2), upWidth, upHeight, null);

            g2d.dispose();

            // 保存文件
            FileUtils.forceMkdir(new File(FilenameUtils.getFullPathNoEndSeparator(new File(fileName).getPath())));
            ImageIO.write(image, "png", new File(fileName));

            return true;
        }
        catch (IOException e)
        {
            log.error("create png file error", e);
        }

        return false;
    }

    /**
     * @param targetFileName
     *        保存文件名称
     * @param bgFile
     *        底层图片
     * @param upFile
     *        顶层图片
     * @param targetWidth
     *        目标图片宽
     * @param targetHeight
     *        目标图片高
     * @return
     */
    public static boolean createStackCodePic(String targetFileName, File bgFile, File upFile, int targetWidth, int targetHeight)
    {
        try
        {
            // 生成目标文件
            BufferedImage image = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(new Color(255, 255, 255));

            // 读取图片
            if (bgFile.exists() && upFile.exists())
            {
                BufferedImage shopPic = ImageIO.read(bgFile);
                BufferedImage typePic = ImageIO.read(upFile);
                if (shopPic != null)
                {
                    int typeWidth = 0;
                    int typeHeight = 0;
                    if (typePic != null)
                    {
                        typeWidth = typePic.getWidth();
                        typeHeight = typePic.getHeight();
                    }

                    g2d.drawImage(shopPic, 0, 0, targetWidth, targetHeight, null);
                    if (typePic != null)
                    {
                        g2d.drawImage(typePic, targetWidth - typeWidth, targetHeight - typeHeight, typeWidth, typeHeight, null);
                    }
                    g2d.dispose();
                    // 保存文件
                    new File(targetFileName).getParentFile().mkdirs();
                    ImageIO.write(image, "png", new File(targetFileName));
                    return true;
                }
            }
        }
        catch (IOException e)
        {
            log.error("create png file error", e);
        }
        return false;
    }

    /**
     * @param targetFileName
     *        保存文件名称
     * @param bgFile
     *        底层图片
     * @param upFile
     *        顶层图片
     * @param targetWidth
     *        目标图片宽
     * @param targetHeight
     *        目标图片高
     * @return
     */
    public static boolean conertImageFile(String targetFileName, File imageFile)
    {
        try
        {
            // 读取图片
            if (imageFile.exists())
            {
                BufferedImage image = ImageIO.read(imageFile);
                if (image != null)
                {
                    // 生成目标文件
                    BufferedImage targetImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2d = targetImage.createGraphics();
                    g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                    g2d.dispose();
                    // 保存文件
                    new File(targetFileName).getParentFile().mkdirs();
                    ImageIO.write(targetImage, "png", new File(targetFileName));
                    return true;
                }
            }
        }
        catch (IOException e)
        {
            log.error("create png file error", e);
        }
        return false;
    }

    public static int[] getWidthAndHeight(InputStream input) throws IOException
    {
        int[] result = new int[2];
        BufferedImage buff = ImageIO.read(input);
        result[0] = buff.getWidth();
        result[0] = buff.getHeight();
        return result;
    }

    public static int[] getWidthAndHeight(File file) throws IOException
    {
        FileInputStream input = null;
        try
        {
            input = FileUtils.openInputStream(file);
            return getWidthAndHeight(input);
        }
        finally
        {
            IOUtils.closeQuietly(input);
        }

    }

    public static String getFormatName(Object o)
    {
        try
        {
            // Create an image input stream on the image
            ImageInputStream iis = ImageIO.createImageInputStream(o);
            // Find all image readers that recognize the image format
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext())
            {
                // No readers found
                return null;
            }

            // Use the first reader
            ImageReader reader = iter.next();
            // Close stream
            iis.close();
            // System.out.println(reader.getWidth(0));

            // Return the format name
            return reader.getFormatName();
        }
        catch (IOException e)
        {
            //
        }
        // The image could not be read
        return null;
    }
}
