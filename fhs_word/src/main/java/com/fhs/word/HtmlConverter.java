
package com.fhs.word;

import com.fhs.common.utils.Logger;
import com.spire.doc.BookmarkEnd;
import com.spire.doc.BookmarkStart;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.BookmarksNavigator;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextSelection;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class HtmlConverter {
    private static Logger log = Logger.getLogger(HtmlConverter.class);


    /**
     * 生成文件
     * @param content html文件内容 模板文件
     * @param outputFile doc文件路径   输出文件路径名称
     */
    public static void generate(String content, File outputFile)
    {
        InputStream templateStream = null;
        try
        {
            Document doc = Jsoup.parse(content);
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
            String htmlContent = doc.toString();
            htmlContent = htmlContent.replace("<head>","<head> <meta charset='UTF-8'/>");
            htmlContent = htmlContent.replaceAll("&nbsp;"," ");
            System.out.println(htmlContent);
            // Load the XHTML document.
            wordMLPackage.getMainDocumentPart().getContent().addAll(XHTMLImporter.convert(htmlContent,""));

            // Save it as a DOCX document on disc.
            wordMLPackage.save(outputFile);
            // Desktop.getDesktop().open(outputFile);

        }
        catch (Exception e)
        {
            //throw new RuntimeException("Error converting file " + content, e);
            e.printStackTrace();
        }
        finally
        {
            if (templateStream != null)
            {
                try
                {
                    templateStream.close();
                }
                catch (Exception ex)
                {
                    log.error("Can not close the input stream.", ex);

                }
            }
        }
    }
    /**
     * 指定位置添加书签
     * @param imgMap  图片map  key名称   value 路径
     * @param tempFilePath  插入图片文本路径
     */

    public static void addBookmark(Map<String, String> imgMap, String tempFilePath) {
        //加载文档
        com.spire.doc.Document doc = new com.spire.doc.Document();
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

    /**
     * 指定位置插入图片
     * @param tempFilePath   插入图片文本路径
     * @param imgMap  图片map  key名称   value 路径
     * @param outFilePath   最终输出文件路径
     */
    public static void InsertPicture(String tempFilePath, Map<String, String> imgMap, String outFilePath) {
        //加载包含书签的文档
        com.spire.doc.Document doc1 = new com.spire.doc.Document();
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
        doc1.saveToFile(outFilePath, FileFormat.Doc);
        doc1.dispose();
    }

}