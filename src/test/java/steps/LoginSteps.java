package steps;

import enitities.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.cucumber.java8.PendingException;

public class LoginSteps implements En {

    public LoginSteps() {
        Given("^.* is on the Login Page$", () -> {
            throw new PendingException("Implement me");
        });
        Then("^.* should see the login form$", () -> {
            throw new PendingException("Implement me");
        });
        When("^.* logs? in with .* credentials:$", (User user) -> {
            throw new PendingException("Implement me");
        });
        Then("^.* should see the correct error:$", (DataTable table) -> {
            throw new PendingException("Implement me");
        });
    }
}
