package com.fhs.common.utils;

import java.util.*;

/**
 * excel 合并单元格
 *本类生成的html需要和
 * https://blog.csdn.net/shuaizai88/article/details/72723935
 * 一起使用
 * 记得maven安装html2excel.jar  -- 现在叫myexcel了
 * https://github.com/liaochong/myexcel
 */
public class TableUtils
{
    public static  class TD
    {
        private int rowSpan;
        private int colSpan;
        private String content;
        public TD(int rowSpan,int colSpan,String content)
        {
            this.colSpan = colSpan;
            this.rowSpan = rowSpan;
            this.content = content;
        }
        public int getRowSpan()
        {
            return rowSpan;
        }
        public void setRowSpan(int rowSpan)
        {
            this.rowSpan = rowSpan;
        }
        public int getColSpan()
        {
            return colSpan;
        }
        public void setColSpan(int colSpan)
        {
            this.colSpan = colSpan;
        }
        public String getContent()
        {
            return content;
        }
        public void setContent(String content)
        {
            this.content = content;
        }
        
    }
    
    public static List<Map<String,TD>> mergeCell(List<Map<String,Object>> dataList,
                                                 String groupKey,String[] margeKeys,
                                                 String[] withOutKeys)
    {
        /*
         * 这可能是我写的有史以来最恶心的代码。。。。。
         * 
         * 思路：如果自己这个key 和 主的分组key相同那么 就合并    比如  王磊磊，王磊嗯，王小二，王小三，李爱民 这一组数据
         * 那么姓就是分组合并的groupKey 如果 第二个字需要合并 ，第三个字不需要合并，我们的处理方式是。
         * 首先遍历需要合并的key 这里只有第二个字，然后遍历所有的名字，记录本次的姓 和第二个字和上次的是否相同(默认的名字为空所以第一个和空比较一定是不同的)
         * 如果姓不同，那么就需要给姓new 一个单元格，并且设置它的单元格row是1，如果相同 那么就把单元格的原来的rowspan+1 最终第一行数据有一个 数据为王 rowspan为4的
         * td，第2,3,4因为都姓王所以木有TD对象，会有null，第五会有一个 内容为李 rowspan为1的TD 。。 这是groupKey对应的数据的处理方式。
         * 
         * 接着对比王磊磊和王磊嗯 都姓王并且第二个字都相同，所以王磊嗯的第二个字没有TD会被合并，然后 -- 明天再写。。
         *
         * 
         * 
         * 
         */
        // 我是返回的
        List<Map<String,TD>> resultList  = new ArrayList<>();
        Map<String,TD> tempTdMap = null;
        Object preContent = null;
        Object preGroupKeyContent = null;
        Map<String,Object> tempMap = null;
        int rowSize = dataList.size();
        Object nowContent = null;
        Object nowGroupKeyContent = null;
        TD groupKeyTd = null;
        TD tempTd = null;
        boolean isOneLoop = true;
        for(String tempKey : margeKeys)
        {
            preContent = null;
            preGroupKeyContent = new Object();
            for(int i = 0;i < rowSize; i ++)
            {
                tempMap = dataList.get(i);

                nowContent = tempMap.get(tempKey);
                nowGroupKeyContent = tempMap.get(groupKey);
                // 第一次去给resultList添加TD的map，有多少条数据就添加多少个TDMAP
                if(isOneLoop)
                {
                    tempTdMap = new HashMap<String,TD>();
                    resultList.add(tempTdMap);
                    // 如果groupkey相同，那么temptdmap对应的tempKey 就被合并不再给td对象了，但是会给合并的groupKeyTd的rowspan+1
                    if(preGroupKeyContent.equals(nowGroupKeyContent))
                    {
                        tempTdMap.put(groupKey, null);
                        groupKeyTd.setRowSpan((groupKeyTd.getRowSpan() + 1));
                    }
                    else
                    {
                        groupKeyTd =  new TD(1, 1, StringUtil.toString(nowGroupKeyContent));
                        tempTdMap.put(groupKey, groupKeyTd);
                    }
                }
                else
                {
                    tempTdMap = resultList.get(i);
                }
                // 如果可以合并
                if(preGroupKeyContent.equals(nowGroupKeyContent) && (preContent== null || preContent.equals(nowContent)))
                {
                    //本条tempTDMap 对应的key 因为可以合并单元格 ，所以设置null。代表不用输出td
                    tempTdMap.put(tempKey, null);
                    tempTd.setRowSpan((tempTd.getRowSpan() + 1));
                }
                else
                {
                    preContent = nowContent ;
                    // 如果
                    tempTd = new TD(1, 1, StringUtil.toString(nowContent));
                    tempTdMap.put(tempKey, tempTd);
                }
                if(!preGroupKeyContent.equals(nowGroupKeyContent))
                {
                    preGroupKeyContent = nowGroupKeyContent != null ? nowGroupKeyContent : "";
                }
            }
            isOneLoop = false;
        }

        for(String tempKey : withOutKeys)
        {
            for(int i = 0;i < rowSize; i ++)
            {
                tempMap = dataList.get(i);
                nowContent = tempMap.get(tempKey);
                tempTd = new TD(1, 1, StringUtil.toString(nowContent));
                tempTdMap = resultList.get(i);
                tempTdMap.put(tempKey, tempTd);
            }
        }
        return resultList;
    }

    /**
     *
     * 组装数据成为html里的数据，外面包上table就可以了
     *
     * @param mergeCell 合并后的数据
     * @param groupKey 最大的合并单位，作为主要的合并
     * @param isAddIndex 是否在第一列添加索引
     * @param startIndex 如果添加索引，第一个序号是什么
     * @param showKeys 显示的table的头，顺序是表头从左到右，不包含索引
     * @return
     */
    public static String initTableTrInfo(List<Map<String, TD>> mergeCell, String groupKey, boolean isAddIndex, Integer startIndex, String[] showKeys){
        StringBuilder sbr = new StringBuilder();
        Map<String, TD> tempMapTd = null;
        TD tempTd = null;
        for (int i = 0; i < mergeCell.size(); i++)
        {
            tempMapTd = mergeCell.get(i);
            sbr.append("<tr>");
            if(isAddIndex && tempMapTd.get(groupKey) != null){
                sbr.append("<td rowspan=\"").append(tempMapTd.get(groupKey).getRowSpan()).append("\">").append(startIndex).append("</td>");
                startIndex++;
            }
            for (int j = 0; j < showKeys.length; j++)
            {
                tempTd = tempMapTd.get(showKeys[j]);
                if (tempTd != null)
                {
                    sbr.append("<td rowspan=\"").append(tempTd.getRowSpan()).append("\" >");
                    sbr.append(tempTd.getContent() == null ? "" : tempTd.getContent());
                    sbr.append("</td>");
                }
            }
            sbr.append("</tr>\n\r");
        }
        return sbr.toString();
    }
    /*public static void main(String[] args)
    {
        List<Map<String,Object>> dataList = new ArrayList<>();
        Map<String,Object>  tempMap = null;
        for(int i = 0;i < 12;i++)
        {
            tempMap = new LinkedHashMap<>();
            tempMap.put("index1", i);
            tempMap.put("index2", i/2);
            tempMap.put("index3", i/3);
            tempMap.put("index4", i/4);
            dataList.add(tempMap);
            System.out.println(tempMap);
        }
        System.out.println("-------------------------------------------------------------------");
        List<Map<String,TD>> tdList = mergeCell(dataList,"index4",new String[]{"index2","index3"},new String[]{"index1"});
        String html  = initTableTrInfo(tdList, "index4", true,1,new String[]{"index1","index2","index3","index4"});
        System.out.println(html);
    }*/
}