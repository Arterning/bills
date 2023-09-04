package cn.ning.money.book.business;

public enum WeatherDisasterExprireEnum {
    HEAVY_RAIN_1("11B03", "1", "12"),
    HEAVY_RAIN_2("11B03", "2", "6"),
    HEAVY_RAIN_3("11B03", "3", "3"),
    HEAVY_RAIN_4("11B03", "4", "3"),
    HEAVY_FOG_1("11B17","2","12"),
    HEAVY_FOG_2("11B17","3","6"),
    HEAVY_FOG_3("11B17","4","2"),
    HEAVY_WIND("11B06", "*", "24"),
    THUNDER("11B14", "*", "6"),
    THUNDER_RAIN_WIND("11B20","*", "6"),
    SMOG("11B19", "*", "24"),
    HEAT("11B09","*","24"),
    ICY_ROAD("11B21", "*", "12"),
    HAIL("11B15", "*","6");

    private String code;
    private String level;
    private String expire;

    WeatherDisasterExprireEnum(String code, String level, String expire) {
        this.code = code;
        this.level = level;
        this.expire= expire;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public static String getExpireHour(String code, String level) {
        for (WeatherDisasterExprireEnum item : WeatherDisasterExprireEnum.values()) {
            if (item.getCode().equals(code) && item.getLevel().equals(level)) {
                return item.getExpire();
            }
        }
        return "0";
    }

}
