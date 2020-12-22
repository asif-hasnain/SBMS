package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.Invoice;
import util.*;

import java.sql.Connection;
import java.util.List;

public class GetInvoiceDetails implements RequestHandler<GetInvoiceDetailsRequest, GetInvoiceDetailsResponse> {
    Connection con = null;
    @Override
    public GetInvoiceDetailsResponse handleRequest(GetInvoiceDetailsRequest input, Context context) {
        System.out.println("input : "+ input);
        if(input == null || !CommonUtil.isValidString(input.getBrokerageId())){
            return new GetInvoiceDetailsResponse(new Response(Constant.INVALID_INPUT,Constant.INVALID_INPUT_MSG));
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        List<Invoice> invoiceList = DBUtil.getInvoiceList(input.getBrokerageId(),con);
        if(invoiceList != null){
            if(invoiceList.size()>0){
                invoiceList.stream().filter(invoice -> CommonUtil.isValidString(invoice.getPayment_id()))
                        .forEach(invoice -> invoice.setPaid(true));
                return new GetInvoiceDetailsResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),invoiceList);
            }
        }

        return new GetInvoiceDetailsResponse(new Response(Constant.DEFAULT_ERROR,"No Invoice Found."));
    }
}
class GetInvoiceDetailsRequest{
    String brokerageId;

    public String getBrokerageId() {
        return brokerageId;
    }

    public void setBrokerageId(String brokerageId) {
        this.brokerageId = brokerageId;
    }

    @Override
    public String toString() {
        return "GetInvoiceDetailsRequest{" +
                "brokerageId='" + brokerageId + '\'' +
                '}';
    }
}
class GetInvoiceDetailsResponse{
    Response response;
    List<Invoice> invoiceList;

    public GetInvoiceDetailsResponse(Response response) {
        this.response = response;
    }

    public GetInvoiceDetailsResponse(Response response, List<Invoice> invoiceList) {
        this.response = response;
        this.invoiceList = invoiceList;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<Invoice> getInvoice() {
        return invoiceList;
    }

    public void setInvoice(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @Override
    public String toString() {
        return "GetInvoiceDetailsResponse{" +
                "response=" + response +
                ", invoiceList=" + invoiceList +
                '}';
    }
}
