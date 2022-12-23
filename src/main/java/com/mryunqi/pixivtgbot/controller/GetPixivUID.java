package com.mryunqi.pixivtgbot.controller;

import com.alibaba.fastjson2.JSONObject;
import com.mryunqi.pixivtgbot.config.RestTemplateConfig;
import com.mryunqi.pixivtgbot.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author mryunqi
 * @date 2022/12/24
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class GetPixivUID {
    @GetMapping("/test")
    public ResponseResult<JSONObject> testRestTemplate() throws Exception {
        RestTemplate restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());
        // Set the cookie in the request header
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Mobile Safari/537.36");
        headers.add("Cookie", "first_visit_datetime_pc=2022-12-23+14:51:59; yuid_b=QDYnRgc; p_ab_id=5; p_ab_id_2=7; p_ab_d_id=1079426776; __utmz=235335808.1671774711.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _fbp=fb.1.1671774714153.942343342; _gid=GA1.2.1311762958.1671774724; device_token=5075801bb5b11229294d942fd2bb1db4; privacy_policy_agreement=5; c_type=21; privacy_policy_notification=0; a_type=0; b_type=1; QSI_S_ZN_5hF4My7Ad6VNNAi=v:0:0; __utmc=235335808; first_visit_datetime=2022-12-23+15:38:52; webp_available=1; login_ever=yes; __utmv=235335808.|2=login ever=yes=1^3=plan=normal=1^5=gender=male=1^6=user_id=84927832=1^9=p_ab_id=5=1^10=p_ab_id_2=7=1^11=lang=zh=1^20=webp_available=yes=1; user_language=zh; _gcl_au=1.1.1930163617.1671795240; tag_view_ranking=Lt-oEicbBr~w8ffkPoJ_S~eK9vnMvjjT~qWFESUmfEs~Xhx8A905o1~9EhdSqCy25~9hikV84Xs8~0xsDLqCEW6~Ie2c51_4Sp~u8McsBs7WV~YY_pHZNioT~PHQDP-ccQD~Txs9grkeRc~MM6RXH_rlN~NsbQEogeyL~_EOd7bsGyl~aKhT3n4RHZ~SqVgDNdq49~59dAqNEUGJ~XhOHJMaDOw~H9o4sUN8F1~Eb3vocA_K6~hVjOQfk-f5~8mx_ydyfeP~R7xe_c649k~MSGqmxsUO0~ziiAzr_h04~hW_oUTwHGx~XDEWeW9f9i~pzzjRSV6ZO~kTt0oLhqq2~mqf4KYn6Dx~_Jc3XITZqL~04VgASKa8y~iu2YDB5Fqp~I3HFXzGTQb~d8fHMcEG3R~j0lptk7ELi~M2vKPRxAge~r1vRjXa1Om~AYK00_a66q~VR_7iptpRd~-7RnTas_L3~oJAJo4VO5E~BtXd1-LPRH~hAaisbVESe~KrMg4c4zFf~OJ1L-rFMuU~t99pwr5FhR~RTJMXD26Ak~q303ip6Ui5~7WfWkHyQ76~HeoNvjfpXz~D0nMcn6oGk~qtVr8SCFs5; __utma=235335808.1449257093.1671774711.1671795201.1671799359.4; __cf_bm=F.b.7wZFnhPQaOdDPXB478yJZbdF2kJxKVMjycwUX.w-1671799414-0-AQag61Rh6Y1mzbkJw6jL8fX2R5jB651ZiTzAbxT8l5C9hSQbSXRhSGj4Hm3SoLiDgWOG2g3+ammwGHzZ/kMS+UO35GPhZZZtu4G/LGQkncIM; _ga_75BBYNYN9J=GS1.1.1671799361.4.1.1671801032.0.0.0; PHPSESSID=84927832_mWFy9eOYmzUiaI10xkkHQ69zJDdg2Ke3; _ga_MZ1NL4PHH0=GS1.1.1671799394.3.1.1671801164.0.0.0; _gat_gtag_UA_76252338_1=1; __utmt=1; __utmb=235335808.8.10.1671799359; _ga_3WKBFJLFCP=GS1.1.1671799388.3.1.1671801165.0.0.0; _ga=GA1.1.447330095.1671774713");
        HttpEntity<String> request = new HttpEntity<>(headers);
        // Make the GET request to the URL
        ResponseEntity<String> response = restTemplate.exchange("https://www.pixiv.net/touch/ajax/user/illusts?id=20778107&type=illust&lang=zh", HttpMethod.GET, request, String.class);
        // Get the JSON data from the response
        String json = response.getBody();
        JSONObject jsonObject = JSONObject.parseObject(json);
        if (jsonObject != null) {
            return ResponseResult.ok(jsonObject.getJSONObject("body"));
        }
        return ResponseResult.fail("系统异常");
    }
}
