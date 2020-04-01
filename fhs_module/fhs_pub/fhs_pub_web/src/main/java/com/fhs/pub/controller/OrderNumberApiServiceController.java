package com.fhs.pub.controller;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import com.fhs.pub.api.rpc.FeignOrderNumberApiService;
import com.fhs.pub.service.OrderNumberService;
import com.fhs.pub.service.impl.OrderNumberServiceImpl;
import com.fhs.pub.vo.OrderNumberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 生成订单号服务
 */
@RestController
@RequestMapping("api/orderNumber")
public class OrderNumberApiServiceController implements FeignOrderNumberApiService {

    private static Logger LOG = Logger.getLogger(OrderNumberApiServiceController.class);

    /**
     * redis key
     */
    private static final String REDIS_KEY = "pubservice:order_number";

    @Autowired
    OrderNumberService orderNumberService;

    @Autowired
    RedisCacheService<String> redisCacheService;

    @RequestMapping("/getOrderNumber")
    @Override
    public HttpResult<String> getOrderNumber(String type) {
        try
        {
            long listSize = redisCacheService.getForListSize(REDIS_KEY + ":" + type);
            LOG.debug("队列中序列条数 ：" + listSize);
            // size为0 说明队列中已经没有序列、可以生成订单号了
            if (listSize == 0)
            {
                generation(type, DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN_DATE_NO_));// 生成订单号
            }
            //获取订单号
            String orderNo = redisCacheService.getBRPop(REDIS_KEY + ":" + type);
            return HttpResult.success(orderNo);
        }
        catch (Exception e1)
        {
            LOG.error("订单号默认值设定失败 ",e1);
            return HttpResult.error(null,"获取订单号失败");
        }
    }

    /**
     * 生成订单号
     */
    protected void generation(String type,String date)
    {

        OrderNumberVO serviceOrderLog = this.getServiceOrderLog(type,date);
        if (serviceOrderLog == null)
        {
            serviceOrderLog = new OrderNumberVO();
            serviceOrderLog.setId(StringUtil.getUUID());
            serviceOrderLog.setType(type);
            serviceOrderLog.setTime(date);
            serviceOrderLog.setNumber(1);
            orderNumberService.insertSelective(serviceOrderLog);
        }
        Integer orderIndex = serviceOrderLog.getNumber();
        int minOrderIndex = orderIndex;
        orderIndex += Constant.ONCE_ORDER_NUM_CREATE;
        serviceOrderLog.setNumber(orderIndex);
        orderNumberService.updateSelectiveById(serviceOrderLog);
        List<String> orderList =  orderNumList(minOrderIndex ,date);
        redisCacheService.addSet(REDIS_KEY + ":" + type, orderList);
        // 设定redis失效时间
        redisCacheService.expire(REDIS_KEY + ":" + type, this.getExpireTime());
    }

    /**
     * 获取配置信息
     *
     * @param type
     * @return
     */
    private OrderNumberVO getServiceOrderLog(String type, String date)
    {
        OrderNumberVO serviceOrderLog = new OrderNumberVO();
        serviceOrderLog.setType(type);
        serviceOrderLog.setTime(date);
        serviceOrderLog = orderNumberService.selectBean(serviceOrderLog);
        return serviceOrderLog;
    }

    /**
     * 重置计数器
     *
     * @param type
     */
    public void updateServiceOrderLog(String type,String date)
    {
        OrderNumberVO serviceOrderLog = this.getServiceOrderLog(type,date);
        if (serviceOrderLog != null)
        {
            serviceOrderLog.setNumber(1);
            orderNumberService.updateSelectiveById(serviceOrderLog);
        }
    }

    /**
     * 组装orderNumList
     *
     * @param minOrderIndex
     * @param date
     * @return
     */
    private List<String> orderNumList(int minOrderIndex,String date)
    {
        List<String> dataList = new ArrayList<String>();
        for (int i = minOrderIndex; i < (Constant.ONCE_ORDER_NUM_CREATE + minOrderIndex); i++)
        {
            dataList.add(StringUtil.formatOrderNumber(date, i));
        }
        return dataList;
    }

    /**
     * redis晚上12点失效
     *
     * @return
     */
    private int getExpireTime()
    {
        long now = System.currentTimeMillis();
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        final long diff = cal.getTimeInMillis() - now;
        return Integer.valueOf(diff/1000 + "");
    }

}
