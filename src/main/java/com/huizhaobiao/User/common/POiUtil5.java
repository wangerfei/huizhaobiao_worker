package com.huizhaobiao.User.common;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.huizhaobiao.User.pojo.ParentData;
import com.huizhaobiao.User.pojo.SegmentData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2019/7/10.
 */
public class POiUtil5 {

    public static  boolean creatPOI(String sonInFilePath, String parentFilePath,String outFilePath, List<SegmentData> pbtableDatas, ParentData segmentData)
    {

        if (sonInFilePath.isEmpty()||parentFilePath.isEmpty()||outFilePath.isEmpty())
        {

            return  false;
        }

        DocxRenderData segment = new DocxRenderData(new File(sonInFilePath), pbtableDatas );
        segmentData.setSegment(segment);

        XWPFTemplate template = XWPFTemplate.compile(parentFilePath).render(segmentData);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outFilePath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
