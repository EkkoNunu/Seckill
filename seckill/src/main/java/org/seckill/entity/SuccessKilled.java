package org.seckill.entity;

import java.util.Date;

/**
 * Created by 荔枝 on 2016-11-11.
 */
public class SuccessKilled {
    private long seckillId;

    private long userPhone;

    private Date create_time;

    private short state;

//    一个秒杀实体是对应多个秒杀成功的用户，是一个多对一，在多方把一方的Seckill写出来，这是一个复核属性
    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", create_time=" + create_time +
                ", state=" + state +
                '}';
    }
}
