package com.lzj.storage.controller;


import com.lzj.storage.api.StorageApi;
import com.lzj.storage.entity.StorageEntity;
import com.lzj.storage.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzj
 * @since 2021-01-17
 */
@RestController
@RequestMapping("/storage")
public class StorageController implements StorageApi {

    @Autowired
    IStorageService storageService;

    @PostMapping("/deduct")
    @Override
    public void deduct(String commodityCode, int count) {
        storageService.debuct(commodityCode,count);
    }
}

