package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by 荔枝 on 2016-11-11.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复秒杀
     * @param seckillId
     * @param userphone
     * @return
     */

    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userphone") long userphone);

    /**
     * 根据ID查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userphone") long userphone);
}
