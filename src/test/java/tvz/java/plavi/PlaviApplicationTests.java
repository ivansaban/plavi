package tvz.java.plavi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaviApplicationTests {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private static final String SPRING_STARTUP = "root of context hierarchy";

    @Test
	public void contextLoads() {
	}

    @Test
    public void emptyApplicationContext() {
        PlaviApplication.main(getArgs());
        assertThat(getOutput()).contains(SPRING_STARTUP);
    }

    private String[] getArgs(String... args) {
        List<String> list = new ArrayList<>(Arrays.asList(
                "--spring.main.webEnvironment=false", "--spring.main.showBanner=OFF",
                "--spring.main.registerShutdownHook=false"));
        if (args.length > 0) {
            list.add("--spring.main.sources="
                    + StringUtils.arrayToCommaDelimitedString(args));
        }
        		return list.toArray(new String[list.size()]);
    }

    private String getOutput() {
        return this.outputCapture.toString();
    }


}
