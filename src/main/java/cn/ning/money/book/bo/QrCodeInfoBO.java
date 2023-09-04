package cn.ning.money.book.bo;

import lombok.Data;



@Data
public class QrCodeInfoBO {

    // 是否已扫描
    private Boolean isScanned = false;

    // 是否过期
    private Boolean isExpired = false;

    // 扫码登录时间
    private String scannedTime;

    // token字符
    private String token;
}
