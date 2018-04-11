package Medo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import commonUtil.CommonVariable;
import data.common.*;
import data.request.parameters.SignInParameter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Produces(CommonVariable.JSON_WITH_UTF8)
    @Consumes(CommonVariable.JSON_WITH_UTF8)
    public ResultWrapper<String> getIt(@NotNull @Valid TestParameter pa) {
        return ResultWrapper.success(pa.getProjName());

    }

    @GET
    @Path("/t")
    @Produces(CommonVariable.JSON_WITH_UTF8)
    public TestParameter getInfo()
    {
        TestParameter tp=new TestParameter();
        tp.setProjName("wokao");
        tp.setProjID(1);
        return  tp;

    }

    @GET
    @Path("/t2")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultWrapper getWrapper()
    {
        return ResultWrapper.success("Heello");
    }

    @GET
    @Path("/t3")
    @Produces(MediaType.APPLICATION_JSON)
    public String testtt()
    {
        return CommonVariable.NOTHING_TO_RETURN;
    }



//    @POST
//    @Produces("application/json;charset=UTF-8")
//    @Consumes("application/json;charset=UTF-8")
//    public String testPost(SignInParameter pa)
//    {
//        return pa.getAccount()+pa.getPassword();
//    }

    @JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
    public static class TestParameter
    {
        @JsonProperty("ID")
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private  int projID;
        private String projName;
        private Timestamp sTime;

        public Timestamp getsTime() {
            return sTime;
        }

        public void setsTime(Timestamp sTime) {
            this.sTime = sTime;
        }

        public int getProjID() {
            return projID;
        }

        public void setProjID(int projID) {
            this.projID = projID;
        }

        public String getProjName() {
            return projName;
        }

        public void setProjName(String projName) {
            this.projName = projName;
        }
    }

}
