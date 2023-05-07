package exercise;

import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private Map<String, String> expectedBean = new HashMap<>();
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            expectedBean.put(beanName, level);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (expectedBean.containsKey(beanName)) {
            return  Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                            String message = "Was called method: " + method.getName() + "() with arguments: " + Arrays.toString(args);
                            if (expectedBean.get(beanName).equals("info")) {
                                LOGGER.info(message);
                            } else {
                                LOGGER.debug(message);
                            }
                        return method.invoke(bean, args);
                    }
            );
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
// END
