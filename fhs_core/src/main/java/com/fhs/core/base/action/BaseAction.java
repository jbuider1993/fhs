package com.fhs.core.base.action;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * action 基类
 *
 * @author wanglei
 * @version [版本号, 2015年1月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseAction<T> {
    private final Logger LOG = Logger.getLogger(BaseAction.class);

    /**
     * 成功
     */
    protected static final String SUCCESS = "success";

    /**
     * 失败
     */
    protected static final String FAIL = "fail";

    /**
     * 检查通过
     */
    protected static final String CHECK_PASSES = "{\"result\":true}";

    /**
     * 检查未通过
     */
    protected static final String CHECK_FAILS = "{\"result\":false}";


    /**
     * 获取Application
     *
     * @param request request
     * @return Application
     */
    protected ServletContext getApplication(HttpServletRequest request) {
        return request.getSession().getServletContext();
    }



    /**
     * 获取分页参数
     *
     * @param request
     * @return 分页参数
     */
    protected PageSizeInfo getPageSizeInfo(HttpServletRequest request) {
        return this.getPageSizeInfo(ConverterUtils.toInt(request.getParameter(Constant.PAGE)),
                ConverterUtils.toInt(request.getParameter(Constant.ROWS)));
    }


    /**
     * 获取分页信息
     *
     * @param page 当前第几页
     * @param rows 每页多少条数据
     * @return 分页信息
     */
    protected PageSizeInfo getPageSizeInfo(int page, int rows) {
        if (page == Constant.ZERO || rows == Constant.ZERO) {
            return new PageSizeInfo(0, 10);
        } else {
            return new PageSizeInfo((page - 1) * rows, rows);
        }
    }

    /**
     * <获取参数map> 这里面有"group_code"和"projectIds"
     *
     * @return 参数map
     */
    public  EMap<String, Object> getParameterMap(HttpServletRequest request) {
        EMap<String, Object> resultMap = new EMap<String, Object>();
        Map<String, String[]> tempMap = request.getParameterMap();
        Set<String> keys = tempMap.keySet();
        for (String key : keys) {
            resultMap.put(key, request.getParameter(key));
        }
        return resultMap;
    }

    /**
     * <获取已经计算好分页起始行的参数map>
     *
     * @return 计算好分页起始行的参数map
     */
    protected EMap<String, Object> getPageTurnNum(HttpServletRequest request) {
        EMap<String, Object> paramMap = this.getParameterMap(request);
        PageSizeInfo pageSizeInfo = getPageSizeInfo(paramMap.getInteger(Constant.PAGE, 0), paramMap.getInteger(Constant.ROWS, 0));
        paramMap.put(Constant.START, pageSizeInfo.getPageStart());
        paramMap.put(Constant.END, pageSizeInfo.getPageSize());
        return paramMap;
    }

    /**
     * <向前台写json>
     *
     * @param str      需要向前台写的字符串
     * @param response @param
     */
    protected void outWriteJson(String str, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.println(str);
            pw.flush();
        } catch (IOException e) {
            LOG.error(this, e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * <向浏览器输出成功，失败 json>
     *
     * @param flag     是否成功 是 true 否false
     * @param response response
     */
    protected void outToClient(boolean flag, HttpServletResponse response) {
        if (flag) {
            outWrite(CHECK_PASSES, response);
        } else {
            outWrite(CHECK_FAILS, response);
        }
    }

    /**
     * <向浏览器输出成功，失败 jsonp>
     *
     * @param flag     是否成功 是 true 否false
     * @param response response
     * @param request  request
     */
    protected void outToClientJsonP(boolean flag, HttpServletResponse response, HttpServletRequest request) {
        if (flag) {
            this.outJsonp(CHECK_PASSES, response, request);
        } else {
            this.outJsonp(CHECK_FAILS, response, request);
        }
    }

    /**
     * <将分页数据写到前台>
     *
     * @param dataList 当前页需要显示的数据
     * @param count    符合过滤条件的数据总数
     */
    protected void writeJsonForPager(List<?> dataList, long count, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Constant.TOTAL, count);
        resultMap.put(Constant.ROWS, dataList);
        String json = JsonUtils.object2json(resultMap);
        this.outWrite(json, response);
    }

    /**
     * <将数据写到前台,并带有合计行>
     *
     * @param dataList 当前页需要显示的数据
     */
    protected void writeJsonForFooter(List<?> dataList, List<?> footerList, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // resultMap.put(Constant.TOTAL, count);
        resultMap.put(Constant.ROWS, dataList);
        resultMap.put(Constant.FOOTER, footerList);
        String json = JsonUtils.object2json(resultMap);
        // System.out.println(json);
        this.outWrite(json, response);
    }

    /**
     * <将分页数据写到前台-- jsonp>
     *
     * @param request  request
     * @param dataList 当前页需要显示的数据
     * @param count    符合过滤条件的数据总数
     */
    protected void writeJsonPForPager(HttpServletRequest request, HttpServletResponse response, List<?> dataList,
                                      long count) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Constant.TOTAL, count);
        resultMap.put(Constant.ROWS, dataList);
        String json = JsonUtils.object2json(resultMap);
        this.outJsonp(json, response, request);
    }

    /**
     * <向前台写字符串>
     *
     * @param str      需要向前台写的字符串
     * @param response response
     */
    protected void outWrite(String str, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
        PrintWriter pw = null;
        try {
            response.setContentType("text/html");
            pw = response.getWriter();
            pw.print(str);
            pw.flush();
        } catch (IOException e) {
            LOG.error(this, e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * 向客户端输入jsonp
     *
     * @param request  request
     * @param response response
     * @param json     json
     * @return
     */
    protected void outJsonp(String json, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setCharacterEncoding("UTF-8");
        String jsonpCallback = request.getParameter("jsonpCallback");// 客户端请求参数
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (!CheckUtils.isNullOrEmpty(jsonpCallback)) {
                out.println(jsonpCallback + "(" + json + ")");// 返回jsonp格式数据
            } else {
                out.println(json);// 返回json格式数据
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(this, e);
        } finally {
            if (!CheckUtils.isNullOrEmpty(out)) {
                out.close();
            }
        }
    }

    /**
     * 上传文件 非必要请使用服务类上传，不要自己上传
     *
     * @param filedata 文件数据
     * @return 文件对象
     */
    protected Map<String, String> uploadFile(MultipartFile filedata, String saveFilePath) {
        if (filedata == null) {
            return null;
        }
        Map<String, String> resultMap = new HashMap<String, String>();
        String fileP = File.separator;
        String fileName = filedata.getOriginalFilename();
        // 后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        String fileId = StringUtil.getUUID();
        String currentDate = DateUtils.getCurrentDateStr("yyyy/MM/dd");
        // 文件类型
        String prefix = suffix.replace(".", "");

        File file = null;
        try {
            file = new File(saveFilePath + currentDate + fileP + prefix + fileP + fileId + suffix);
            org.apache.commons.io.FileUtils.copyInputStreamToFile(filedata.getInputStream(), file);
        } catch (IOException e) {
            LOG.error(this, e);
        }
        resultMap.put("fileName", fileName);
        resultMap.put("fileSuffix", suffix);
        resultMap.put("fileId", fileId);
        resultMap.put("uploadDate", currentDate);
        return resultMap;
    }

    /**
     * 分页对象
     */
    public static class PageSizeInfo {
        private int pageStart;

        private int pageSize;

        public PageSizeInfo(int pageStart, int pageSize) {
            this.pageSize = pageSize;
            this.pageStart = pageStart;
        }

        public int getPageStart() {
            return pageStart;
        }

        public void setPageStart(int pageStart) {
            this.pageStart = pageStart;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

    }


}
