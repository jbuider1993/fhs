package com.fhs.demo.test;

import com.fhs.common.ExcelExportTools;
import com.fhs.common.utils.ExcelUtils;
import com.github.liaochong.myexcel.core.BeetlExcelBuilder;
import com.github.liaochong.myexcel.core.DefaultExcelBuilder;
import com.github.liaochong.myexcel.core.DefaultStreamExcelBuilder;
import com.github.liaochong.myexcel.core.ExcelBuilder;
import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import lombok.Data;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.crypto.hash.Hash;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class MyExcelTest {
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        // 显式标明开始构建
        DefaultStreamExcelBuilder defaultExcelBuilder = DefaultStreamExcelBuilder.of(ArtCrowd.class).hasStyle()
                .threadPool(Executors.newFixedThreadPool(10))
                .start();
        // 多线程异步获取数据并追加至excel，join等待线程执行完成
        List<CompletableFuture> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture future = CompletableFuture.runAsync(() -> {
                List<ArtCrowd> dataList = MyExcelTest.getDataList();
                // 数据追加
                defaultExcelBuilder.append(dataList);
            });
            futures.add(future);
        }
        futures.forEach(CompletableFuture::join);
        // 最终构建
        Workbook workbook = defaultExcelBuilder.build();
        ExcelUtils.writeExcel(workbook,"d:/test.xlsx");
        System.out.println("耗时:" +(System.currentTimeMillis()- start));
    }

    static int sum = 0;

    static Object obj = new Object();

    public static List<ArtCrowd> getDataList(){
        synchronized (obj)
        {
            List<ArtCrowd> dataList = new ArrayList<>();
            for(int i =0;i<1000;i++)
            {
                sum ++ ;
                ArtCrowd temp = new ArtCrowd();
                temp.setAge(sum);
                temp.setName("姓名" + sum);
                temp.setBirthday(new Date());
                dataList.add(temp);
            }
            return dataList;
        }

    }

}
@Data
class ArtCrowd {
    // index代表列索引，从0开始
    @ExcelColumn(index = 0, title = "姓名")
    private String name;

    @ExcelColumn(index = 1, title = "年龄")
    private int age;

    @ExcelColumn(index = 2, title = "生日",dateFormatPattern="yyyy-MM-dd")
    private Date birthday;
}
