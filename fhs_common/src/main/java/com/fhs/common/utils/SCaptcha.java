package com.fhs.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码工具类
 * Linux 会报错，需要开启awt支持方可解决
 */
public class SCaptcha
{
    // 图片的宽度。
    private int width = 280;
    // 图片的高度。
    private int height = 38;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 50;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;

    private static char[] codeSequence = { '0', '1' ,'2', '3', '4', '5', '6', '7', '8', '9' };

    // 生成随机数
    private static  Random random = new Random();

    public SCaptcha() {
        this.createCode();
    }

    /**
     *
     * @param width
     *            图片宽
     * @param height
     *            图片高
     */
    public SCaptcha(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    /**
     *
     * @param width
     *            图片宽
     * @param height
     *            图片高
     * @param codeCount
     *            字符个数
     * @param lineCount
     *            干扰线条数
     */
    public SCaptcha(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    public void createCode() {
        int codeX = 0;
        int fontHeight = 0;
        fontHeight = height - 5;// 字体的高度
        codeX = width / (codeCount+3);// 每个字符的宽度

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体
        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(fontHeight);
        g.setFont(font);

        // 绘制干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = getRandomNumber(width);
            int ys = getRandomNumber(height);
            int xe = xs + getRandomNumber(width / 8);
            int ye = ys + getRandomNumber(height / 8);
            g.setColor(getRandomColor());
            g.drawLine(xs, ys, xe, ye);
        }

        StringBuffer randomCode = new StringBuffer();
        // 随机产生验证码字符
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random
                    .nextInt(codeSequence.length)]);
            // 设置字体颜色
            g.setColor(getRandomColor());
            // 设置字体位置
            g.drawString(strRand, (i + 1) * codeX,
                    getRandomNumber(height / 4) + 25);
            randomCode.append(strRand);
        }
        code = randomCode.toString();
    }
    /**
     * 生成几位随机数
     * @param length 几位
     * @return 随机数
     */
    public static String getRandomCode(int length)
    {
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < length; i++) {
            randomCode.append(String.valueOf(codeSequence[random
                    .nextInt(codeSequence.length)]));
        }
        return randomCode.toString();
    }

    /** 获取随机颜色 */
    private Color getRandomColor() {
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r, g, b);
    }

    /** 获取随机数 */
    public static int getRandomNumber(int number) {
        return random.nextInt(number);
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code;
    }

    /** 字体样式类 */
    class ImgFontByte {
        public Font getFont(int fontHeight) {
            try {
                Font baseFont = Font.createFont(Font.TRUETYPE_FONT,
                        new ByteArrayInputStream(hex2byte(getFontByteStr())));
                return baseFont.deriveFont(Font.PLAIN, fontHeight);
            } catch (Exception e) {
                return new Font("Arial", Font.PLAIN, fontHeight);
            }
        }

        private byte[] hex2byte(String str) {
            if (str == null)
                return null;
            str = str.trim();
            int len = str.length();
            if (len == 0 || len % 2 == 1)
                return null;

            byte[] b = new byte[len / 2];
            try {
                for (int i = 0; i < str.length(); i += 2) {
                    b[i / 2] = (byte) Integer.decode(
                            "0x" + str.substring(i, i + 2)).intValue();
                }
                return b;
            } catch (Exception e) {
                return null;
            }
        }

        // 字体文件的十六进制字符串
        private String getFontByteStr() {
               return  "0001000000100040000400c04f532f327d8175d4000087740000005650434c5461e3d9fb000087cc00000036636d61709cbc69ab00007a64000005e863767420bb32bf1600000f24000000326670676d8333c24f00000f1000000014676c7966302665d800000fc40000663c68646d7806beef530000806c0000070868656164c6469a91000088040000003668686561068002f40000883c00000024686d7478e8bd09b4000077b0000001ac6b65726efffe00650000804c0000001e6c6f6361001a319600007600000001b06d617870013e049f00008860000000206e616d65a927f7000000010c00000e04706f737469df66ea0000795c0000010670726570eacfd8a800000f580000006b0000001f017a000000000000000001de00000000000000000001001c00520000000000000002000e01de0000000000000003003201ec0000000000000004001c005200000000000000050024021e0000000000000006001a02420000000000000007005c0052000100000000000000ef025c0001000000000001000e028500010000000000020007034b0001000000000003001903520001000000000004000e028500010000000000050012036b0001000000000006000d037d0001000000000007002e0285000300010409000001de00000003000104090001001c00520003000104090002000e01de0003000104090003003201ec0003000104090004001c005200030001040900050024021e0003000104090006001a02420003000104090007005c00520003000104090008002c038a000300010409000900180aa2000300010409000a01300b16000300010409000b004c0a12000300010409000c00440c46000300010409000d076003b6000300010409000e00600a120068007400740070003a002f002f006d0065006d0062006500720073002e0061006f006c002e0063006f006d002f00760072006f006f006d0066006f006e00640065002f007400740066002f0020002d00200041006300740069006f006e0020004a00610063006b0073006f006e00200043006f007000790072006900670068007400200028004300290020003100390039003800200054006f006d0020004d00750072007000680079002000370020002d00200046007200650065002100200042007500740020006e006f007400200074006f0020006200650020007200650073006f006c006400200028006f006e00200043004400200066006f007200200069006e007300740061006e00630065002100290020002d00200049006d0069006700680074006200650054004d00400061006f006c002e0063006f006d0020002d00200033003300390020005300740069006c006c002000480069006c006c002000520064002e0020002d002000480061006d00640065006e002000430054002000300036003500310038002e00310038003300300020002d00200045006c0069006d0069007400610074006500730020004f0064006f00720073002e0020004e006f00740020004100200043006f007600650072002d0055007000210052006500670075006c0061007200460072006f0067003a002000200041006300740069006f006e0020004a00610063006b0073006f006e00200031002e003000460072006f0067003a002000200038002f00320033002f0039003800200031002e00300041006300740069006f006e004a00610063006b0073006f006e687474703a2f2f6d656d626572732e616f6c2e636f6d2f76726f6f6d666f6e64652f7474662f202d20416374696f6e204a61636b736f6e20436f7079726967687420284329203139393820546f6d204d75727068792037202d20467265652120427574206e6f7420746f206265207265736f6c6420286f6e20434420666f7220696e7374616e63652129202d20496d696768746265544d40616f6c2e636f6d202d20333339205374696c6c2048696c6c2052642e202d2048616d64656e2043542030363531382e31383330202d20456c696d697461746573204f646f72732e204e6f74204120436f7665722d557021526567756c617246726f673a2020416374696f6e204a61636b736f6e20312e3046726f673a2020382f32332f393820312e30416374696f6e4a61636b736f6e005b0044006900760069006400650020004200790020005a00650072006f005d00200046006f006e0074007300480065007200650020006900730020007400680065002000730075006d006d0061007200790020006f006600200074006800650020006c006900630065006e0073006500200066006f00720020007400680069007300200066006f006e0074002c0020007700680069006300680020006d006100790020006200650020006f00760065007200720069006400640065006e00200062007900200028006d006f007300740020006c0069006b0065006c007900200076006500720079002000730069006d0069006c0061007200290020006e006500770020006c006900630065006e0073006500730020006100740020007400680065002000550052004c002000620065006c006f0077002e000d000a000d000a004e004f0020004d004f004e004500590020006d00750073007400200065007600650072002000650078006300680061006e00670065002000680061006e0064007300200066006f00720020007400680069007300200066006f006e0074002000660069006c0065002c00200077006900740068006f007500740020004500580050004c00490043004900540020005700520049005400540045004e0020005000450052004d0049005300530049004f004e002000660072006f006d0020007400680065002000640065007300690067006e00650072002e000d000a000d000a00540068006900730020006d00650061006e007300200079006f00750020004d004100590020004e004f0054002000530045004c004c0020005400480049005300200046004f004e00540020006f006e0020006100200066006f006e0074002d0063006f006c006c0065006300740069006f006e002000430044002c0020006e006f0072002000730069006e00670075006c00610072006c00790020006e006f0072002000700061007200740020006f006600200061006e00790020006f0074006800650072002000740079007000650020007000610063006b006100670065002e000d000a000d000a0059006f00750020006d00610079002000640069007300740072006900620075007400650020007400680069007300200066006f006e0074002000660069006c006500200074006f00200061006e0079006f006e006500200079006f0075002000770061006e0074002c0020006100730020006c006f006e006700200061007300200079006f007500200064006f0020006e006f00740020006d006f006400690066007900200069007400200061006e006400200064006f0020006e006f0074002000630068006100720067006500200061006e00790020006d006f006e006500790020006f0072002000730065007200760069006300650073002e000d000a000d000a0059006f0075002000630061006e00200075007300650020007400680069007300200066006f006e007400200069006e0020006e006f006e0063006f006d006d00650072006300690061006c0020006100700070006c00690063006100740069006f006e007300200061006e006400200077006500620073006900740065007300200066007200650065006c007900200061006e006400200077006900740068006f007500740020007400680065002000640065007300690067006e00650072002700730020007000650072006d0069007300730069006f006e002e000d000a000d000a0059006f0075002000630061006e00200075007300650020007400680069007300200066006f006e007400200074006f002000630072006500610074006500200063006f006d006d00650072006300690061006c002000700072006f006400750063007400730020006f00720020007700650062002000730069007400650073002c00200062007500740020007700680065006e00200061007000700072006f00700072006900610074006500200049002700640020006c006f0076006500200066006f007200200079006f007500200074006f002000730065006e00640020006d00650020006100200063006f006d0070006c0069006d0065006e007400610072007900200063006f007000790020006f006600200074006800650020006900740065006d00200079006f0075002000750073006500200069007400200069006e002e000d000a000d000a0046006f00720020007400680065002000660075006c006c0020006c006900630065006e0073006500200061006e006400200075007000640061007400650073003a000d000a000d000a0068007400740070003a002f002f006d0065006d0062006500720073002e0061006f006c002e0063006f006d002f00760072006f006f006d0066006f006e00640065002f007400740066002f006c006500670061006c002e00680074006d006c000d000a000d000a004d00610069006c0069006e006700200061006400640072006500730073003a000d000a000d000a0054006f006d0020004d0075007200700068007900200037000d000a0033003300390020005300740069006c006c002000480069006c006c002000520064000d000a00480061006d00640065006e002000430054002000300036003500310038002e0031003800330030000d000a005500530041000d000a004300720065006100740065006400200062007900200054006f006d0020004d0075007200700068007900200037002e000d000a000d000a005400680069007300200069007300200061006e00200075006e006500760065006e00200073006f007200740020006f006600200033004400200061006300740069006f006e00200066006f006e0074002e0020004c006f006f006b002000610074002000690074002e000d000a000d000a005b0044006900760069006400650020004200790020005a00650072006f005d00200066006f006e00740073003a000d000a0068007400740070003a002f002f006d0065006d0062006500720073002e0061006f006c002e0063006f006d002f00760072006f006f006d0066006f006e00640065002f007400740066002f000d000a0068007400740070003a002f002f006d0065006d0062006500720073002e0061006f006c002e0063006f006d002f0069006d0069006700680074006200650074006d002f4001002c764520b003254523616818236860442d000b030900210018001b0023001c009b003d0063012d0169011001d800b801b200de00f901d55a725a725a725a72000400060000401f1212111110100f0f0e0e0d0d0c0c0b0b0a0a090908080707060601010000018db801ff85456844456844456844456844456844456844456844456844456844456844456844456844456844456844456844b3030246002bb3050446002bb10202456844b10404456844000002003f000001b6032000030007005640200108084009020704020100060502030205040500070605010201030000010046762f3718003f3c2f3c10fd3c10fd3c012f3cfd3c2f3cfd3c003130014968b900000008496861b0405258381137b90008ffc0385933112111253311233f0177fec7fafa0320fce03f02a300060024001000fd03060028003500480055005e006900914044016a6a406b295d5a3d3a5c56095303175f3b3a021b0029022f65032f3603034642403f3d05450304034a4902070b05495549050d68052c4f04233204612c002301011746762f3718003f3f2ffd10fd10fd2ffd3c10fd012ffd3c2f3cfd173c10fd2ffd10fd3c2ffd3c3c2ffd2e2e2e002e2e2e2e3130014968b90017006a496861b0405258381137b9006affc038591314061d01140607160706070623222726272627262726351437363534373637363736333217161716131406232226353436333217160334272627111633143d01343534263d013436031134262726270607061514131736272635160717362734232207061514163332fb0705010401050c34330d02180a0103050f0c01010d072d0e2620030412190d0102361a1e3a30222818161c020310090802062b04010103234a03258a01070a03090a0c202218131526122a02a203640289012a0a3e66060705010f15041e43958401072a2729180603030103020a0d2802fd961e2d392022301f1b021d19070e1afe000506254412291440015e0366fe5101db031d04010204041f373cfe988e15070a01320c07121a390e1119122400ffff001001aa026b03550027000ffffd024f0007000f0140024c0006002e0045021f02a7002500e500e900ef00f901020130408101fdf1f0eae9e7e6e3d3d0cbc9c3bfb8b4afacaaa89b997c6e6a5d5b57504d3a3930241d18110efffaf0eceae8e6d5cecbc6c4bdb9b1afa593898682807674717066655f322a26221b1a090807001f030514020305f502903a029059029035022d530290dc053e0b05552a2804ee553e04f6f54704d74a4404ded77a9e7e019346762f3718002f2f2fb901000004fdb801013c2f3cfd3c10fd2f3cfd2f3cfd3c10fd10fd012ffd2ffd10fd10fdb801003c10fd2f3cfd10fd2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e313001b9010400264968b900930103496861b0405258b90103004038b801031137b90103ffc038590114060736151407171514232226232206232226353437363332170716173635342…";
        }
    }
}
