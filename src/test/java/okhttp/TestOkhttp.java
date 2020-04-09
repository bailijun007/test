package okhttp;

import com.alibaba.fastjson.JSON;
import com.blj.pojo.OkResponseEntity;
import com.blj.util.HttpReqUtil;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author BaiLiJun  on 2020/4/9
 */
//@SpringBootTest
//@ActiveProfiles("local")
//@RunWith(SpringRunner.class)
public class TestOkhttp {

   public static void main(String[] args)
       {
           OkHttpClient client = new OkHttpClient();

//           String url = "http://api.zb.live/data/v1/ticker?market=btc_usdt";
           String url = "https://www.okex.com/api/spot/v3/instruments/BTC-USDT/ticker";
           Request request = new Request.Builder()
                   .get()
                   .url(url)
                   .build();
           Call call = client.newCall(request);

           //同步调用,返回Response,会抛出IO异常
           try {
               Response response = call.execute();
               final ResponseBody responseBody = response.body();
               final String string = responseBody.string();
               final OkResponseEntity entity = JSON.parseObject(string, OkResponseEntity.class);
               System.out.println("entity = " + entity.toString());
           } catch (IOException e) {
               e.printStackTrace();
           }

       }




}