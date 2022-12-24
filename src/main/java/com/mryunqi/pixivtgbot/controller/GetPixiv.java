package com.mryunqi.pixivtgbot.controller;

import com.alibaba.fastjson2.JSONObject;
import com.mryunqi.pixivtgbot.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.mryunqi.pixivtgbot.service.Structure.painterIllustrationStructure;


/**
 * @author mryunqi
 * @date 2022/12/24
 */
@Slf4j
@RestController
@Api(value = "V1-API", tags = {"API接口"})
@RequestMapping("/api")
public class GetPixiv {
    @ApiOperation(value="获取指定画师插画列表", notes="页面（page）可为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "画师UID", dataType = "Long",dataTypeClass = Long.class, paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer",dataTypeClass = Integer.class, paramType = "query")
    })
    @GetMapping("/PainterIllustrationList")
    public ResponseResult<JSONObject> painterIllustrationList(
            @RequestParam(required = false,name = "uid") long uid,
            @RequestParam(name = "page",defaultValue = "1") int page
    ) throws Exception {
        if (page == 0){
            page =1;
        } else if (page < 0) {
            page = 1;
        }
        JSONObject result = painterIllustrationStructure(uid,page);
        return ResponseResult.ok(result);
    }
}
