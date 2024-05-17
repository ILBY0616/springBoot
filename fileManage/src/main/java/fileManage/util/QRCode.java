package fileManage.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

public class QRCode {

    /**
     * 生成二维码并将其写入HttpServletResponse
     *
     * @param response        HttpServletResponse 用于输出二维码图像
     * @param width           二维码的宽度
     * @param height          二维码的高度
     * @param margin          二维码的边距
     * @param level           二维码的错误校正级别
     * @param format          输出图像的格式
     * @param content         二维码的内容
     * @param foregroundColor 前景色
     * @param backgroundColor 背景色
     * @param image           要嵌入到二维码中的图像
     * @throws IOException 如果生成二维码或写入响应时发生错误
     */
    public static void generateQRCode(HttpServletResponse response, int width, int height, int margin, String level, String format, String content, Color foregroundColor, Color backgroundColor, BufferedImage image) throws IOException {
        // 检查输入参数是否合法
        if (response == null || width <= 0 || height <= 0 || margin < 0 || level == null || format == null || content == null || foregroundColor == null || backgroundColor == null) {
            throw new IllegalArgumentException("Invalid arguments provided");
        }

        ServletOutputStream sos = null;
        try {
            // 创建 QRCodeWriter 实例
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // 设置二维码的参数
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.valueOf(level));
            hints.put(EncodeHintType.MARGIN, margin);

            // 生成二维码的 BitMatrix
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            // 将 BitMatrix 转换为 BufferedImage
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, new MatrixToImageConfig(foregroundColor.getRGB(), backgroundColor.getRGB()));

            if (image != null) {
                // 获取二维码和嵌入图像的尺寸
                int qrWidth = qrImage.getWidth();
                int qrHeight = qrImage.getHeight();
                int imageWidth = qrWidth / 5; // 嵌入图像的大小占二维码大小的1/5
                int imageHeight = qrHeight / 5;

                // 缩放嵌入图像到合适的大小
                BufferedImage scaledImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = scaledImage.createGraphics();
                g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
                g.dispose();

                // 在二维码中心绘制嵌入图像
                Graphics2D combinedGraphics = qrImage.createGraphics();
                int x = (qrWidth - imageWidth) / 2;
                int y = (qrHeight - imageHeight) / 2;
                combinedGraphics.drawImage(scaledImage, x, y, null);
                combinedGraphics.dispose();
            }

            // 将生成的二维码图像写入响应输出流
            sos = response.getOutputStream();
            ImageIO.write(qrImage, format, sos);

        } catch (com.google.zxing.WriterException e) {
            throw new IOException("Error generating QR code", e);
        } finally {
            // 确保输出流被关闭
            if (sos != null) {
                sos.flush();
                sos.close();
            }
        }
    }
}
