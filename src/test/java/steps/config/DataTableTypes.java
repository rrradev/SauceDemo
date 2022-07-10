package steps.config;

import enitities.User;
import io.cucumber.java8.LambdaGlue;

import java.util.Map;

public class DataTableTypes implements LambdaGlue {

    public DataTableTypes() {
        DataTableType("[empty]", (Map<String, String> entry) -> new User(
                entry.get("username"),
                entry.get("password")
        ));
    }
}
