package java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import shptools.ImgUtil;
import shptools.ShpTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        System.setProperty("java.awt.headless", "true");
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.setProperty("java.awt.headless", "true");
        request.setCharacterEncoding("utf-8");// 设置编码格式
        response.setCharacterEncoding("utf-8");// 设置编码格式
        String s=request.getParameter("s");//用getParame()方法来接收数据，括号内放要接收的东西
//        String s ="{\"Object_set\":\"1,2,3,\",\"cqlString\":\"cql查询语句1\",\"Shp_name\":8}";
        //            "Object_set":"sql",
//                "cqlString":"cql查询语句1",
//                "Shp_name":"newshpname"
        JSONObject jsonObject = JSON.parseObject(s);
        String Object_set=jsonObject.getString("Object_set");
        String cqlString=jsonObject.getString("cqlString");
        String Shp_name=jsonObject.getString("Shp_name");
        String[] strs=Object_set.split("\\,");
        List<String> shpnamelist=new LinkedList<>();
        for (int i = 0; i <strs.length ; i++) {
            shpnamelist.add(strs[i]);
        }
        System.out.println(shpnamelist);
        System.out.println(cqlString);
        System.out.println(Shp_name);
        ShpTools shpTools=new ShpTools();
        String imagePath=null;
        try {
            imagePath=shpTools.ShpPocess(shpnamelist,cqlString,Shp_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(imagePath);
//        response.s;

        if(null != imagePath && !"".equals(imagePath.trim())) {
            ImgUtil.showImage(response, imagePath, true);
        }

//        String path=System.getProperty("user.dir").toString()+"\\data\\";
//        shpTools.deleteAnyone(path);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.setProperty("java.awt.headless", "true");
        doGet(request, response);//当用post提交方式时，可以调用get方法，使用上面的方式
    }

}
