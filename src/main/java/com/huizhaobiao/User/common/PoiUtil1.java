package com.huizhaobiao.User.common;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.render.RenderAPI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/6.
 */
public class PoiUtil1 {

    /*
    * @pram title 图标标题
    *@pram bdname 标段（包）名称
    * @pram bdcode 标段（包）编号
    *@pram  pwhcy 全部评委签名
    * @pram tableContent 表格内容 第一行为标题
    *@pram  rowAll 表格总行数
    * @pram outFile 输出文件名称
    *
    * @return 成功 true 失败 false
    * */
    public  static Boolean createTable(String title,String bdname,String bdcode,
                               ArrayList<String> pwhcy ,HashMap<Integer,ArrayList<String>> tableContent,int rowAll,String outFile)
    {
        //构造数据
        Map<String, Object> datas = new HashMap<String, Object>(){{
            put("title", new TextRenderData(title));
            put("bdname", new TextRenderData( bdname));
            put("bdcode", new TextRenderData(bdcode));

            put("solution_compare", new TableRenderData(new ArrayList<RenderData>(){{

                for (String tableName:tableContent.get(0))
                {
                   add(new TextRenderData( tableName));
                }

            }}, new ArrayList<Object>(){{
            for (int i=1;i<rowAll;i++)
                {
                    String rowContent="";
                    for (String content : tableContent.get(i)) {
                        rowContent=content+";"+rowContent;
                    }
                    rowContent= rowContent.substring(0,rowContent.length()-1);
                    add(rowContent);
                }

            }}, "no datas", 10000));
            String assessName="";
            for(String name : pwhcy)
            {
                assessName= (name+","+assessName);
            }
            put("pwhcy", new TextRenderData( assessName ));
        }};
        //读取模板，进行渲染
        XWPFTemplate doc = XWPFTemplate
                .create("src/main/resources/pbtable.docx");
        RenderAPI.render(doc, datas);
        //输出渲染后的文件
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("src/main/resources/"+outFile+".docx");
            doc.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            return false;
        }
        return  true;
    }

}
