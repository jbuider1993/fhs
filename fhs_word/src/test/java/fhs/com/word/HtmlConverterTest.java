

package fhs.com.word;

import com.fhs.word.HtmlConverter;
import com.fhs.common.utils.FileUtils;
import com.spire.doc.*;
import com.spire.doc.Document;
import com.spire.doc.documents.BookmarksNavigator;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextSelection;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class HtmlConverterTest {
    @Test
    public void html2wordTest() throws Exception {
        /*Document doc = Jsoup.parse(FileUtils.readTxtFile(new FileInputStream(new File("D:\\progects\\fhs-framework\\fhs_word\\src\\test\\resources\\html\\word_template.html")))
        );
        String htmlContent = doc.toString();
        htmlContent = htmlContent.replace("<head>","<head> <meta charset='UTF-8'/>");
        System.out.println(htmlContent);*/
        /**
         * 根据模板生成word
         */
        HtmlConverter.generate(FileUtils.readTxtFile(new FileInputStream(new File("D:\\progects\\fhs-framework\\fhs_word\\src\\test\\resources\\html\\words_template.html")))
                , new File("d:/1.doc"));

        //要插入的图片文件地址
        Map<String, String> imgMap = new HashMap<>();
        imgMap.put("每日收入趋势折线图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板2118.png");
        imgMap.put(" 临停收入趋势柱图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板2120.png");
        imgMap.put("会员积分优惠减免分布饼图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板2331.png");
        imgMap.put("缴费途径分析饼图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板2476.png");
        imgMap.put("每日车流分析柱图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板2647.png");
        imgMap.put("工作日及周末日均车流趋势柱图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板2808.png");
        imgMap.put("入口分布饼图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板2920.png");
        imgMap.put("出口分布饼图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板2993.png");
        imgMap.put("停放时长柱图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板3104.png");
        imgMap.put("入场时间点柱图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板3214.png");
        imgMap.put("出场时间点柱图", "C:\\Users\\亮仔\\Desktop\\月报模板.files\\月报模板3315.png");
        //要插入的图片的doc文件
        String tempFilePath = "d:/1.doc";
        //最终生成的doc文件地址
        String filePath = "d:/2.doc";
        HtmlConverter.addBookmark(imgMap, tempFilePath);
        HtmlConverter.InsertPicture(tempFilePath, imgMap, filePath);
    }

    /* *//* *
     * 指定位置添加书签
     * *//*

    private static void addBookmark(Map<String, String> imgMap, String tempFilePath) {
        //加载文档
        Document doc = new Document();
        doc.loadFromFile(tempFilePath);

        //查找指定字符串
        Set<String> set = imgMap.keySet();
        for (String key : set) {
            TextSelection textSelection = doc.findString(key, false, false);
            TextRange range = textSelection.getAsOneRange();
            Paragraph para = range.getOwnerParagraph();
            int index = para.getChildObjects().indexOf(range);

            //查找所有“指定字符串”文本
            TextSelection[] textSelections = doc.findAllString(key, false, false);

            //设置高亮颜色
            for (TextSelection selection : textSelections) {
                selection.getAsOneRange().getCharacterFormat().setTextColor(Color.red);
            }

            //添加书签
            BookmarkStart start = new BookmarkStart(doc, key);
            BookmarkEnd end = new BookmarkEnd(doc, key);
            para.getChildObjects().insert(index, start);
            para.getChildObjects().insert(index + 2, end);
        }

        //保存文档
        doc.saveToFile(tempFilePath, FileFormat.Doc);
        doc.dispose();


    }

    private static void InsertPicture(String tempFilePath, Map<String, String> imgMap, String filePath) {
        //加载包含书签的文档
        Document doc1 = new Document();
        doc1.loadFromFile(tempFilePath);
        Set<String> set = imgMap.keySet();
        for (String key : set) {
            //定位到指定书签位置起始标签位置，插入图片
            BookmarksNavigator bookmarksNavigator1 = new BookmarksNavigator(doc1);
            bookmarksNavigator1.moveToBookmark(key, true, false);
            Paragraph para1 = new Paragraph(doc1);
            DocPicture picture = para1.appendPicture(imgMap.get(key));
            picture.setWidth(500f);
            picture.setHeight(200f);
            picture.setTextWrappingStyle(TextWrappingStyle.Through);
            bookmarksNavigator1.insertParagraph(para1);
        }
        //保存文档
        doc1.saveToFile(filePath, FileFormat.Doc);
        doc1.dispose();
    }*/
}



