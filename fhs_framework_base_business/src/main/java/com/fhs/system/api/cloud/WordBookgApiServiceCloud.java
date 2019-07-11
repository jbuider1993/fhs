package com.fhs.system.api.cloud;

import com.fhs.core.result.HttpResult;
import com.fhs.system.api.FeignWordBookApiService;
import com.fhs.system.bean.Wordbook;
import com.fhs.system.bean.WordbookVO;
import com.fhs.system.service.WordBookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典服务
 */
@RestController
@RequestMapping("/api/wordbook")
public class WordBookgApiServiceCloud implements FeignWordBookApiService {

    @Autowired
    private WordBookService wordBookService;

    @GetMapping("/getWordBookList")
    @Override
    public HttpResult<List<WordbookVO>> getWordBookList(String wordBookGroupCode) {
        Wordbook param = new Wordbook();
        param.setWordbookGroupCode(wordBookGroupCode);
        List<Wordbook> wordbookList  = wordBookService.findForList(param);
        List<WordbookVO> result = new ArrayList<>();
        wordbookList.forEach(wordbook -> {
            WordbookVO temp =  new WordbookVO();
            BeanUtils.copyProperties(wordbook,temp);
            result.add(temp);
        });
        return HttpResult.success(result);
    }
}
