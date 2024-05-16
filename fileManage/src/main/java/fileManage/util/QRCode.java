package fileManage.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

public class QRCode {
    public static void createQRCode(HttpServletResponse response, int width, int height,int margin ,String level,String format,String content) throws IOException {
        ServletOutputStream sos = null;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.valueOf(level));
            hashtable.put(EncodeHintType.MARGIN, margin);
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE,height,width,hashtable);
            sos = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,format,sos);
        }catch (WriterException e){
            System.out.println("Error:" + e.getMessage());
        }finally {
            if (sos != null) {
                sos.flush();
                sos.close();
            }
        }
    }
}
