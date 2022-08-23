package works.springfield.framework.app.user;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private List userListV1 = new ArrayList(List.of(new User("moe"), new User("larry"), new User("curly")));

    public Supplier fetchUserCount() {
        return ()-> userListV1.size();
    }

    // constructor injector for exposing metrics at Actuator /prometheus
    public UserController(MeterRegistry registry) {
        Gauge.builder("usercontroller.usercount", fetchUserCount())
                .tag("version", "v1")
                .description("usercontroller descrip")
                .register(registry);
    }

    @Timed(value = "user.get.time", description = "time to retrieve users", percentiles = { 0.5, 0.9 })
    @Operation(summary = "get list of users")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public Iterable findAllUsers() {
        logger.debug("doing findAllUsers");
        logger.info("doing findAllUsers");
        logger.warn("doing findAllUsers");
        logger.error("doing findAllUsers");
        return userListV1;
    }

    @Operation(summary = "delete last user")
    @ApiResponse(responseCode = "200")
    @DeleteMapping
    public void deleteUser() {
        logger.debug("called deleteUser");
        if (userListV1.size() > 0) {
            userListV1.remove(userListV1.size() - 1);
        }
    }


}
