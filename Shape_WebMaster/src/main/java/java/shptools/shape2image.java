package java.shptools;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class shape2image {
    private static MapContent map = new MapContent();
    private static CoordinateReferenceSystem crs=null;
    /**
     * ���shp�ļ�
     * @param shpPath
     */
    public void addShapeLayer(String shpPath){
        try{
            File file = new File(shpPath);
            ShapefileDataStore shpDataStore = null;
            shpDataStore = new ShapefileDataStore(file.toURL());
            //���ñ���
            Charset charset = Charset.forName("GBK");
            shpDataStore.setCharset(charset);
            String typeName = shpDataStore.getTypeNames()[0];

            SimpleFeatureSource featureSource = null;
            featureSource =  shpDataStore.getFeatureSource (typeName);

            crs=featureSource.getBounds().getCoordinateReferenceSystem();
            Style shpStyle = SLD.createPolygonStyle(null, null, 0.5f);
            Layer layer = new FeatureLayer(featureSource, shpStyle);
            map.addLayer(layer);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * �����������������ȡ��ͼ���ݣ�������ͼƬ
     * @param paras
     * @param imgPath
     */
    public void getMapContent(Map paras, String imgPath){
        try{
            double[] bbox = (double[]) paras.get("bbox");
            double x1 = bbox[0], y1 = bbox[1],
                    x2 = bbox[2], y2 = bbox[3];
            int width = (int) paras.get("width"),
                    height=(int) paras.get("height");

            // ���������Χ
//            CoordinateReferenceSystem crs = DefaultGeographicCRS.WGS84;
            ReferencedEnvelope mapArea = new ReferencedEnvelope(x1, x2, y1, y2, crs);
            // ��ʼ����Ⱦ��
            StreamingRenderer sr = new StreamingRenderer();
            sr.setMapContent(map);
            // ��ʼ�����ͼ��
            BufferedImage bi = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Rectangle rect = new Rectangle(0, 0, width, height);
            // ���Ƶ�ͼ
            sr.paint((Graphics2D) g, rect, mapArea);
            //��BufferedImage����д���ļ��С�
            int length=imgPath.length();
            StringBuilder img=new StringBuilder(imgPath);
            img=img.replace((length-3),length,"png");
            String pngpath=""+img;

            ImageIO.write(bi,"png",new File(pngpath));


            BufferedImage bufferedImage;
            try {
                // read image file
                bufferedImage = ImageIO.read(new File(pngpath));
                // create a blank, RGB, same width and height, and a white
                // background
                BufferedImage newBufferedImage = new BufferedImage(
                        bufferedImage.getWidth(), bufferedImage.getHeight(),
                        BufferedImage.TYPE_INT_RGB);
                // TYPE_INT_RGB:����һ��RBGͼ��24λ��ȣ��ɹ���32λͼת����24λ
                newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
                        Color.WHITE, null);
                // write to jpeg file

                ImageIO.write(newBufferedImage, "jpg", new File(imgPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * ��������Է���
     * @param args
     */
    public static void main(String[] args){
        long start = System.currentTimeMillis();

        shape2image shp2img = new shape2image();
        String shpPath = "G:\\Java_Projects\\webapp\\src\\data2\\1.shp";

        String imgPath = "G:\\Java_Projects\\webapp\\src\\data2\\1.jpg";
        Map paras = new HashMap();
        double[] bbox = new double[]{1035474.697000,3434604.859900,1051816.279800,3456772.785900};
        paras.put("bbox", bbox);
        paras.put("width", 1920);
        paras.put("height", 1080);
        shp2img.addShapeLayer(shpPath);

        shp2img.getMapContent(paras, imgPath);
        System.out.println("ͼƬ������ɣ�����ʱ"+(System.currentTimeMillis() - start)+"ms");


    }
}