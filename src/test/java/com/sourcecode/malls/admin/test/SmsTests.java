package com.sourcecode.malls.admin.test;
import java.util.Map;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SmsTests {
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shenzhen", "LTAIwvOEmE5rhnxS", "XdzLN3mHH1Og83Lx7D7ArVKJshcEJw");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "18675528657");
        request.putQueryParameter("SignName", "百士兴科技有限公司");
        request.putQueryParameter("TemplateCode", "SMS_162450479");
        request.putQueryParameter("TemplateParam", "{code: 123456}");
        try {
        	ObjectMapper mapper = new ObjectMapper();
            CommonResponse response = client.getCommonResponse(request);
            Map<String, Object> result = mapper.readValue(response.getData(), Map.class);
            System.out.println(result.get("Code"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}