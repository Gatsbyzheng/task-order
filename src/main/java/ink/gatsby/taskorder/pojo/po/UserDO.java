package ink.gatsby.taskorder.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userName;

    private String password;

    private Date createTime;

    private Date updateTime;
}
