package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    /**
     * 根据线路id查询图片信息
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);
}
