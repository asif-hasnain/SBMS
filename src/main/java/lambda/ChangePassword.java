package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import util.*;

import java.sql.Connection;

public class ChangePassword implements RequestHandler<ChangePasswordRequest, Response> {
    Connection con = null;
    @Override
    public Response handleRequest(ChangePasswordRequest input, Context context) {
        System.out.println("input: " + input);
        if (input == null || !CommonUtil.isValidEmail(input.getEmailId())
                || !CommonUtil.isValidString(input.getOtp())|| !CommonUtil.isValidString(input.getPassword())) {
            return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        String dbOTP = DBUtil.getotp(input.getEmailId(),con);
        if(CommonUtil.isValidString(dbOTP) && dbOTP.equalsIgnoreCase(input.getOtp())){
            String password = CommonUtil.hash256Calculator(input.getPassword());
            boolean updatePassword = DBUtil.updatePassword(input.getEmailId(),input.getPassword(),con);
            if(updatePassword){
                return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
            }
        } else {
            return new Response(Constant.DEFAULT_ERROR, "Invalid OTP");
        }
        return new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG);
    }
}
class ChangePasswordRequest{
    String emailId;
    String otp;
    String password;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ChangePasswordRequest{" +
                "emailId='" + emailId + '\'' +
                ", otp='" + otp + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
