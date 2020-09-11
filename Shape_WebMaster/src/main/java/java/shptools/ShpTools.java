package java.shptools;

import com.vividsolutions.jts.geom.*;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bson.Document;
import org.geotools.data.DataStoreFactorySpi;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.filter.text.cql2.CQLException;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class ShpTools {
    private SimpleFeatureSource featureSource_ISO;
    private SimpleFeatureSource featureSource_GBK;
    private static final int  BUFFER_SIZE = 2 * 1024;
    private String Type;
    private String properties;


//    private static String path=System.getProperty("user.dir").toString()+"\\data\\";
//    private static String path="/usr/local/apache-tomcat-8.5.47/webapps/data/";
//    private static  String path="G:\Java_Projects\\webapp\\src\\data\\";
    private  static String path="G:\\Java_Projects\\webapp\\data\\";

    static {
        File file=new File(path);
        System.out.println(path);
        if(!file.exists()){
            file.mkdir();
        }
//        deleteAnyone(path);
    }

    public  SimpleFeatureSource getFeatureSource(){
        return this.featureSource_GBK;
    }

    public  ArrayList<List<SimpleFeature>> getFeaturesList(String filePath){
        ArrayList<List<SimpleFeature>> modelList = new ArrayList<>();
        File folder = new File(filePath);
        if (!folder.isDirectory()) {
            if (folder.toString().endsWith(".shp")) {
                try {
                    modelList.add( getFeatureList(folder.toString()));
                    return modelList;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("shp");
            }
        }else{
            File[] files = folder.listFiles();
            if (!(files.length > 0)) {
                System.out.println("error");
                return modelList;
            }

            for (File file : files) {
                if (!file.toString().endsWith(".shp")) {
                    continue;
                }
                try {
                    List<SimpleFeature> models = getFeatureList(file.toString());
                    modelList.add(models);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return modelList;
    }


    public  List<SimpleFeature> getFeatureList(String shpFile){
        List<SimpleFeature> featureList = new LinkedList<SimpleFeature>();
        try{
            File shapeFile = new File(shpFile);
            ShapefileDataStore store = new ShapefileDataStore(shapeFile.toURI().toURL());
            ShapefileDataStore store2 = new ShapefileDataStore(shapeFile.toURI().toURL());
            featureSource_ISO=store.getFeatureSource();
//            System.out.println(store.getCharset());
//            store.setCharset(Charset.forName("GBK"));
            store2.setCharset(Charset.forName("GBK"));

            System.out.println("GBK");
            featureSource_GBK=store2.getFeatureSource();
//            System.out.println(store.getCharset());

            SimpleFeatureSource sfSource = store.getFeatureSource();
            SimpleFeatureIterator sfIter = sfSource.getFeatures().features();
            while(sfIter.hasNext()){
                SimpleFeature feature = sfIter.next();
                featureList.add(feature);
            }
            sfIter.close();
            store.dispose();
        }catch (IOException e){
            e.printStackTrace();
        }
        return featureList;
    }

    public ArrayList<List<Document>> getparseFeature2doc(ArrayList<List<SimpleFeature>> simpleFeaturelist ){
        ArrayList<List<Document>> docList=new ArrayList<>();
        for (int i = 0; i <simpleFeaturelist.size() ; i++) {
            List<Document> documents= new LinkedList<Document>() ;
            documents=parseFeature2doc(simpleFeaturelist.get(i));
            docList.add(documents);
        }
        return docList;
    }

    public List<Document> parseFeature2doc(List<SimpleFeature> SimpleFeature){
        List<Document> documents= new LinkedList<Document>() ;
        for (int j = 0; j < SimpleFeature.size(); j++) {
            documents.add(parseFeature2doc(SimpleFeature.get(j)));
        }
        return  documents;
    }

    public  Document parseFeature2doc(SimpleFeature simpleFeature){
        Document document = new Document();
        String typeName = simpleFeature.getFeatureType().getTypeName();

        CoordinateReferenceSystem coordinateReferenceSystem = simpleFeature.getBounds().getCoordinateReferenceSystem();
        FeatureJSON featureJSON = new FeatureJSON();
        StringWriter writer = new StringWriter();
        featureJSON.setEncodeNullValues(true);
        try{
            featureJSON.writeFeature(simpleFeature, writer);
        }catch (IOException e){
            e.printStackTrace();
        }

        Map<String, Object> FeaturesKeymap = new HashMap<String, Object>();
        Iterator<? extends Property> iterator1= simpleFeature.getValue().iterator();
        while (iterator1.hasNext()) {
            Property property = iterator1.next();
            FeaturesKeymap.put(property.getName().toString(),property.getValue().toString());
//            System.out.println(property.getName().toString()+property.getValue().toString());
        }
        String featureStr = writer.toString();
        document.append("typename", typeName);
        if(coordinateReferenceSystem==null){
            document.append("crs",null);
        }else
        { document.append("crs", coordinateReferenceSystem.toString());}
        document.append("features", featureStr);
        JSONObject jsonObj = JSONObject.fromObject(FeaturesKeymap);//???json??
        document.append("properties",jsonObj.toString());
//        System.out.println(typeName);
////        System.out.println(coordinateReferenceSystem.toString());
//        System.out.println(featureStr);
//        System.out.println(properties);

        return document;
    }

    public  void parsedoc2shp(List<Document> documentList, String shpFile) throws FactoryException{
        if (documentList.size() == 0){
            return;
        }
//        String strType;
        Document document = documentList.get(0);
        String strFeature = document.getString("features");
        Document doc = Document.parse(strFeature);
//        strType = doc.getString("type");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        DataStoreFactorySpi factory = new ShapefileDataStoreFactory();
        try{
            params.put("url", new File(shpFile).toURI().toURL());
            params.put("create spatial index", Boolean.TRUE);
            SimpleFeatureType schema = featureSource_ISO.getSchema();
//            System.out.println(schema.getAttributeDescriptors().toString());
            ShapefileDataStore ds=(ShapefileDataStore)factory.createNewDataStore(params);
//            Charset charset=Charset.forName("ISO-8859-1");
//            ds.setCharset(Charset.forName("GBK"));
            ds.createSchema(schema);

            FeatureWriter<SimpleFeatureType, SimpleFeature> writer =
                    ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);
            for(Document document2 : documentList){
//                Document document3 = Document.parse(document2.getString("features"));
                SimpleFeature feature = writer.next();
                String strPro1 = document2.getString("properties");
                Document tdoc = Document.parse(strPro1);
                Set<String> set1 = tdoc.keySet();
                for (String str : set1){

                    String strAttrkey = new String(str.toString().getBytes("GBK"), "ISO-8859-1");
                    String strAttrvalue=new String(tdoc.getString(str).toString().getBytes("GBK"), "ISO-8859-1");
//                    System.out.println(str.toString()+tdoc.toString());
//                    System.out.println(strAttrkey+strAttrvalue);
                    feature.setAttribute(strAttrkey, strAttrvalue);
                }
                writer.write();
            }
            //i ++;
//            FeatureJSON featureJSON = new FeatureJSON();
//            SimpleFeature simpleFeature = featureJSON.readFeature(bson);
//            FileDataStore store = FileDataStoreFinder.getDataStore(new File(shpFile));
            //SimpleFeatureSource featureSource = store.getFeatureSource();
            writer.close();
            ds.dispose();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassCastException c){
            c.printStackTrace();
        }

    }


    public List<SimpleFeature> Selectbyattribute(String shpname,String cql) throws IOException, CQLException {
        ArrayList<List<SimpleFeature>> data = new ArrayList<>();
        String filePath=path+shpname+".shp";
        data = getFeaturesList(filePath);
        SimpleFeatureSource featureSource=getFeatureSource();
        String str=cql;
        Filter filter =  GeotoolsFilterUtil.whereclauseToFilter(str);
        System.out.println(filter.toString());
        SimpleFeatureCollection sfc= (SimpleFeatureCollection) featureSource.getFeatures(filter);
        List<SimpleFeature> featureList = new LinkedList<SimpleFeature>();
        SimpleFeatureIterator sfIter =sfc.features();
        while(sfIter.hasNext()){
            SimpleFeature feature = sfIter.next();
            featureList.add(feature);
        }
        sfIter.close();
        return featureList;
    }

    public String ShpPocess(List<String> shpnamelist, String cql, String shpname) throws Exception {

        for (int i = 0; i < shpnamelist.size(); i++) {

            downloadshp(shpnamelist.get(i));
            unzipshpFiles(shpnamelist.get(i));
        }
        List<SimpleFeature> simpleFeatureList = new LinkedList<>();
        for (int i = 0; i < shpnamelist.size(); i++) {
            simpleFeatureList.addAll(Selectbyattribute(shpnamelist.get(i), cql));
        }
        List<Document> doc = parseFeature2doc(simpleFeatureList);
        String shpFileout = path + shpname + ".shp";
        parsedoc2shp(doc, shpFileout);
        zipshpFiles(shpname);
//        uploadshp(shpname);
        shape2png(shpname);
//        uploadpng(shpname);
        return path+shpname+".jpg";
    }


    public void downloadshp(String shpname) throws Exception{
        PreparedStatement preparedStatement = null;
        InputStream inputStream= null;
        OutputStream outputStream = null;
        try {
            Connection connection = DataConnection.getDataConnection();
            preparedStatement = null;

            preparedStatement = connection.prepareStatement("select WJNR,WJMC FROM SHPDATA WHERE ID='" + shpname+ "'");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                throw new Exception("??????SHPDATA?§Ó??????ID?????");
            }
            resultSet.next();
            String name = resultSet.getString("WJMC");
            Blob blob = resultSet.getBlob("WJNR");
            inputStream = blob.getBinaryStream();
            outputStream = new FileOutputStream(path+name);

            byte[] buffer = new byte[2048];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (inputStream != null){
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public  void getTypeAndproperties(String shpname) throws IOException {
        String filepath=path+shpname+".shp";
        File shapeFile = new File(filepath);
        ShapefileDataStore store = new ShapefileDataStore(shapeFile.toURI().toURL());
        SimpleFeatureSource sfSource = store.getFeatureSource();
        SimpleFeatureIterator sfIter = sfSource.getFeatures().features();
        SimpleFeature feature = sfIter.next();
        Type=feature.getFeatureType().getTypeName();
        sfIter.close();
        store.dispose();

        List<SimpleFeature> simpleFeatures=getFeatureList(filepath);
        Map<String, Object> FeaturesKeymap = new HashMap<String, Object>();
        Iterator<? extends Property> iterator1= simpleFeatures.get(0).getValue().iterator();
        while (iterator1.hasNext()) {
            Property property = iterator1.next();
            String key = new String(property.getName().toString().getBytes("ISO-8859-1"), "GBK");
            String value=new String(property.getValue().toString().getBytes("ISO-8859-1"), "GBK");
            FeaturesKeymap.put(key ,value);
//            System.out.println(property.getName().toString()+property.getValue().toString());
        }
        properties=FeaturesKeymap.keySet().toString();
    }

    public void uploadshp(String shpname) throws Exception{
        getTypeAndproperties(shpname);
        PreparedStatement preparedStatement = null;
        try {
            String id=null;
            ByteArrayOutputStream buffer = null;
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();
            diskFactory.setSizeThreshold(10 * 1024);
            ServletFileUpload upload = new ServletFileUpload(diskFactory);
            upload.setHeaderEncoding("UTF-8");
            upload.setSizeMax(30 * 1024 * 1024);
            String filepath=path+shpname+".zip";
            id=shpname;
            shpname=shpname+".zip";

//            System.out.println(filepath);
//            System.out.println(shpname);
//            System.out.println(id);

            buffer = processUploadFile(filepath);

            if (id == null || id.equals("")) {
                throw new Exception("ID???????");
            }
            if (buffer == null) {
                throw new Exception("???????????????");
            }

            Connection dataConnection = DataConnection.getDataConnection();
            String sql = "insert into SHPDATA (ID, WJNR, WJMC,FeatureType,Properties) values(?,?,?,?,?)";
            Blob blob = dataConnection.createBlob();
            blob.setBytes(1, buffer.toByteArray());
            preparedStatement = dataConnection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setBlob(2, blob);
            preparedStatement.setString(3, shpname);
            preparedStatement.setString(4,this.Type);
            preparedStatement.setString(5,this.properties);
            preparedStatement.executeUpdate();
//            System.out.println(this.Type+this.properties);
        }catch (Exception e){
            throw e;
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        };

    }
    public void uploadpng(String shpname) throws Exception{
        getTypeAndproperties(shpname);
        PreparedStatement preparedStatement = null;
        try {
            String id=null;
            ByteArrayOutputStream buffer = null;
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();
            diskFactory.setSizeThreshold(10 * 1024);
            ServletFileUpload upload = new ServletFileUpload(diskFactory);
            upload.setHeaderEncoding("UTF-8");

            upload.setSizeMax(30 * 1024 * 1024);
            String filepath=path+shpname+".jpg";
            id=shpname+".jpg";
            shpname=shpname+".jpg";

//            System.out.println(filepath);
//            System.out.println(shpname);
//            System.out.println(id);

            buffer = processUploadFile(filepath);

            if (id == null || id.equals("")) {
                throw new Exception("error");
            }
            if (buffer == null) {
                throw new Exception("error");
            }

            Connection dataConnection = DataConnection.getDataConnection();
            String sql = "insert into SHPDATA (ID, WJNR, WJMC,FeatureType,Properties) values(?,?,?,?,?)";
            Blob blob = dataConnection.createBlob();
            blob.setBytes(1, buffer.toByteArray());
            preparedStatement = dataConnection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setBlob(2, blob);
            preparedStatement.setString(3, shpname);
            preparedStatement.setString(4,null);
            preparedStatement.setString(5,null);
            preparedStatement.executeUpdate();
//            System.out.println(this.Type+this.properties);
        }catch (Exception e){
            throw e;
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        };

    }

    public ByteArrayOutputStream processUploadFile(String filePath) throws Exception{

//        String filePath = req.getServletContext().getRealPath("upload");
//        filename= item.getName();
        File uploadFile = new File(filePath + File.separator);
        FileInputStream fis = new FileInputStream(uploadFile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
//        String docType = filename.substring(filename.lastIndexOf(".") + 1);
//        uploadFile.delete();
        fis.close();
        bos.close();

        return bos;
    }

    public  void zipshpFiles(String shpname) {
        String shp=path+shpname+".shp";
        String shx=path+shpname+".shx";
        String dbf=path+shpname+".dbf";
        String prj=path+shpname+".prj";
        String zip=path+shpname+".zip";
        File[] srcFiles = { new File(shp), new File(shx), new File(dbf),new File(prj)};
        File zipFile = new File(zip);
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;

        try {

            fileOutputStream = new FileOutputStream(zipFile);
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            ZipEntry zipEntry = null;

            for (int i = 0; i < srcFiles.length; i++) {
                fileInputStream = new FileInputStream(srcFiles[i]);
                zipEntry = new ZipEntry(srcFiles[i].getName());
                zipOutputStream.putNextEntry(zipEntry);
                int len;

                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void unzipshpFiles(String shpname) throws RuntimeException {
            String srcpath=path+shpname+".zip";
//            System.out.println(srcpath);
            File srcFile = new File(srcpath);
            long start = System.currentTimeMillis();

            if (!srcFile.exists()) {
                throw new RuntimeException(srcFile.getPath() + "????????????");
            }

            ZipFile zipFile = null;
            try {
                zipFile = new ZipFile(srcFile);
                Enumeration<?> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = (ZipEntry) entries.nextElement();

                    if (entry.isDirectory()) {
                        String dirPath = path + "/" + entry.getName();
//                        String dirPath = downpath+ "/" ;
                        File dir = new File(dirPath);
                        dir.mkdirs();
                    } else {

                        File targetFile = new File(path + "/" + entry.getName());
                        targetFile.createNewFile();
                        InputStream is = zipFile.getInputStream(entry);
                        FileOutputStream fos = new FileOutputStream(targetFile);
                        int len;
                        byte[] buf = new byte[BUFFER_SIZE];
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                        fos.close();
                        is.close();
                    }
                }

                long end = System.currentTimeMillis();
            } catch (Exception e) {

                throw new RuntimeException("unzip error from ZipUtils", e);

            } finally {

                if(zipFile != null){
                    try {
                        zipFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public static boolean deleteAnyone(String FileName){
        File file = new File(FileName);
        if ( !file.exists() ){

            return false;
        }else{
            if ( file.isFile() ){
                return deleteFile(FileName);
            }else{
                return deleteDir(FileName);
            }
        }
    }

    public static boolean deleteFile(String fileName){
        File file = new File(fileName);

        if (  file.exists() && file.isFile() ){

            if ( file.delete()){

                return true;
            }else{
                return false;
            }
        }else{

            return false;
        }
    }

    public static boolean deleteDir(String dirName){

        if ( dirName.endsWith(File.separator) )
            dirName = dirName + File.separator;

        File file = new File(dirName);

        if ( !file.exists() || ( !file.isDirectory() ) ){
            return false;
        }
        File[] fileArrays = file.listFiles();
        for ( int i = 0 ; i < fileArrays.length ; i++ ){

            deleteAnyone(fileArrays[i].getAbsolutePath());
        }
        return true;
    }
    public void shape2png(String shpname){
        Map paras = new HashMap();
        double[] bbox = new double[]{1035474.697000,3434604.859900,1051816.279800,3456772.785900};
        paras.put("bbox", bbox);
        paras.put("width", 900);
        paras.put("height", 900);
        shape2image shp2img = new shape2image();
        String shpPath=path+shpname+".shp";
        String imgPath=path+shpname+".jpg";
        shp2img.addShapeLayer(shpPath);
        shp2img.getMapContent(paras, imgPath);
    }
}



