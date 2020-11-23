package util;



import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestLambda implements RequestHandler<Object,Object> {
    Connection con = null;
    @Override
    public Object handleRequest(Object input, Context context) {
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        String query = "SELECT * FROM USER";
        try {
            CallableStatement statement = con.prepareCall(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            List<User> userList = ResultSetObjectMapper.mapRersultSetToObject(resultSet,
                    User.class);
            statement.close();
            if (userList != null && userList.size() > 0) {
                for (User a:userList) {
                    System.out.println(a.toString());
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson.toJson(event));
//        return null;
    }
}
