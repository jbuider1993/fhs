package com.fhs.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.constant.Constant;
import com.fhs.common.excel.ExcelValidor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <excel读写工具类>
 * 如果要做合并单元格或者对excel的字体，边框有要求请参考https://blog.csdn.net/shuaizai88/article/details/72723935
 * 并且将html2excel.jar安装到本地maven仓库中
 * @author wanglei
 * @version [版本号, 2013年8月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ExcelUtils
{
    // Commons Logging instance.
    private static Logger log = Logger.getLogger(ExcelUtils.class);

    /**
     * <格式化数据，用于生成excel>
     *
     * @param dataList 数据list
     * @param field 字段
     * @return 格式化后的数据
     */
    public static Object[][] formartData(List<Map<String, Object>> dataList, String[] field)
    {
        if (field == null || field.length == 0 || dataList == null)
        {
            return new Object[0][0];
        }

        Object[][] obj = new Object[dataList.size()][field.length];
        String tempFiled = null;
        String[] splitfileds = null;
        StringBuilder sbuilder = null;
        Map<String, Object> tempDataMap = null;
        int size = dataList.size();
        for (int i = 0; i < size; i++)
        {
            tempDataMap = dataList.get(i);
            for (int j = 0; j < field.length; j++)
            {
                tempFiled = field[j];
                if (tempFiled.contains(Constant.CENTER_LINE))
                {
                    splitfileds = tempFiled.split(Constant.CENTER_LINE);
                    sbuilder = new StringBuilder("");
                    for (int q = 0; q < splitfileds.length; q++)
                    {
                        tempFiled = splitfileds[q];
                        sbuilder.append(ConverterUtils.toString(tempDataMap.get(tempFiled)) + Constant.CENTER_LINE);
                    }
                    obj[i][j] = sbuilder.toString().substring(0, sbuilder.length() - Constant.ONE);
                }
                else
                {
                    obj[i][j] = StringUtil.toString(tempDataMap.get(tempFiled));
                }
            }

        }
        return obj;
    }

    /**
     *
     * <导出excel接口>
     *
     * @param filePath 目标文件路径
     * @param dataArray 导出的数据
     * @param titleArray 标题数据
     * @param styleArray
     * @param cellStyleArray
     */
    public static void exportExcel(String filePath, Object[][] dataArray, Object[] titleArray, int[][] styleArray,
        Object[] cellStyleArray)
    {
        log.debug("导出文件开始" + filePath);
        createExcel07(filePath, dataArray, titleArray, styleArray, (XSSFCellStyle[])cellStyleArray);
        log.debug("导出文件结束" + filePath);
    }

    /**
     *
     * <导出excel接口>
     *
     * @param filePath 目标文件路径
     * @param sheetNameList sheet名称
     * @param styleArray 样式
     */
    public static void exportMuchSheetExcel(String filePath, List<Object[][]> dataArrayList,
        List<String[]> titleArrayList, List<String> sheetNameList, List<int[][]> styleArray)
    {
        log.debug("导出文件开始" + filePath);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = null;
        int size = dataArrayList.size();
        for (int i = 0; i < size; i++)
        {
            sheet = wb.createSheet(sheetNameList.get(i));
            initSheet07(sheet, dataArrayList.get(i), titleArrayList.get(i), null, null,1);
        }
        writeExcel(wb, filePath);
        log.debug("导出文件结束" + filePath);
    }

    /**
     * <根据文件路径获取03版本的excel对象>
     *
     * @param filePath 文件路径
     * @return
     * @return poi excel对象
     */
    public static HSSFWorkbook getWorkbook03(String filePath)
    {
        HSSFWorkbook wb = null;
        try
        {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(new File(filePath)));
            wb = new HSSFWorkbook(fs);
        }
        catch (Exception e)
        {
            log.error(e);
        }
        return wb;
    }

    /**
     * <根据文件路径获取07版本的excel对象>
     *
     * @param filePath 文件路径
     * @return
     * @return poi excel对象
     */
    public static XSSFWorkbook getWorkbook07(String filePath)
    {
        XSSFWorkbook wb = null;
        try
        {
            wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
        }
        catch (Exception e)
        {
            log.error(e);
        }
        return wb;
    }


    /**
     *
     * <导出excel，根据数据和配置创建一个03版本的excel，只导出该excel的第一个sheet页
     *
     * @param filePath
     * @param dataArray
     * @param templatePath
     */
    public static void createOneSheetExcel03(String filePath, Object[][] dataArray, String templatePath,
        int[][] styleArray, HSSFCellStyle[] cellStyleArray)
    {
        HSSFWorkbook wb = getWorkbook03(templatePath);
        HSSFSheet sheet = null;
        sheet = wb.getSheetAt(0);
        int startRowNum = Constant.ONE;
        Object[][] titleArray = null;
        initSheet03(sheet, dataArray, titleArray, styleArray, cellStyleArray, startRowNum);
        writeExcel(wb, filePath);
    }

    /**
     *
     * <导出excel，根据数据和配置创建一个03版本的excel，只导出该excel的第一个sheet页
     *
     * @param dataArray
     * @param templatePath
     */
    public static Workbook createOneSheetExcel03WorkBook( Object[][] dataArray, String templatePath,
                                             int[][] styleArray, HSSFCellStyle[] cellStyleArray)
    {
        HSSFWorkbook wb = getWorkbook03(templatePath);
        HSSFSheet sheet = null;
        sheet = wb.getSheetAt(0);
        int startRowNum = Constant.ONE;
        Object[][] titleArray = null;
        initSheet03(sheet, dataArray, titleArray, styleArray, cellStyleArray, startRowNum);
        return wb;
    }

    /**
     *
     * <导出excel，根据数据和配置创建一个03版本的excel，只导出该excel的第一个sheet页
     *
     * @param filePath 临时文件存放路径
     * @param dataArray 内容数组
     * @param templatePath 模板路径
     * @param titleArray 标题的数组
     */
    public static void createOneSheetExcel03(String filePath, Object[][] dataArray, String templatePath,
        int[][] styleArray, HSSFCellStyle[] cellStyleArray, Object[][] titleArray)
    {
        HSSFWorkbook wb = getWorkbook03(templatePath);
        HSSFSheet sheet = null;
        sheet = wb.getSheetAt(0);
        int startRowNum = Constant.ONE;
        HSSFCellStyle style = wb.createCellStyle();
        cellStyleArray = new HSSFCellStyle[1];
        cellStyleArray[0] = style;
        initSheet03(sheet, dataArray, titleArray, styleArray, cellStyleArray, startRowNum);
        writeExcel(wb, filePath);
    }

    /**
     * 获取一个exccel所有的sheet的名字，支持03和07 版本的excel
     * @param filePath excel文件路径
     * @return
     */
    public static List<String> getSheetNames(String filePath)
    {
        Workbook workbook = null;
        if(filePath.endsWith("xlsx"))
        {
            workbook =  getWorkbook03(filePath);
        }
        else{
            workbook =  getWorkbook07(filePath);
        }
        List<String> sheetNames=  new ArrayList<>();
        //获取每个Sheet表
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheetNames.add(workbook.getSheetAt(i).getSheetName());
        }
        return sheetNames;
    }

    /**
     *
     * <导出excel，根据数据和配置创建一个03版本的excel，只导出该excel的第一个sheet页
     *
     * @param filePath
     * @param dataArray
     * @param templatePath
     * @param startRowNum 开始行号
     */
    public static void createOneSheetExcel03(String filePath, Object[][] dataArray, String templatePath,
        int[][] styleArray, HSSFCellStyle[] cellStyleArray, int startRowNum)
    {
        HSSFWorkbook wb = getWorkbook03(templatePath);
        HSSFSheet sheet = null;
        sheet = wb.getSheetAt(0);
        Object[][] titleArray = null;
        initSheet03(sheet, dataArray, titleArray, styleArray, cellStyleArray, startRowNum);
        writeExcel(wb, filePath);
    }




    /**
     * 根据列配置和excel input stream 将excel转换为对象
     *  本支撑暂时支持最多26列
     * @param clazz 需要将excel的数据转换为什么对象
     * @param is excel的inputstream
     * @param colSettStr 列配置
     * @param titleRowNum 标题行
     * @param colNum 一共多少列
     * @param <T>  没啥
     * @return 每一行都会被转换为一个集合
     */
    public static <T> List<T> formartExcelData(Class<T> clazz, InputStream is, String colSettStr, int titleRowNum, int colNum) throws IOException, IllegalAccessException, InstantiationException {
        return formartExcelData( clazz,  is,  colSettStr,  titleRowNum,  colNum,null);
    }

    /**
     * 根据列配置和excel input stream 将excel转换为对象
     *  本支撑暂时支持最多26列
     * @param clazz 需要将excel的数据转换为什么对象
     * @param is excel的inputstream
     * @param colSettStr 列配置
     * @param titleRowNum 标题行
     * @param colNum 一共多少列
     * @param <T>  没啥
     * @return 每一行都会被转换为一个集合
     */
    public static <T> List<T> formartExcelData(Class<T> clazz, InputStream is, String colSettStr, int titleRowNum, int colNum, ExcelValidor excelValidor) throws IOException, IllegalAccessException, InstantiationException {

        /*
            教程 colSett = [{'index':'a','trans':{'男':1,'女':2},'valid':['required','int','mobile','idNum','email','phone'],'field':'sex'}]
                    第几列，如果遇到男则实际值为1 女为2 验证 必填 并且是int
          */
        /*
               不要删除这段代码是给copy用的
         List<User> userList = formartExcelData(User.class, new FileInputStream(new File("d:/user.xls")),"[" +
                "{'index':'a','valid':['required','int'],'field':'userId'}," +
                "{'index':'b','valid':['required'],'field':'userName'}," +
                "{'index':'c','trans':{'男':1,'女':2},'valid':['required'],'field':'sex','fieldTye':'int'}" +
                "]", 0,3);
         */
         // 1读取excel的数据
        Object[][] excelDatas = readExcelContent03( is,  titleRowNum,  colNum);
        colSettStr = colSettStr.replaceAll("'","\"");
        JSONArray  colSettArray = JSON.parseArray(colSettStr);
        JSONObject tempColSett = null;
        List<T> resultList = new ArrayList<>();
        T rowBean = null;
        StringBuilder errorBuilder = new StringBuilder();
        int rowIndex = 1;
        //  2 遍历所有的行，每一行都是一个T对象
        for(Object[] row : excelDatas)
        {
            rowBean = clazz.newInstance();
            resultList.add(rowBean);
            // 遍历所有的需要的字段，进行校验和转换
            for(int i =0;i<colSettArray.size();i++)
            {
                tempColSett = colSettArray.getJSONObject(i);
                // 获取当前配置的所在列
                int index = ConverterUtils.char2Int(tempColSett.getString("index").charAt(0));
                Object fieldVal = row[index-1];
                String valid = tempColSett.getString("valid");
                char colName = tempColSett.getString("index").charAt(0);
                //如果指定了自定义校验算法又给了自定义验证器
                if(ConverterUtils.toString(valid).contains("ex.") && excelValidor !=null){
                    //使用自定义验证器
                    if(!excelValidor.validParam( fieldVal, valid, errorBuilder, colName, rowIndex + titleRowNum))
                    {
                        continue;
                    }
                }
                else if(!validParam( fieldVal, valid, errorBuilder, colName, rowIndex + titleRowNum))
                {
                    continue;
                }
                // 转换字典
                JSONObject trans = tempColSett.getJSONObject("trans");
                if(trans !=null && (!CheckUtils.isNullOrEmpty(fieldVal)) && (!trans.containsKey(fieldVal)))
                {
                    errorBuilder.append("第" + (rowIndex + titleRowNum) + "行，第" + tempColSett.getString("index").charAt(0) + "列输入有误，配置为：" + trans.toJSONString() + ";");
                    continue;
                }
                if(trans !=null)
                {
                    fieldVal = trans.getString(ConverterUtils.toString(fieldVal));
                }
                if((valid!=null && valid.contains("int")) || (tempColSett.containsKey("fieldTye") && "int".equals(tempColSett.getString("fieldTye"))))
                {
                    ReflectUtils.setValue(rowBean,tempColSett.getString("field"),ConverterUtils.toInt(fieldVal));
                    continue;
                }
                if((valid!=null && valid.contains("double")) || (tempColSett.containsKey("fieldTye") && "double".equals(tempColSett.getString("fieldTye"))))
                {
                    ReflectUtils.setValue(rowBean,tempColSett.getString("field"),ConverterUtils.toDouble(fieldVal));
                    continue;
                }
                ReflectUtils.setValue(rowBean,tempColSett.getString("field"),fieldVal);

            }
            rowIndex ++;
        }
        //如果有校验错误就返回错误信息
        if(errorBuilder.toString().length()!=0)
        {
            throw new IllegalArgumentException(errorBuilder.toString());
        }
        return resultList;
    }

    /**
     * 校验参数
     * @param param 参数
     * @param valid 校验配置
     * @param errorBuilder 错误字符串
     * @param colName 列名字
     * @param rowIndex 行index
     * @return 是否校验通过
     */
    private static boolean validParam(Object param,String valid,StringBuilder errorBuilder,char colName,int rowIndex)
    {
        if(valid == null)
        {
            return true;
        }
        // 如果必填但是没填
        if(valid.contains("required") && CheckUtils.isNullOrEmpty(param))
        {
            errorBuilder.append("第" + rowIndex  + "行，第" +  colName + "列为必填;");
            return false;
        }

        // 如果参数为空就不需要校验了
        if(CheckUtils.isNullOrEmpty(param))
        {
            return true;
        }

        // 要求int但是不是int
        if(valid.contains("int") && (!CheckUtils.isNumber(param)))
        {
            errorBuilder.append("第" + rowIndex  + "行，第" + colName + "列必须为数字");
            return false;
        }
        // 要求double但是不是double
        if(valid.contains("double") && (!CheckUtils.isDouble(param)))
        {
            errorBuilder.append("第" + rowIndex  + "行，第" + colName + "列必须为数字");
            return false;
        }
        // 要求手机号但是不是手机号
        if(valid.contains("mobile") && (!CheckUtils.checkPhone(ConverterUtils.toString(param))))
        {
            errorBuilder.append("第" + rowIndex  + "行，第" + colName + "列必须为手机号");
            return false;
        }
        // 要求手机号但是不是手机号
        if(valid.contains("phone") && (!CheckUtils.checkFixedPhone(ConverterUtils.toString(param))))
        {
            errorBuilder.append("第" + rowIndex  + "行，第" + colName + "列必须为座机号");
            return false;
        }
        // 要求身份证号但是不是
        if(valid.contains("idCard") && (!CheckUtils.checkIdCard(ConverterUtils.toString(param))))
        {
            errorBuilder.append("第" + rowIndex  + "行，第" + colName + "列必须为身份证号");
            return false;
        }
        // 要求邮箱但是不是
        if(valid.contains("email") && (!CheckUtils.checkEmail(ConverterUtils.toString(param))))
        {
            errorBuilder.append("第" + rowIndex  + "行，第" + colName + "列必须为邮箱");
            return false;
        }
        return true;
    }

    /**
     *
     * <导出excel，根据数据和配置创建一个03版本的excel，只导出该excel的第一个sheet页,带合并单元格
     *
     * @param filePath
     * @param dataArray
     * @param templatePath
     */
    @SuppressWarnings("deprecation")
    public static void createOneSheetExcel03(String filePath, Object[][] dataArray, String templatePath,
        int[][] styleArray, HSSFCellStyle[] cellStyleArray, int[][] rowspan)
    {
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        if (null != templatePath)
        {
            wb = getWorkbook03(templatePath);
            sheet = wb.getSheetAt(0);
        }
        else
        {
            wb = new HSSFWorkbook();
            sheet = wb.createSheet("new   sheet");
        }
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象

        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        style.setAlignment(HorizontalAlignment.CENTER);// 水平
        int startRowNum = Constant.ONE;
        Object[][] titleArray = null;
        for (int i = 0; i < rowspan.length; i++)
        {
            sheet.addMergedRegion(new CellRangeAddress(rowspan[i][0], rowspan[i][1], 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(rowspan[i][0], rowspan[i][1], 1, 1));
        }
        initSheet03(sheet, dataArray, titleArray, styleArray, cellStyleArray, startRowNum);
        writeExcel(wb, filePath);
    }

    /**
     *
     * <导出excel，根据数据和配置创建一个03版本的excel，只导出该excel的第一个sheet页,带合并单元格加一个参数 合并列的坐标>
     *
     * @param filePath  输出文件的路径
     * @param dataArray 数据二维数组
     * @param templatePath 模板路径
     * @param styleArray 暂未实现  填写null
     * @param cellStyleArray 暂未实现  填写null
     * @param rowspan 需要合并的行的数组， rowspan的[i个]的第rowspan[i][0]行到第rowspan[i][1]行合并 具体合并哪些列参照下个参数配置
     * @param megreColIndex 哪些列要合并，传一个数组
     */
    @SuppressWarnings("deprecation")
    public static void createOneSheetExcel03(String filePath, Object[][] dataArray, String templatePath,
        int[][] styleArray, HSSFCellStyle[] cellStyleArray, int[][] rowspan, int[] megreColIndex)
    {
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        if (null != templatePath)
        {
            wb = getWorkbook03(templatePath);
            sheet = wb.getSheetAt(0);
        }
        else
        {
            wb = new HSSFWorkbook();
            sheet = wb.createSheet("new   sheet");
        }
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象

        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        style.setAlignment(HorizontalAlignment.CENTER);// 水平
        int startRowNum = Constant.ONE;
        Object[][] titleArray = null;
        for (int i = 0; i < rowspan.length; i++)
        {
            for (int j = 0; j < megreColIndex.length; j++)
            {
                sheet.addMergedRegion(new CellRangeAddress(rowspan[i][0], rowspan[i][1], megreColIndex[j],
                    megreColIndex[j]));
            }
        }
        initSheet03(sheet, dataArray, titleArray, styleArray, cellStyleArray, startRowNum);
        writeExcel(wb, filePath);
    }

    /**
     *
     * 根据数据创建WorkBook
     * @param dataArray 数据集合
     * @param templatePath 模板
     */
    public static Workbook createOneSheetExcel07WorkBook( Object[][] dataArray,String templatePath,int titleRowNum)
    {
        XSSFWorkbook wb = getWorkbook07(templatePath);
        XSSFSheet sheet = wb.getSheetAt(0);
        initSheet07(sheet, dataArray, null, null, null,titleRowNum);
        return wb;
    }

    /**
     *
     * <导出excel,根据数据和配置创建一个03版本的excel，改excel有多个sheet>
     *
     * @param filePath
     * @param dataArray
     */
    private static void createExcel07(String filePath, Object[][] dataArray, Object[] titleArray, int[][] styleArray,
                                      XSSFCellStyle[] cellStyleArray)
    {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        initSheet07(sheet, dataArray, titleArray, styleArray, cellStyleArray,1);
        writeExcel(wb, filePath);
    }

    /**
     * <初始化sheet中的数据>
     *
     * @param sheet sheet对象
     * @param dataArray 数据集合
     * @param styleArray 样式集合
     * @param cellStyleArray workbook创建出来的样式集合
     * @param startRowNum 起始行
     */
    private static void initSheet03(HSSFSheet sheet, Object[][] dataArray, Object[][] titleArray, int[][] styleArray,
        HSSFCellStyle[] cellStyleArray, int startRowNum)
    {
        HSSFRow row = null;
        HSSFCell cell = null;
        Object[] rowDataArray = null;
        Object cellData = null;
        if (null != titleArray && titleArray.length > 0)
        {
            for (int i = 0; i < titleArray.length; i++)
            {
                row = sheet.createRow(i);
                rowDataArray = titleArray[i];
                for (int j = 0; j < rowDataArray.length; j++)
                {
                    cell = row.createCell(j);
                    cellData = rowDataArray[j];
                    if (cellData instanceof String)
                    {
                        cell.setCellValue(ConverterUtils.toString(cellData));
                    }
                    else if (cellData instanceof Integer)
                    {
                        cell.setCellValue(ConverterUtils.toInt(cellData));
                    }
                    else if (cellData instanceof Double)
                    {
                        cell.setCellValue(ConverterUtils.toDouble(cellData));
                    }
                    else if (cellData instanceof Date)
                    {
                        cell.setCellValue((Date)cellData);
                    }

                }
            }
        }
        if (null != dataArray && dataArray.length > 0)
        {
            for (int i = 0; i < dataArray.length; i++)
            {
                row = sheet.createRow(i + startRowNum);
                rowDataArray = dataArray[i];
                for (int j = 0; j < rowDataArray.length; j++)
                {
                    cell = row.createCell(j);
                    cellData = rowDataArray[j];
                    if (cellData instanceof String)
                    {
                        cell.setCellValue(ConverterUtils.toString(cellData));
                    }
                    else if (cellData instanceof Integer)
                    {
                        cell.setCellValue(ConverterUtils.toInt(cellData));
                    }
                    else if (cellData instanceof Double)
                    {
                        cell.setCellValue(ConverterUtils.toDouble(cellData));
                    }
                    else if (cellData instanceof Date)
                    {
                        cell.setCellValue((Date)cellData);
                    }
                    else{
                        // 不属于上述值，则直接转换成string
                        cell.setCellValue(String.valueOf(cellData));
                    }
                    // 如果当前单元格的这个值 和上一行当前列的值相同 那么合并

                }
            }
        }
        if (cellStyleArray != null)
        {
            for (int i = 0; i < cellStyleArray.length; i++)
            {
                cell.setCellStyle(cellStyleArray[i]);
            }
        }
    }


    /**
     * <初始化sheet中的数据>
     *
     * @param sheet sheet对象
     * @param dataArray 数据集合
     * @param styleArray 样式集合
     * @param cellStyleArray workbook创建出来的样式集合
     * @param styleArray 起始行
     * @param cellStyleArray 截止行
     */
    public static void initSheet07(XSSFSheet sheet, Object[][] dataArray, Object[] titleArray, int[][] styleArray,
        XSSFCellStyle[] cellStyleArray,int titleRow)
    {
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;
        Object[] rowDataArray = null;
        Object cellData = null;
        if(titleArray!=null)
        {
            titleRow = 1;
            int startRowNum = titleArray.length;
            for (int i = 0; i < startRowNum; i++)
            {
                cell = row.createCell(i);
                cellData = titleArray[i];
                cell.setCellValue(ConverterUtils.toString(cellData));
            }
        }

        for (int i = 0; i < dataArray.length; i++)
        {
            row = sheet.createRow(i + titleRow);
            rowDataArray = dataArray[i];
            for (int j = 0; j < rowDataArray.length; j++)
            {
                cell = row.createCell(j);
                cellData = rowDataArray[j];
                if (cellData instanceof String)
                {
                    cell.setCellValue(ConverterUtils.toString(cellData));
                }
                else if (cellData instanceof Integer)
                {
                    cell.setCellValue(ConverterUtils.toInt(cellData));
                }
                else if (cellData instanceof Double)
                {
                    cell.setCellValue(ConverterUtils.toDouble(cellData));
                }
                else if (cellData instanceof Date)
                {
                    cell.setCellValue((Date)cellData);
                }
            }
        }
    }



    /**
     * <将excel对象到处到文件中>
     *
     * @param wb excel对象
     * @param filePath 到处路径
     */
    public static void writeExcel(Workbook wb, String filePath)
    {
        File file = new File(filePath);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                log.error(e);
            }
        }

        FileOutputStream fileOut = null;
        try
        {
            fileOut = new FileOutputStream(filePath);
            wb.write(fileOut);
        }
        catch (IOException ex)
        {
            log.error(ex);
        }
        finally
        {
            try
            {
                if (null != fileOut)
                {
                    fileOut.close();
                }
            }
            catch (IOException e)
            {
                log.error(e.getCause());
            }
        }
    }

    /**
     *
     * <导入excel的接口>
     *
     */
    public static Object[][] importExcel(String filePath, int titleRowNum, int colNum)
    {
        File file = new File(filePath);
        // 判断目标文件是否存在
        if (!file.exists())
        {
            log.error("ExcelUtils.exportExcel    目标文件" + filePath + "不存在！");
        }

        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);

        // 根据不同excel版本调用不同的excel类型
        Object[][] dataList = null;
        if ("xls".equals(extension))
        {
            dataList = readExcelContent03(filePath, titleRowNum, colNum);
        }
        else if ("xlsx".equals(extension))
        {
            dataList = readExcelContent07(filePath, titleRowNum, colNum);
        }
        else
        {
            log.error("ExcelUtils.exportExcel    不支持的文件类型或用户将xls文件后缀更改为xlsx");
        }
        return dataList;
    }

    /**
     * 读取Excel数据内容
     *
     * @param titleRowNum 标题的总行数
     * @param colNum 总列数
     * @return Map 包含单元格数据内容的Map对象
     */
    private static Object[][] readExcelContent07(String filePath, int titleRowNum, int colNum)
    {
        XSSFWorkbook wb = getWorkbook07(filePath);
        XSSFSheet sheet;

        sheet = wb.getSheetAt(0);
        // 得到数据的总行数
        int rowNum = sheet.getLastRowNum();

        // 如果该sheet没有数据，退出
        if (0 == rowNum)
        {
            return null;
        }

        XSSFRow row = sheet.getRow(titleRowNum);
        if (null == row)
        {
            return null;
        }

        // 正文内容应该从第二行开始,第一行为表头的标题
        Object[][] dataArray = new Object[rowNum - titleRowNum][colNum];
        // 添加到数组的行数，
        int dataRow = 0;
        // 空行数
        int nullRows = 0;
        for (int i = titleRowNum + 1; i <= rowNum; i++)
        {
            row = sheet.getRow(i);
            if (row == null)
            {
                nullRows++;
                continue;
            }
            int j = 0;
            // 是否是空行，如果为空行，则赋值的数组行数不会变，下一次循环会覆盖
            boolean isNullFlag = true;
            while (j < colNum)
            {

                dataArray[dataRow][j] = getCellValue(row.getCell(j));
                j++;
                if (!"".equals(getCellValue(row.getCell(j))))
                {
                    isNullFlag = false;
                }
            }
            if (isNullFlag)
            {
                nullRows++;
            }
            else
            {
                dataRow++;
            }
        }
        return Arrays.copyOfRange(dataArray, 0, rowNum - titleRowNum - nullRows);
    }



    /**
     *  读取excel内容
     * @param is excel inputstream
     * @param titleRowNum 行号
     * @param colNum 一共多少列
     * @return 数据
     */
    private static Object[][] readExcelContent03(InputStream is, int titleRowNum, int colNum) throws IOException {
        return readExcelContent03( new HSSFWorkbook(is),  titleRowNum,  colNum);
    }

    /**
     *  读取excel内容
     * @param wb excel
     * @param titleRowNum 行号
     * @param colNum 一共多少列
     * @return 数据
     */
    private static Object[][] readExcelContent03(HSSFWorkbook wb, int titleRowNum, int colNum)
    {
        HSSFSheet sheet;

        // 获取sheet个数
        int sheetNO = wb.getNumberOfSheets();
        String[] name = null;
        name = new String[sheetNO + 1];
        sheet = wb.getSheetAt(0);

        // 得到数据的总行数
        int rowNum = sheet.getLastRowNum();

        // 如果该sheet没有数据，跳过当前循环
        if (0 == rowNum)
        {
            return null;
        }
        HSSFRow row = sheet.getRow(titleRowNum + 1);
        if (null == row)
        {
            return null;
        }

        // 正文内容应该从第二行开始,第一行为表头的标题
        Object[][] dataArray = new Object[rowNum - titleRowNum][colNum];
        // 添加到数组的行数，
        int dataRow = 0;
        // 空行数
        int nullRows = 0;
        for (int i = titleRowNum + 1; i <= rowNum; i++)
        {
            row = sheet.getRow(i);
            if (null == row)
            {
                nullRows++;
                continue;
            }
            int j = 0;
            // 是否是空行，如果为空行，则赋值的数组行数不会变，下一次循环会覆盖
            boolean isNullFlag = true;
            while (j < colNum)
            {

                dataArray[dataRow][j] = getCellValue(row.getCell(j));
                j++;
                if (!"".equals(getCellValue(row.getCell(j))))
                {
                    isNullFlag = false;
                }
            }
            if (isNullFlag)
            {
                nullRows++;
            }
            else
            {
                dataRow++;
            }
        }
        return Arrays.copyOfRange(dataArray, 0, rowNum - titleRowNum - nullRows);
    }

    /**
     * 读取Excel数据内容
     *
     * @param filePath excel文件路径
     * @param titleRowNum 标题的行数
     * @param colNum 列数
     * @return Map 包含单元格数据内容的Map对象
     */
    private static Object[][] readExcelContent03(String filePath, int titleRowNum, int colNum)
    {
        HSSFWorkbook wb = getWorkbook03(filePath);
        return readExcelContent03( wb,  titleRowNum,  colNum);
    }


    public static boolean checkExcel(String filePath)
    {
        getWorkbook07(filePath);
        return true;

    }

    // 解决excel类型问题，获得数值
    @SuppressWarnings("deprecation")
    public static String getCellValue(Cell cell)
    {
        String value = "";
        if (null == cell)
        {
            return value;
        }
        switch (cell.getCellType())
        {
        // 数值型
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell))
                {
                    // 如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date);
                    ;
                }
                else
                {// 纯数字
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    // 解决1234.0 去掉后面的.0
                    if (null != value && !"".equals(value.trim()))
                    {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1]))
                        {
                            value = item[0];
                        }
                    }
                }
                break;
            // 字符串类型
            case STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case FORMULA:
                // 读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN"))
                {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            // 布尔类型
            case BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            // 空值
            case BLANK:
                value = "";
                log.error("excel出现空值");
                break;
            // 故障
            case ERROR:
                value = "";
                log.error("excel出现故障");
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if ("null".endsWith(value.trim()))
        {
            value = "";
        }
        return value;
    }
}
