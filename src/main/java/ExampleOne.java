import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * Created by wb-zj373670 on 2019/1/11.
 */
public class ExampleOne {

   public void test(Person person){

   }

    public static void main(String[] args) {
       try {
           Random random = new Random();
           System.out.println(random.nextLong());
//           HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//           CloseableHttpClient httpClient = httpClientBuilder.build();
//           HttpPost httpPost = new HttpPost("https://login.alibaba-inc.com/authorize/login.do");
//           httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
//           Map<String, String> treeMap = new TreeMap();
//           treeMap.put("appcode","taoadapt-o7H42kHAg773JZ4EbQBFj");
//           treeMap.put("name","wb-zj373670");
//           treeMap.put("password", org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString("Zj011559".getBytes()));
//           treeMap.put("authtype","user");
//           treeMap.put("pwdencrypt","false");
//           HttpGet httpGet = new HttpGet("https://login.alibaba-inc.com/rpc/oauth/sync.json");
//           CloseableHttpResponse httpGetResponse = httpClient.execute(httpGet);
//           String string = EntityUtils.toString(httpGetResponse.getEntity(), "UTF-8");
//           JSONObject object = JSON.parseObject(string);
//           treeMap.put("time", object.getString("content"));
//           StringBuilder sign = new StringBuilder();
//           List<NameValuePair> list = new ArrayList<>();
//           for(Map.Entry<String, String> entry : treeMap.entrySet()){
//               sign.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//               if(entry.getKey().equals("password")){
//                   list.add(new BasicNameValuePair("password","Zj011559"));
//               }else {
//                   list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//               }
//
//           }
//           sign.deleteCharAt(sign.length()-1);
//           list.add(new BasicNameValuePair("sign", DigestUtils.md5Hex("9ae0034b-e847-4c1d-b401-79cd4227ba9c"+sign.toString())));
//           httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
//           CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//           String tokenString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
//           JSONObject.parse(tokenString);
       }catch (Exception e){

       }

    }

}
