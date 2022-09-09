package top.wakem.abbrlink.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class LinkAbbr {
    @TableId
    private Long id;
    private String link;
    private String linkAbbr;
    private Long pageView;
    private Long isDelete;
    private Date expireTime;
    private Date createTime;
    private byte[] extra;
}
