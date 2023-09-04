package cn.ning.money.book.business;
import java.util.Date;

public class CalcDateDiffTest {

    public static void main(String[] args) {
        // 创建两个示例的 Date 对象
        Date date1 = new Date(); // 第一个时间
        Date date2 = new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000); // 第一个时间后的两天

        // 获取时间戳
        long timestamp1 = date1.getTime();
        long timestamp2 = date2.getTime();

        // 计算时间差并转换为小时
        long timeDifferenceMillis = Math.abs(timestamp2 - timestamp1);
        long hoursDifference = timeDifferenceMillis / (60 * 60 * 1000);

        // 检查是否相差48小时
        if (hoursDifference >= 48) {
            System.out.println("两个时间相差 48 小时或更多。");
        } else {
            System.out.println("两个时间相差少于 48 小时。");
        }
    }

}
