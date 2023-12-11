package com.alinesno.infra.base.im.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alinesno.infra.base.im.entity.ChannelEntity;
import com.alinesno.infra.base.im.mapper.ChannelMapper;
import com.alinesno.infra.base.im.service.IChannelService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class ChannelServiceImpl extends IBaseServiceImpl<ChannelEntity, ChannelMapper> implements IChannelService {

    @Override
    public String createChannel(ChannelEntity entity) {

        // 创建 Random 对象
        Random random = new Random();

        // 生成 1 到 60 之间的随机数（包括 1 和 60）
        int min = 1;
        int max = 60;
        int randomNumber = random.nextInt(max - min + 1) + min;

        // 打印生成的随机数
        log.debug("随机数为: " + randomNumber);

        entity.setIcon("http://data.linesno.com/icons/sepcialist/dataset_" +randomNumber+ ".png");
        entity.setChannelId(IdUtil.nanoId());

        this.save(entity) ;

        return entity.getId()+"";
    }
}
