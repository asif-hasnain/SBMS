package lambda;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.Customer;
import mapper.DBMapper.Employee;
import mapper.LoginResponse;

import com.amazonaws.services.lambda.runtime.Context;
import mapper.SignUpRequest;
import mapper.DBMapper.User;
import util.*;

public class SignUp implements RequestHandler<SignUpRequest, LoginResponse> {
	Connection con = null;
	@Override
	public LoginResponse handleRequest(SignUpRequest input, Context context) {
		System.out.println("input : " + input);
		if (input == null || !CommonUtil.isValidString(input.getPassword())
				|| !(CommonUtil.isValidString(input.getGender())
						&& (input.getGender().equalsIgnoreCase("F") || input.getGender().equalsIgnoreCase("M")))
				|| !CommonUtil.isValidName(input.getFirstName()) || !CommonUtil.isValidName(input.getLastName())
				|| !CommonUtil.isValidString(input.getSt_address_primary()) || !CommonUtil.isValidString(input.getCity_primary())
				|| !CommonUtil.isValidString(input.getState_primary()) || input.getState_primary().length() != 2
				|| input.getZipcode_primary()<10000
				|| !CommonUtil.isValidString(input.getSt_address_secondary()) || !CommonUtil.isValidString(input.getCity_secondary())
				|| !CommonUtil.isValidString(input.getState_secondary()) || input.getState_secondary().length() != 2
				|| input.getZipcode_secondary()<10000
				|| !CommonUtil.isValidString(input.getUser_type()) || !(input.getUser_type().equalsIgnoreCase("C")
				|| !(input.getUser_type().equalsIgnoreCase("A")|| input.getUser_type().equalsIgnoreCase("E"))
				||(input.getUser_type().equalsIgnoreCase("E") && 
						(!CommonUtil.isValidString(input.getEmp_department()) || !CommonUtil.isValidString(input.getDesignation())))
				||(input.getUser_type().equalsIgnoreCase("A") &&
				(!CommonUtil.isValidString(input.getMembership_type()))))) {
			return new LoginResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		} else if (!CommonUtil.isValidEmail(input.getEmailId())) {
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
			 ResultSet resultSet = statement.getResultSet();
			 List<User> userList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, User.class);
			 if(userList != null && userList.size() > 0) {
				 return new LoginResponse(new Response(Constant.EMAIL_ADDRESS_ALREADY_EXIST, Constant.EMAIL_ADDRESS_ALREADY_EXIST_MSG));
			 } 
			 statement.close();
			 String userId = UUID.randomUUID().toString();
			 String authKey = UUID.randomUUID().toString();
			 CallableStatement statement1 = createStatement(con,input,userId,authKey);
			 System.out.println(statement1.toString());

			 boolean statementResultType1 = statement1.execute();
			 System.out.println("statementResultType1 :" + statementResultType1 + "  "+ statement1.getUpdateCount());
			 statement1.close();
			 if(!statementResultType1) {
			 	CommonUtil.verifyEmailInSES(input.getEmailId());
			 	int id = 0;
			 	if(input.getUser_type().equalsIgnoreCase("C")){
			 		Customer customer = DBUtil.getCustomerByUserId(userId,con);
			 		if(customer != null) id = customer.getCustomer_id();
				} else {
					Employee employee = DBUtil.getEmployeeByUserId(userId,con);
					if(employee != null) id = employee.getEmployee_id();
				}
				 if(CommonUtil.isValidString(input.getBrokerage_id())) {
					 return new LoginResponse(new Response(Constant.SUCCESS,
						Constant.SUCCESS_MSG), input.getFirstName()+" "+ input.getLastName(),id);
				 } else {
					 return new LoginResponse(new Response(Constant.SUCCESS,
							 Constant.SUCCESS_MSG), input.getFirstName()+" "+ input.getLastName(),userId,input.getUser_type(),authKey+"#"+userId,input.getBrokerage_id(),id);
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
	}

	public CallableStatement createStatement(Connection con,SignUpRequest input,String userId,String authKey) throws SQLException{
		String updateQuery = "{call " + DBUtil.ADD_NEW_USER + "}";
		CallableStatement statement = con.prepareCall(updateQuery);

		statement.setString(1,userId);
		statement.setString(2,input.getUser_type().toUpperCase());
		statement.setString(3,input.getFirstName().toUpperCase());
		statement.setString(4,input.getLastName().toUpperCase());
		statement.setDate(5,new java.sql.Date(input.getDob().getTime()));
		statement.setString(6,input.getGender().toUpperCase());
		statement.setString(7,input.getEmailId().toUpperCase());
		statement.setString(8,CommonUtil.hash256Calculator(input.getPassword()));
		if(input.getUser_type().equalsIgnoreCase("A")) {
			statement.setString(9, UUID.randomUUID().toString());
			statement.setString(10, input.getBrokerage_name().toUpperCase());
			statement.setString(11, input.getMembership_type().toUpperCase());
		} else if(CommonUtil.isValidString(input.getBrokerage_id())){
			statement.setString(9, input.getBrokerage_id());
			statement.setString(10, null);
			statement.setString(11, null);
		} else {
			statement.setString(9, Constant.DEFAULT_BROKERAGE_ID);
			statement.setString(10, null);
			statement.setString(11, null);
		}
		if(CommonUtil.isValidString(input.getRelationship_manager())){
			statement.setString(12, input.getRelationship_manager());
		} else {
			statement.setString(12, null);
		}
		if(input.getUser_type().equalsIgnoreCase("E")){
			statement.setString(13, input.getDesignation().toUpperCase());
		} else statement.setString(13, null);
		statement.setDouble(14, input.getSalary());
		statement.setDouble(15, input.getCommision());
		if(CommonUtil.isValidString(input.getSuperviser_id())){
			statement.setString(16, input.getSuperviser_id());
		} else statement.setString(16, null);
		statement.setString(17,UUID.randomUUID().toString());
		statement.setString(18,input.getSt_address_primary().toUpperCase());
		statement.setString(19,input.getCity_primary().toUpperCase());
		statement.setString(20,input.getState_primary().toUpperCase());
		statement.setInt(21,input.getZipcode_primary());
		statement.setString(22,UUID.randomUUID().toString());
		statement.setString(23,input.getSt_address_secondary().toUpperCase());
		statement.setString(24,input.getCity_secondary().toUpperCase());
		statement.setString(25,input.getState_secondary().toUpperCase());
		statement.setInt(26,input.getZipcode_secondary());
		statement.setString(27,authKey);
		return statement;
	}
}
