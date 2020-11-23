package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.acegisecurity.util.EncryptionUtils;
import org.acegisecurity.util.EncryptionUtils.EncryptionException;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import org.bouncycastle.util.encoders.Hex;

public class CommonUtil {
	public static String hash256Calculator(String inputString) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] hash = digest.digest(
				inputString.getBytes(StandardCharsets.UTF_8));
		return new String(Hex.encode(hash));
	}
	
	public static boolean isValidString(String input){
		return (input != null && !input.equals("") && !input.equals("null") && !input.trim().isEmpty());
	}
	
	public static boolean isValidName(String name) {
		try {
			if(!isValidString(name))return false;
			Matcher matcher = Constant.VALID_NAME_REGEX.matcher(name);
			return matcher.find();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isValidEmail(String email) {
		try {
			if(!isValidString(email)) return false;
			Matcher matcher = Constant.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
			return matcher.find();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String encrypt(String value) throws EncryptionException, UnsupportedEncodingException {
		return URLEncoder.encode(EncryptionUtils.encrypt("987654321012345678909876", value), "UTF-8");
	}

	public static String decryptKey(String encryptedKey) throws EncryptionException, UnsupportedEncodingException {
		return (EncryptionUtils.decrypt("987654321012345678909876", URLDecoder.decode(encryptedKey)));
	}
	public static String getSecret(String secretName, String region) {

		AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
				.withRegion(region)
				.build();

		String secret = "", decodedBinarySecret = "";
		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
				.withSecretId(secretName);
		GetSecretValueResult getSecretValueResult = null;

		try {
			getSecretValueResult = client.getSecretValue(getSecretValueRequest);
		} catch (DecryptionFailureException e) {
			throw e;
		} catch (InternalServiceErrorException e) {
			throw e;
		} catch (InvalidParameterException e) {
			throw e;
		} catch (InvalidRequestException e) {
			throw e;
		} catch (ResourceNotFoundException e) {
			throw e;
		}

		if (getSecretValueResult.getSecretString() != null) {
			secret = getSecretValueResult.getSecretString();
			return secret;
		} else {
			decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
			return decodedBinarySecret;
		}


	}

	public static void sendEmail(String toEmailID, String emailBody, String subject) throws Exception{
			AmazonSimpleEmailService client =
					AmazonSimpleEmailServiceClientBuilder.standard()
							// Replace US_WEST_2 with the AWS Region you're using for
							// Amazon SES.
							.withRegion(Regions.US_EAST_2).build();
			SendEmailRequest request = new SendEmailRequest()
					.withDestination(
							new Destination().withToAddresses(toEmailID))
					.withMessage(new Message()
							.withBody(new Body()
									.withText(new Content()
											.withCharset("UTF-8").withData(emailBody)))
							.withSubject(new Content()
									.withCharset("UTF-8").withData(subject)))
					.withSource("mah1042@nyu.edu");
					// Comment or remove the next line if you are not using a
					// configuration set
			client.sendEmail(request);
			System.out.println("Email sent!");
		}

		public static void verifyEmailInSES(String emailId){
		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().build();
			VerifyEmailIdentityRequest request = new VerifyEmailIdentityRequest().withEmailAddress(emailId);
			VerifyEmailIdentityResult response = client.verifyEmailIdentity(request);
		}catch(Exception e){
			System.out.println(e);
		}
		}
}
