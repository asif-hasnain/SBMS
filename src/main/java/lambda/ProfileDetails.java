package lambda;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.*;
import mapper.ProfileDetailsRequest;
import mapper.ProfileDetailsResponse;
import util.*;


public class ProfileDetails implements RequestHandler<ProfileDetailsRequest, ProfileDetailsResponse>{
	Connection con = null;
	@Override
	public ProfileDetailsResponse handleRequest(ProfileDetailsRequest input, Context context) {
		System.out.println("input : " + input);
		if(input == null|| !CommonUtil.isValidString(input.getUserId())) {
			return new ProfileDetailsResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		User user = DBUtil.getUserByUserId(input.getUserId(), con);
		if(user == null) {
			return new ProfileDetailsResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		List<Address> addressList = DBUtil.getAddressByUserId(user.getUserId(), con);
		if(user.getUser_type().equalsIgnoreCase("C")){
			Customer customer = DBUtil.getCustomerByUserId(user.getUserId(),con);
			return new ProfileDetailsResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),
					user.getEmail_id(),user.getFirst_name(),user.getLast_name(), user.getGender(), user.getDob(),addressList,user.getUser_type(), customer.getRelationship_manager(),customer.getCustomer_id());
		} else if(user.getUser_type().equalsIgnoreCase("E")){
			Employee employee = DBUtil.getEmployeeByUserId(user.getUserId(),con);
			int supervisorId = 0;
			if(CommonUtil.isValidString(employee.getSuperviser())){
				Employee supervisor = DBUtil.getEmployeeByUserId(employee.getSuperviser(),con);
				if(supervisor!=null)supervisorId = supervisor.getEmployee_id();
			}
			return new ProfileDetailsResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),
					user.getEmail_id(), user.getFirst_name(), user.getLast_name(), user.getGender(), user.getDob(),addressList, user.getUser_type(), employee.getDesignation(), supervisorId,employee.getEmployee_id());
		} else if(user.getUser_type().equalsIgnoreCase("A")){
			Brokerage brokerage = DBUtil.getBrokerageData(user.getBrokerage_id(),con);
			if(brokerage == null ){
				return new ProfileDetailsResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
			}
			return new ProfileDetailsResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),
					user.getEmail_id(), user.getFirst_name(), user.getFirst_name(), user.getGender(), user.getDob(),addressList, user.getUser_type(), brokerage.getMembership_type(), brokerage.getBrokerage_name());
		}
		return new ProfileDetailsResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
	}

}
