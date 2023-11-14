package guru.springframework.sfgpetclinic.junitextensions;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

  private static final Logger logger = Logger.getLogger(TimingExtension.class.getName());

  private static final String START_TIME = "start time";

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    getStore(context).put(START_TIME, System.currentTimeMillis());
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    Method testMethod = context.getRequiredTestMethod();
    Long startTime = getStore(context).remove(START_TIME, long.class);
    if (startTime == null)
      startTime = 0L;
    long duration = System.currentTimeMillis() - startTime;

    logger.info(() -> String.format("Method [%s] took %s ms", testMethod.getName(), duration));
  }

  private ExtensionContext.Store getStore(ExtensionContext context) {
    return context
        .getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
  }

}
