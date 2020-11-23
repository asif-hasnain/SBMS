package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.Customer;
import mapper.DBMapper.CustomerData;
import mapper.LoginResponse;
import util.*;

import java.sql.Connection;
import java.util.List;


public class GetAllCustomerInBrokerage implements RequestHandler<GetAllCustomerInBrokerageRequest, GetAllCustomerInBrokerageResponse> {
    Connection con = null;
    @Override
    public GetAllCustomerInBrokerageResponse handleRequest(GetAllCustomerInBrokerageRequest input, Context context) {
        System.out.println("input: "+ input);
        if(input == null || !CommonUtil.isValidString(input.getBrokerageId())) {
            return new GetAllCustomerInBrokerageResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        List<CustomerData> customerList = DBUtil.getAllCustomerInBrokerage(input.getBrokerageId(),con);
        if(customerList != null && customerList.size()>0){
            return new GetAllCustomerInBrokerageResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),
                    customerList);
        }

        return new GetAllCustomerInBrokerageResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
    }
}
class GetAllCustomerInBrokerageRequest{
    String brokerageId;

    public String getBrokerageId() {
        return brokerageId;
    }

    public void setBrokerageId(String brokerageId) {
        this.brokerageId = brokerageId;
    }

    @Override
    public String toString() {
        return "GetAllCustomerInBrokerageRequest{" +
                "brokerageId='" + brokerageId + '\'' +
                '}';
    }
}
class GetAllCustomerInBrokerageResponse{
    Response response;
    List<CustomerData> customerList;

    public GetAllCustomerInBrokerageResponse(Response response) {
        this.response = response;
    }

    public GetAllCustomerInBrokerageResponse(Response response, List<CustomerData> customerList) {
        this.response = response;
        this.customerList = customerList;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<CustomerData> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerData> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "GetAllCustomerInBrokerageResponse{" +
                "response=" + response +
                ", customerList=" + customerList +
                '}';
    }
}

