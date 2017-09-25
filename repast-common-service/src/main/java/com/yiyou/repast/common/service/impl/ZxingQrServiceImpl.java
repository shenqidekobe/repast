package com.yiyou.repast.common.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.yiyou.repast.common.qrcode.Base64;
import com.yiyou.repast.common.qrcode.QREntity;
import com.yiyou.repast.common.qrcode.ZxingImage;
import com.yiyou.repast.common.qrcode.ZxingUtil;
import com.yiyou.repast.common.qrcode.ZxingValidation;
import com.yiyou.repast.common.service.IZxingQrService;

@Service
public class ZxingQrServiceImpl implements IZxingQrService {

	@Override
	public String qrAsBase64(Map<String, String> params) {
		ByteArrayOutputStream out = produce(params);
		String base64 = Base64.encodeToString(out.toByteArray());
		return base64;
	}

	@Override
	public ByteArrayOutputStream qrAsByte(Map<String, String> params) {
		ByteArrayOutputStream out = produce(params);
		return out;
	}
	
	
	private ByteArrayOutputStream produce(Map<String, String> params) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		QREntity p = new QREntity();
		try {
			String d = params.get("data") == null ? "Repast" : params.get("data");
			String c = params.get("encoding") == null ? StandardCharsets.ISO_8859_1.name() : params.get("encoding");
			p.setData(URLDecoder.decode(d, c));
			p.setEncoding(c);

			String ecLevel = params.get("eclevel") == null ? "l" : params.get("eclevel");
			p.setEcLevel(ZxingUtil.parseErrorCorrectLevel(ecLevel));

			String margin = params.get("margin") == null ? "1" : params.get("margin");
			if (ZxingValidation.isNum(margin)) {
				p.setMargin(Integer.parseInt(margin));
			}
			String size = params.get("size") == null ? "200x200" : params.get("size");
			if (size.contains("x")) {
				String[] sizea = size.split("x");
				if (ZxingValidation.isNum(sizea[0]) && ZxingValidation.isNum(sizea[1])) {
					p.setWidth(Integer.parseInt(sizea[0]));
					p.setHeight(Integer.parseInt(sizea[1]));
				}
			}

			String bgc = params.get("bgcolor") == null ? "DEFAULT" : params.get("bgcolor");
			String fgc = params.get("fgcolor") == null ? "DEFAULT" : params.get("fgcolor");
			p.setBackgroundColor(ZxingValidation.isHex(bgc) ? Integer.valueOf(bgc.substring(2), 16) : ZxingImage.WHITE);
			p.setForegroundColor(ZxingValidation.isHex(fgc) ? Integer.valueOf(fgc.substring(2), 16) : ZxingImage.BLACK);
			String format = "png";
			p.setFormat(format.toLowerCase());
			BufferedImage data= ZxingUtil.encodeWithImg(p);
			ImageIO.write(data, p.getFormat().toLowerCase(), out);
		} catch (Exception e) {
		}
		return out;
	}

}
