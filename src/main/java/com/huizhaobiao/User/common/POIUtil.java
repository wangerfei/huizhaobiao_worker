package com.huizhaobiao.User.common;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.render.RenderAPI;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2019/7/8.
 */
public class POIUtil {

    /*
   * @pram inFile 输入模板文件
   *@pram outFile 输出文件
   *@pram Content 标签内容 （标签名称，标签内容）
   *@pram  tableContent  表内容（标签名称（行号，行内容））
   *
   * @return 成功 true 失败 false
   * */
    public  static Boolean createTable(String inFile,String outFile,
                                       HashMap<String,String> Content,
                                       Map<String,HashMap<Integer,ArrayList<String>>> tableContent)
    {

        if (inFile.isEmpty()||outFile.isEmpty())
        {
            return  false;
        }
      /*  //构造数据
        Map<String, Object> datas = new HashMap<String, Object>(){{

            Set setContent=Content.entrySet();
            Iterator objs=setContent.iterator();
            while (objs.hasNext()) {
                Map.Entry entry = (Map.Entry) objs.next();
                String entry_title = entry.getKey().toString();
                String entry_value = Content.get(entry_title);
                put(entry_title, new TextRenderData(entry_value));
            }
            Set setTable=tableContent.entrySet();
            Iterator objs_table=setTable.iterator();
            while (objs_table.hasNext())
            {
                Map.Entry entry = (Map.Entry) objs_table.next();
                put(entry.getKey().toString().replace("#",""), new TableRenderData(new ArrayList<RenderData>(){{

                    for (String tableName:tableContent.get(entry.getKey()).get(Integer.valueOf(0)))
                    {
                        add(new TextRenderData( tableName));
                    }

                }}, new ArrayList<Object>(){{

                    for (int i=1;i<tableContent.get(entry.getKey()).size();i++)
                    {
                        String rowContent="";
                        for (String content : tableContent.get(entry.getKey()).get(i)) {
                            rowContent=content+";"+rowContent;
                        }
                        rowContent= rowContent.substring(0,rowContent.length()-1);
                        add(rowContent);
                    }

                }}, "no datas", 10000));
            }
        }};*/


        Set<String> set_titel=tableContent.keySet();
        Map<String, Object> datas=null;
        for (String set_titel_t:set_titel) {
             datas = POIUtil.createTabel(Content,tableContent.get(set_titel_t) , inFile);
        }


        //读取模板，进行渲染
        XWPFTemplate doc = XWPFTemplate
                .create(inFile);
        RenderAPI.render(doc, datas);
        //输出渲染后的文件
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outFile);
            doc.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            return false;
        }
        return  true;
    }


    public static boolean create_son_table (String inFile,Map<String, Object> datas,String outFile )
    {
        //读取模板，进行渲染
        XWPFTemplate doc = XWPFTemplate
                .create(inFile);
        RenderAPI.render(doc, datas);
        //输出渲染后的文件
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outFile);
            doc.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public  static  Map<String, Object> createTabel(HashMap<String,String> Content_two,HashMap<Integer,ArrayList<String>> tableContent_two,String inSonFile)
    {



        List<String> titelAll= POIUtil3.getContent(inSonFile);

        Map<String, Object> datas = new HashMap<String, Object>(){{



   /*         put("fengye", new TextRenderData(""));*/




            //获取本文的 文本标签
            List<String> titelAll_tiem  = new ArrayList<String>(); //需换成动态的
            for (String tiem :titelAll)
            {
                if (!tiem.contains("#"))
                {
                    titelAll_tiem.add(tiem);
                }
            }

            for (String contentListKey : titelAll_tiem) {

               /*new  XWPFParagraph ();
                this.para.insertNewRun(0).addCarriageReturn();*/
                put(contentListKey, new TextRenderData(Content_two.get(contentListKey)));

            }

            Set setTable=tableContent_two.entrySet();
            Iterator objs_table=setTable.iterator();
            while (objs_table.hasNext())
            {
                Map.Entry entry = (Map.Entry) objs_table.next();

                //需换成 动态的
                String tabelAll_tiem  = ""; //需换成动态的
                for (String tiem :titelAll)
                {
                    if (tiem.contains("#"))
                    {
                        tabelAll_tiem=tiem;
                    }
                }
                //"#compare"

                put(tabelAll_tiem.replace("#",""), new TableRenderData(new ArrayList<RenderData>(){{

                    for (String tableName:tableContent_two.get(Integer.valueOf(0)))
                    {
                        add(new TextRenderData( tableName));
                    }

                }}, new ArrayList<Object>(){{

                    for (int i=1;i<tableContent_two.get(entry.getKey()).size();i++)
                    {
                        String rowContent="";
                        for (String content : tableContent_two.get(i)) {
                            rowContent=content+";"+rowContent;
                        }
                        rowContent= rowContent.substring(0,rowContent.length()-1);
                        add(rowContent);
                    }

                }}, "no datas", 10000));


            }
        }};

        return  datas;

    }



}
