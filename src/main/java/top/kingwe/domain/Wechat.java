package top.kingwe.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wechat implements Serializable {
    private Integer wechatId;

    private static final long serialVersionUID = 1L;
}