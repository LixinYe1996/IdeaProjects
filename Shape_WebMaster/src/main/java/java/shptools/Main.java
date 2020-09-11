package java.shptools;


import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ShpTools shpTools=new ShpTools();
//        String filePath="G:\\Java_Projects\\webapp\\data\\无人机应急面.shp";
//
//
//
//        ArrayList<List<SimpleFeature>> data = new ArrayList<>();
//        data =shpTools.getFeaturesList(filePath);
//        SimpleFeatureSource featureSource=shpTools.getFeatureSource();
//
//        SimpleFeatureCollection sfc= (SimpleFeatureCollection) featureSource.getFeatures();
//        List<SimpleFeature> featureList = new LinkedList<SimpleFeature>();
//        SimpleFeatureIterator sfIter =sfc.features();
//        while(sfIter.hasNext()){
//            SimpleFeature feature = sfIter.next();
//            featureList.add(feature);
//        }
//        sfIter.close();
//
//        List<Document> doc= shpTools.parseFeature2doc(featureList);
//        System.out.println(doc.size());
//        List<Document> outdoc=new LinkedList<Document>() ;;
//        for (int i = 0; i <doc.size()-2000; i++) {
//            outdoc.add(doc.get(2000+i));
//        }
//        System.out.println(outdoc.size());
//        shpTools.parsedoc2shp(outdoc,"无人机应急面3.shp");

        //      String shpFileout="G:\\Java_Projects\\ShpToolsWebApp\\src\\data\\1.shp";

//                String shpFileout="G:\\Java_Projects\\ShpToolsWebApp\\src\\data\\2.shp";

//                String shpFileout="G:\\Java_Projects\\ShpToolsWebApp\\src\\data\\3.shp";


//                shpTools.parsedoc2shp(doc, shpFileout);

//        String filename="1.zip";
//        shpTools.download("ddddsdds",filename);


//        String filenameup="test.zip";
//        shpTools.upload(filenameup);


//        shpTools.downloadshp("test");

//          shpTools.zipshpFiles("无人机应急面3");
//          shpTools.uploadshp("无人机应急面3");


//          shpTools.downloadshp("1");
//          shpTools.unzipshpFiles("1");

//        List<String> shpnamelist=new LinkedList<>();
//        shpnamelist.add("省界_region");
////        shpnamelist.add("无人机应急线");
//////        shpnamelist.add("3");
//        String CQL="NAME='台湾'";


        List<String> shpnamelist=new LinkedList<>();
        shpnamelist.add("无人机应急面1");
        shpnamelist.add("无人机应急面2");
        shpnamelist.add("无人机应急面3");
        String CQL="Shape_Area>'1000' AND Shape_Leng>100";

//        String CQL="\"土地利用\"='房屋'";
//        String CQL="\"土地利用\"='房屋'";
        System.out.println(shpnamelist);
//
        shpTools.ShpPocess(shpnamelist,CQL,"new无人机应急面");
//          shpTools.downloadshp("test");
//          shpTools.unzipshpFiles("test");





  }
}
