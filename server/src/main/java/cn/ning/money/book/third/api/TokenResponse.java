package cn.ning.money.book.third.api;

import java.util.List;

public class TokenResponse {

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

        private int expiresIn;
        private int refreshTokenExpiresIn;
        private String accessToken;
        private UserBean user;
        private String refreshToken;
        private List<?> permissions;
        private List<RolesBean> roles;

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public int getRefreshTokenExpiresIn() {
            return refreshTokenExpiresIn;
        }

        public void setRefreshTokenExpiresIn(int refreshTokenExpiresIn) {
            this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public List<?> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<?> permissions) {
            this.permissions = permissions;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public static class UserBean {
            /**
             * id : 194
             * userName : null
             * userPwd : null
             * email : tanzhenglong@zoomlion.com
             * cellPhone : 17388984494
             * profilePhoto : null
             * company : 中联智慧农业股份有限公司
             * companyPhone : null
             * companyContact : 谭正龙
             * companyAddress : null
             * userType : 0
             * userTag : 1
             * status : 1
             * effectiveBeginDate : 2023-08-16 00:00:00
             * effectiveEndDate : 2023-08-31 23:59:59
             * extraInfo : null
             * description :
             * appId : 6b8f02d8de614cbe8e6d16bf4e334d2b
             * appKey : null
             * licensePath : null
             * callbackUrl :
             * liveCallbackUrl :
             * conceal : 0
             * isDelete : 0
             * creator : 124
             * createTime : 2023-08-16 19:11:11
             * updater : 78
             * updateTime : 2023-08-24 18:31:33
             */

            private int id;
            private Object userName;
            private Object userPwd;
            private String email;
            private String cellPhone;
            private Object profilePhoto;
            private String company;
            private Object companyPhone;
            private String companyContact;
            private Object companyAddress;
            private int userType;
            private int userTag;
            private int status;
            private String effectiveBeginDate;
            private String effectiveEndDate;
            private Object extraInfo;
            private String description;
            private String appId;
            private Object appKey;
            private Object licensePath;
            private String callbackUrl;
            private String liveCallbackUrl;
            private int conceal;
            private int isDelete;
            private int creator;
            private String createTime;
            private int updater;
            private String updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }

            public Object getUserPwd() {
                return userPwd;
            }

            public void setUserPwd(Object userPwd) {
                this.userPwd = userPwd;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getCellPhone() {
                return cellPhone;
            }

            public void setCellPhone(String cellPhone) {
                this.cellPhone = cellPhone;
            }

            public Object getProfilePhoto() {
                return profilePhoto;
            }

            public void setProfilePhoto(Object profilePhoto) {
                this.profilePhoto = profilePhoto;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public Object getCompanyPhone() {
                return companyPhone;
            }

            public void setCompanyPhone(Object companyPhone) {
                this.companyPhone = companyPhone;
            }

            public String getCompanyContact() {
                return companyContact;
            }

            public void setCompanyContact(String companyContact) {
                this.companyContact = companyContact;
            }

            public Object getCompanyAddress() {
                return companyAddress;
            }

            public void setCompanyAddress(Object companyAddress) {
                this.companyAddress = companyAddress;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public int getUserTag() {
                return userTag;
            }

            public void setUserTag(int userTag) {
                this.userTag = userTag;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getEffectiveBeginDate() {
                return effectiveBeginDate;
            }

            public void setEffectiveBeginDate(String effectiveBeginDate) {
                this.effectiveBeginDate = effectiveBeginDate;
            }

            public String getEffectiveEndDate() {
                return effectiveEndDate;
            }

            public void setEffectiveEndDate(String effectiveEndDate) {
                this.effectiveEndDate = effectiveEndDate;
            }

            public Object getExtraInfo() {
                return extraInfo;
            }

            public void setExtraInfo(Object extraInfo) {
                this.extraInfo = extraInfo;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAppId() {
                return appId;
            }

            public void setAppId(String appId) {
                this.appId = appId;
            }

            public Object getAppKey() {
                return appKey;
            }

            public void setAppKey(Object appKey) {
                this.appKey = appKey;
            }

            public Object getLicensePath() {
                return licensePath;
            }

            public void setLicensePath(Object licensePath) {
                this.licensePath = licensePath;
            }

            public String getCallbackUrl() {
                return callbackUrl;
            }

            public void setCallbackUrl(String callbackUrl) {
                this.callbackUrl = callbackUrl;
            }

            public String getLiveCallbackUrl() {
                return liveCallbackUrl;
            }

            public void setLiveCallbackUrl(String liveCallbackUrl) {
                this.liveCallbackUrl = liveCallbackUrl;
            }

            public int getConceal() {
                return conceal;
            }

            public void setConceal(int conceal) {
                this.conceal = conceal;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public int getCreator() {
                return creator;
            }

            public void setCreator(int creator) {
                this.creator = creator;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getUpdater() {
                return updater;
            }

            public void setUpdater(int updater) {
                this.updater = updater;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }

        public static class RolesBean {
            /**
             * id : 11
             * roleName : 企业账户
             * appId : 3
             * description : null
             * isDelete : 0
             * creator : 1
             * createTime : 2023-02-21 17:37:08
             * updater : 1
             * updateTime : 2023-02-21 17:37:08
             */

            private int id;
            private String roleName;
            private int appId;
            private Object description;
            private int isDelete;
            private int creator;
            private String createTime;
            private int updater;
            private String updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public int getAppId() {
                return appId;
            }

            public void setAppId(int appId) {
                this.appId = appId;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public int getCreator() {
                return creator;
            }

            public void setCreator(int creator) {
                this.creator = creator;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getUpdater() {
                return updater;
            }

            public void setUpdater(int updater) {
                this.updater = updater;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
