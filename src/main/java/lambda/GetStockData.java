package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mapper.IEXData;
import mapper.GetStockDataRequest;
import mapper.GetStockDataResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import util.CommonUtil;
import util.Constant;
import util.Response;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetStockData implements RequestHandler<GetStockDataRequest, GetStockDataResponse> {
    @Override
    public GetStockDataResponse handleRequest(GetStockDataRequest input, Context context) {
        if(input == null || input.getStockList() == null || input.getStockList().size()== 0){
            System.out.println("Invalid Input");
            return new GetStockDataResponse(new Response(Constant.INVALID_INPUT,Constant.INVALID_INPUT_MSG));
        }
        System.out.println("input : " + input.toString());
        List<String> stockList = new ArrayList<>();
        stockList.addAll(input.getStockList());
        Map<String, IEXData> response = getQuote(stockList);
        if(response == null || response.size()==0){
            System.out.println("Map<String, Quote> response == null || response.size()==0");
            return new GetStockDataResponse(new Response(Constant.DEFAULT_ERROR,Constant.DEFAULT_ERROR_MSG));
        }
        System.out.println("Map<String, Quote> response : " + response);
        return new GetStockDataResponse(response,new Response(Constant.SUCCESS,Constant.SUCCESS_MSG));
    }


    public Map<String, IEXData> getQuote(List<String> stocks){
        String url = getQuoteURL(stocks);
        if(!CommonUtil.isValidString(url)){
            return null;
        }
        RequestConfig customizedRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        HttpClientBuilder customizedClientBuilder = HttpClients.custom().setDefaultRequestConfig(customizedRequestConfig);
        CloseableHttpClient httpclient = customizedClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpresponse = httpclient.execute(httpGet);
            System.out.println(httpresponse.getStatusLine());
            HttpEntity entity = httpresponse.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println("result : " + result);
                Gson gson = new Gson();
                Type iexMapType = new TypeToken<Map<String, IEXData>>() {}.getType();
                Map<String, IEXData> iexData = gson.fromJson(result, iexMapType);
                System.out.println("response : " + iexData.toString());
                return iexData;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getQuoteURL(List<String> stocks){
        String url = (Constant.isProd? Constant.IEX_BASE_URL: Constant.IEX_SANDBOX_BASE_URL) + Constant.IEX_BATCH_GET_QUOTES;
        StringBuilder stockList = new StringBuilder();
        for(String stock : stocks){
            stockList.append(stock+",");
        }
        stockList.deleteCharAt(stockList.length()-1);
        url = url.replaceFirst("#",stockList.toString());
        url = url.replaceFirst("#",Constant.isProd?Constant.IEX_SECRET_KEY:Constant.IEX_SANDBOX_SECRET_KEY);
        System.out.println("URL : " + url);
        return url;
    }
}
