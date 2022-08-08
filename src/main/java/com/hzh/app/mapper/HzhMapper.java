package com.hzh.app.mapper;

import com.hzh.app.canal.UserTmp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HzhMapper {

    int seleect();

    UserTmp seleectUserTmp(@Param("u") UserTmp u);

    List<String> allName();

    List<UserTmp> allUserTmp();
}
