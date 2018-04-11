package Medo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/**
 * Created by liudongdong on 2015/4/2.
 */
@Path("test2")
public class Test2 {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public Result test(@QueryParam("uid") String uid)
    {
        return new Result(3,uid);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result testPost(Result re)
    {
        return new Result(5,"this is a server object"+re.getName());
    }


    public static class Result
    {
        private int id;
        private String name;

        public Result()
        {}

        public Result(int id,String name)
        {
            this.id=id;
            this.name=name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
