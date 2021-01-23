package com.lzj.storage.service.impl;

import com.lzj.storage.dao.StorageMapper;
import com.lzj.storage.entity.StorageEntity;
import com.lzj.storage.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzj
 * @since 2021-01-17
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, StorageEntity> implements IStorageService {

    @Override
    public void debuct(String commodityCode, int count) {
        StorageEntity storage = super.lambdaQuery()
                .eq(StorageEntity::getCommodityCode, commodityCode).last("for update").one();

        if (storage == null){
            throw new RuntimeException("商品不存在");
        }
        if (storage.getCount() < count){
            throw new RuntimeException("库存不足");
        }
        storage.setCount(storage.getCount() - count);
        super.updateById(storage);
    }
}
