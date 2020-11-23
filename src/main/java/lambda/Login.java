package lambda;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.Customer;
import mapper.DBMapper.Employee;
import mapper.LoginRequest;
import mapper.LoginResponse;
import mapper.DBMapper.User;
import util.*;


public class Login implements RequestHandler<LoginRequest, LoginResponse> {
    Connection con = null;
    @Override
    public LoginResponse handleRequest(LoginRequest input, Context context) {
        System.out.println("input: "+ input);
        if(input == null || !CommonUtil.isValidString(input.getPassword())) {
            return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
        } else if(!CommonUtil.isValidEmail(input.getEmailId())) {
            return new LoginResponse(new Response(Constant.INVALID_EMAIL_ADDRESS, Constant.INVALID_EMAIL_ADDRESS_MSG));
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        try {
            String query = "{call " + DBUtil.GET_USER_BY_EMAIL_ID + "}";
            System.out.println("Query: "+ query);
            CallableStatement statement = con.prepareCall(query);
            statement.setString(1, input.getEmailId());
            System.out.println(statement.toString());
            boolean statementResultType = statement.execute();
            System.out.println("statementResultType : "+statementResultType);
            if(statementResultType) {
                ResultSet resultSet = statement.getResultSet();
                List<User> userList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, User.class);
                statement.close();
                if(userList == null || userList.size() == 0) {
                    return new LoginResponse(new Response(Constant.INCORRECT_EMAIL_ADDRESS_OR_PASSWORD, Constant.INCORRECT_EMAIL_ADDRESS_OR_PASSWORD_MSG));
                } else if(userList.size() > 1) {
                    return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
                }else if(CommonUtil.isValidString(userList.get(0).getPassword()) && checkLoginCredentials(userList.get(0).getPassword(), input.getPassword())) {
                    String authKey = DBUtil.addAuthenticationDetails(userList.get(0).getUserId(), con);
                    System.out.println("authKey :" + authKey);
                    User user = userList.get(0);
                    if(CommonUtil.isValidString(authKey)) {
                        int id = 0;
                        if(user.getUser_type().equalsIgnoreCase("C")){
                            Customer customer = DBUtil.getCustomerByUserId(user.getUserId(),con);
                            if(customer != null) id = customer.getCustomer_id();
                        } else {
                            Employee employee = DBUtil.getEmployeeByUserId(user.getUserId(),con);
                            if(employee != null) id = employee.getEmployee_id();
                        }
                        return new LoginResponse(new Response(Constant.SUCCESS,
                                Constant.SUCCESS_MSG), user.getFirst_name()+" "+ user.getLast_name(),user.getUserId(),user.getUser_type(),authKey+"#"+user.getUserId(),user.getBrokerage_id(),id);
                    }
                }else {
                    return new LoginResponse(new Response(Constant.INCORRECT_EMAIL_ADDRESS_OR_PASSWORD, Constant.INCORRECT_EMAIL_ADDRESS_OR_PASSWORD_MSG));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
    }
    public boolean checkLoginCredentials(String dbPassword, String password) {
        String calculatedKey = CommonUtil.hash256Calculator(password);
        if(CommonUtil.isValidString(calculatedKey) && CommonUtil.isValidString(dbPassword) && calculatedKey.equals(dbPassword)) {
            return true;
        } else {
            return false;
        }

    }

}

