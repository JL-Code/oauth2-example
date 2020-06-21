package org.example.oauth2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.oauth2.entity.User;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/21 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface UserDao extends BaseMapper<User> {
}
