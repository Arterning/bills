package cn.ning.money.book.third.api;

public class OpenSessionResponse {


    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String sessionId;
        private String sessionToken;
        private int status;
        private String rtcAppId;
        private String rtcChannelId;
        private String rtcUid;
        private String rtcToken;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getSessionToken() {
            return sessionToken;
        }

        public void setSessionToken(String sessionToken) {
            this.sessionToken = sessionToken;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRtcAppId() {
            return rtcAppId;
        }

        public void setRtcAppId(String rtcAppId) {
            this.rtcAppId = rtcAppId;
        }

        public String getRtcChannelId() {
            return rtcChannelId;
        }

        public void setRtcChannelId(String rtcChannelId) {
            this.rtcChannelId = rtcChannelId;
        }

        public String getRtcUid() {
            return rtcUid;
        }

        public void setRtcUid(String rtcUid) {
            this.rtcUid = rtcUid;
        }

        public String getRtcToken() {
            return rtcToken;
        }

        public void setRtcToken(String rtcToken) {
            this.rtcToken = rtcToken;
        }
    }
}
