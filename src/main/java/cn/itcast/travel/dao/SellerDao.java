package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {
    /**
     * 根据sid查询商家对象
     *
     */
    public Seller findById(int id);
}
