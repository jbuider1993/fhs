package com.fhs.core.db;

/**
 * <pre>
 * 读/写动态数据库 决策者
 * 根据DataSourceType是write/read 来决定是使用读/写数据库
 * 通过ThreadLocal绑定实现选择功能
 * </pre>
 *
 * @author Zhang Kaitao
 */
public class ReadWriteDataSourceDecision {

    public enum DataSourceType {
        write, read, other, param;
    }


    /**
     * 到底选择了主库从库还是其他
     */
    private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<DataSourceType>();

    /**
     * 如果选择其他的话，那么DataSourceName 是什么
     */
    private static final ThreadLocal<String> holderDataSourceName = new ThreadLocal<String>();


    /**
     * 标记为写库
     */
    public static void markWrite() {
        if (isChoiceParam()) {
            return;
        }
        holder.set(DataSourceType.write);
    }

    /**
     * 标记为读库
     */
    public static void markRead() {
        if (isChoiceParam()) {
            return;
        }
        holder.set(DataSourceType.read);
    }

    /**
     * 标记为其他自定义
     */
    public static void markOther() {
        if (isChoiceParam()) {
            return;
        }
        holder.set(DataSourceType.other);
    }

    public static void markParam() {
        holder.set(DataSourceType.param);
    }

    /**
     * 手动设置一个dataSourceName名字
     */
    public static void setDataSource(String dataSourceName) {
        holderDataSourceName.set(dataSourceName);
    }

    public static String getDataSource() {
        return holderDataSourceName.get();
    }


    public static void reset() {
        holder.set(null);
        holderDataSourceName.set(null);
        holder.remove();
        holderDataSourceName.remove();
    }

    public static boolean isChoiceNone() {
        return null == holder.get();
    }

    public static boolean isChoiceWrite() {
        return DataSourceType.write == holder.get();
    }

    public static boolean isChoiceOther() {
        return DataSourceType.other == holder.get();
    }

    public static boolean isChoiceRead() {
        return DataSourceType.read == holder.get();
    }

    public static boolean isChoiceParam() {
        return DataSourceType.param == holder.get();
    }

}