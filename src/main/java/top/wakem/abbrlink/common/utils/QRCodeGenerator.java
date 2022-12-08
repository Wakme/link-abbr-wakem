package top.wakem.abbrlink.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Hashtable;

import static org.apache.catalina.manager.Constants.CHARSET;

public class QRCodeGenerator {
    private static final String PNG = "PNG";

    public static void main(String[] args) throws IOException, WriterException {
        String text = "https://ma3you.com/";
        int width = 300;
        int height = 300;
        String path = "./test";

        System.out.println(getQRCode2Base64(text, width, height));
    }

    public static boolean writeQRCode2Path(String content, int width, int height, Path path) {
        try {
            BitMatrix QRCode = getNormalQRCode(content, width, height);
            MatrixToImageWriter.writeToPath(QRCode, PNG, path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getQRCode2Base64(String content, int width, int height) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BitMatrix QRCode = getNormalQRCode(content, width, height);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(QRCode);
            ImageIO.write(bufferedImage, PNG, outputStream);

            return "data:image/png;base64," + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            return "";
        }
    }

    private static BitMatrix getNormalQRCode(String content, int width, int height) throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
        //调用去除白边方法
        return deleteWhite(bitMatrix);
    }


    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2];
        int resHeight = rec[3];

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }

}
