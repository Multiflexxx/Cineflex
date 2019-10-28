package qr_code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

//https://www.callicoder.com/generate-qr-code-in-java-using-zxing/
public class QrCodeGenerator {
    private static final String QR_CODE_IMAGE_PATH = "../../../GitProjekte/Cineflex/web/img/qrcode/MyQRCode.png;";  //"web/img/qrcode/MyQRCode.png";

    public static void generateQRCodeImage(String text, String filepath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(EncryptQr.encrypt(text, "9S25TB8TBP7PWW6T2XGQ6BM8DZBLYACS"), BarcodeFormat.QR_CODE, 300, 300);

        //Path path = FileSystems.getDefault().getPath(filepath);
        Path path = FileSystems.getDefault().getPath(filepath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage("Test", "web/img/qrcode/MyQRCode.png");
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }
}
