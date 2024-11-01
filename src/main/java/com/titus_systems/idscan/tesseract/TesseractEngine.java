package com.titus_systems.idscan.tesseract;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractEngine extends Tesseract{

    public TesseractEngine (){
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")){
            // datapath para windows
            this.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
            this.setLanguage("por");
            System.out.println("Conexão com Tesseract aberta com sucesso!");

        } else if (osName.contains("nux") || osName.contains("nix")){
            // datapath para linux
            this.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
            this.setLanguage("por");
            System.out.println("Conexão com Tesseract aberta com sucesso");
            
        } else {
            throw new UnsupportedOperationException("Sistema operacional não suportado.");
        }
    }


    public String extractTextFromimage(String imagePath) throws Exception{
        BufferedImage image = startPreProcessing(imagePath);

        File f = new File("MyFile.png");
        ImageIO.write(image, "PNG", f);

        try{
            System.out.println("Iniciando extração de texto com Tesseract...");
            String result = this.doOCR(f);
            // f.delete();
            System.out.println(result);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    public BufferedImage loadImage(String imagePath) {
        BufferedImage image = null;
        try {
            File file = new File(imagePath);
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
        }
        return image;
    }

    public BufferedImage startPreProcessing (String imagePath) {
        BufferedImage image = loadImage(imagePath);
        this.setPageSegMode(1);
        System.out.println("Iniciando pre processamento da imagem...");
        image = this.toGreyScale(image);
        image = this.adjustContrast(image, 1.4f);
        image = this.applyGaussianBlur(image);
        image = this.sharpenImage(image);
        // image = this.binarizeImage(image, 160);
        return image;
    } 

    public BufferedImage toGreyScale(BufferedImage original){
        System.out.println("Transformando imagem em preto e branco...");
        int width = Math.abs(original.getWidth());
        int height = Math.abs(original.getHeight());
        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = original.getRGB(x, y);
                Color c = new Color(color);
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                int grayscale = red + green + blue;
                Color newColor = new Color(grayscale, grayscale, grayscale);
                grayscaleImage.setRGB(x, y, newColor.getRGB());
            }
        }
        
        return grayscaleImage;
    }
    
    public BufferedImage adjustContrast(BufferedImage original, float contrastFactor) {
        // Um valor maior que 1 aumentará o contraste, enquanto valores entre 0 e 1 diminuirão o contraste. Por exemplo, um valor de 1.2 aumentará o contraste em 20%.
        // O bias (deslocamento) normalmente é 0, mas você pode ajustá-lo para efeitos mais refinados
        float bias = 0f;
        RescaleOp op = new RescaleOp(contrastFactor, bias, null);
        BufferedImage contrastedImage = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        op.filter(original, contrastedImage);
        return contrastedImage;
    }

    public BufferedImage binarizeImage(BufferedImage original, int threshold) {
        System.out.println("Iniciando binarização da imagem...");
        int width = Math.abs(original.getWidth());
        int height = Math.abs(original.getHeight());
        BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = original.getRGB(x, y);
                int gray = (color >> 16) & 0xff;
                if (gray < threshold) {
                    binaryImage.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    binaryImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        
        return binaryImage;
    }


    public BufferedImage applyGaussianBlur(BufferedImage original) {
        System.out.println("Aplicando remoção de ruído na imagem...");
        float[] matrix = {
            1f/16f, 2f/16f, 1f/16f,
            2f/16f, 4f/16f, 2f/16f,
            1f/16f, 2f/16f, 1f/16f
        };
        Kernel kernel = new Kernel(3, 3, matrix);
        ConvolveOp op = new ConvolveOp(kernel);
        return op.filter(original, null);
    }

    public BufferedImage sharpenImage(BufferedImage original) {
        float[] sharpenKernel = {
            0.f, -1.f,  0.f,
            -1.f,  5.f, -1.f,
            0.f, -1.f,  0.f
        };

        Kernel kernel = new Kernel(3, 3, sharpenKernel);
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        BufferedImage sharpenedImage = new BufferedImage(
            original.getWidth(), original.getHeight(), original.getType());

        convolveOp.filter(original, sharpenedImage);
        return sharpenedImage;
    }

    public BufferedImage convertTo300DPI(BufferedImage original, float dpi) {
        System.out.println("Convertendo imagem para 300 dpi...");
        int originalWidth = Math.abs(original.getWidth());
        int originalHeight = Math.abs(original.getHeight());

        if (dpi > 0 && dpi < 300) {
        } else {
            double scaleFactor = 300.0 / dpi;
    
            // Calcula a nova largura e altura
            int newWidth = (int) (originalWidth * scaleFactor);
            int newHeight = (int) (originalHeight * scaleFactor);
    
            return resizeImage(original, Math.abs(newWidth), Math.abs(newHeight));
        }
    
        return original;
    }

    public BufferedImage resizeImage(BufferedImage original, int newWidth, int newHeight) {
        System.out.println("Aumentando a resolução da imagem...");
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(original, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        return resizedImage;
    }

    public float getPngDpi(File file) throws IOException {
        System.out.println("Examinando resolução da imagem...");
        ImageInputStream input = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
        // Verifica se a imagem possui metadados
        if (readers.hasNext()) {
            ImageReader reader = readers.next();
            reader.setInput(input, true);
            IIOMetadata metadata = reader.getImageMetadata(0);
            
            if (metadata != null) {
                // O PNG geralmente armazena DPI nas tags "pHYs"
                IIOMetadataNode node = (IIOMetadataNode) metadata.getAsTree(metadata.getNativeMetadataFormatName());
                
                IIOMetadataNode physNode = (IIOMetadataNode) node.getElementsByTagName("pHYs").item(0);
                if (physNode != null) {
                    int pixelsPerUnitX = (Integer) physNode.getUserObject();
                    int pixelsPerUnitY = (Integer) physNode.getUserObject();
                    // DPI é calculado a partir da resolução em pixels por unidade (1/2.54 para converter para polegadas)
                    float dpiX = pixelsPerUnitX / 0.01f; // 1 unit = 0.01 m
                    float dpiY = pixelsPerUnitY / 0.01f; // 1 unit = 0.01 m
                    System.out.println("Imagem em " + "dpiX" + " DPI");
                    return dpiX;
                }
            }
            System.out.println("Os metadados da imagem não puderam ser acessados");
        }
        return -1;
    }
}
