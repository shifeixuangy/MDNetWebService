package medoresources;

import commonUtil.CommonVariable;
import data.common.ResultWrapper;
import data.common.TbCompany;
import data.proj.TbEventRecord;
import data.proj.TbGroup;
import data.request.parameters.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.CompanyService;
import services.EventRecordService;
import services.GroupService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/9.
 */
@Path("/")
public class CompanyResources {
    private static final Log logger = LogFactory.getLog(CompanyResources.class);
    private CompanyService cs = new CompanyService();

    @Path("/GetCompanyList")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<List<TbCompany>> getCompanyList(int companyID) {
        ResultWrapper<List<TbCompany>> result = null;
        try {
            result = ResultWrapper.success(cs.getCompanyList(companyID));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/AddCompany")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<Integer> addCompany(String companyName) {
        ResultWrapper<Integer> result = null;
        try {
            result = ResultWrapper.success(cs.addCompany(companyName));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/UpdateCompany")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> updateCompany(TbCompany company) {
        ResultWrapper<String> result = null;
        try {
            cs.updateCompany(company);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

    @Path("/DeleteCompany")
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> deleteCompany(int companyID) {
        ResultWrapper<String> result = null;
        try {
            cs.deleteCompany(companyID);
            result = ResultWrapper.success(CommonVariable.NOTHING_TO_RETURN);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result = ResultWrapper.serverException(ex.getMessage());
        }
        return result;
    }

}













