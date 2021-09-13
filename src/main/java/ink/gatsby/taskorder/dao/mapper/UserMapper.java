package ink.gatsby.taskorder.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.gatsby.taskorder.pojo.po.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
