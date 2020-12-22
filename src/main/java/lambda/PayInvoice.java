package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.Invoice;
import util.*;

import java.sql.Connection;
import java.util.List;

public class PayInvoice implements RequestHandler<PayInvoiceRequest, Response> {
    Connection con = null;
    @Override
    public Response handleRequest(PayInvoiceRequest input, Context context) {
        System.out.println("input : " + input);
        if (input == null || !CommonUtil.isValidString(input.getInvoice_id())) {
            return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        List<Invoice> invoiceList = DBUtil.getInvoice(input.getInvoice_id(),con);
        if(invoiceList != null && invoiceList.size() == 1 && !CommonUtil.isValidString(invoiceList.get(0).getPayment_id())){
            boolean payInvoice = DBUtil.payInvoice(input.getInvoice_id(),"BANK", invoiceList.get(0).getInvoice_amount(),con);
            if(payInvoice){
                return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
            }
        }
        return new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG);
    }
}
class PayInvoiceRequest{
    String invoice_id;

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    @Override
    public String toString() {
        return "PayInvoiceRequest{" +
                "invoice_id='" + invoice_id + '\'' +
                '}';
    }
}