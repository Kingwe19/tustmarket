package top.kingwe.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户id，数据库中的用户唯一标志（主键）
     */
    private Integer userId;

    /**
     * 用户手机号
     */
    private String usernameTel;

    /**
     * 用户邮箱
     */
    private String usernameEmail;

    /**
     * 用户昵称，非空
     */
    private String username;

    /**
     * 用户密码，非空
     */
    private String password;

    /**
     * 用户头像，默认值为存储在物理硬盘上的图片
     */
    private String profileImageUrl;

    /**
     * 用户是否为管理员（0为否，1为是，默认为0）
     */
    private Integer admin;

    /**
     * 性别（1男，2女）
     */
    private Integer sex;

    /**
     * QQ表对应的外键，保存用户qq登录的信息
     */
    private String qqId;

    /**
     * 微信表对应的外键，保存用户微信登录的信息
     */
    private String wechatId;

    /**
     * 账户停用标志（0停用，1启用，默认为1）
     */
    private Integer openClose;

    /**
     * 用户是否为vip（0否，1是）
     */
    private Integer vip;

    private static final long serialVersionUID = 1L;
}