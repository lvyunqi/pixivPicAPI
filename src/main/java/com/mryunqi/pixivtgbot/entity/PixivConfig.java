package com.mryunqi.pixivtgbot.entity;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;


/**
 * @author mryunqi
 * @date 2022/12/24
 */
@Data
public class PixivConfig {
    @Value("${pixiv.cookie}")
    private String cookie;
}
