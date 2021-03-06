package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by 荔枝 on 2016-11-11.
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */

    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     * 根据ID查询秒杀对象
     * @param seckillId
     * @return
     */

    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀对象
     * @param offset
     * @param limit
     * @return
     */

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);




}
