package util;

import java.sql.Connection;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.Authentication;

public class APIGatewayAuthorizer implements RequestHandler<TokenAuthorizerContext, AuthPolicy> {
	Connection con = null;

	@Override
	public AuthPolicy handleRequest(TokenAuthorizerContext input, Context context) {
		System.out.println("input : " + input);
		String token = input.getAuthorizationToken();
		String methodArn = input.getMethodArn();
		String[] arnPartials = methodArn.split(":");
		String region = arnPartials[3];
		String awsAccountId = arnPartials[4];
		String[] apiGatewayArnPartials = arnPartials[5].split("/");
		String restApiId = apiGatewayArnPartials[0];
		String stage = apiGatewayArnPartials[1];
		System.out.println("token :" + input.getAuthorizationToken());
		System.out.println("methodArn :" + methodArn);
		try {
			if (CommonUtil.isValidString(token)) {
				String[] split = token.split("#");
				String methodSplit[] = input.getMethodArn().split("/");
				String api = methodSplit[3];
				for(int i = 4;i<methodSplit.length;i++){
					api += ("/" + methodSplit[i]);
				}
				if (split != null && split.length == 2) {
					con = JDBCConnection.getJDBCCOnnection(con, 0);
					Authentication auth = DBUtil.getAuthenticationDetails(split[0], con);
					String userId = split[1];
					if (auth != null && auth.getUser_id().equalsIgnoreCase(userId)) {
						if(DBUtil.verifyAccess(userId,api,con)) {
							System.out.println("Allow");
							return new AuthPolicy("1234", AuthPolicy.PolicyDocument.getAllowAllPolicy(region,
									awsAccountId, restApiId, stage));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Deny");
//		return new AuthPolicy("1234", AuthPolicy.PolicyDocument.getAllowAllPolicy(region,
//				awsAccountId, restApiId, stage));
		return new AuthPolicy("1234",
				AuthPolicy.PolicyDocument.getDenyAllPolicy(region, awsAccountId, restApiId, stage));

	}
}