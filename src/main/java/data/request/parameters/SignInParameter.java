package data.request.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by liudongdong on 2015/4/23.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class SignInParameter {
    @NotEmpty
    private String account;
    @NotEmpty
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
