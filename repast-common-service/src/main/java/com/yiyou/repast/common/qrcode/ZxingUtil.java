package com.yiyou.repast.common.qrcode;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;

public class ZxingUtil {

    @SuppressWarnings("unlikely-arg-type")
	public static ByteMatrix encode(QREntity p)
            throws WriterException {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        if (!StandardCharsets.ISO_8859_1.equals(p.getEncoding())) {
            // Only set if not QR code default
            hints.put(EncodeHintType.CHARACTER_SET, p.getEncoding());
        }
        return Encoder.encode(p.getData(), p.getEcLevel(), hints).getMatrix();
    }


    public static BufferedImage encodeWithImg(QREntity p)
            throws WriterException {
        ImageEntity img = new ImageEntity(p.getHeight(), p.getWidth(), p.getMargin(), p.getForegroundColor(), p.getBackgroundColor());
        return ZxingImage.toBufferedImage(encode(p), img);
    }

    public static ErrorCorrectionLevel parseErrorCorrectLevel(String ecl) {
    	if ("L".equals(ecl) || "l".equals(ecl) ) {
    		return ErrorCorrectionLevel.L;
    	} else if ("Q".equals(ecl) || "q".equals(ecl) ) {
    		return ErrorCorrectionLevel.Q;
    	} else if ("M".equals(ecl) || "m".equals(ecl) ) {
	    	return ErrorCorrectionLevel.M;
    	} else if ("H".equals(ecl) || "h".equals(ecl) ) {
    		return ErrorCorrectionLevel.H;
    	} else {
    		throw new IllegalArgumentException("invalid error correct level : " + ecl);
    	}
    }
}
