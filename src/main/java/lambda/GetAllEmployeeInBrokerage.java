package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.CustomerData;
import mapper.DBMapper.EmployeeData;
import util.*;

import java.sql.Connection;
import java.util.List;

public class GetAllEmployeeInBrokerage implements RequestHandler<GetAllEmployeeInBrokerageRequest, GetAllEmployeeInBrokerageResponse> {
    Connection con = null;
    @Override
    public GetAllEmployeeInBrokerageResponse handleRequest(GetAllEmployeeInBrokerageRequest input, Context context) {
        System.out.println("input: "+ input);
        if(input == null || !CommonUtil.isValidString(input.getBrokerageId())) {
            return new GetAllEmployeeInBrokerageResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        List<EmployeeData> employeeList = DBUtil.getAllEmployeeInBrokerage(input.getBrokerageId(),con);
        if(employeeList != null){
            return new GetAllEmployeeInBrokerageResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),
                    employeeList);
        }
        return new GetAllEmployeeInBrokerageResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
    }
}
class GetAllEmployeeInBrokerageRequest{
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
class GetAllEmployeeInBrokerageResponse{
    Response response;
    List<EmployeeData> employeeList;

    public GetAllEmployeeInBrokerageResponse(Response response) {
        this.response = response;
    }

    public GetAllEmployeeInBrokerageResponse(Response response, List<EmployeeData> employeeList) {
        this.response = response;
        this.employeeList = employeeList;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<EmployeeData> getEmployeeList() {
        return employeeList;
    }

    public void setCustomerList(List<EmployeeData> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "GetAllCustomerInBrokerageResponse{" +
                "response=" + response +
                ", employeeList=" + employeeList +
                '}';
    }
}