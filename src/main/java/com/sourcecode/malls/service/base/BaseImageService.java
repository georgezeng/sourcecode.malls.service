package com.sourcecode.malls.service.base;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sourcecode.malls.service.FileOnlineSystemService;

public abstract class BaseImageService {
	@Autowired
	protected FileOnlineSystemService fileService;
	
	public BufferedImage generateQRCodeImage(String text, int width, int height, int margin, String logoPath)
			throws Exception {
		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.MARGIN, margin);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
		BufferedImage qrCode = MatrixToImageWriter.toBufferedImage(bitMatrix);
		if (!StringUtils.isEmpty(logoPath)) {
			BufferedImage logo = ImageIO.read(new ByteArrayInputStream(fileService.load(true, logoPath)));
			// Calculate the delta height and width
			int deltaHeight = height - logo.getHeight();
			int deltaWidth = width - logo.getWidth();
			BufferedImage combined = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) combined.getGraphics();
			g.drawImage(qrCode, 0, 0, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.drawImage(logo, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
			return combined;
		}
		return qrCode;
	}
}
