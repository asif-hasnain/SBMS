package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.User;
import util.*;

import java.sql.Connection;
import java.util.Random;

public class EmailOTP implements RequestHandler<EmailOTPRequest, Response> {
    Connection con = null;
    @Override
    public Response handleRequest(EmailOTPRequest input, Context context) {
        System.out.println("input: " + input);
        if (input == null || !CommonUtil.isValidEmail(input.getEmailId())) {
            return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        String numbers = "1234567890";
        Random random = new Random();
        char[] otpArr = new char[6];

        for (int i = 0; i < 6; i++) {
            otpArr[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        String otp = new String(otpArr);
        User user = DBUtil.getUserByEmailId(input.getEmailId(),con);
        if(user == null){
            return new Response(Constant.DEFAULT_ERROR, "Email Address does not exist");
        }
        boolean addToDB = DBUtil.addOTP(input.getEmailId(), otp, con);
        if (addToDB) {
            String emailBody = "Hi "+ user.getFirst_name() +",\n" +
                    "Please use this OTP to reset your password:\n" +
                    "\n " +
                    otp +"\n" +
                    "\n" +
                    "Thank you,\n" +
                    "SBM Team\n";
            String subject = "SBM Password Reset";

            try {
                CommonUtil.sendEmail(input.getEmailId(),emailBody,subject);
                DBUtil.addEmailLog(input.getEmailId(),subject,emailBody,user.getUserId(),con);
                return new Response(Constant.SUCCESS,Constant.SUCCESS_MSG);
            } catch (Exception e) {
                e.printStackTrace();
                return new Response(Constant.DEFAULT_ERROR, "Email address not verified. Verification link is sent during signup from no-reply-aws@amazon.com");
            }
        }
        return new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG);
    }

}
class EmailOTPRequest{
    String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "EmailOTPRequest{" +
                "emailId='" + emailId + '\'' +
                '}';
    }
}