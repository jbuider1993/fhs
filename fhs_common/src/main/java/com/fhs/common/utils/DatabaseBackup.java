package com.fhs.common.utils;

import java.io.*;

/**
 * MySQL数据库的备份与恢复 缺陷：可能会被杀毒软件拦截
 * 
 * @author wanglei
 * @version v1.1
 */
public class DatabaseBackup
{
    private static final Logger LOG = Logger.getLogger(DatabaseBackup.class);
    
    /** MySQL安装目录的Bin目录的绝对路径 */
    private String mysqlBinPath;
    
    /** 访问MySQL数据库的用户名 */
    private String username;
    
    /** 访问MySQL数据库的密码 */
    private String password;
    
    public String getMysqlBinPath()
    {
        return mysqlBinPath;
    }
    
    public void setMysqlBinPath(String mysqlBinPath)
    {
        this.mysqlBinPath = mysqlBinPath;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public DatabaseBackup(String mysqlBinPath, String username, String password)
    {
        if (!mysqlBinPath.endsWith(File.separator))
        {
            mysqlBinPath = mysqlBinPath + File.separator;
        }
        this.mysqlBinPath = mysqlBinPath;
        this.username = username;
        this.password = password;
    }
    
    /**
     * 备份数据库
     * 
     * @param output 输出流
     * @param dbname 要备份的数据库名
     */
    public void backup(OutputStream output, String dbname)
    {
        String command =
            "cmd /C mysqldump -u" + username + " -p" + password + " --set-charset=utf8 " + dbname;
        PrintWriter p = null;
        BufferedReader reader = null;
        try
        {
            p = new PrintWriter(new OutputStreamWriter(output, "utf8"));
            Process process = Runtime.getRuntime().exec(command);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            reader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                p.println(line);
            }
            p.flush();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
                if (p != null)
                {
                    p.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 备份数据库，如果指定路径的文件不存在会自动生成
     * 
     * @param dest 备份文件的路径
     * @param dbname 要备份的数据库
     */
    public void backup(String dest, String dbname)
    {
        try
        {
            OutputStream out = new FileOutputStream(dest);
            backup(out, dbname);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 恢复数据库
     * 
     * @param 文件路径
     * @param dbname 数据库名
     */
    public boolean restore(String dest, String dbname)
    {
        try
        {
            String command =
                "cmd /c " + mysqlBinPath + "mysql -u" + username + " -p" + password + " " + dbname + " < " + dest;
            Runtime.getRuntime().exec(command);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("restore_db_error");
            LOG.error(this, e);
        }
        return false;
    }
    
    /**
     * 获取数据库备份文件的二进制
     * 
     * @param output 输出流
     * @param dbname 要备份的数据库名
     */
    public byte[] backupToDesk(String dbname)
    {
        String command =
            "cmd /C " + mysqlBinPath + "mysqldump -u" + username + " -p" + password + " --set-charset=utf8 " + dbname;
        try
        {
            Process process = Runtime.getRuntime().exec(command);
            return input2byte(process.getInputStream());
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public byte[] input2byte(InputStream inStream)
        throws IOException
    {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0)
        {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
    
    public static void main(String[] args)
    {
        // Configuration config = HibernateSessionFactory.getConfiguration();
        // String binPath = config.getProperty("mysql.binpath");
        // String userName = config.getProperty("connection.username");
        // String pwd = config.getProperty("connection.password");
        String binPath = "D:/javaTools/mysql-5.6.17-winx64/bin";
        String userName = "root";
        String pwd = "root";
        DatabaseBackup bak = new DatabaseBackup(binPath, userName, pwd);
        // // bak.backup("d:/ttt.sql", "test");
        bak.restore("d:/ttt.sql", "test");
        System.exit(0);
    }
}