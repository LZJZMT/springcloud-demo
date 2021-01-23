package com.lzj.storage.api;

import com.lzj.common.ApplicationNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/1/20 20:17
 */
@FeignClient(name = ApplicationNameConstant.STORAGE_APP)
public interface StorageApi {

    @PostMapping("/storage/deduct")
    void deduct(@RequestParam String commodityCode,@RequestParam int count);

}
