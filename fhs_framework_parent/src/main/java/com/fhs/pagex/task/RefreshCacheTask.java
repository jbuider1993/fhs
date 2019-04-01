package com.fhs.pagex.task;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.FileUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.config.EConfig;
import com.fhs.pagex.service.JoinService;
import com.fhs.pagex.service.PagexDataService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 自动读取js内容，刷新到缓存中定时任务
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.task
 * @ClassName: RefreshCacheTask
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 20:39
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 20:39
 * @Version: 1.0
 */
@Component
public class RefreshCacheTask implements InitializingBean,Runnable,ApplicationContextAware {
    private ApplicationContext applicationContext;



    private static final Logger LOG = Logger.getLogger(RefreshCacheTask.class);

    @Autowired
    private EConfig config;

    /**
     * 让他先来
     */
    @Autowired
    private JoinService joinService;

    /**
     * 资源加载器
     */
    private ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    @Override
    public void afterPropertiesSet() throws Exception {
        //先去刷新一遍，然后判断是否是开发者模式如果是，则继续刷新
        try
        {
            this.refresh();
        }
        catch(Exception e)
        {
            LOG.error("刷新文件出错:",e);
        }
        //正式环境不需要配置
        if(CheckUtils.isNotEmpty(EConfig.getOtherConfigPropertiesValue("isDevModel")))
        {
            new Thread(this).start();
        }

    }


    /**
     * 刷新js->page的任务
     */
    public void run(){
        while(true)
        {

            try {
                try
                {
                    refresh();
                }catch (Exception e)
                {
                    LOG.error("刷新文件出错:",e);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.error(e);
            }
        }
    }

    /**
     * 遍历所有的js文件刷新到内存中
     */
    public void refresh(){
        try {
            Resource[] resources = resolver.getResources("classpath*:META-INF/resources/pagex/**/*.js");
            String fileName = null;
            for(Resource resource : resources)
            {
                //刷新缓存中的js文件
                PagexDataService.SIGNEL.refreshJsFile( resource.getFilename(),FileUtils.readTxtFile(resource.getInputStream()));
            }
            resources = resolver.getResources("classpath*:META-INF/resources/pagex/**/*.html");
            for(Resource resource : resources)
            {
                String path = resource.getURL().getPath();
                path = path.substring(path.indexOf("/pagex/"));
                PagexDataService.SIGNEL.getAddPageExtendsHtmlPathMap().put(resource.getFilename().replace(".html",""),path);
            }
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
