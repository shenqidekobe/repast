package com.yiyou.repast.common.qrcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.ByteMatrix;

public class ZxingImage {

    public static final int BLACK = 0x000000;
    public static final int WHITE = 0xFFFFFF;

    /*
     * Note that the input matrix uses 0 == white, 1 == black, while the output
     * matrix uses 0 == black, 255 == white (i.e. an 8 bit greyscale bitmap).
     */

    /**
     *
     * @param input Byte Matrix
     * @param img Image Entity
     * @return Image Object
     */
    public static BufferedImage toBufferedImage(ByteMatrix input, ImageEntity img) {
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        int margin = img.getMargin();
        int qrWidth = inputWidth + margin * 2;
        int qrHeight = inputHeight + margin * 2;
        int outputWidth = Math.max(img.getWidth(), qrWidth);
        int outputHeight = Math.max(img.getHeight(), qrHeight);
        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = (outputWidth - inputWidth * multiple) / 2;
        int topPadding = (outputHeight - inputHeight * multiple) / 2;

        BitMatrix output = new BitMatrix(outputWidth, outputHeight);
        int inputY = 0;
        for(int outputY = topPadding; inputY < inputHeight; outputY += multiple) {
            int inputX = 0;
            for(int outputX = leftPadding; inputX < inputWidth; outputX += multiple) {
                if(input.get(inputX, inputY) == 1) {
                    output.setRegion(outputX, outputY, multiple, multiple);
                }
                ++inputX;
            }
            ++inputY;
        }

        return toBufferedImage(output, img.getBgColor(), img.getFgColor());
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix, int bgColor, int fgColor) {
            int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                (fgColor == BLACK && bgColor == WHITE) ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? fgColor : bgColor);
            }
        }
        return image;

    }

    public static void toSVGDocument(ByteMatrix matrix, Writer out, int width, int height, int margin, int fgColor, int bgColor){
        toSVGDocument(matrix, out, new ImageEntity(width, height, margin, fgColor, bgColor));
    }

    public static void toSVGDocument(ByteMatrix matrix, Writer out, ImageEntity img){
        Color fg = new Color(img.getFgColor());
        Color bg = new Color(img.getBgColor());

        int inputWidth = matrix.getWidth();
        int inputHeight = matrix.getHeight();
        int qrWidth = inputWidth + (img.getMargin() * 2);
        int qrHeight = inputHeight + (img.getMargin() * 2);
        int outputWidth = Math.max(img.getWidth(), qrWidth);
        int outputHeight = Math.max(img.getHeight(), qrHeight);
        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        int topPadding = (outputHeight - (inputHeight * multiple)) / 2;

        SVGGraphics2D g2d = initSVGGraphics2D();

        g2d.setStroke(new BasicStroke(1.0f));
        for (int inputY = 0, outputY = topPadding; inputY < inputHeight; inputY++, outputY += multiple) {
            for (int inputX = 0, outputX = leftPadding; inputX < inputWidth; inputX++, outputX += multiple) {
                if (matrix.get(inputX, inputY) == 1){
                    if (g2d.getColor() != fg) {
                        g2d.setColor(fg);
                    }
                    g2d.fill(new Rectangle(outputX, outputY, multiple, multiple));
                } else {
                    if (g2d.getColor() != bg) {
                        g2d.setColor(bg);
                    }
                    g2d.fill(new Rectangle(outputX, outputY, multiple, multiple));
                }
            }
        }


        try{
            g2d.stream(out);
        } catch (SVGGraphics2DIOException e){
        }
    }

    public static void toSVGDocument(BitMatrix matrix, Writer out, ImageEntity img, int multiple) {
        Color fg = new Color(img.getFgColor());
        Color bg = new Color(img.getBgColor());

        SVGGraphics2D g2d = initSVGGraphics2D();

        g2d.setStroke(new BasicStroke(1.0f));
        for (int inputY = 0; inputY < img.getHeight(); inputY++) {
            for (int inputX = 0; inputX < img.getWidth(); inputX++) {
                if (matrix.get(inputX, inputY)){
                    if (g2d.getColor() != fg) {
                        g2d.setColor(fg);
                    }
                    g2d.drawRect(inputX, inputY, multiple, multiple);
                } else {
                    if (g2d.getColor() != bg) {
                        g2d.setColor(bg);
                    }
                    g2d.drawRect(inputX, inputY, multiple, multiple);
                }
            }
        }

        try{
            g2d.stream(out);
        } catch (SVGGraphics2DIOException e){
        }
    }

    private static SVGGraphics2D initSVGGraphics2D(){

        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        // Create an instance of org.w3c.dom.Document.
        Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);
        // Create an instance of the SVG Generator.
        return new SVGGraphics2D(document);
    }
}
